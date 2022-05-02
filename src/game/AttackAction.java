package game;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.Weapon;
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
    protected Random rand = new Random();

    private final Action actionOnTargetDefeat;

    /**
     * Constructor.
     *
     * @param target the Actor to attack
     */
    public AttackAction(Actor target, String direction, Action actionOnTargetDefeat) {
        this.target = target;
        this.direction = direction;
        this.actionOnTargetDefeat = actionOnTargetDefeat;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        Weapon weapon = actor.getWeapon();
        if (!(rand.nextInt(100) <= weapon.chanceToHit())) {
            return actor + " misses " + target + ".";
        }
        if (actor.hasCapability(Status.SUPER_MUSHROOM_EFFECT_ONGOING)) {
            actor.removeCapability(Status.SUPER_MUSHROOM_EFFECT_ONGOING);
        }
        if (actor.hasCapability(Status.INSTANT_KILL_ENEMY)) {
            return actor + " instantly kills " + target + "."
                    + System.lineSeparator() + actionOnTargetDefeat.execute(target, map);
        }
        if (target.hasCapability(Status.IMMUNITY)) {
            return target + "is invincible and takes no damage.";
        }
        int damage = weapon.damage();
        String result = actor + " " + weapon.verb() + " " + target + " for " + damage + " damage.";
        target.hurt(damage);
        if (target instanceof Enemy) {
            // have the attacker be followed by the enemy it attacks
            ((Enemy) target).followNewActor(actor);
        }
        if (!target.isConscious()) {
            result += System.lineSeparator() + actionOnTargetDefeat.execute(target, map);
        }
        return result;
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " attacks " + target + " at " + direction;
    }

}
