package game.managers;


import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.FancyGroundFactory;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.positions.World;
import game.actors.Goomba;
import game.actors.Koopa;
import game.grounds.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LocationMemoryManager {


    private ArrayList<Location> locationMemory;
    private ArrayList<GameMap> gameMapMemory;

    /**
     * An instance of WalletManager
     */
    private static LocationMemoryManager instance;

    /**
     * Constructor
     */
    private LocationMemoryManager() {
        locationMemory = new ArrayList<>();
        gameMapMemory = new ArrayList<>();

        FancyGroundFactory groundFactory = new FancyGroundFactory(new Dirt(), new Wall(), new Floor(),
                new Mature(), new Sapling(), new Sprout(), new Lava(), new WarpPipe());
        List<String> map = Arrays.asList(
                "C.....+.....L......",
                ".........L.........",
                "......+......L.....",
                "...L.....T.........",
                "...........L.......",
                "...................");
        GameMap gameMap = new GameMap(groundFactory, map);



        appendLastGameMap(gameMap);
    }

    /**
     *
     * @return an instance of WalletManager
     */
    public static LocationMemoryManager getInstance() {
        if (instance == null) {
            instance = new LocationMemoryManager();
        }
        return instance;
    }

    /**
     *
     * @param location A TradableItem item that needs to be addded into the tradebleItemList
     */
    public void appendLastLocation(Location location) {
        this.locationMemory.add(location);
    }

    public void appendLastGameMap(GameMap map) {
        this.gameMapMemory.add(map);
    }

    /**
     *
     * @return An arraylist of TradableItem
     */
    public ArrayList<Location> getLocationMemory() {
        return new ArrayList<Location>(this.locationMemory);
    }

    public ArrayList<GameMap> getGameMapMemory() {
        return gameMapMemory;
    }


}
