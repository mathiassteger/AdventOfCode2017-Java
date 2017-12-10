package day03;

import java.util.ArrayList;
import java.util.Arrays;

import util.ReadInputHelper;

public class Main {
	public static int currentNumber = 1;
	public static int input = 361527;
	public static boolean bool = true;

	public static void main(String[] args) {
		City city = new City();

		int counter = 1;

		int lastH = 0;
		int lastV = 0;

		try {
			city.getClass().getDeclaredMethod("R", int.class, int.class, int.class).invoke(city, 1, currentNumber,
					input);
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		while (bool) {
			String methodName;
			int steps = counter;
			if (counter % 2 == 1) {
				if (lastH == 0) {
					methodName = "L";
					lastH = 1;
					++counter;
				} else {
					methodName = "L";
					lastH = 0;
				}

			} else {
				if (lastV == 0) {
					methodName = "L";
					lastV = 1;
				} else {
					methodName = "L";
					lastV = 0;
					++counter;
				}
			}

			try {
				city.getClass().getDeclaredMethod(methodName, int.class, int.class, int.class).invoke(city, steps,
						currentNumber, input);
			} catch (Exception e1) {
				e1.printStackTrace();
			}

		}
	}
}

class City {
	int size = 1000;
	int[][] matrix = new int[size][size];
	int offset = size / 2;
	public int x = 0, y = 0;
	int counter = 1;

	public City() {
		matrix[x + offset][y + offset] = 1;
	}

	int direction = 0;

	public void R(int steps, int currentNumber, int input) {
		direction = (direction + 1) % 4;
		move(steps, currentNumber, input);
	}

	public void L(int steps, int currentNumber, int input) {
		direction = ((((direction - 1) % 4) + 4) % 4);
		move(steps, currentNumber, input);
	}

	private void move(int steps, int temp, int input) {
		switch (direction) {
		case 0: // North
			while (steps > 0) {
				y -= 1;
				steps--;
				// addNeighbours(x,y);
				add1(x, y);

			}
			break;
		case 1: // East
			while (steps > 0) {
				x += 1;
				steps--;
				// addNeighbours(x,y);
				add1(x, y);
			}
			break;
		case 2: // South
			while (steps > 0) {
				y += 1;
				// addNeighbours(x,y);
				add1(x,y);
				steps--;
			}
			break;
		case 3: // West
			while (steps > 0) {
				x -= 1;
				steps--;
				// addNeighbours(x,y);
				add1(x,y);
			}
			break;
		}
	}

	void addNeighbours(int posX, int posY) {
		int x = posX + offset, y = posY + offset;

		int a1 = matrix[y + 1][x];
		int a2 = matrix[y + 1][x + 1];
		int a3 = matrix[y][x + 1];
		int a4 = matrix[y - 1][x + 1];
		int a5 = matrix[y - 1][x];
		int a6 = matrix[y - 1][x - 1];
		int a7 = matrix[y][x - 1];
		int a8 = matrix[y + 1][x - 1];

		matrix[y][x] = a1 + a2 + a3 + a4 + a5 + a6 + a7 + a8;
		if (matrix[y][x] >= Main.input) {
			System.out.println(this.x + ", " + this.y);
			System.out.println(Math.abs(this.x) + Math.abs(this.y));

			for (int i = 0; i < matrix.length; i++) {
				for (int j = 0; j < matrix[0].length; j++) {
					System.out.print(matrix[i][j] + "\t");
				}
				System.out.println("\n");
			}
			Main.bool = false;
		}
	}

	void add1(int posX, int posY) {
		counter++;

		int x = posX + offset, y = posY + offset;
		matrix[y][x] = counter;
		if (matrix[y][x] == Main.input) {
			System.out.println(this.x + ", " + this.y);
			System.out.println(Math.abs(this.x) + Math.abs(this.y));

			Main.bool = false;
		}
	}
}