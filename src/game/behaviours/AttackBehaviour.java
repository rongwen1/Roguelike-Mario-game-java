package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.interfaces.Behaviour;
import game.AttackAction;

public class AttackBehaviour implements Behaviour {

    private final Action actionOnDefeat;

    public AttackBehaviour(Action actionOnDefeat) {
        this.actionOnDefeat = actionOnDefeat;
    }

    // TODO: develop and use it to attack the player automatically.
    @Override
    public Action getAction(Actor actor, GameMap map) {
        String direction = "North West";
        if (true) {
            return new AttackAction(actor, direction, actionOnDefeat);
        }
        return null;
    }

}
