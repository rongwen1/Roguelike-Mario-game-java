package game;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actions.MoveActorAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;

import java.util.Random;

public class JumpAction extends Action {

    private Jumpable jumpable;
    private Location jumpableLocation;
    private String direction;



    public JumpAction(Jumpable jumpable, Location jumpableLocation, String direction){
        this.jumpable = jumpable;
        this.jumpableLocation = jumpableLocation;
        this.direction = direction;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        String result = "";

        if(Math.random() <= jumpable.chanceToJump()){
            map.moveActor(actor, jumpableLocation);

            result += actor + " jumps onto the " +jumpable +  " (" + jumpableLocation.x() +"," + jumpableLocation.y() + ")" ;

        }
        else {
            int damage = jumpable.damage();
            actor.hurt(damage);
            result += actor + " fails to jump onto the wall with " +damage +" damage";
        }

        return result;

    }


    @Override
    public String menuDescription(Actor actor) {

        return actor + " jumps onto the " + direction + " " + jumpable + " (" + jumpableLocation.x() + "," + jumpableLocation.y() + ")";

    }

}
