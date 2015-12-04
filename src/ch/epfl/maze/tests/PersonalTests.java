package ch.epfl.maze.tests;


import ch.epfl.maze.physical.Animal;
import ch.epfl.maze.physical.Maze;
import ch.epfl.maze.physical.World;
import ch.epfl.maze.physical.zoo.Mouse;
import ch.epfl.maze.util.Vector2D;

import ch.epfl.maze.graphics.Display;
import ch.epfl.maze.physical.Daedalus;
import ch.epfl.maze.physical.pacman.Blinky;
import ch.epfl.maze.physical.pacman.Clyde;
import ch.epfl.maze.physical.pacman.Inky;
import ch.epfl.maze.physical.pacman.PacMan;
import ch.epfl.maze.physical.pacman.Pinky;
import ch.epfl.maze.physical.zoo.Bear;
import ch.epfl.maze.physical.zoo.Hamster;
import ch.epfl.maze.physical.zoo.Monkey;
import ch.epfl.maze.physical.zoo.Panda;
import ch.epfl.maze.simulation.DaedalusSimulation;
import ch.epfl.maze.simulation.MazeSimulation;
import ch.epfl.maze.simulation.Simulation;
import ch.epfl.maze.util.LabyrinthGenerator;


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
		
//		Maze maze = new Maze(LABYRINTH);
//
//		// initial maze should be solved
//		System.out.println(maze.isSolved());
//		System.out.println(maze.getAnimals().size());
//
//		// adds dummy animal
//		Animal dummy = new Mouse(new Vector2D(3, 0));
//		maze.addAnimal(dummy);
//		System.out.println(maze.getAnimals().size());
//		
//		Animal retrieved = maze.getAnimals().get(0);
//
//		System.out.println(maze.hasAnimal(dummy));
//		System.out.println(retrieved == dummy);
//		System.out.println(maze.isSolved());
//		//System.out.println(dummy);
//
//
//		// retrieves dummy animal from Maze
//		//Animal retrieved = maze.getAnimals().get(0);
//
//
//		// removes dummy animal
//		maze.removeAnimal(dummy);
//		System.out.println(maze.hasAnimal(dummy));
//		System.out.println(maze.getAnimals().size());
//		System.out.println(maze.isSolved());
//		
//		
//		
//		//System.out.println(test.x);
		
		int test = World.getWidth();
		
		System.out.println(test);
		
	}

	
	
}