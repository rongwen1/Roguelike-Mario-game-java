package game.interfaces;

import game.managers.ResetManager;

/**
 * <h1>Resettable</h1>
 * Any class that implements this class will have capability to perform reset
 */
public interface Resettable {

    /**
     * Allows any classes that use this interface to reset abilities, attributes, and/or items.
     * HINT: play around with capability, the actual implementation happens in the tick or playTurn
     * method.
     */
    void resetInstance();

    /**
     * a default interface method that register current instance to the Singleton manager. It allows
     * corresponding class uses to be affected by global reset USAGE: Use this method at the
     * constructor of `this` instance.
     */
    default void registerInstance() {
        ResetManager.getInstance().appendResetInstance(this);
    }

}
