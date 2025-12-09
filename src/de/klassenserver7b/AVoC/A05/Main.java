
package de.klassenserver7b.AVoC.A05;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

/**
**/

public class Main {

	@SuppressWarnings("unchecked")
	ArrayList<Character>[] lists = new ArrayList[9];

	/**
	 * @param args
	 * 
	 * 
	 * 
	 */
	public static void main(String[] args) {
		new Main().run();

	}

	/**
	 * 
	 
	
	
	*/
	public Main() {
	}

	public void run() {

		initializeLists();

		List<String> lines;

		try {
			lines = Files.readAllLines(new File("input.txt").toPath());
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(0);
			return;
		}

		for (int i = 0; i < 9; i++) {

			String line = lines.get(i);
			// System.out.println(line);

			char[] chars = new char[10];
			line.getChars(0, line.length(), chars, 0);

			for (char c : chars) {
				if (Character.isWhitespace(c) || c == '\u0000') {
					continue;
				}
				lists[i].add(c);
			}
		}

		for (int i = 10; i < lines.size(); i++) {

			String line = lines.get(i);
			String[] split = line.split(",");

			move(Integer.parseInt(split[0]), Integer.parseInt(split[1]), Integer.parseInt(split[2]));

		}

		for (int i = 0; i < lists.length; i++) {
			System.out.print(lists[i].get(lists[i].size() - 1));
		}
	}

	private void move(int amount, int from, int to) {

		ArrayList<Character> cs = new ArrayList<>();

		for (int i = amount - 1; i >= 0; i--) {
			cs.add(lists[from - 1].remove(lists[from - 1].size() - 1));
		}

		for (int i = cs.size() - 1; i >= 0; i--) {
			Character c = cs.get(i);
			lists[to - 1].add(c);
		}

	}

	private void initializeLists() {
		for (int i = 0; i < lists.length; i++) {
			lists[i] = new ArrayList<Character>();
		}
	}

}
