package game;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.FancyGroundFactory;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.positions.World;
import game.actors.Player;
import game.actors.Toad;
import game.grounds.*;
import game.items.Coin;
import game.items.FireFlower;
import game.items.PowerStar;
import game.items.SuperMushroom;

/**
 * The main class for the Mario World game.
 *
 */
public class Application {

	public static void main(String[] args) {

			World world = new World(new Display());

			FancyGroundFactory groundFactory = new FancyGroundFactory(new Dirt(), new Wall(), new Floor(), new Mature(), new Sapling(), new Sprout()
					,new HealthFountain(), new PowerFountain());

			List<String> map = Arrays.asList(
				"..........................................##..........+.........................",
				"............+............+..................#...................................",
				"............................................#...................................",
				".............................................##......................+..........",
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
				".......................................................##.......................");

		map = Arrays.asList(
				"..........................................##....................................",
				"............................................#...................................",
				"............................................#...................................",
				".............................................##.................................",
				"...............................................#................................",
				"................................................#...............................",
				"..................................................#.............................",
				".................................................##.............................",
				"................................................##..............................",
				".........................................#____####..............................",
				"........................................#_____###...............................",
				"........................................#______###..............................",
				".........................................#_____###..............................",
				".................................................##.............................",
				"...................................................#............................",
				"....................................................#...........................",
				".....................................................#..........................",
				"......................................................#.........................",
				".................................H.A...................##.......................");
		/*map = Arrays.asList(
				"..........................................##....................................",
				"............................................#...................................",
				"............................................#...................................",
				".............................................##.................................",
				"...............................................#................................",
				"................................................#...............................",
				"..................................................#.............................",
				".................................................##.............................",
				"..................................++............##..............................",
				"........................t................#____####..............................",
				"..................................+.....#_____###...............................",
				"........................T............T..#__t___###..............................",
				"......................................T..#_____###..............................",
				"..........................................t......##.............................",
				"...................................................#............................",
				"....................................................#..................+........",
				".....................................................#..........................",
				"......................................................#.........................",
				".......................................................##.......................");*/
			GameMap gameMap = new GameMap(groundFactory, map);
			world.addGameMap(gameMap);




			////////RW_TESTING////////
			//Add ground
			Mature sapling = new Mature();
			gameMap.at(1,1).setGround(sapling);

			//Add item
			gameMap.at(41, 17).addItem(new SuperMushroom("Super Mushroom", '^', true));
			gameMap.at(41, 16).addItem(new PowerStar());
			gameMap.at(43,17).addItem(new Coin(20));
			//Add fire
			gameMap.at(42,18).addItem(new FireFlower());
			//Add player
			Actor mario = new Player("Mario", 'm', 1000);
			world.addPlayer(mario, gameMap.at(42, 17));

			gameMap.at(43, 10).addActor(new Toad());



			//Actor mario = new Player("Player", 'm', 100);
			//world.addPlayer(mario, gameMap.at(42, 10));
			// FIXME: the Goomba should be generated from the Tree
			//gameMap.at(35, 10).addActor(new Goomba());
			//gameMap.at(35, 15).addActor(new Koopa());



			//Add 5-7 sprouts randomly onto the map
			Random r = new Random();
			int sproutCount = r.nextInt(3) + 5;   //5-7 inclusive
			int xMax = gameMap.getXRange().max();
			int yMax = gameMap.getYRange().max();
			int randomX, randomY;
			for (int i = 0; i < sproutCount;){
				randomX = r.nextInt(xMax + 1);   //Generate random number between 0 and xMax inclusive
				randomY = r.nextInt(yMax + 1);   //Generate random number between 0 and yMax inclusive
				Location currentLocation = gameMap.at(randomX, randomY);
				if (currentLocation.getDisplayChar() == '.' //If it is a dirt
						&& !currentLocation.containsAnActor()){   //If actor is not in current location
					currentLocation.setGround(new Sprout());
					i += 1;
				}
			}

			world.run();

	}

}
