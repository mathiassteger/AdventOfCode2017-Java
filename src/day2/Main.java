package day2;

import java.util.ArrayList;

import util.ReadInputHelper;

public class Main {
	public static void main(String[] args) {
		ArrayList<String> lines = new ReadInputHelper(2).getLines();
		int sum = 0;
		for (int i = 0; i < lines.size(); i++) {

			String line = lines.get(i);
			String[] numbers = line.split("\t");

			label: for (int j = 0; j < numbers.length; j++) {
				for (int k = 0; k < numbers.length; k++) {

					int number1 = Integer.parseInt(numbers[j]);
					int number2 = Integer.parseInt(numbers[k]);

					if (number1 == number2)
						continue;


					if (number1 % number2 == 0) {
						sum += number1 / number2;
						break label;
					} else if (number2 % number1 == 0) {
						sum += number2 / number1;
						break label;
					}
				}
			}
		}
		System.out.println(sum);
	}

	public static int getAdd(String[] numbers) {
		int smallest = 0;
		int biggest = 0;

		smallest = Integer.parseInt(numbers[0]);
		biggest = Integer.parseInt(numbers[0]);

		for (String number : numbers) {
			int n = Integer.parseInt(number);

			if (n > biggest) {
				biggest = n;
				continue;
			}

			if (n < smallest) {
				smallest = n;
				continue;
			}

		}
		return Math.abs(smallest - biggest);
	}
}
