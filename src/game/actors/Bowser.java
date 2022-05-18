package game.actors;

import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.enums.Status;
import game.items.HandcuffKeys;
import java.util.List;

public class Bowser extends Enemy {

    private static final List<String> DIALOG_OPTS = List.of(
            "What was that sound? Oh, just a fire.",
            "Princess Peach! You are formally invited... to the creation of my new kingdom!",
            "Never gonna let you down!",
            "Wrrrrrrrrrrrrrrrryyyyyyyyyyyyyy!!!!");

    /**
     * Constructor.
     */
    public Bowser() {
        super("Bowser", 'B', 500, false);
        addItemToInventory(new HandcuffKeys()); // item to drop
        addCapability(Status.DROP_FIRE_WHEN_ATTACK);
    }

    @Override
    public List<String> getDialog() {
        return DIALOG_OPTS;
    }

    /**
     * Creates and returns an intrinsic weapon.
     *
     * @return a freshly-instantiated IntrinsicWeapon
     */
    @Override
    protected IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(80, "punches"); // 50% hit rate
    }

    @Override
    public void resetInstance() {
        // no defeat on reset
        heal(getMaxHp());
        followNewActor(null);
        // TODO move to orig pos
    }

}
