package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;

public class LavaAction extends Action {
    private String direction;
    private Location location;


    /**
     * Constructor
     * @param direction Direction of the jumpable ground from actor
     */
    public LavaAction(String direction, Location location){
        this.direction = direction;
        this.location = location;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        actor.hurt(15);
        map.moveActor(actor, location);
        return "15 damage caused on player";
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " move to " + direction + " Lava";
    }
}
