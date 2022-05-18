package game.managers;


import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import java.util.ArrayList;

public class MemoryManager {

    private ArrayList<Location> locationMemory;
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
     * @param location A Location location that represents the last location from the current map
     */
    public void appendNextLocation(Location location) {
        this.locationMemory.add(location);
    }

    /**
     * @param map A GameMap gameMap that represents the map
     */
    public void appendGameMap(GameMap map) {
        this.gameMapMemory.add(map);
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

}
