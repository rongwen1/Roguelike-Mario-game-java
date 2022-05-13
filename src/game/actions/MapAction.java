package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.positions.World;
import game.managers.LocationMemoryManager;
import static game.enums.Status.MOVE_TO_SECOND_MAP;

public class MapAction extends Action {

    private String direction;
    private Location location;
    private World world;



    public MapAction(String direction, Location location){
        this.direction = direction;
        this.location = location;
        world = new World(new Display());
    }

    @Override
    public String execute(Actor actor, GameMap map) {

        GameMap nextMap = LocationMemoryManager.getInstance().getGameMapMemory().get(
                (LocationMemoryManager.getInstance().getGameMapMemory().size()-1));
        LocationMemoryManager.getInstance().appendLastGameMap(map);


       if(!actor.hasCapability(MOVE_TO_SECOND_MAP)) {
            actor.addCapability(MOVE_TO_SECOND_MAP);
            LocationMemoryManager.getInstance().appendLastLocation(location);
        }

        else {
           actor.removeCapability(MOVE_TO_SECOND_MAP);
       }

        world.addGameMap(nextMap);
        if(actor.hasCapability(MOVE_TO_SECOND_MAP)){
            world.addPlayer(actor,nextMap.at(0,0));
        }
        else{
            Location location = LocationMemoryManager.getInstance().getLocationMemory().get(
                    (LocationMemoryManager.getInstance().getLocationMemory().size()-1));
            world.addPlayer(actor,nextMap.at(location.x(),location.y()));
        }

        world.run();
        return "Map shifted";

    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " shifts map";
    }
}