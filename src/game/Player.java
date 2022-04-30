package game;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.displays.Menu;

import java.util.List;

/**
 * Class representing the Player.
 */
public class Player extends Actor  {

	private final Menu menu = new Menu();
	//private WalletManager walletManager = new WalletManager(this);

	/**
	 * Constructor.
	 *
	 * @param name        Name to call the player in the UI
	 * @param displayChar Character to represent the player in the UI
	 * @param hitPoints   Player's starting number of hitpoints
	 */
	public Player(String name, char displayChar, int hitPoints) {
		super(name, displayChar, hitPoints);
		this.addCapability(Status.HOSTILE_TO_ENEMY);
	}

	@Override
	public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
		// Handle multi-turn Actions
		if (lastAction.getNextAction() != null)
			return lastAction.getNextAction();

		// Print player's hp
		System.out.println("Player's HP: " + printHp());
		// Print wallet balance
		System.out.println("Wallet: $" + WalletManager.getInstance().getWalletBalance(this));

		////For testing. Check actor's capabilities every turn////
		System.out.println("Actor's capabilities: ");
		List<Enum<?>> status = this.capabilitiesList();
		for (Enum<?> stat: status){
			System.out.println(stat.toString());
		}

		// return/print the console menu
		return menu.showMenu(this, actions, display);
	}

	@Override
	public char getDisplayChar(){
		return this.hasCapability(Status.TALL) ? Character.toUpperCase(super.getDisplayChar()): super.getDisplayChar();
	}

	@Override //This returns allowableAction.
	public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {

		List<Item> items = this.getInventory();  //This inventory contains all items picked up
		return super.allowableActions(otherActor, direction, map);

	}




}
