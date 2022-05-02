package game;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.Weapon;
import game.behaviours.FollowBehaviour;


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

    private final Action actionOnDefeat;

    /**
     * Constructor.
     *
     * @param target the Actor to attack
     */
    public AttackAction(Actor target, String direction, Action actionOnDefeat) {
        this.target = target;
        this.direction = direction;
        this.actionOnDefeat = actionOnDefeat;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        Weapon weapon = actor.getWeapon();
        if (!(rand.nextInt(100) <= weapon.chanceToHit())) {
            return actor + " misses " + target + ".";
        }
        int damage = weapon.damage();
        String result = actor + " " + weapon.verb() + " " + target + " for " + damage + " damage.";
        target.hurt(damage);
        if (target instanceof Enemy) {
            ((Enemy) target).behaviours.put(20, new FollowBehaviour(actor));
        }
        if (!target.isConscious()) {
            result += System.lineSeparator() + actionOnDefeat.execute(target, map);
        }
        return result;
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " attacks " + target + " at " + direction;
    }

}
