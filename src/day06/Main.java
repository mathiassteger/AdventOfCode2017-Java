package day06;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {
	public static void main(String[] args) {
		char[] banks = { 0, 5, 10, 0, 11, 14, 13, 4, 11, 8, 8, 7, 1, 4, 12, 11 };
		ArrayList<String> results = new ArrayList<>();
		int steps = 0;
		int flagLimit = 1;
		int flags = 0;
		results.add(new String(banks));

		while (true) {
			int value = banks[0];
			int index = 0;

			for (int i = banks.length - 1; i >= 0; i--) {
				if (banks[i] >= value) {
					value = banks[i];
					index = i;
				}
			}

			banks[index] = 0;
			while (value > 0) {
				index = (index + 1) % banks.length;
				banks[index]++;

				value--;
			}

			if (results.contains(new String(banks))) {
				results.clear();
				results.add(new String(banks));
				flags++;
				if (flags > flagLimit) {
					break;
				}
				steps = 0;
			}
			results.add(new String(banks));
			steps++;
		}
		System.out.println(steps);
	}
}