package game.grounds;

import edu.monash.fit2099.engine.positions.Location;
import game.interfaces.Resettable;

import java.util.Random;

/**
 * <h1>Tree</h1>
 * Abstract class that represents tree. Any class that inherits
 * this class will have a common property of tree.
 */
public abstract class Tree extends HighGround implements Resettable {
    /**
     * if this class should reset game in boolean
     */
    private boolean resetGame;

    /**
     * Constructor for tree abstract class.
     * It calls super class to set displayChar.
     * Since this class implements resettable interface, it registers this class into ResetManager.
     */
    public Tree(char displayChar) {
        super(displayChar);
        this.registerInstance();
        resetGame = false;
    }

    /**
     * Called once per turn, so that grounds can experience the passage time.
     * @param location The location of the Ground
     */
    @Override
    public void tick(Location location) {
        if (resetGame){
            Random r = new Random();
            int low = 1;
            int high = 10;
            int result = r.nextInt(high) + low;
            //Tree will be converted to dirt at 50% chance
            if (result <= 5 ) {
                location.setGround(new Dirt());
            }
            resetGame = false;
        }


        super.tick(location);
    }

    /**
     * This method will be executed when player choose to reset action.
     */
    @Override
    public void resetInstance() {
        resetGame = true;

    }

    /**
     * It stores this resettable instance into ResetManager
     */
    @Override
    public void registerInstance() {
        Resettable.super.registerInstance();
    }
}
