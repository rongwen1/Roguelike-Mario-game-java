package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.positions.World;
import game.managers.MemoryManager;

import java.util.Map;

import static game.enums.Status.MOVE_TO_SECOND_MAP;

public class TeleportAction extends Action {

    private String direction;
    private Location location;
    private World world ;


    public TeleportAction(String direction, Location location){
        this.direction = direction;
        this.location = location;
        world = new World(new Display());
    }

    @Override
    public String execute(Actor actor, GameMap map) {

        //Map<Actor, Location> temp = MemoryManager.getInstance().getActorLocation().get(MemoryManager.getInstance().getActorLocation().size() - 1);

        // Get all the actors and their corresponding position in the current map
        MemoryManager.getInstance().appendActorLocation(MemoryManager.getInstance().actorLocationMap(map));

        // Retrieve the last map from the memory
        GameMap nextMap = MemoryManager.getInstance().getGameMapMemory().get(
                (MemoryManager.getInstance().getGameMapMemory().size() - 1));

        // Add the current map to the memory
        MemoryManager.getInstance().appendLastGameMap(map);

        world.addGameMap(nextMap);
//
//
        if (!actor.hasCapability(MOVE_TO_SECOND_MAP)) {
            actor.addCapability(MOVE_TO_SECOND_MAP);
            MemoryManager.getInstance().appendLastLocation(location);
        } else {
            actor.removeCapability(MOVE_TO_SECOND_MAP);
        }


        if (actor.hasCapability(MOVE_TO_SECOND_MAP)) {
            //map.moveActor(actor,new Location(nextMap,0,0));
           world.addPlayer(actor, nextMap.at(0, 0));
        } else {
            Location location = MemoryManager.getInstance().getLocationMemory().get(
                    (MemoryManager.getInstance().getLocationMemory().size() - 1));
            world.addPlayer(actor, nextMap.at(location.x(), location.y()));


        }

//        for (Actor actor2 : temp.keySet()) {
//            nextMap.addActor(actor2, temp.get(actor2));
//
//        }

        world.run();
        return "Map shifted";
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " shifts map";
    }
}