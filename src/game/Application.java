package game;

import java.util.Arrays;
import java.util.List;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.FancyGroundFactory;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.World;

/**
 * The main class for the Mario World game.
 *
 */
public class Application {

	public static void main(String[] args) {

			World world = new World(new Display());

			FancyGroundFactory groundFactory = new FancyGroundFactory(new Dirt(), new Wall(), new Floor(), new Mature(), new Sapling(), new Sprout());

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
				".......................................................##.......................");
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
				".........................................#____####..............................",
				"..................................+.....#_____###...............................",
				".....................................T..#__t___###..............................",
				"......................................T..#_____###..............................",
				"..........................................t......##.............................",
				"...................................................#............................",
				"....................................................#...........................",
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
			SuperMushroom superMushroom = new SuperMushroom("Super Mushroom", '^', true);
			gameMap.at(41, 17).addItem(superMushroom);
			SuperMushroom superMushroom2= new SuperMushroom("Super Mushroom", '^', true);
			gameMap.at(41, 18).addItem(superMushroom2);
			PowerStar powerStar= new PowerStar();
			gameMap.at(41, 16).addItem(powerStar);
			//Add player
			Actor mario = new Player("Player", 'm', 100);
			world.addPlayer(mario, gameMap.at(42, 17));




			/*Actor mario = new Player("Player", 'm', 100);
			world.addPlayer(mario, gameMap.at(42, 10));
			// FIXME: the Goomba should be generated from the Tree
			gameMap.at(35, 10).addActor(new Goomba());

			gameMap.at(43, 10).addActor(new Toad());*/



			world.run();

	}

}
