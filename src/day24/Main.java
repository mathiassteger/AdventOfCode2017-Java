package day24;

import java.util.ArrayList;
import java.util.Arrays;

import util.ReadInputHelper;

public class Main {
	static ArrayList<Comp> comps = new ArrayList<>();
	static ArrayList<Comp> starters = new ArrayList<>();
	static ArrayList<Comp> all = new ArrayList<>();

	public static void main(String[] args) {
		ArrayList<String> lines = new ReadInputHelper(24).getLines();

		int sum = 0;
		int bsize = 0;

		for (int i = 0; i < lines.size(); i++) {
			String line = lines.get(i);
			String[] parts = line.split("/");

			Comp a = new Comp(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]));

			if (a.isBase()) {
				if (a.x == 0) {
					starters.add(new Comp(a));
				} else {
					starters.add(new Comp(a.y, a.x));
				}
			} else {
				comps.add(a);
			}

			all.add(a);
		}

		ArrayList<ArrayList<Comp>> bs = new ArrayList<>();

		for (int i = 0; i < starters.size(); i++) {
			ArrayList<Comp> t = new ArrayList<>();
			t.add(new Comp(starters.get(i)));
			bs.add(t);
		}

		boolean changeFlag = true;
		while (changeFlag) {
			// try {
			// Thread.sleep(1000);
			// } catch (InterruptedException e) {
			// // TODO Auto-generated catch block
			// e.printStackTrace();
			// }
			System.out.println(bs.size());
			changeFlag = false;
			sum = 0;
			for (int i = bs.size() - 1; i > -1; i--) {
				ArrayList<Comp> temp = bs.remove(i);
				// System.out.println(temp.toString());

					
					int localSum = 0;

					for (Comp c : temp) {
						localSum += c.y + c.x;
					}

					if (localSum > sum) {
						sum = localSum;
					}
				
				for (Comp o : comps) {
					Comp cold = temp.get(temp.size() - 1);
					if (cold.isPossibleConnector(o) && !temp.contains(o)) {

						ArrayList<Comp> gotem = new ArrayList<>(temp);

						if (cold.y == o.x) {
							gotem.add(new Comp(o));
						} else {
							gotem.add(new Comp(o.y, o.x));
						}

						bs.add(gotem);
						changeFlag = true;
					}
				}
			}
		}
		System.out.println("strength: " + sum);
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

	public Comp(Comp o) {
		this.x = o.x;
		this.y = o.y;
	}

	public void initSuccessors(ArrayList<Comp> comps) {
		ArrayList<Comp> temp = new ArrayList<>();
		this.successorsInitiated = true;

		for (Comp c : comps) {
			if (isPossibleConnector(c)) {
				String connector = getConnector(c);

				// temp.add(c);
				// if (!predecessors.contains(c))
				// c.predecessors.add(this);

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

	public boolean isPossibleConnector(Comp o) {
		if (o.equals(this))
			return false;

		return o.x == this.y || o.y == this.y;
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

		return (o.x == this.x && o.y == this.y) || (o.y == this.x && o.x == this.y);
	}

	public String toString() {
		return "[" + x + ", " + y + "]";
	}

	public String printSuccessors() {
		return Arrays.toString(successors);
	}
}
