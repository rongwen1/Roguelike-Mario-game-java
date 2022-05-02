package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.displays.Menu;
import edu.monash.fit2099.engine.positions.GameMap;
import game.items.ResetTheGame;
import game.enums.Status;
import game.interfaces.Resettable;
import game.managers.ConsumedItemManager;
import game.managers.ResetManager;
import game.managers.WalletManager;
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
        this.addItemToInventory(new ResetTheGame("ResetTheGame", 'R', false));
    }

    /**
     * Called every turn. The player's turn.
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return The selected action
     */
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


    /**
     * Returns List of allowable action the player can make
     * @param otherActor the Actor that might be performing attack
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return
     */
    @Override //This returns allowableAction.
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {

        ActionList actions = super.allowableActions(otherActor, direction, map);

        return actions;
    }

    /**
     * Called when reset game
     */
    @Override
    public void resetInstance() {
        this.heal(9999); // TODO fix
        //this.capabilitiesList().forEach(this::removeCapability);
    }

}
