package game;

import edu.monash.fit2099.engine.positions.Location;

import java.util.Random;

public class Sapling extends Tree{
    private int turns;

    /**
     * Constructor.
     *
     */
    public Sapling() {
        super('t');
        turns = 0;
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

}
