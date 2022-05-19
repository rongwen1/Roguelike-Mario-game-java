package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.displays.Menu;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.actions.ResetAction;
import game.enums.Status;
import game.interfaces.Resettable;
import game.items.Bottle;
import game.managers.ConsumedItemManager;
import game.managers.MemoryManager;
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
     * boolean, true if reset action used
     */
    private boolean hasReset;

    /**
     * punch base damage
     */
    private int baseDamage;

    /**
     * Instance of MemoryManager
     */
    private final  MemoryManager memoryManager;



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
        registerInstance();
        hasReset = false;
        //Add bottle to player
        this.addItemToInventory(Bottle.getInstance());
        //Set base damage as 5
        baseDamage = 5;
        memoryManager = MemoryManager.getInstance();
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

        // Handle reset action
        if (lastAction instanceof ResetAction){
            this.toggleHasReset();
        }
        if (!this.hasReset){
            actions.add(new ResetAction());
        }

        //If actor has certain capability
        if (this.hasCapability(Status.INCREEASE_BASE_DAMAGE_BY_15)){
            baseDamage += 15;
            this.removeCapability(Status.INCREEASE_BASE_DAMAGE_BY_15);
        }

        //Ticker for ConsumedItemManager
        consumedItemManager.consumedItemTicker();

        // Print player's hp
        System.out.println("Player's HP: " + printHp());
        // Print wallet balance
        System.out.println("Wallet: $" + WalletManager.getInstance().getWalletBalance(this));

        //For testing. Prints out player damage
        System.out.println("Base damage: " + baseDamage);
        ////For testing. Check actor's capabilities every turn////
//        System.out.println("Actor's capabilities: ");
//        List<Enum<?>> status = this.capabilitiesList();
//        for (Enum<?> stat : status) {
//            System.out.println(stat.toString());
//        }

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

    @Override
    protected IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(baseDamage, "punches");
    }

    /**
     * Called when reset game
     */
    @Override
    public void resetInstance() {
        heal(getMaxHp());
        capabilitiesList().forEach(this::removeCapability);
        this.addCapability(Status.HOSTILE_TO_ENEMY);
    }

    private void toggleHasReset() {
        this.hasReset = true;
    }

}
