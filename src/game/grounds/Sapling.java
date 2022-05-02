package game.grounds;

import edu.monash.fit2099.engine.positions.Location;
import game.items.Coin;

import java.util.Random;

/**
 * <h1>Sapling</h1>
 * Class that represents sapling. It inherits Tree class.
 */
public class Sapling extends Tree {
    /**
     * number of turns this ground have stayed in this world
     */
    private int turns;
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
     * Chance to spawn coin on current location in %
     */
    private static final int SPAWN_COIN_CHANCE = 10;

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
        super.tick(location);

        //10% change to spawn coin (20$) on sapling
        Random r = new Random();
        int result = r.nextInt(100) + 1;   //Generate random number between 1 and 100 inclusive
        if (result <= SPAWN_COIN_CHANCE) {
            //Spawn coin
            location.addItem(new Coin(20));
        }

        //Convert to mature after 10 turns
        if(turns == 1000){
            Mature mature = new Mature();
            location.setGround(mature);
        }

        //Increment turns
        turns += 1;

    }
    /**
     * Getter for damage
     * @return integer that representing the damage caused on player
     */
    @Override
    public int damage() {
        return damage;
    }

    /**
     * Getter for chance to jump
     * @return double that representing the success rate
     */
    @Override
    public double chanceToJump() {
        return chanceToJump;
    }

    /**
     * Getter for name
     * @return String representing name of the object
     */
    public String toString() {
        return NAME;
    }

}
