package game.grounds;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.MoveActorAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.JumpAction;
import game.actors.PiranhaPlant;
import game.actors.Player;
import game.managers.MemoryManager;

import static game.enums.Status.ON_LAVA_ZONE;


public class WarpPipe extends HighGround {
    /**
     * Damage actor receives when it fails to jump to this ground
     */
    private int damage;
    /**
     * Actor's chance to jump on this ground
     */
    private double chanceToJump;
    /**
     * Name of this ground
     */
    private final String NAME;

    /**
     * number of turns this ground have stayed in this world
     */
    private int turn;


    /**
     * Constructor for high ground
     */
    public WarpPipe() {
        super('C');
        this.damage = 0;
        this.chanceToJump = 1;
        this.NAME = "Warp Pipe";
        turn = 1;

    }

    /**
     * Called once per turn, so that grounds can experience the passage time.
     * @param location The location of the Ground
     */
    @Override
    public void tick(Location location) {
        turn += 1;
        if (turn == 2) {
            location.addActor(new PiranhaPlant());
        }
        super.tick(location);
    }


    /**
     * Add the allowable action to the ground
     * @param actor the Actor acting
     * @param location the current Location
     * @param direction the direction of the Ground from the Actor
     * @return ActionList that has all the actions can be performed on this ground
     */
    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction) {
        // if player stand on the top of warp pipe, can move to other map
        if (location.getActor() instanceof Player) {
            Location nextLocation = MemoryManager.getInstance().getLocationMemory().get(MemoryManager.getInstance().getLocationMemory().size() - 1);
            // if player is on the safe zone
            if (location.map() == MemoryManager.getInstance().getGameMapMemory().get(0)) {
                // remove ON_LAVA_ZONE if player on safe zone
                if (actor.hasCapability(ON_LAVA_ZONE)) {
                    actor.removeCapability(ON_LAVA_ZONE);
                }
                // remember the current warp pipe location
                MemoryManager.getInstance().appendNextLocation(location);
                // kill the piranha plant in the lava zone
                if (MemoryManager.getInstance().getGameMapMemory().get(1).at(0, 0).getActor() instanceof PiranhaPlant) {
                    MemoryManager.getInstance().getGameMapMemory().get(1).removeActor(MemoryManager.getInstance().getGameMapMemory().get(1).at(0,0).getActor());
                }
            }
            // player on the lava zone
            else {
                actor.addCapability(ON_LAVA_ZONE);
            }
            // player on lava zone and wants to move to safe zone
            if (actor.hasCapability(ON_LAVA_ZONE)) {
                actor.removeCapability(ON_LAVA_ZONE);
                return (new ActionList(new MoveActorAction(MemoryManager.getInstance().getGameMapMemory().get(0).at(nextLocation.x(), nextLocation.y()), "to SAFE ZONE")));
            }
            // if player on safe zone, move to lava zone
            else {
                actor.addCapability(ON_LAVA_ZONE);
                return (new ActionList(new MoveActorAction(MemoryManager.getInstance().getGameMapMemory().get(1).at(0,0), "to LAVA ZONE")));
            }
        }
        // If player stand next to it, can jump
        else {
            return new ActionList(new JumpAction(this, location, direction));
        }
    }

    /**
     * Getter for damage
     * @return integer that representing the damage caused on player
     */
    @Override
    public int damage() {
        return damage;
    }
    /**
     * Getter for chance to jump
     * @return double that representing the success rate
     */
    @Override
    public double chanceToJump() {
        return chanceToJump;
    }

    /**
     * Getter for name
     * @return String representing name of the object
     */
    @Override
    public String toString() {
        return NAME;
    }
}
