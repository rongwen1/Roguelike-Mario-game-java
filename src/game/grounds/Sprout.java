package game.grounds;

import edu.monash.fit2099.engine.positions.Location;
import game.items.FireFlower;
import game.actors.Goomba;

import java.util.Random;

/**
 * <h1>Sprout</h1>
 * Class that represents sprout. It inherits Tree class.
 */
public class Sprout extends Tree{
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
     * Chance to spawn goomba in %
     */
    private static final int SPAWN_GOOMBA_CHANCE = 10;

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
        super.tick(location);

        //10% chance to spawn Goomba from Sprout
        Random r = new Random();
        int result = r.nextInt(100) + 1;   //Generate random number between 1 and 100 inclusive
        if (result <= SPAWN_GOOMBA_CHANCE && location.getActor() == null) {
            Goomba goomba = new Goomba();
            location.addActor(goomba);
        }

        //After 10 turns
        if(turns == 10){
            //Spawn fire flower at 50% chance
            result = r.nextInt(2) ;   //Generate random number of 0 or 1
            if (result == 1){
                location.addItem(new FireFlower());
            }
            //Convert to sprout
            Sapling sapling = new Sapling();
            location.setGround(sapling);
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
