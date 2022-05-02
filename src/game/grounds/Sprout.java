package game.grounds;

import edu.monash.fit2099.engine.positions.Location;

/**
 * <h1>Sprout</h1>
 * Class that represents sprout. It inherits Tree class.
 */
public class Sprout extends Tree{
    /**
     * number of turns this ground have stayed in this world
     */
    private int turns;
    private int damage;
    private double chanceToJump;
    private final String NAME;

    /**
     * Constructor for sprout
     */
    public Sprout() {
        super('+');
        turns = 1;
        this.damage = 10;
        this.chanceToJump = 0.9;
        this.NAME = "Sprout";
    }

    /**
     * Called once per turn, so that grounds can experience the passage time.
     * @param location The location of the Ground
     */
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
    /**
     *
     * @return integer that representing the damage caused on player
     */
    @Override
    public int damage() {
        return damage;
    }

    /**
     *
     * @return double that representing the success rate
     */
    @Override
    public double chanceToJump() {
        return chanceToJump;
    }
    /**
     *
     * @return String representing name of the object
     */
    public String toString() {
        return NAME;
    }

}
