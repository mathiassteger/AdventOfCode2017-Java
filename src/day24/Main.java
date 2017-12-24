package day24;

import java.util.ArrayList;
import java.util.Arrays;

import util.ReadInputHelper;

public class Main {
	static ArrayList<Comp> comps = new ArrayList<>();
	static ArrayList<Comp> starters = new ArrayList<>();
	static ArrayList<Comp> all = new ArrayList<>();

	public static void main(String[] args) {
		ArrayList<String> lines = new ReadInputHelper(0).getLines();

		int sum = 0;

		for (int i = 0; i < lines.size(); i++) {
			String line = lines.get(i);
			String[] parts = line.split("/");
			Comp a = new Comp(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]));

			if (a.isBase()) {
				starters.add(a);
			} else {
				comps.add(a);
			}

			all.add(a);
		}

		for (int i = 0; i < starters.size(); i++) {
			Comp starter = starters.get(i);
			starter.initSuccessors(comps);
		}

		for (int i = 0; i < all.size(); i++) {
			all.get(i).getB("");
			all.get(i).getSum(0);
		}
	}

	static Comp getComp(int x, int y) {
		int index = all.indexOf(new Comp(x, y));
		return all.get(index);
	}

}

class Comp {
	public int x, y;
	ArrayList<Comp> predecessors = new ArrayList<>();
	Comp[] successors = null;
	boolean successorsInitiated = false;
	boolean visited = false;
	boolean xOcc = false, yOcc = false;

	public Comp(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public void initSuccessors(ArrayList<Comp> comps) {
		ArrayList<Comp> temp = new ArrayList<>();
		this.successorsInitiated = true;

		for (Comp c : comps) {
			if (isConnected(c)) {
				String connector = getConnector(c);
				
//				temp.add(c);
//				if (!predecessors.contains(c))
//					c.predecessors.add(this);
				
				
				
				if (connector.equals("x")) {
					if (!xOcc) {
						xOcc = true;
						temp.add(c);
						if (!predecessors.contains(c))
							c.predecessors.add(this);
					}
				} else if (connector.equals("y")) {
					if (!yOcc) {
						yOcc = true;
						temp.add(c);
						if (!predecessors.contains(c))
							c.predecessors.add(this);
					}
				}

			}
		}

		for (Comp c : temp) {
			if (!c.successorsInitiated)
				c.initSuccessors(comps);
		}

		successors = temp.toArray(new Comp[temp.size()]);
	}

	public void resetVisits() {
		this.visited = false;
	}

	public void getSum(int prevSum) {
		int sum = x + y + prevSum;

		for (Comp c : predecessors) {
			c.getSum(sum);
		}
		
		if (isBase()) {
			System.out.println(sum);
		}
	}

	public void getB(String bs) {
		String out = toString() + ", " + bs;

		for (Comp c : predecessors) {
			c.getB(out);
		}

		if (isBase()) {
			System.out.println(out);
		}
	}

	public boolean isConnected(Comp o) {
		if (o.equals(this))
			return false;

		return o.x == this.x || o.x == this.y || o.y == this.x || o.y == this.y;
	}

	public String getConnector(Comp o) {
		if (o.equals(this))
			return "";
		
		if (o.x == this.x) {
			return "x";
		} else if (o.x == this.y) {
			return "y";
		} else if (o.y == this.x) {
			return "x";
		} else if (o.y == this.y) {
			return "y";
		} else {
			return "";
		}
	}

	public boolean hasPredecessor() {
		return !(predecessors.size() > 0);
	}

	public boolean isBase() {
		return x == 0 || y == 0;
	}

	public boolean equals(Object other) {
		Comp o = (Comp) other;

		return o.x == this.x && o.y == this.y;
	}

	public String toString() {
		return "[" + x + ", " + y + "]";
	}

	public String printSuccessors() {
		return Arrays.toString(successors);
	}
}
