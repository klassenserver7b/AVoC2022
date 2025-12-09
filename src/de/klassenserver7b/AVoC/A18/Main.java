
package de.klassenserver7b.AVoC.A18;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

/**
**/
 
public class Main {

	/**
	 * 
	 
 
 
 */	public Main() {
	}

	/**
	 * @param args
	 
 
 
 */	public static void main(String[] args) {

		new Main().run();

	}

	public void run() {

		List<String> lines;

		try {
			lines = Files.readAllLines(new File("input.txt").toPath());
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(0);
			return;
		}

		ArrayList<Cube> cubes = new ArrayList<>();

		for (String s : lines) {
			String[] split = s.split(",");
			cubes.add(new Cube(Integer.valueOf(split[0]), Integer.valueOf(split[1]), Integer.valueOf(split[2])));
		}

		int count = 0;

		for (Cube c : cubes) {

			int sites = 6;

			for (Cube test : cubes) {
				if (c.hasNeighbour(test)) {
					sites--;
				}
			}

			count += sites;

		}

		System.out.println(count);

	}
}
