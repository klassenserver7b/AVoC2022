package de.klassenserver7b.AVoC.A11;

import java.math.BigInteger;
import java.util.ArrayList;

public class Monkey {

	public static final int MULTIPLY_ACTION = 0;
	public static final int ADD_ACTION = 1;
	public static final int MULTIPLYOLD = 2;

	private ArrayList<BigInteger> items;
	private int action;
	private int actionparameter;
	private int divisibleby;
	private int truemonkeyid;
	private int falsemonkeyid;
	private int itemcount;

	public Monkey(int action, int actionparameter, int divisibleby, int truemonkeyid, int falsemonkeyid) {
		items = new ArrayList<>();
		this.action = action;
		this.actionparameter = actionparameter;
		this.divisibleby = divisibleby;
		this.truemonkeyid = truemonkeyid;
		this.falsemonkeyid = falsemonkeyid;
		itemcount = 0;
	}

	public Monkey() {
		items = new ArrayList<>();
		this.action = 0;
		this.actionparameter = 0;
		this.divisibleby = 0;
		this.truemonkeyid = 0;
		this.falsemonkeyid = 0;
		itemcount = 0;
	}

	public int size() {
		return items.size();
	}

	public BigInteger get(int index) {
		return items.get(index);
	}

	public boolean add(BigInteger e) {
		return items.add(e);
	}

	public ArrayList<Monkey> performAllTests(ArrayList<Monkey> monkeys, BigInteger modulo) {

		for (BigInteger i : items) {

			BigInteger newvalue = calcValue(i, modulo);

			if (test(newvalue)) {

				Monkey m = monkeys.get(truemonkeyid);
				m.add(newvalue);
				monkeys.set(truemonkeyid, m);

			} else {

				Monkey m = monkeys.get(falsemonkeyid);
				m.add(newvalue);
				monkeys.set(falsemonkeyid, m);

			}

			itemcount++;

		}

		items.clear();

		return monkeys;
	}

	private boolean test(BigInteger item) {
		return item.mod(BigInteger.valueOf(divisibleby)).equals(BigInteger.ZERO);
	}

	private BigInteger calcValue(BigInteger item, BigInteger modulo) {

		switch (action) {

		case MULTIPLY_ACTION -> {
			item = item.multiply(BigInteger.valueOf(actionparameter));
		}
		case ADD_ACTION -> {
			item = item.add(BigInteger.valueOf(actionparameter));
		}
		case MULTIPLYOLD -> {
			item = item.multiply(item);
		}

		}

		// item = item.divide(BigInteger.valueOf(3));
		item = item.mod(modulo);

		return item;

	}

	public void printMonkeyStats() {

		System.out.print("items: ");
		for (BigInteger i : items) {
			System.out.print(i.longValue() + ", ");
		}
		System.out.println();
		System.out.println("action: " + action);
		System.out.println("actionp: " + actionparameter);

		System.out.println("divisibleby: " + divisibleby);

		System.out.println("truemonkeyid: " + truemonkeyid);
		System.out.println("falsemonkeyid: " + falsemonkeyid);

		System.out.println();
		System.out.println();
	}

	public int getItemcount() {
		return itemcount;
	}

	public void setItems(ArrayList<BigInteger> items) {
		this.items = items;
	}

	public void setAction(int action) {
		this.action = action;
	}

	public void setActionparameter(int actionparameter) {
		this.actionparameter = actionparameter;
	}

	public void setDivisibleby(int divisibleby) {
		this.divisibleby = divisibleby;
	}

	public void setTruemonkeyid(int truemonkeyid) {
		this.truemonkeyid = truemonkeyid;
	}

	public void setFalsemonkeyid(int falsemonkeyid) {
		this.falsemonkeyid = falsemonkeyid;
	}

}
