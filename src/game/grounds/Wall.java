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

	@Override
	public int damage() {
		return damage;
	}

	@Override
	public double chanceToJump() {
		return chanceToJump;
	}

	public String toString() {
		return NAME;
	}
}
