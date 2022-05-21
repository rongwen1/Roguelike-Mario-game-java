package game.items;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import game.interfaces.Drinkable;
import game.interfaces.NonMultiTurnBuffItem;
import game.actions.ConsumeAction;

import java.util.ArrayList;
import java.util.List;

public class Bottle extends Item implements NonMultiTurnBuffItem {
    private final ArrayList<Drinkable> drinks;
    private static Bottle instance;

    /***
     * Constructor.
     */
    private Bottle() {
        super("Bottle", 'B', false);
        drinks = new ArrayList<>();
    }

    /**
     * Get the singleton instance of consumed item manager
     * @return ConsumedItemManager
     */
    public static Bottle getInstance(){
        if (instance == null) {
            instance = new Bottle();
        }
        return instance;
    }

    @Override
    public List<Action> getAllowableActions() {
        List<Action> actionList = new ArrayList<Action>();
        if (drinks.size() > 0){
            actionList.add(new ConsumeAction(this));
        }

        return actionList;
    }

    public void appendDrink(Drinkable drink) {
        drinks.add(drink);
    }

    @Override
    public String toString() {
        return super.toString() + this.drinkableArrayToString();
    }

    private String drinkableArrayToString(){
        String output = "[";
        if (drinks.size() > 0){
            output += drinks.get(0).toString();
        }
        for (int i = 1; drinks.size() > i; i++){
            output += ", " + drinks.get(i).toString();
        }
        output += "]";
        return output;
    }

    @Override
    public String consume(Actor actor) {
        //take first item in arrayList
        Drinkable drink = drinks.get(0);
        //remove first item in arrayList
        drinks.remove(0);

        //drink that drinkable item
        drink.drink(actor);

        //return action to be printed
        return actor.toString() + " drinks " + drink;
    }


}
