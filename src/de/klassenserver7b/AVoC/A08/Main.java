package de.klassenserver7b.AVoC.A08;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

	public Main() {
	}

	public static void main(String[] args) {

		new Main().run();

	}

	public void run() {

		List<String> lines;

		Integer[][] trees = new Integer[99][99];

		try {
			lines = Files.readAllLines(new File("input.txt").toPath());
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(0);
			return;
		}

		for (int i = 0; i < lines.size(); i++) {
			String l = lines.get(i);

			char[] chars = new char[99];
			l.getChars(0, l.length(), chars, 0);

			for (int j = 0; j < chars.length; j++) {

				trees[i][j] = Integer.valueOf(chars[j] + "");

			}

		}

		ArrayList<Integer> vals = new ArrayList<>();

		for (int i = 0; i < 99; i++) {

			for (int j = 0; j < 99; j++) {

				vals.add(getViewScore(i, j, trees));

			}

		}

		Collections.sort(vals);

		for (int i : vals) {
			System.out.println(i);
		}

	}

	private int getViewScore(int x, int y, Integer[][] trees) {
		int value = trees[x][y];

		return isVisibleTop(value, x, y, trees) * isVisibleLeft(value, x, y, trees)
				* isVisibleBottom(value, x, y, trees) * isVisibleRight(value, x, y, trees);

	}

	private int isVisibleTop(int value, int x, int y, Integer[][] trees) {
		int count = 0;

		for (int i = y - 1; i >= 0; i--) {

			int tvalue = trees[x][i];

			count++;
			if (tvalue >= value) {
				return count;
			}

		}

		return count;

	}

	private int isVisibleLeft(int value, int x, int y, Integer[][] trees) {
		int count = 0;

		for (int i = x - 1; i >= 0; i--) {
			int tvalue = trees[i][y];

			count++;
			if (tvalue >= value) {
				return count;
			}
		}

		return count;

	}

	private int isVisibleRight(int value, int x, int y, Integer[][] trees) {
		int count = 0;

		for (int i = x + 1; i < 99; i++) {
			int tvalue = trees[i][y];

			count++;
			if (tvalue >= value) {
				return count;
			}
		}

		return count;

	}

	private int isVisibleBottom(int value, int x, int y, Integer[][] trees) {
		int count = 0;

		for (int i = y + 1; i < 99; i++) {
			int tvalue = trees[x][i];

			count++;
			if (tvalue >= value) {
				return count;
			}
		}

		return count;

	}

}
