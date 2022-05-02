package game.grounds;

public class Wall extends HighGround {

	private int damage;
	private double chanceToJump;
	private final String NAME;

	public Wall() {
		super('#');
		this.damage = 20;
		this.chanceToJump = 0.8;
		this.NAME = "Wall";

	}

	@Override
	public boolean blocksThrownObjects() {
		return true;
	}
	/**
	 *
	 * @return integer that representing the damage caused on player
	 */
	@Override
	public int damage() {
		return damage;
	}

	/**
	 *
	 * @return double that representing the success rate
	 */
	@Override
	public double chanceToJump() {
		return chanceToJump;
	}

	/**
	 *
	 * @return String representing name of the object
	 */
	public String toString() {
		return NAME;
	}
}
