package games.stendhal.server.entity.creature;

import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;

import games.stendhal.server.entity.player.Player;
import marauroa.common.game.RPClass;
import marauroa.common.game.RPObject;
import marauroa.common.game.SyntaxException;

public class Fox_Pet extends Pet{
	/** the logger instance. */
	private static final Logger logger = Logger.getLogger(Fox_Pet.class);

	@Override
	void setUp() {
		HP = 200;
		// each chicken or fish would give +5 HP
		incHP = 4;
		ATK = 55;
		DEF = 10;
		XP = 100;
		baseSpeed = 0.8;

		setAtk(ATK);
		setDef(DEF);
		setXP(XP);
		setBaseHP(HP);
		setHP(HP);
	}
	
	public static double getProbability() {
		return probability;
	}

	
	@Override
	public boolean getHasStolen() {
		return this.hasStolen;
	}
	public static void generateRPClass() {
		try {
			final RPClass fox = new RPClass("fox_pet");
			fox.isA("pet");
			// fox.add("weight", Type.BYTE);
			// fox.add("eat", Type.FLAG);
		} catch (final SyntaxException e) {
			logger.error("cannot generate RPClass", e);
		}
	}

	/**
	 * Creates a new wild fox.
	 */
	public Fox_Pet() {
		this(null);
	}

	/**
	 * Creates a new fox that may be owned by a player.
	 * @param owner owning player, or <code>null</code>
	 */
	public Fox_Pet(final Player owner) {
		// call set up before parent constructor is called as it needs those
		// values
		super();
		setOwner(owner);
		setUp();
		setRPClass("fox_pet");
		put("type", "fox_pet");

		if (owner != null) {
			// add pet to zone and create RPObject.ID to be used in setPet()
			owner.getZone().add(this);
			owner.setPet(this);
		}

		update();
	}

	/**
	 * Creates a fox based on an existing fox RPObject, and assigns it to a
	 * player.
	 *
	 * @param object object containing the data for the fox
	 * @param owner
	 *            The player who should own the fox
	 */
	public Fox_Pet(final RPObject object, final Player owner) {
		super(object, owner);
		setRPClass("fox_pet");
		put("type", "fox_pet");
		update();
	}

	@Override
	protected List<String> getFoodNames() {
		return Arrays.asList(
				"meat",
				"ham",
				"tripe",
				"milk",
				"chicken",
				"trout",
				"cod",
				"mackerel",
				"char",
				"perch",
				"roach",
				"surgeonfish",
				"clownfish"
				);
	}

	/**
	 * Does this domestic animal take part in combat?
	 *
	 * @return true, if it can be attacked by creatures, false otherwise
	 */
	@Override
	protected boolean takesPartInCombat() {
		return false;
	}
	
	/**
	 * Can this Pet steal?
	 *
	 * @return true, if it can be attacked by creatures, false otherwise
	 */
	@Override
	protected boolean canSteal() {
		return true;
	}

	}	

