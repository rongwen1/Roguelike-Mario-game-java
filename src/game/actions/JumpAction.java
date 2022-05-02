package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.interfaces.Jumpable;
import game.enums.Status;

/**
 * Special Action for jumping onto high ground.
 */
public class JumpAction extends Action {

    /**
     * The jumpable ground that actor wants to jump
     */
    private Jumpable jumpable;
    /**
     * Location od the jumpable ground
     */
    private Location jumpableLocation;
    /**
     * Direction of the jumpable ground from actor
     */
    private String direction;

    /**
     * Constructor
     * @param jumpable The jumpable ground that actor wants to jump
     * @param jumpableLocation Location od the jumpable ground
     * @param direction Direction of the jumpable ground from actor
     */
    public JumpAction(Jumpable jumpable, Location jumpableLocation, String direction){
        this.jumpable = jumpable;
        this.jumpableLocation = jumpableLocation;
        this.direction = direction;
    }

    /**
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return String that representing the result.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        String result = "";
        // player can jump with 100% success rate after consuming supermushroom
        if(actor.hasCapability(Status.JUMP_FREELY)){
            map.moveActor(actor, jumpableLocation);
            result += actor + " jumped onto the " + direction + " " + jumpable +  " (" + jumpableLocation.x() +"," + jumpableLocation.y() + ")";
        }

        else {
            if (Math.random() <= jumpable.chanceToJump()) {
                map.moveActor(actor, jumpableLocation);
                result += actor + " jumped onto the " + direction + " " + jumpable + " (" + jumpableLocation.x() + "," + jumpableLocation.y() + ")";

            } else {
                int damage = jumpable.damage();
                actor.hurt(damage);
                if (!actor.isConscious()) {
                    map.removeActor(actor);
                } else {
                    result += actor + " failed to jump onto the " + direction + " " + jumpable + " with " + damage + " damage";

                }

            }
        }

        return result;

    }


    /**
     *
     * @param actor The actor performing the action.
     * @return A string that representing the action can be done by actor on the menu
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " jumps onto the " + direction + " " + jumpable + " (" + jumpableLocation.x() + "," + jumpableLocation.y() + ")";

    }

}
