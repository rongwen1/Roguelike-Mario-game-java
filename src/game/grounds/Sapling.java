package game.grounds;

import edu.monash.fit2099.engine.positions.Location;

public class Sapling extends Tree {
    private int turns;
    private int damage;
    private double chanceToJump;
    private final String NAME;

    /**
     * Constructor.
     *
     */
    public Sapling() {
        super('t');
        turns = 1;
        this.damage = 20;
        this.chanceToJump = 0.8;
        this.NAME = "Sapling";
    }


    @Override
    public void tick(Location location) {

        /*//10% change to spawn coin (20$) on sapling
        Random r = new Random();
        int low = 1;
        int high = 10;
        int result = r.nextInt(high) + low;   //Generate random number between 1 and 10 inclusive
        if (result == 1) {
            //Spawn coin
            location.addItem(new Coin(20));
        }

        //Convert to mature after 10 turns
        if(turns == 1000){
            Mature mature = new Mature();
            location.setGround(mature);
        }*/

        //Increment turns
        turns += 1;

    }

    @Override
    public int damage() {
        return damage;
    }

    @Override
    public double chanceToJump() {
        return chanceToJump;
    }

    public String toString() {
        return NAME;
    }

}
