
package de.klassenserver7b.AVoC.A14;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import de.klassenserver7b.AVoC.A09.PVector;

/**
**/
 
public class Main {

	int[][] fields = new int[341][170];
	private int count = 0;
	private PVector pos;

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

		for (String s : lines) {

			String[] points = s.split(" -> ");

			for (int i = 0; i < points.length - 1; i++) {

				PVector start = new PVector(Integer.valueOf("" + points[i].split(",")[0]),
						Integer.valueOf("" + points[i].split(",")[1]));
				PVector end = new PVector(Integer.valueOf("" + points[i + 1].split(",")[0]),
						Integer.valueOf("" + points[i + 1].split(",")[1]));

				drawLine(start, end);

			}

		}

		for (int i = 0; i < fields.length; i++) {
			fields[i][169] = 1;
		}

		for (int i = 0; i < 30000; i++) {

			if (fields[convertX(500)][0] == 2) {
				System.out.println(count);
				System.exit(0);
				break;
			}

			pos = new PVector(convertX(500), 0);
			fields[pos.x][pos.y] = 2;

			while (move()) {
			}

			count++;

		}

		for (int i = 0; i < fields[0].length; i++) {

			for (int j = 0; j < fields.length; j++) {

				System.out.print(fields[j][i]);

			}

			System.out.println();

		}

	}

	private boolean move() {

//		if (pos.y >= fields[0].length - 1) {
//
//			for (int i = 0; i < fields[0].length; i++) {
//
//				for (int j = 0; j < fields.length; j++) {
//
//					System.out.print(fields[j][i]);
//
//				}
//
//				System.out.println();
//
//			}
//
//			System.out.println(count);
//			System.exit(0);
//			return false;
//
//		}

		if (fields[pos.x][pos.y + 1] == 0) {
			fields[pos.x][pos.y] = 0;
			fields[pos.x][pos.y + 1] = 2;
			pos.y++;
			return true;
		}

		if (fields[pos.x - 1][pos.y + 1] == 0) {
			fields[pos.x][pos.y] = 0;
			fields[pos.x - 1][pos.y + 1] = 2;
			pos.y++;
			pos.x--;
			return true;
		}

		if (fields[pos.x + 1][pos.y + 1] == 0) {
			fields[pos.x][pos.y] = 0;
			fields[pos.x + 1][pos.y + 1] = 2;
			pos.y++;
			pos.x++;
			return true;
		}

		return false;
	}

	private int convertX(int x) {
		return x - 330;
	}

	private void drawLine(PVector start, PVector end) {

		if (start.x > end.x) {

			for (int i = start.x; i >= end.x; i--) {

				fields[convertX(i)][start.y] = 1;

			}
			return;

		}
		if (start.x < end.x) {

			for (int i = start.x; i <= end.x; i++) {

				fields[convertX(i)][start.y] = 1;

			}
			return;

		}
		if (start.y > end.y) {

			for (int i = start.y; i >= end.y; i--) {

				fields[convertX(start.x)][i] = 1;

			}
			return;

		}
		if (start.y < end.y) {

			for (int i = start.y; i <= end.y; i++) {

				fields[convertX(start.x)][i] = 1;

			}
			return;

		}

	}

}
