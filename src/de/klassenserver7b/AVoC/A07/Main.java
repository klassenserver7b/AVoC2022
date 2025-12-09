
package de.klassenserver7b.AVoC.A07;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
**/
 
public class Main {

	private int dirsum;
	ArrayList<Integer> dirsizes = new ArrayList<>();

	/**
	 * 
	 
 
 
 */	public Main() {
		dirsum = 0;
	}

	/**
	 * @param args
	 
 
 
 */	public static void main(String[] args) {
		new Main().run();
	}

	public void run() {

		TreeNode<CustomFile> root = new TreeNode<>(new CustomFile("/", 0, true));

		List<String> lines;

		try {
			lines = Files.readAllLines(new File("input.txt").toPath());
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(0);
			return;
		}

		TreeNode<CustomFile> current = root;

		for (String s : lines) {

			if (s.startsWith("$ cd")) {
				current = performCD(s.split(" ")[2], current);
				continue;
			}

			if (s.startsWith("$")) {
				continue;
			}

			addFile(s, current);
		}

		calculateFullSum(current.getRoot());

		int fullspace = 70000000;
		int neededspace = 30000000 - (fullspace - 49192532);
		System.out.println(neededspace);
		System.out.println();

		Collections.sort(dirsizes);

		for (int i : dirsizes) {

			if (i > neededspace) {
				System.out.println(i);
			}
		}

	}

	public void calculateFullSum(TreeNode<CustomFile> node) {

		if (node.equals(node.getRoot())) {

			calculateDirSum(node);

			dirsizes.add(dirsum);

			dirsum = 0;

		}

		for (TreeNode<CustomFile> n : node.children) {

			if (!n.getData().isDirectory()) {
				continue;

			} else {

				calculateDirSum(n);

				dirsizes.add(dirsum);

				dirsum = 0;

				calculateFullSum(n);
			}

		}

	}

	public void calculateDirSum(TreeNode<CustomFile> node) {

		for (TreeNode<CustomFile> n : node.children) {

			if (!n.getData().isDirectory()) {
				dirsum += n.getData().getSize();
			} else {
				calculateDirSum(n);
			}

		}

	}

	public TreeNode<CustomFile> addFile(String command, TreeNode<CustomFile> node) {

		if (command.startsWith("dir")) {
			node.addChild(new CustomFile(command.substring(4), 0, true));
		} else {
			String[] split = command.split(" ");
			node.addChild(new CustomFile(split[1], Integer.valueOf(split[0]), false));
		}

		return node;
	}

	public TreeNode<CustomFile> performCD(String dir, TreeNode<CustomFile> node) {

		if (dir.equals("..")) {
			return (node.parent == null ? node : node.parent);
		}

		if (dir.equals("/")) {
			return node.getRoot();
		}

		TreeNode<CustomFile> n = node.search(new CustomFile(dir, 0, true));

		if (n == null) {
			System.err.println("Invalid dir " + dir);
		}

		return n;

	}

}
