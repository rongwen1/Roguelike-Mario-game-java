package game.actors;

import java.util.ArrayList;
import java.util.List;

/**
 * Flying variant of koopa; traverses over walls and other forbidden grounds.
 */
public class FlyingKoopa extends Koopa {

    /**
     * Immutable list of dialog options to be returned by {@code getDialog()}, preventing object
     * creation on each call.
     * <br> For this, a new mutable array from parent class has been recreated, that allows for
     * modification ie. in the constructor.
     */
    private final List<String> opts = new ArrayList<>(DIALOG_OPTS); // not an actual constant

    /**
     * Constructor.
     */
    public FlyingKoopa() {
        super("Flying Koopa", 'F', 150);
        opts.add("Pam pam pam!");
    }

    /**
     * Provides a list of possible dialog to be chosen from by {@code processDialog()}.
     *
     * @return a list of possible dialog to choose from.
     */
    @Override
    public List<String> getDialog() {
        return List.copyOf(opts);
    }

}
