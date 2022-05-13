package game.grounds;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.JumpAction;
import game.actions.MapAction;


public class WarpPipe extends HighGround{

    /**
     *
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
     * Constructor for high ground
     *
     */
    public WarpPipe() {
        super('C');
        this.damage = 0;
        this.chanceToJump = 1;
        this.NAME = "Warp Pipe";

    }

    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction) {
        if(location.containsAnActor()){
            return new ActionList(new MapAction(direction, location));
        }
        else{
            return new ActionList(new JumpAction(this,location,direction));
        }
    }

    @Override
    public boolean canActorEnter(Actor actor) {
        return true;
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
        return NAME ;
    }
}
