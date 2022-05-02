package game.grounds;

import game.interfaces.Resettable;

import java.util.Random;

/**
 * <h1>Tree</h1>
 * Abstract class that represents tree. Any class that inherits
 * this class will have a common property of tree.
 */
public abstract class Tree extends HighGround implements Resettable {

    /**
     * Constructor for tree abstract class.
     * It calls super class to set displayChar.
     * Since this class implements resettable interface, it registers this class into ResetManager.
     */
    public Tree(char displayChar) {
        super(displayChar);
        this.registerInstance();
    }

    /**
     * This method will be executed when player choose to reset action.
     */
    @Override
    public void resetInstance() {
        Random r = new Random();
        int low = 1;
        int high = 10;
        int result = r.nextInt(high) + low;
        //Tree will be converted to dirt at 50% chance
        if (result <=5 ) {
            //////NEED TO BE CHANGED//////
            //location.setGround(new Dirt());
        }
    }

    /**
     * It stores this resettable instance into ResetManager
     */
    @Override
    public void registerInstance() {
        Resettable.super.registerInstance();
    }
}
