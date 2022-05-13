package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.Weapon;
import game.items.Fire;
import game.actors.Enemy;
import game.enums.Status;
import java.util.Random;

/**
 * Special Action for attacking other Actors.
 */
public class AttackAction extends Action {

    /**
     * The Actor that is to be attacked
     */
    protected Actor target;

    /**
     * The direction of incoming attack.
     */
    protected String direction;

    /**
     * Random number generator
     */
    private static final Random r = new Random(); // not const according to google style

    /**
     * The action to perform on {@code target} defeat.
     */
    private final Action actionOnTargetDefeat;

    /**
     * Constructor with three arguments.
     *
     * @param target               the actor that is to be attacked.
     * @param direction            the direction of incoming attack.
     * @param actionOnTargetDefeat the action to perform on {@code target} defeat.
     */
    public AttackAction(Actor target, String direction, Action actionOnTargetDefeat) {
        this.target = target;
        this.direction = direction;
        this.actionOnTargetDefeat = actionOnTargetDefeat;
    }

    /**
     * Perform the Action.
     *
     * @param actor the actor performing the action.
     * @param map   the map the actor is on.
     * @return a description of what happened that can be displayed to the user.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        Weapon weapon = actor.getWeapon();
        // have the attacker be followed by the enemy it attacks
        if (target instanceof Enemy) {
            ((Enemy) target).followNewActor(actor);
        }
        // check if action misses
        if (!(r.nextInt(100) <= weapon.chanceToHit())) {
            return actor + " misses " + target + ".";
        }
        // remove certain effects
        if (target.hasCapability(Status.SUPER_MUSHROOM_EFFECT_ONGOING)) {
            target.removeCapability(Status.SUPER_MUSHROOM_EFFECT_ONGOING);
        }
        // process other effects
        if (actor.hasCapability(Status.INSTANT_KILL_ENEMY)) {
            return actor + " instantly kills " + target + "."
                    + System.lineSeparator() + actionOnTargetDefeat.execute(target, map);
        }
        if (target.hasCapability(Status.IMMUNITY)) {
            return target + "is invincible and takes no damage.";
        }
        if (actor.hasCapability(Status.DROP_FIRE_WHEN_ATTACK)){ //Drops fire at target location after successful attack
            map.locationOf(target).addItem(new Fire());
        }
        // perform the attack
        int damage = weapon.damage();
        String result = actor + " " + weapon.verb() + " " + target + " for " + damage + " damage.";
        target.hurt(damage);
        // check if target was defeated
        if (!target.isConscious()) {
            result += System.lineSeparator() + actionOnTargetDefeat.execute(target, map);
        }
        return result;
    }


    /**
     * Returns a descriptive string to describe the action in the menu.
     *
     * @param actor the actor performing the action.
     * @return the text we put on the menu.
     */
    @Override
    public String menuDescription(Actor actor) {
        String output = actor + " attacks " + target + " at " + direction;
        if (actor.hasCapability(Status.DROP_FIRE_WHEN_ATTACK)){
            output += " with fire!";
        }

        return output;
    }

}
