package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.displays.Menu;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.managers.ConsumedItemManager;
import game.managers.ResetManager;
import game.Status;
import game.managers.WalletManager;
import game.actions.ResetAction;
import game.interfaces.Resettable;

import java.util.List;

/**
 * Class representing the Player.
 */
public class Player extends Actor implements Resettable {

    /**
     * Class that are capable of displaying lines of string
     */
    private final Menu menu = new Menu();
    /**
     * Instance of ConsumedItemManager.
     */
    private final ConsumedItemManager consumedItemManager;
    /**
     * If player has already performed resetAction
     */
	private boolean didReset;

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
        consumedItemManager = ConsumedItemManager.getInstance();
        ResetManager.getInstance().appendResetInstance(this);
    }

    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        // Handle multi-turn Actions
		if (lastAction.getNextAction() != null) {
			return lastAction.getNextAction();
		}

        //Ticker for ConsumedItemManager
        consumedItemManager.consumedItemTicker();
        //If player has super mushroom effect ongoing, change the display character to M
        if (this.hasCapability(Status.SUPER_MUSHROOM_EFFECT_ONGOING)) {
            this.setDisplayChar('M');
        } else if (!this.hasCapability(Status.SUPER_MUSHROOM_EFFECT_ONGOING)) {
            this.setDisplayChar('m');
        }
        // Print player's hp
        System.out.println("Player's HP: " + printHp());
        // Print wallet balance
        System.out.println("Wallet: $" + WalletManager.getInstance().getWalletBalance(this));

        for (Item item : getInventory()) {
            System.out.println(item.toString());
        }

        ////For testing. Check actor's capabilities every turn////
        System.out.println("Actor's capabilities: ");
        List<Enum<?>> status = this.capabilitiesList();
        for (Enum<?> stat : status) {
            System.out.println(stat.toString());
        }

        // return/print the console menu
        return menu.showMenu(this, actions, display);
    }

    @Override
    public char getDisplayChar() {
        return this.hasCapability(Status.TALL) ? Character.toUpperCase(super.getDisplayChar())
                : super.getDisplayChar();
    }

	public void markResetAsDone() {
		didReset = true;
	}

    @Override //This returns allowableAction.
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {

        List<Item> items = this.getInventory();  //This inventory contains all items picked up

        ActionList actions = super.allowableActions(otherActor, direction, map);
		if (!didReset) {
			actions.add(new ResetAction());
		}
        return actions;
    }

    @Override
    public void resetInstance() {
        this.heal(9999); // TODO fix
        this.capabilitiesList().forEach(this::removeCapability);
    }

}
