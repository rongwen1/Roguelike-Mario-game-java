package game;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

public class SellWrenchAction extends Action {

    private Wrench wrench;

    public SellWrenchAction(){
        wrench = new Wrench();

    }

    @Override
    public String execute(Actor actor, GameMap map) {

        if (wrench.getValue() <= WalletManager.getInstance().getWalletBalance(actor)) {
            WalletManager.getInstance().appendTradableItem(wrench);
            actor.addItemToInventory(wrench);
            return actor + " obtained a wrench";
        } else {
            return "You don't have enough coins!";
        }
    }


    @Override
    public String menuDescription(Actor actor) {
        return "Player buys Wrench ($200)";
    }
}
