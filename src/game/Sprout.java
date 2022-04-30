package game;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;

import java.util.Random;

public class Sprout extends Tree implements Jumpable{
    private int turns;
    private int damage;
    private double chanceToJump;

    public Sprout() {
        super('+');
        turns = 0;
        this.damage = 10;
        this.chanceToJump = 0.9;
    }


    @Override
    public void tick(Location location) {
        super.tick(location);
        //Increment turns
        turns += 1;

        /*//10% chance to spawn Goomba from Sprout
        Random r = new Random();
        int low = 1;
        int high = 10;
        int result = r.nextInt(high) + low;   //Generate random number between 1 and 10 inclusive
        if (result == 1 && location.getActor() == null) {
            Goomba goomba = new Goomba();
            location.addActor(goomba);
        }

        //Convert to sapling after 10 turns
        if(turns == 100){
            Sapling sapling = new Sapling();
            location.setGround(sapling);
        }*/
    }

    @Override
    public boolean canActorEnter(Actor actor) {
        return false;
    }

    @Override
    public int damage() {
        return damage;
    }

    @Override
    public double chanceToJump() {
        return chanceToJump;
    }

    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction){
        if(location.containsAnActor()){
            return new ActionList();
        }
        else {
            return new ActionList(new JumpAction(this, location, direction));
        }
    }
    public String toString() {
        return "Sprout";
    }

}
