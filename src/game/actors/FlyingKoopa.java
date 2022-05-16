package game.actors;

import java.util.ArrayList;
import java.util.List;

public class FlyingKoopa extends Koopa {

    // recreate a new mutable array from parent class, static to the current subclass
    private static final List<String> opts = new ArrayList<>(DIALOG_OPTS); // not an actual constant

    public FlyingKoopa() {
        super(150);
        opts.add("Pam pam pam!");
    }

    @Override
    public List<String> getDialog() {
        return opts;
    }

}
