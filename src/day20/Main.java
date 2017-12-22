package day20;

import java.util.ArrayList;
import java.util.Arrays;

import util.ReadInputHelper;

public class Main {
	public static void main(String[] args) {
		ArrayList<String> temp = new ReadInputHelper(20).getLines();
		Particle[] pas = new Particle[temp.size()];

		for (int i = 0; i < temp.size(); i++) {
			System.out.println(temp.get(i));
			int[] p;
			int[] v;
			int[] a;

			String[] arrays = temp.get(i).split(" ");

			p = getNumbers(arrays[0]);
			v = getNumbers(arrays[1]);
			a = getNumbers(arrays[2]);

			pas[i] = new Particle(i, p, v, a);
		}

		int j = 0;
		while (true) {
			System.out.println(Arrays.toString(pas));
			for (Particle p : pas) {
				for (Particle po : pas) {
					if (po.equals(p))
						continue;
					if(po.dead)
						continue;
					if (po.p[0] == p.p[0] && po.p[1] == p.p[1] && po.p[2] == p.p[2]) {
						po.dead = true;
						System.out.println("----------------DESTROYING: " + po.name + "----------------");
					}
				}
			}
			
			for (Particle pa : pas) {
				System.out.print(pa.name + "\t");
			}
			System.out.println();
			for (Particle pa : pas) {
				System.out.print(pa.getTCD() + "\t");
			}
			System.out.println();
			for (Particle pa : pas) {
				System.out.print(pa.dead + "\t");
			}
			System.out.println();
			
			ArrayList<Particle> remaining = new ArrayList<>();
			for (Particle p : pas) {
				if (!p.dead)
					remaining.add(p);
			}
			
			pas = remaining.toArray(new Particle[remaining.size()]);
			System.out.print(pas.length + ", ");
			System.out.println(Arrays.toString(pas));


			for (Particle pa : pas) {
				if (pa.canAdd)
					pa.update();
			}
			j++;
			

			System.out.println();
		}

	}

	static int[] getNumbers(String string) {
		String[] temp0 = string.split("<");
		String[] temp1 = temp0[1].split(">");
		String[] temp2 = temp1[0].split(",");

		int[] temp3 = new int[temp2.length];
		for (int i = 0; i < temp2.length; i++) {
			temp3[i] = Integer.parseInt(temp2[i]);
		}

		return temp3;
	}
}

class Particle {
	int[] p;
	int[] v;
	int[] a;
	int name;
	boolean canAdd = true;
	boolean dead = false;

	public Particle(int name, int[] p, int[] v, int[] a) {
		this.p = p;
		this.v = v;
		this.a = a;
		this.name = name;
	}

	void update() {
		if (!canAdd)
			return;

		for (int i = 0; i < v.length; i++) {
			if (!canAdd(v[i], a[i])) {
				canAdd = false;
			}
		}

		if (!canAdd)
			return;

		for (int i = 0; i < v.length; i++) {
			if (!canAdd(v[i], p[i])) {
				canAdd = false;
			}
		}

		if (!canAdd)
			return;

		for (int i = 0; i < v.length; i++) {
			v[i] += a[i];
		}

		for (int i = 0; i < v.length; i++) {
			p[i] += v[i];
		}

	}

	long getTCD() {
		long sum = 0;

		for (int i = 0; i < p.length; i++) {
			sum += (p[i]);
		}

		return sum;
	}

	public boolean canAdd(int me, int... args) {
		int total = me;
		for (int arg : args) {
			if (total >= 0) {
				if (java.lang.Integer.MAX_VALUE - total >= arg) { // since total
																	// is
																	// positive,
																	// (MaxValue
																	// - total)
																	// will
																	// never
																	// overflow
					total += arg;
				} else {
					return false;
				}
			} else {
				if (java.lang.Integer.MIN_VALUE - total <= arg) { // same logic
																	// as above
					total += arg;
				} else {
					return false;
				}
			}
		}
		return true;
	}

	@Override
	public String toString() {
		return "" + name;
	}
}
