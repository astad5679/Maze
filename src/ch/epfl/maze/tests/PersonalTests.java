package ch.epfl.maze.tests;


import ch.epfl.maze.physical.Animal;
import ch.epfl.maze.physical.Maze;
import ch.epfl.maze.physical.zoo.Mouse;
import ch.epfl.maze.util.Vector2D;

/**
 * Test case for {@code Maze} implementation.
 * 
 */

public class PersonalTests {
	
	private final static int[][] LABYRINTH = {
			{ 1, 1, 1, 3, 1 },
			{ 1, 0, 0, 0, 1 },
			{ 1, 2, 1, 1, 1 }
		};

	public static void main(String[] args) {
		
		Maze maze = new Maze(LABYRINTH);

		// initial maze should be solved
		System.out.println(maze.isSolved());
		System.out.println(maze.getAnimals().size());

		// adds dummy animal
		Animal dummy = new Mouse(new Vector2D(3, 0));
		maze.addAnimal(dummy);
		System.out.println(maze.getAnimals().size());
		
		Animal retrieved = maze.getAnimals().get(0);

		System.out.println(maze.hasAnimal(dummy));
		System.out.println(retrieved == dummy);
		System.out.println(maze.isSolved());
		//System.out.println(dummy);


		// retrieves dummy anima from Maze
		//Animal retrieved = maze.getAnimals().get(0);


		// removes dummy animal
		maze.removeAnimal(dummy);
		System.out.println(maze.hasAnimal(dummy));
		System.out.println(maze.getAnimals().size());
		System.out.println(maze.isSolved());

			/**
			 * Test case for several methods in {@code Maze}.
			 */


		
	}

	
	
}