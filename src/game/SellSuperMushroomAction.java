package game;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;


public class SellSuperMushroomAction extends Action{

    @Override
    public String execute(Actor actor, GameMap map) {
        SuperMushroom superMushroom = new SuperMushroom("Super Mushroom", '^', false);//
        if (superMushroom.getValue() <= WalletManager.getInstance().getWalletBalance(actor)) {
            WalletManager.getInstance().appendTradableItem(superMushroom);
            actor.addItemToInventory(superMushroom);
            return actor + " obtained a Super Mushroom";
        }
        else {
            return "You don't have enough coins!";
        }

    }

    @Override
    public String menuDescription(Actor actor) {
        return "Player buys Super Mushroom ($400)";
    }

}
