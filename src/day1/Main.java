package day1;

import util.ReadInputHelper;

public class Main {
	public static void main(String[] args) {
		new ReadInputHelper(1).getLines().forEach(line -> {
			int sum = 0;
			char[] temp = line.toCharArray();
			for (int i = 0; i < temp.length; i++) {
				int current = (Integer.parseInt("" + temp[i]));
				sum += (Integer.parseInt(""+temp[(i + temp.length/2) % temp.length])) == current ? current : 0;
			}
			System.out.println(sum);
		});
	}
}