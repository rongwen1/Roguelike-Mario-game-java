package game.grounds;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.JumpAction;
import game.actions.TeleportAction;
import game.actors.PiranhaPlant;
import game.interfaces.Resettable;

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

    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction) {
        if (location.containsAnActor()) {
            return new ActionList(new TeleportAction(direction, location));
        } else {
            return new ActionList(new JumpAction(this, location, direction));
        }
    }

    @Override
    public int damage() {
        return damage;
    }

    @Override
    public double chanceToJump() {
        return chanceToJump;
    }

    @Override
    public String toString() {
        return NAME;
    }

    @Override
    public void tick(Location location) {
        super.tick(location);
        // relies on the fact that tick() runs only on the second global game turn, to fulfill
        // assignment requirements.
        if (!addedPlant && !location.containsAnActor()) {
            location.addActor(new PiranhaPlant());
            addedPlant = true;
        }
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
