package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.VictoryAction;
import game.enums.Status;
import game.interfaces.Talkable;
import java.util.List;

public class PrincessPeach extends Actor implements Talkable {

    private int tick = 0;

    private static final List<String> DIALOG_OPTS = List.of(
            "Dear Mario, I'll be waiting for you...",
            "Never gonna give you up!",
            "Release me, or I will kick you!");

    /**
     * Constructor.
     */
    public PrincessPeach() {
        super("Princess Peach", 'P', 9999); // arbitrary amounts of health
    }

    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        final ActionList actions = super.allowableActions(otherActor, direction, map);
        if (otherActor.hasCapability(Status.UNLOCKS_PEACH_HANDCUFFS)) {
            actions.add(new VictoryAction());
        }
        return actions;
    }

    @Override
    public List<String> getDialog() {
        return DIALOG_OPTS;
    }

    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        if (tick++ % 2 == 0) {
            processDialog(display);
        }
        return new DoNothingAction();
    }

}
