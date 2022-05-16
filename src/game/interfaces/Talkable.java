package game.interfaces;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import java.util.List;
import java.util.Random;

public interface Talkable {

    /**
     * Random number generator. Static final inside an interface. Public because interfaces do not
     * allow for default-private member variables (only for methods)
     */
    Random talkableRandom = new Random();

    /**
     * Implement this method to provide a list of possible dialog to be chosen from by {@code
     * processDialog()}.
     *
     * @return a list of possible dialog to choose from.
     */
    List<String> getDialog();

    /**
     * Ran inside playTurn, to be processed in every (odd) turn.
     * <br> Create an attribute of:
     * <br> <code>private int tick = 0;</code>
     * <br> and run with:
     * <br> <code>if (tick++ % 2 == 0) processDialog(display);</code>
     *
     * @param display display instance as given by playTurn.
     * @see edu.monash.fit2099.engine.actors.Actor#playTurn(ActionList, Action, GameMap, Display)
     */
    default void processDialog(Display display) {
        final List<String> opts = this.getDialog();
        final int index = talkableRandom.nextInt(opts.size());
        display.println(this + ": \"" + opts.get(index) + "\"");
    }

    /**
     * Same as its method counterpart, except without a display argument. To be used outside the
     * implementing method.
     *
     * @return the chosen string.
     * @see Talkable#processDialog(Display)
     */
    default String processDialog() {
        final List<String> opts = this.getDialog();
        final int index = talkableRandom.nextInt(opts.size());
        return opts.get(index);
    }

}
