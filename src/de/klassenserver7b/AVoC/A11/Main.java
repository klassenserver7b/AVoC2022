
package de.klassenserver7b.AVoC.A11;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

/**
**/
 
public class Main {

	BigInteger modulo;

	/**
	 * 
	 
 
 
 */	public Main() {
		modulo = BigInteger.ONE;
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

		ArrayList<Monkey> monkeys = new ArrayList<>();

		for (int i = 0; i < 8; i++) {

			Monkey m = new Monkey();

			for (int j = 0; j < 7; j++) {

				int lineindex = i * 7 + j;
				String line = lines.get(lineindex);

				performChanges(m, line);

			}

			monkeys.add(m);

		}
		
		System.out.println(modulo.longValue());

		for (int i = 0; i < 10000; i++) {

			for (int j = 0; j < monkeys.size(); j++) {

				Monkey m = monkeys.get(j);

				monkeys = m.performAllTests(monkeys, modulo);
			}

		}

		for (Monkey m : monkeys) {
			System.out.println(m.getItemcount());
		}

	}

	private Monkey performChanges(Monkey m, String line) {

		if (line.startsWith("  Starting items: ")) {
			line = line.replace("  Starting items: ", "");
			String[] split = line.split(", ");

			ArrayList<BigInteger> items = new ArrayList<>();
			for (int i = 0; i < split.length; i++) {
				items.add(BigInteger.valueOf(Integer.valueOf(split[i])));
			}
			m.setItems(items);
			return m;
		}

		if (line.startsWith("  Operation: ")) {

			if (line.equalsIgnoreCase("  Operation: new = old * old")) {
				m.setAction(Monkey.MULTIPLYOLD);
			} else {

				if (line.contains("*")) {
					m.setAction(Monkey.MULTIPLY_ACTION);
				} else {
					m.setAction(Monkey.ADD_ACTION);
				}

				m.setActionparameter(Integer.valueOf(line.split(" ")[line.split(" ").length - 1]));

			}
			return m;
		}

		if (line.startsWith("  Test: ")) {
			String[] split = line.split(" ");
			m.setDivisibleby(Integer.valueOf(split[split.length - 1]));
			modulo = modulo.multiply(BigInteger.valueOf(Integer.valueOf(split[split.length - 1])));
			return m;
		}

		if (line.startsWith("    If true: ")) {
			m.setTruemonkeyid(Integer.valueOf("" + line.charAt(line.length() - 1)));
			return m;
		}

		if (line.startsWith("    If false: ")) {
			m.setFalsemonkeyid(Integer.valueOf("" + line.charAt(line.length() - 1)));
			return m;
		}

		return m;
	}

}
