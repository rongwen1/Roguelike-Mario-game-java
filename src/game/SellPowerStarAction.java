package game;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

public class SellPowerStarAction extends Action {
    @Override
    public String execute(Actor actor, GameMap map) {
        actor.addItemToInventory(new Wrench());



        return actor + " bought a Power Star from Toad";

    }

    @Override
    public String menuDescription(Actor actor) {

        return "Player buys PowerStar ($600)";
    }
}
