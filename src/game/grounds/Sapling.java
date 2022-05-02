package game.grounds;

import edu.monash.fit2099.engine.positions.Location;

/**
 * <h1>Sapling</h1>
 * Class that represents sapling. It inherits Tree class.
 */
public class Sapling extends Tree {
    /**
     * number of turns this ground have stayed in this world
     */
    private int turns;
    private int damage;
    private double chanceToJump;
    private final String NAME;

    /**
     * Constructor for sapling
     */
    public Sapling() {
        super('t');
        turns = 1;
        this.damage = 20;
        this.chanceToJump = 0.8;
        this.NAME = "Sapling";
    }

    /**
     * Called once per turn, so that grounds can experience the passage time.
     * @param location The location of the Ground
     */
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
