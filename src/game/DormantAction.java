package game;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;

public class DormantAction extends Action {

    @Override
    public String execute(Actor actor, GameMap map) {
        Location loc = map.locationOf(actor);
        map.removeActor(actor);
        loc.addActor(new DormantKoopa());
        return "Koopa has gone into hiding!";
    }

    @Override
    public String menuDescription(Actor actor) {
        return "";
    }

}
