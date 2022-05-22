package game.grounds;

import static game.enums.Status.ON_LAVA_ZONE;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.MoveActorAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.JumpAction;
import game.actors.PiranhaPlant;
import game.actors.Player;
import game.interfaces.Resettable;
import game.managers.MemoryManager;

/**
 * <h1>WarpPipe</h1>
 * A ground that teleport player to Lava Zone.
 */
public class WarpPipe extends HighGround implements Resettable {

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
     * If a PiranhaPlant has been added to this WarpPipe before.
     */
    private boolean addedPlant = false;

    /**
     * Constructor for high ground
     */
    public WarpPipe() {
        super('C');
        this.damage = 0;
        this.chanceToJump = 1;
        this.NAME = "Warp Pipe";
        registerInstance();
    }

    /**
     * Called once per turn, so that grounds can experience the passage time.
     *
     * @param location The location of the Ground
     */
    @Override
    public void tick(Location location) {
        super.tick(location);
        // relies on the fact that tick() runs only on the second global game turn, to fulfill
        // assignment requirements.
        if (!addedPlant && !location.containsAnActor()) {
            location.addActor(new PiranhaPlant());
        }
        addedPlant = true; // unconditional - only ever tries once to add a plant
    }

    /**
     * Add the allowable action to the ground
     *
     * @param actor     the Actor acting
     * @param location  the current Location
     * @param direction the direction of the Ground from the Actor
     * @return ActionList that has all the actions can be performed on this ground
     */
    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction) {
        // if player stand on the top of warp pipe, can move to other map
        if (location.getActor() instanceof Player) {
            Location nextLocation = MemoryManager.getInstance().getLocationMemory()
                    .get(MemoryManager.getInstance().getLocationMemory().size() - 1);
            // if player is on the safe zone
            if (location.map() == MemoryManager.getInstance().getGameMapMemory().get(0)) {
                // remove ON_LAVA_ZONE if player on safe zone
                if (actor.hasCapability(ON_LAVA_ZONE)) {
                    actor.removeCapability(ON_LAVA_ZONE);
                }
                // remember the current warp pipe location
                MemoryManager.getInstance().appendNextLocation(location);
                // kill the piranha plant in the lava zone
                if (MemoryManager.getInstance().getGameMapMemory().get(1).at(0, 0)
                        .getActor() instanceof PiranhaPlant) {
                    MemoryManager.getInstance().getGameMapMemory().get(1).removeActor(
                            MemoryManager.getInstance().getGameMapMemory().get(1).at(0, 0)
                                    .getActor());
                }
            }
            // player on the lava zone
            else {
                actor.addCapability(ON_LAVA_ZONE);
                // kill the piranha plant in the safe zone while teleporting
                if (MemoryManager.getInstance().getGameMapMemory().get(0).at(nextLocation.x(), nextLocation.y())
                        .getActor() instanceof PiranhaPlant) {
                    MemoryManager.getInstance().getGameMapMemory().get(0).removeActor(
                            MemoryManager.getInstance().getGameMapMemory().get(0).at(nextLocation.x(), nextLocation.y())
                                    .getActor());
                }
            }
            // player on lava zone and wants to move to safe zone
            if (actor.hasCapability(ON_LAVA_ZONE)) {
                actor.removeCapability(ON_LAVA_ZONE);
                return (new ActionList(new MoveActorAction(
                        MemoryManager.getInstance().getGameMapMemory().get(0)
                                .at(nextLocation.x(), nextLocation.y()), "to SAFE ZONE")));
            }
            // if player on safe zone, move to lava zone
            else {
                actor.addCapability(ON_LAVA_ZONE);
                return (new ActionList(new MoveActorAction(
                        MemoryManager.getInstance().getGameMapMemory().get(1).at(0, 0),
                        "to LAVA ZONE")));
            }
        }
        // If player stand next to it, can jump
        else {
            return new ActionList(new JumpAction(this, location, direction));
        }
    }

    /**
     * Getter for damage
     *
     * @return integer that representing the damage caused on player
     */
    @Override
    public int damage() {
        return damage;
    }

    /**
     * Getter for chance to jump
     *
     * @return double that representing the success rate
     */
    @Override
    public double chanceToJump() {
        return chanceToJump;
    }

    /**
     * Getter for name
     *
     * @return String representing name of the object
     */
    @Override
    public String toString() {
        return NAME;
    }

    /**
     * Callback for when the current Resettable needs to be reset.
     */
    @Override
    public void resetInstance() {
        // Reset to false, causing tick() to run the logic again, after the warppipe ground has
        // already instantiated.
        addedPlant = false;
    }

}
