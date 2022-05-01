package game.grounds;

import edu.monash.fit2099.engine.positions.Location;

public class Sprout extends Tree{
    private int turns;
    private int damage;
    private double chanceToJump;
    private final String NAME;

    public Sprout() {
        super('+');
        turns = 1;
        this.damage = 10;
        this.chanceToJump = 0.9;
        this.NAME = "Sprout";
    }


    @Override
    public void tick(Location location) {

        /*//10% chance to spawn Goomba from Sprout
        Random r = new Random();
        int low = 1;
        int high = 10;
        int result = r.nextInt(high) + low;   //Generate random number between 1 and 10 inclusive
        if (result == 1 && location.getActor() == null) {
            Goomba goomba = new Goomba();
            location.addActor(goomba);
        }

        //Convert to sapling after 10 turns
        if(turns == 100){
            Sapling sapling = new Sapling();
            location.setGround(sapling);
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
