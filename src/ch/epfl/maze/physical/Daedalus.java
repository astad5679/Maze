package ch.epfl.maze.physical;

import java.util.ArrayList;
import java.util.List;


/**
 * Daedalus in which predators hunt preys. Once a prey has been caught by a
 * predator, it will be removed from the daedalus.
 * 
 */

public final class Daedalus extends World {
	private List<Predator> predators = new ArrayList<Predator>();
	private List<Predator> predatorsDupe = new ArrayList<Predator>();
	private List<Prey> prey = new ArrayList<Prey>();
	private List<Prey> preyDupe = new ArrayList<Prey>();
	//do I define these as their precise instance or not? Animal v Predator/prey
	
	
	
	/**
	 * Constructs a Daedalus with a labyrinth structure
	 * 
	 * @param labyrinth
	 *            Structure of the labyrinth, an NxM array of tiles
	 */

	public Daedalus(int[][] labyrinth) {
		super(labyrinth);
		// TODO
	}

	@Override
	public boolean isSolved() {
		// TODO
		return prey.isEmpty();
	}

	/**
	 * Adds a predator to the daedalus.
	 * 
	 * @param p
	 *            The predator to add
	 */

	public void addPredator(Predator p) {
		predators.add(p);
		predatorsDupe.add((Predator) p.copy());
		// TODO
	}

	/**
	 * Adds a prey to the daedalus.
	 * 
	 * @param p
	 *            The prey to add
	 */

	public void addPrey(Prey p) {
		prey.add(p);
		preyDupe.add((Prey) p.copy());
		// TODO
	}

	/**
	 * Removes a predator from the daedalus.
	 * 
	 * @param p
	 *            The predator to remove
	 */

	public void removePredator(Predator p) {
		// TODO
		predators.remove(p);
	}

	/**
	 * Removes a prey from the daedalus.
	 * 
	 * @param p
	 *            The prey to remove
	 */

	public void removePrey(Prey p) {
		// TODO
		prey.remove(p);
		
	}

	@Override
	public List<Animal> getAnimals() {
		// TODO
		List<Animal> animals = new ArrayList<Animal>();
		
		for (Prey prey : prey) {
			animals.add(prey.copy());
		}
		for (Predator predator : predators) {
			animals.add(predator.copy());
		}
		
		return animals;
	}

	/**
	 * Returns a copy of the list of all current predators in the daedalus.
	 * 
	 * @return A list of all predators in the daedalus
	 */

	public List<Predator> getPredators() {
		// TODO
		return predators;
	}

	/**
	 * Returns a copy of the list of all current preys in the daedalus.
	 * 
	 * @return A list of all preys in the daedalus
	 */

	public List<Prey> getPreys() {
		// TODO
		return prey;
	}

	/**
	 * Determines if the daedalus contains a predator.
	 * 
	 * @param p
	 *            The predator in question
	 * @return <b>true</b> if the predator belongs to the world, <b>false</b>
	 *         otherwise.
	 */

	public boolean hasPredator(Predator p) {
		// TODO
		return predators.contains(p);
	}

	/**
	 * Determines if the daedalus contains a prey.
	 * 
	 * @param p
	 *            The prey in question
	 * @return <b>true</b> if the prey belongs to the world, <b>false</b>
	 *         otherwise.
	 */

	public boolean hasPrey(Prey p) {
		// TODO
		return prey.contains(p);
	}

	@Override
	public void reset() {
		prey = new ArrayList<Prey>();
		predators = new ArrayList<Predator>();
		
		for (Prey creature : preyDupe) {
			prey.add((Prey) creature.copy());
		}
		
		for (Predator creature : predatorsDupe) {
			predators.add((Predator) creature.copy());
		}
	}
}
