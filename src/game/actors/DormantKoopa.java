package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.enums.Status;
import game.actions.DestroyShellAction;
import game.items.SuperMushroom;

public class DormantKoopa extends Actor {

    public DormantKoopa() {
        super("Dormant Koopa", 'D', 9999); // arbitrary amounts of health.
        this.addItemToInventory(new SuperMushroom());
    }

    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        final ActionList actions = super.allowableActions(otherActor, direction, map);
        if (otherActor.hasCapability(Status.BREAKS_KOOPA_SHELL)) {
            actions.add(new DestroyShellAction(this));
        }
        return actions;
    }

    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        return new DoNothingAction();
    }

}
