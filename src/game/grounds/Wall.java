package game.grounds;

public class Wall extends HighGround {

	/**
	 * Damage actor receives when it fails to jump to this ground
	 */
	private int damage;
	/**
	 * Actor's chance to jump on this ground
	 */
	private double chanceToJump;
	/**
	 * Name of this ground
	 */
	private final String NAME;

	/**
	 * Constructor for Wall.
	 */
	public Wall() {
		super('#');
		this.damage = 20;
		this.chanceToJump = 0.8;
		this.NAME = "Wall";

	}

	/**
	 * terrain that blocks thrown objects but not movement, or vice versa
	 * @return true if blocks thrown objects. false otherwise.
	 */
	@Override
	public boolean blocksThrownObjects() {
		return true;
	}

	/**
	 * Getter for damage
	 * @return integer that representing the damage caused on player
	 */
	@Override
	public int damage() {
		return damage;
	}

	/**
	 * Getter for chance to jump
	 * @return double that representing the success rate
	 */
	@Override
	public double chanceToJump() {
		return chanceToJump;
	}

	/**
	 * Getter for name
	 * @return String representing name of the object
	 */
	public String toString() {
		return NAME;
	}
}
