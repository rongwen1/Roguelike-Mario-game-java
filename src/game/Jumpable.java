package game;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;

public interface Jumpable {
    int damage();

    double chanceToJump();

   // String jump(Actor by, Location at);
}
