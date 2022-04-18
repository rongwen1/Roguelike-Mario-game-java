package game;

import edu.monash.fit2099.engine.positions.Location;

import java.util.Random;

public class Sprout extends Tree{
    private int turns;

    public Sprout() {
        super('+');
        turns = 0;
    }


    @Override
    public void tick(Location location) {
        super.tick(location);
        //Increment turns
        turns += 1;

        /*//10% chance to spawn Goomba from Sprout
        Random r = new Random();
        int low = 1;
        int high = 10;
        int result = r.nextInt(high-low) + low;   //Generate random number between 1 and 10 inclusive
        if (result == 1 && location.getActor() == null) {
            Goomba goomba = new Goomba();
            location.addActor(goomba);
        }


        //Convert to sapling after 10 turns
        if(turns == 100){
            Sapling sapling = new Sapling();
            location.setGround(sapling);
        }*/
    }

}
