package day11;

import java.util.ArrayList;
import java.util.Collections;

import util.ReadInputHelper;

public class Main {
	public static void main(String[] args) {
		ArrayList<String> lines = new ReadInputHelper(11).getLines();

		String line = lines.get(0);
		// String line = "ne,s";
		City city = new City();
		int rslt = 0;
		String[] ins = line.split(",");

		for (int i = 0; i < ins.length; i++) {

			try {
				city.getClass().getMethod(ins[i]).invoke(city);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}

			if (city.max() > rslt)
				rslt = city.max();
		}

		System.out.println(city.x + ", " + city.y + ", " + city.z);
		System.out.println(city.max());
		System.out.println(rslt);
	}
}

class City {
	public int x = 0, y = 0, z = 0;

	synchronized public void n() {
		y++;
		z--;
	}

	synchronized public void ne() {
		x--;
		y++;
	}

	synchronized public void se() {
		x--;
		z++;
	}

	synchronized public void s() {
		y--;
		z++;
	}

	synchronized public void sw() {
		x++;
		y--;
	}

	synchronized public void nw() {
		z--;
		x++;
	}

	synchronized public int max() {
		return (Math.abs(x) + Math.abs(y) + Math.abs(z))/2;
	}
}
