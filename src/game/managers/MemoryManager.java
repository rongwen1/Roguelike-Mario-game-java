package game.managers;


import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import java.util.ArrayList;


/**
 * <h1>MemoryManager</h1>
 * Class that represents the player's memory.
 */
public class MemoryManager {

    /**
     * An arrayList contain all the warppipe's location when player stands on it
     */
    private ArrayList<Location> locationMemory;
    /**
     * An arrayList contain all the maps added into the system
     */
    private ArrayList<GameMap> gameMapMemory;


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
     * Added the current warppipe's location into the locationMemory
     * @param location A Location location that represents the last location from the current map
     */
    public void appendNextLocation(Location location) {
        this.locationMemory.add(location);
    }

    /**
     * Added new map into gameMapMemory
     * @param map A GameMap gameMap that represents the map
     */
    public void appendGameMap(GameMap map) {
        this.gameMapMemory.add(map);
    }


    /**
     * Getter for locationMemory
     * @return An arraylist of Location
     */
    public ArrayList<Location> getLocationMemory() {
        return new ArrayList<Location>(this.locationMemory);
    }

    /**
     * Getter for gameMapMemory
     * @return An arraylist of GameMap
     */
    public ArrayList<GameMap> getGameMapMemory() {
        return gameMapMemory;
    }

}
