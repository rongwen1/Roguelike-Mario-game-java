package game.interfaces;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
/**
 * <h1>Jumpable</h1>
 * An interface that will be implemented by all "jumpable" ground
 */
public interface Jumpable {

    /**
     *
     * @return int that containing the damage caused on player
     */
    int damage();

    /**
     *
     * @return double that representing the success rate
     */
    double chanceToJump();

}
