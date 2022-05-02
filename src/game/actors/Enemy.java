package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.AttackAction;
import game.actions.DefeatAction;
import game.behaviours.AttackBehaviour;
import game.behaviours.FollowBehaviour;
import game.behaviours.WanderBehaviour;
import game.enums.Status;
import game.interfaces.Behaviour;
import game.interfaces.Resettable;
import game.managers.ResetManager;
import java.util.Map;
import java.util.TreeMap;

/**
 * A base class for shared logic of enemies in the game.
 */
public abstract class Enemy extends Actor implements Resettable {

    /**
     * An ordered dictionary of behaviors for the current Enemy, indexed and ordered by their
     * priority.
     */
    protected final Map<Integer, Behaviour> behaviours = new TreeMap<>(); // priority, behaviour

    /**
     * The action for the current Enemy to perform if said Enemy is defeated.
     */
    protected Action actionOnSelfDefeat = new DefeatAction();

    /**
     * Constructor with three arguments.
     *
     * @param name        the name of the Actor
     * @param displayChar the character that will represent the Actor in the display
     * @param hitPoints   the Actor's starting hit points
     */
    public Enemy(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);
        this.behaviours.put(10, new AttackBehaviour(new DefeatAction()));
        this.behaviours.put(30, new WanderBehaviour());
        ResetManager.getInstance().appendResetInstance(this);
    }

    /**
     * Returns a new collection of the Actions that the otherActor can do to the current Actor.
     *
     * @param otherActor the Actor that might perform an action.
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return list of actions
     * @see Status#HOSTILE_TO_ENEMY
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = super.allowableActions(otherActor, direction, map);
        // it can be attacked only by the HOSTILE opponent, and this action will not attack the HOSTILE enemy back.
        if (otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
            actions.add(new AttackAction(this, direction, actionOnSelfDefeat));
        }
        return actions;
    }

    /**
     * Swap to a new Actor for the current Enemy to follow.
     *
     * @param target the new actor to follow.
     */
    public void followNewActor(Actor target) {
        // relies on the fact that behaviors is a map - 20 being the slot for a single FollowBehavior
        behaviours.put(20, new FollowBehaviour(target));
    }

    /**
     * Figure out what to do next.
     *
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn.
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return the Action to be performed
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        for (Behaviour Behaviour : behaviours.values()) {
            Action action = Behaviour.getAction(this, map);
            if (action != null) {
                return action;
            }
        }
        return new DoNothingAction();
    }

    /**
     * Callback for when the current Resettable needs to be reset.
     */
    @Override
    public void resetInstance() {
//        new DefeatAction().execute(this);
    }

}
