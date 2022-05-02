package game.items;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.items.Item;
import game.actions.ConsumeAction;
import game.actions.ResetAction;

import java.util.ArrayList;
import java.util.List;

public class ResetTheGame extends Item {
    /***
     * Constructor.
     *  @param name the name of this Item
     * @param displayChar the character to use to represent this item if it is on the ground
     * @param portable true if and only if the Item can be picked up
     */
    public ResetTheGame(String name, char displayChar, boolean portable) {
        super(name, displayChar, portable);
    }

    @Override
    public List<Action> getAllowableActions() {
        List<Action> actionList = new ArrayList<Action>();
        actionList.add(new ResetAction(this));
        return actionList;

    }
}
