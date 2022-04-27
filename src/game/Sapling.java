package game;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;

import java.util.Random;

public class Sapling extends Tree implements Jumpable{
    private int turns;
    private int damage;
    private double chanceToJump;

    /**
     * Constructor.
     *
     */
    public Sapling() {
        super('t');
        turns = 0;
        this.damage = 20;
        this.chanceToJump = 0.8;
    }


    @Override
    public void tick(Location location) {
        super.tick(location);
        //Increment turns
        turns += 1;

        /*//10% change to spawn coin (20$) on sapling
        Random r = new Random();
        int low = 1;
        int high = 10;
        int result = r.nextInt(high-low) + low;   //Generate random number between 1 and 10 inclusive
        if (result == 1) {
            //Code to spawn coin
        }

        //Convert to mature after 10 turns
        if(turns == 100){
            Mature mature = new Mature();
            location.setGround(mature);
        }*/

    }

    @Override
    public boolean canActorEnter(Actor actor) {
        return false;
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
    public ActionList allowableActions(Actor actor, Location location, String direction){
        return new ActionList(new JumpAction(this, location, direction));
    }

    public String toString() {
        return "Sapling";
    }

}
