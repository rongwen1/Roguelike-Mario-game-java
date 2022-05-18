package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;
import game.actors.Enemy;

public class Fire extends Item {
    private int turns;
    private static final int damage = 20;
    /***
     * Constructor.
     */
    public Fire() {
        super("Fire", 'v', false);
        turns = 1;
    }


    @Override
    public void tick(Location currentLocation) {
        // Hurt actor that stands on top of this fire (item)
        if (currentLocation.containsAnActor()){   //Check if there is actor standing on this item
            //Get the actor at current location
            Actor actor = currentLocation.getActor();
            //Hurt the actor
            actor.hurt(damage);
            System.out.println(actor + " has burned, -" + damage + " hit points");
        }

        // Remove from map after 3 turns in the world
        if (turns == 3){
            currentLocation.removeItem(this);
        }

        turns += 1;
    }
}
