package game;

import edu.monash.fit2099.engine.positions.Ground;
import game.grounds.HighGround;

import java.util.Random;

public abstract class Tree extends HighGround implements Resettable{

    private static final double REVERT_CHANCE = 0.5;
    private static final Random r = new Random();

    /**
     * Constructor.
     *
     */
    public Tree(char displayChar) {
        super(displayChar);
        ResetManager.getInstance().appendResetInstance(this);
    }

    @Override
    public void resetInstance() {
        if (r.nextDouble() < REVERT_CHANCE) {
//            asdf;
        }
    }

}
