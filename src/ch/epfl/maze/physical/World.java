package ch.epfl.maze.physical;

import java.util.List;

import ch.epfl.maze.util.Direction;
import ch.epfl.maze.util.Vector2D;

/**
 * World that is represented by a labyrinth of tiles in which an {@code Animal}
 * can move.
 * 
 */

public abstract class World {

	/* tiles constants */
	public static final int FREE = 0;
	public static final int WALL = 1;
	public static final int START = 2;
	public static final int EXIT = 3;
	public static final int NOTHING = -1;
	
	private int newWorld[][];
	private int width;
	private int height;
	/**
	 * Constructs a new world with a labyrinth. The labyrinth must be rectangle.
	 * 
	 * @param labyrinth
	 *            Structure of the labyrinth, an NxM array of tiles
	 */

	public World(int[][] labyrinth) {
		width = labyrinth[0].length;
        height = labyrinth.length;
		newWorld = new int [height][width];
		
		for (int i = 0; i < height; i++){
			for (int j = 0; j < width; j++){
				newWorld[i][j] = labyrinth[i][j];
			}
		}
	}

	/**
	 * Determines whether the labyrinth has been solved by every animal.
	 * 
	 * @return <b>true</b> if no more moves can be made, <b>false</b> otherwise
	 */

	abstract public boolean isSolved();

	/**
	 * Resets the world as when it was instantiated.
	 */

	abstract public void reset();

	/**
	 * Returns a copy of the list of all current animals in the world.
	 * 
	 * @return A list of all animals in the world
	 */

	abstract public List<Animal> getAnimals();

	/**
	 * Checks in a safe way the tile number at position (x, y) in the labyrinth.
	 * 
	 * @param x
	 *            Horizontal coordinate
	 * @param y
	 *            Vertical coordinate
	 * @return The tile number at position (x, y), or the NONE tile if x or y is
	 *         incorrect.
	 */
	public final int getTile(int x, int y) {
		if (y < height && x < width && y >= 0 && x >= 0) { //This condition checks if the indices are trying to access positions out of bounds before the error occurs
			return newWorld[y][x];
		}
		return NOTHING;
	
		/*
		try {
			if (y < height || x < width){
				return newWorld[y][x];
			} 
		} catch (java.lang.ArrayIndexOutOfBoundsException e) {
			System.out.println(e);
		}
		return NOTHING;
		*/
	}

	/**
	 * Determines if coordinates are free to walk on.
	 * 
	 * @param x
	 *            Horizontal coordinate
	 * @param y
	 *            Vertical coordinate
	 * @return <b>true</b> if an animal can walk on tile, <b>false</b> otherwise
	 */

	public final boolean isFree(int x, int y) {
		if (getTile(x, y) == WALL || getTile(x, y) == NOTHING){
			return false;
		}
		return true;	
	}

	/**
	 * Computes and returns the available choices for a position in the
	 * labyrinth. The result will be typically used by {@code Animal} in
	 * {@link ch.epfl.maze.physical.Animal#move(Direction[]) move(Direction[])}
	 * 
	 * @param position
	 *            A position in the maze
	 * @return An array of all available choices at a position
	 */

	public final Direction[] getChoices(Vector2D position) {
		int x = position.getX();
		int y = position.getY();
		int c = 0;
		
		if (getTile(x, y + 1) == 0){
			c++;
		}
		if (getTile(x, y - 1) == 0){
			c++;
		}
		if (getTile(x - 1, y) == 0){
			c++;
		}
		if (getTile(x + 1, y) == 0){
			c++;
		}
		
		if (c == 0) {
			Direction[] direction = {Direction.NONE};
			return direction;
		}
		
		Direction[] direction = new Direction [c];
		int i = 0;
		
		if (getTile(x, y + 1) == 0){
			direction[i] = Direction.DOWN;
			i++;
		}
		if (getTile(x, y - 1) == 0){
			direction[i] = Direction.UP;
			i++;
		}
		if (getTile(x - 1, y) == 0){
			direction[i] = Direction.LEFT;
			i++;
		}
		if (getTile(x + 1, y) == 0){
			direction[i] = Direction.RIGHT;
			i++;
		}
		
		/*
		for (int i1 = 0; i1 < direction.length; i1++) {
			System.out.print(direction[i1] + ", ");
		}
		System.out.println();
		*/
		
		
		return direction;
	}

	/**
	 * Returns horizontal length of labyrinth.
	 * 
	 * @return The horizontal length of the labyrinth
	 */

	public final int getWidth() {	
        return width;
	}

	/**
	 * Returns vertical length of labyrinth.
	 * 
	 * @return The vertical length of the labyrinth
	 */

	public final int getHeight() {
		return height;
	}

	/**
	 * Returns the entrance of the labyrinth at which animals should begin when
	 * added.
	 * 
	 * @return Start position of the labyrinth, null if none.
	 */
	//getTile ?
	public final Vector2D getStart() {
		for (int i = 0; i < height; i++){
			for (int j = 0; j < width; j++){
				if (getTile(j, i) == START){
					Vector2D start = new Vector2D(j, i);
					return start;
				}
			}
		}
		
		return null;
	}

	/**
	 * Returns the exit of the labyrinth at which animals should be removed.
	 * 
	 * @return Exit position of the labyrinth, null if none.
	 */

	public final Vector2D getExit() {
		for (int i = 0; i < height; i++){
			for (int j = 0; j < width; j++){
				if (getTile(j, i) == EXIT){
					Vector2D exit = new Vector2D(j, i);
					return exit;
				}
			}
		}
		
		return null;
	}
}
