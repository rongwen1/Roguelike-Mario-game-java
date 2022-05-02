package game.grounds;

import edu.monash.fit2099.engine.positions.Location;
import game.actors.Koopa;

import java.util.ArrayList;
import java.util.Random;

/**
 * <h1>Mature</h1>
 * Class that represents mature tree. It inherits Tree class.
 */
public class Mature extends Tree {
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
    private final double chanceToJump;
    /**
     * Name of this ground
     */
    private final String NAME;
    /**
     * Chance to spawn koopa in %
     */
    private static final int SPAWN_KOOPA_CHANCE = 15;
    /**
     * Chance to die (Become dirt) in %
     */
    private static final int CHANCE_TO_DIE = 20;

    /**
     * Constructor for mature
     */
    public Mature() {
        super('T');
        turns = 1;
        this.damage = 30;
        this.chanceToJump = 0.7;
        this.NAME = "Mature";
    }

    /**
     * Called once per turn, so that grounds can experience the passage time.
     * @param location The location of the Ground
     */
    @Override
    public void tick(Location location) {
        super.tick(location);

        int low;
        int high;
        int result;
        Random r = new Random();

        //15% chance to spawn Koopa from Sprout
        result = r.nextInt(100) + 1;   //Generate random number between 1 and 100 inclusive
        if (result <= SPAWN_KOOPA_CHANCE && location.getActor() == null) {
            location.addActor(new Koopa());
        }

        //Grow Sprout in one of the surrounding fertile squares every 5 turn.
        ArrayList<Integer> possibleX = new ArrayList<Integer>();
        ArrayList<Integer> possibleY = new ArrayList<Integer>();
        if (turns % 5 == 0){
            int xMin = location.map().getXRange().min();
            int xMax = location.map().getXRange().max();
            int yMin = location.map().getYRange().min();
            int yMax = location.map().getYRange().max();

            int currentX = location.x();
            int currentY = location.y();

            //Find all x and y for surrounding fertile squares that is dirt. Add them into arrayList
            for (int x = currentX - 1; x <= currentX + 1; x++){
                for (int y = currentY - 1; y <= currentY + 1; y++){
                    if (xMin <= x && x <= xMax && yMin <= y && y <= yMax){
                        //System.out.println("X:" + x + "Y:" + y);
                        if (location.map().at(x, y).getGround().getDisplayChar() == '.'){   //Checks if the ground display char is . (dirt)
                            possibleX.add(x);
                            possibleY.add(y);
                            //System.out.println("X:" + x + "Y:" + y);
                        }
                    }
                }
            }

            if (possibleX.size() > 0){   //If there is at least one surrounding fertile squares that is dirt
                low = 0;
                high = possibleX.size();
                result = r.nextInt(high);   //Generate random number between 0 and array size - 1

                int x = possibleX.get(result);
                int y = possibleY.get(result);
                //System.out.println("X:" + x + "Y:" + y);
                location.map().at(x, y).setGround(new Sprout());
            }

        }

        //20% chance to become dirt
        result = r.nextInt(100) + 1;   //Generate random number between 1 and 100 inclusive
        if (result <= CHANCE_TO_DIE) {
            Dirt dirt = new Dirt();
            location.setGround(dirt);
        }

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
