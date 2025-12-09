
package de.klassenserver7b.AVoC.A09;

/**
**/
 
public class PVector {

	public int x;
	public int y;

	/**
	 * 
	 
 
 
 */	public PVector(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * 
	 
 
 
 */	public PVector() {
		this.x = 0;
		this.y = 0;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	@Override
	public boolean equals(Object obj) {

		boolean ret = obj instanceof PVector;

		if (ret) {

			PVector pose = (PVector) obj;

			ret = (pose.x == this.x) && (pose.y == this.y);

		}

		return ret;
	}

}
