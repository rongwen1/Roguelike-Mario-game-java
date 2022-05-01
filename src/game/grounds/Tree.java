package game.grounds;

import game.Resettable;

import java.util.Random;

public abstract class Tree extends HighGround implements Resettable {

    /**
     * Constructor.
     *
     */
    public Tree(char displayChar) {
        super(displayChar);
        this.registerInstance();
    }

    @Override
    public void resetInstance() {
        Random r = new Random();
        int low = 1;
        int high = 10;
        int result = r.nextInt(high) + low;
        if (result <=5 ) {
            //////NEED TO BE CHANGED//////
            //location.setGround(new Dirt());
        }
    }

    @Override
    public void registerInstance() {
        Resettable.super.registerInstance();
    }
}
