
package de.klassenserver7b.AVoC.A18;

/**
**/
 
public class Cube {

	int x;
	int y;
	int z;

	/**
	 * @param x
	 * @param y
	 * @param z
	 
 
 
 */	public Cube(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public boolean hasNeighbour(Cube test) {

		if ((x - 1 == test.x && y == test.y && z == test.z) || (x + 1 == test.x && y == test.y && z == test.z)) {
			return true;
		}

		if ((y - 1 == test.y && z == test.z && x == test.x) || (y + 1 == test.y && z == test.z && x == test.x)) {
			return true;
		}

		if ((z - 1 == test.z && x == test.x && y == test.y) || (z + 1 == test.z && x == test.x && y == test.y)) {
			return true;
		}

		return false;
	}

}
