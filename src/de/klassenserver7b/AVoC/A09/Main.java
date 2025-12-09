
package de.klassenserver7b.AVoC.A09;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

/**
**/
 
public class Main {

	private PVector head;
	private PVector[] tail;
	private ArrayList<PVector> positions;

	/**
	 * 
	 
 
 
 */	public Main() {

		head = new PVector();
		tail = new PVector[9];
		positions = new ArrayList<>();

		for (int i = 0; i < tail.length; i++) {
			tail[i] = new PVector();
		}
	}

	/**
	 * @param args
	 
 
 
 */	public static void main(String[] args) {

		new Main().run();

	}

	public void run() {

		List<String> lines;

		positions.add(new PVector(0, 0));

		try {
			lines = Files.readAllLines(new File("input.txt").toPath());
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(0);
			return;
		}

		for (String s : lines) {

			String[] split = s.split(" ");

			int amount = Integer.valueOf(split[1]);

			for (int i = 0; i < amount; i++) {
				move(split[0]);
			}

		}

		for (PVector p : positions) {
			System.out.println(p.x + ", " + p.y);
		}

		System.out.println(positions.size());

	}

	private void move(String dest) {

		switch (dest) {
		case "U" -> {
			head.y--;
		}
		case "R" -> {
			head.x++;
		}
		case "L" -> {
			head.x--;
		}
		case "D" -> {
			head.y++;
		}
		}

		for (int i = 0; i < tail.length; i++) {
			calcTail(i - 1, i);
		}
		saveTailPos(tail[8].x, tail[8].y);
	}

	private void calcTail(int beforecount, int tc) {

		int hx;
		int hy;

		if (beforecount == -1) {
			hx = head.x;
			hy = head.y;
		} else {

			hx = tail[beforecount].x;
			hy = tail[beforecount].y;

		}

		int xdst = Math.abs(hx - tail[tc].x);
		int ydst = Math.abs(hy - tail[tc].y);

		if (xdst < 2 && ydst < 2) {
			return;
		}

		if (xdst == 2 && ydst == 2) {
			tail[tc].x = (hx + tail[tc].x) / 2;
			tail[tc].y = (hy + tail[tc].y) / 2;
		} else if (xdst == 2) {
			tail[tc].x = (hx + tail[tc].x) / 2;
			tail[tc].y = hy;
		} else if (ydst == 2) {
			tail[tc].y = (hy + tail[tc].y) / 2;
			tail[tc].x = hx;
		}
	}

	private void saveTailPos(int x, int y) {

		PVector newpos = new PVector(x, y);

		if (!positions.contains(newpos)) {
			positions.add(newpos);
		}

	}

}
