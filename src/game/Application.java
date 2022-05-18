package game;

import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.FancyGroundFactory;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.positions.World;
import game.actors.Bowser;
import game.actors.Goomba;
import game.actors.Koopa;
import game.actors.Player;
import game.actors.PrincessPeach;
import game.actors.Toad;
import game.grounds.Dirt;
import game.grounds.Floor;
import game.grounds.HealthFountain;
import game.grounds.Lava;
import game.grounds.Mature;
import game.grounds.PowerFountain;
import game.grounds.Sapling;
import game.grounds.Sprout;
import game.grounds.Wall;
import game.grounds.WarpPipe;
import game.items.Coin;
import game.items.HandcuffKeys;
import game.items.PowerStar;
import game.items.SuperMushroom;
import game.managers.MemoryManager;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * The main class for the Mario World game.
 */
public class Application {

    /**
     * Static variable for the entire application. We store it in an application-wide static
     * variable, because the singular {@code World} we use holds only a singular instance attribute
     * for Player, replaceable using the method {@code addPlayer()}.
     */
    private static final Player currentPlayer =
            new Player("Mario", 'm', 100); // not an immutable constant

    /**
     * Returns the current player.
     *
     * @return the current player of the game.
     */
    public static Player getCurrentPlayer() {
        return currentPlayer;
    }

    public static void main(String[] args) {

        World world = new World(new Display());
        FancyGroundFactory groundFactory = new FancyGroundFactory(new Dirt(), new Wall(),
                new Floor(), new Mature(),
                new Sapling(), new Sprout(), new Lava(), new WarpPipe(), new HealthFountain(),
                new PowerFountain());

        List<String> map = Arrays.asList(
                "..........................................##..........+.........................",
                "............+............+..................#...................................",
                "............................................#...................................",
                "............C................................##......................+..........",
                "...............................................#................................",
                "................................................#...............................",
                ".................+................................#.............................",
                ".................................................##.............................",
                "................................................##..............................",
                ".........+..............................+#____####.................+............",
                ".......................................+#_____###++.............................",
                ".......................................+#______###..............................",
                "........................................+#_____###..............................",
                "........................+........................##.............+...............",
                "...................................................#............................",
                "....................................................#...........................",
                "...................+.................................#..........................",
                "......................................................#.........................",
                "..............................H.A...........C..........##.......................");

        List<String> secondMap = Arrays.asList(
                "C.....+.....L...................",
                ".........L.............T........",
                "......+.........................",
                "................L......t........",
                ".....................+..........",
                "..L.......#..................L..",
                ".................#..............",
                "....L.......................+...",
                ".............L......_...........",
                ".......T...........t............");

        GameMap gameMap = new GameMap(groundFactory, map);
        world.addGameMap(gameMap);
        GameMap secondGameMap = new GameMap(groundFactory, secondMap);
        world.addGameMap(secondGameMap);

        MemoryManager.getInstance()
                .appendActorLocation(MemoryManager.getInstance().actorLocationMap(secondGameMap));

        MemoryManager.getInstance().appendLastGameMap(secondGameMap);

        // add actors
        gameMap.at(43, 10).addActor(new Toad());
        world.addPlayer(currentPlayer, gameMap.at(42, 17));
        //world.addPlayer(currentPlayer, secondGameMap.at(5, 5));
        secondGameMap.at(24, 8).addActor(new Bowser());
        secondGameMap.at(25, 8).addActor(new PrincessPeach());

        ////////RW_TESTING////////
        //Add ground
        Mature sapling = new Mature();
        gameMap.at(1, 1).setGround(sapling);

        //Add item
        SuperMushroom superMushroom = new SuperMushroom("Super Mushroom", '^', true);
        gameMap.at(41, 17).addItem(superMushroom);
        PowerStar powerStar = new PowerStar();
        gameMap.at(41, 16).addItem(powerStar);
        Coin coin = new Coin(20);
        gameMap.at(43, 17).addItem(coin);

        //Actor mario = new Player("Player", 'm', 100);
        //world.addPlayer(mario, gameMap.at(42, 10));
//        gameMap.at(35, 10).addActor(new Goomba());
//        gameMap.at(35, 15).addActor(new Koopa());

        //Add 5-7 sprouts and warp pipe randomly onto the map
        Random r = new Random();
        int sproutCount = r.nextInt(3) + 5;   //5-7 inclusive
        int xMax = gameMap.getXRange().max();
        int yMax = gameMap.getYRange().max();
        int randomX, randomY;
        for (int i = 0; i < sproutCount; ) {
            randomX = r.nextInt(xMax + 1);   //Generate random number between 0 and xMax inclusive
            randomY = r.nextInt(yMax + 1);   //Generate random number between 0 and yMax inclusive
            Location currentLocation = gameMap.at(randomX, randomY);
            if (currentLocation.getDisplayChar() == '.' //If it is a dirt
                    && !currentLocation.containsAnActor()) {   //If actor is not in current location
                currentLocation.setGround(new Sprout());
                currentLocation.setGround(new WarpPipe());
                i += 1;
            }
        }

        MemoryManager.getInstance().appendWorld(world);

        world.run();
    }

}
