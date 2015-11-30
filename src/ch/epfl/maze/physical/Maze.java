package ch.epfl.maze.physical;

import java.util.ArrayList;
import java.util.List;

/**
 * Maze in which an animal starts from a starting point and must find the exit.
 * Every animal added will have its position set to the starting point. The
 * animal is removed from the maze when it finds the exit.
 * 
 */

public final class Maze extends World {

	/**
	 * Constructs a Maze with a labyrinth structure.
	 * 
	 * @param labyrinth
	 *            Structure of the labyrinth, an NxM array of tiles
	 */

	public Maze(int[][] labyrinth) {
		super(labyrinth);
		// TODO
	}

	@Override
	public boolean isSolved() {
		// TODO
		return false;
	}

	@Override
	public List<Animal> getAnimals() {
		// TODO
		ArrayList<Animal> animal = new ArrayList<Animal>();
		
		 
		return animal;
	}

	/**
	 * Determines if the maze contains an animal.
	 * 
	 * @param a
	 *            The animal in question
	 * @return <b>true</b> if the animal belongs to the world, <b>false</b>
	 *         otherwise.
	 */

	public boolean hasAnimal(Animal a) {
		// TODO
		ArrayList<Animal> animal = (ArrayList<Animal>) getAnimals();
		return animal.contains(a);
	}

	/**
	 * Adds an animal to the maze.
	 * 
	 * @param a
	 *            The animal to add
	 */

	public void addAnimal(Animal a) {
		ArrayList<Animal> animal = new ArrayList<Animal>();
		animal.add(a);
		// TODO
	}

	/**
	 * Removes an animal from the maze.
	 * 
	 * @param a
	 *            The animal to remove
	 */

	public void removeAnimal(Animal a) {
		ArrayList<Animal> animal = new ArrayList<Animal>();
		animal.remove(a);
		// TODO
	}

	@Override
	public void reset() {
		// TODO
	}
}
