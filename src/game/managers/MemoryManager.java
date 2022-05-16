package game.managers;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.ActorLocationsIterator;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.positions.World;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MemoryManager {

    private ArrayList<Location> locationMemory;
    private ArrayList<GameMap> gameMapMemory;
    private ArrayList<Map<Actor, Location>> actorLocation;
    private ArrayList<World> world;


    /**
     * An instance of MemoryManager
     */
    private static MemoryManager instance;

    /**
     * Constructor
     */
    private MemoryManager() {
        locationMemory = new ArrayList<>();
        gameMapMemory = new ArrayList<>();
        actorLocation = new ArrayList<>();
        world = new ArrayList<>();
    }

    /**
     * @return an instance of MemoryManager
     */
    public static MemoryManager getInstance() {
        if (instance == null) {
            instance = new MemoryManager();
        }
        return instance;
    }

    /**
     * @param location A Location location that represents the last location from the current map
     */
    public void appendLastLocation(Location location) {
        this.locationMemory.add(location);
    }

    /**
     * @param map A GameMap gameMap that represents the last map
     */
    public void appendLastGameMap(GameMap map) {
        this.gameMapMemory.add(map);
    }



    public void appendActorLocation(Map<Actor, Location> actorLocationMap) {
        this.actorLocation.add(actorLocationMap);
    }

    public void appendWorld(World world){
        this.world.add(world);
    }

    /**
     * @return An arraylist of Location
     */
    public ArrayList<Location> getLocationMemory() {
        return new ArrayList<Location>(this.locationMemory);
    }

    /**
     * @return An arraylist of GameMap
     */
    public ArrayList<GameMap> getGameMapMemory() {
        return gameMapMemory;
    }



    public ArrayList<Map<Actor, Location>> getActorLocation() {
        return actorLocation;
    }

    public Map<Actor, Location> actorLocationMap(GameMap map) {
        Map<Actor, Location> actorToLocation = new HashMap<>();
        int width = map.getXRange().max();
        int height = map.getYRange().max();
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (map.isAnActorAt(new Location(map, i, j))) {
                    actorToLocation.put(map.getActorAt(new Location(map, i, j)), new Location(map, i, j));
                }
            }
        }
        return actorToLocation;
    }

    public ArrayList<World> getWorld() {
        return world;
    }
}
