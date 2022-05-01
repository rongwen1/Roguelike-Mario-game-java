package game.grounds;

import edu.monash.fit2099.engine.positions.Location;

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
    private int damage;
    private double chanceToJump;
    private final String NAME;

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
        int low;
        int high;
        int result;
        Random r = new Random();

        /*//15% chance to spawn Koopa from Sprout
        low = 1;
        high = 100;
        result = r.nextInt(high) + low;   //Generate random number between 1 and 100 inclusive
        if (result <= 15 && location.getActor() == null) {
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
        low = 1;
        high = 5;
        result = r.nextInt(high) + low;   //Generate random number between 1 and 5 inclusive
        if (result == 1) {
            Dirt dirt = new Dirt();
            location.setGround(dirt);
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
