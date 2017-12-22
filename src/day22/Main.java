package day22;

import java.io.IOException;
import java.util.ArrayList;

import util.ReadInputHelper;

public class Main {
	static int size;
	static int x, y;
	static Node[][] map;
	static int direction = 0; // NORTH
	static int speed;
	static int scope;

	public static void main(String[] args) {
		ArrayList<String> lines = new ArrayList<>();
		if (!(args.length < 1)) {
			speed = Integer.parseInt(args[0]);
			scope = Integer.parseInt(args[1]);

			lines = new ReadInputHelper(Integer.parseInt(args[2])).getLines();
		} else {
			speed = 1000;
			scope = 10;
			lines = new ReadInputHelper(0).getLines();
		}
		size = lines.size();
		x = size / 2;
		y = size / 2;
		map = new Node[size][size];

		int counter = 0;
		for (int i = 0; i < lines.size(); i++) {
			String line = lines.get(i);

			for (int j = 0; j < line.length(); j++) {
				if (line.charAt(j) == '#') {
					map[i][j] = new Node(Flag.INFECTED, j, i);
				} else {
					map[i][j] = new Node(Flag.CLEAN, j, i);
				}
			}

		}

		for (int i = 0; i < 10000000; i++) {
			if (y > size - 1 || y < 0 || x > size - 1 || x < 0) {
				int factor = 1;
				map = expandMatrixBy(map, factor);
				x = x + factor;
				y = y + factor;
				size = map.length;
			}

			if (args.length > 3) {
				if (args[3].equals("-relative"))
					printRelativeMap();
				else if (args[3].equals("-absolute"))
					printWholeMap();
			}
			System.out.println(i);

			Node node = map[y][x];
			if (node.flag == Flag.INFECTED) {
				direction = (direction + 1) % 4;
				node.flag = Flag.FLAGGED;
				move(1);
			} else if (node.flag == Flag.CLEAN) {
				direction = (direction + 3) % 4;
				node.flag = Flag.WEAK;
				move(1);
			} else if (node.flag == Flag.WEAK) {
				counter++;
				node.flag = Flag.INFECTED;
				move(1);
			} else {
				node.flag = Flag.CLEAN;
				direction = (direction + 2) % 4;
				move(1);
			}
		}

		System.out.println(counter);
	}

	static void move(int steps) {
		switch (direction) {
		case 0:
			y--;
			break;
		case 1:
			x++;
			break;
		case 2:
			y++;
			break;
		case 3:
			x--;
			break;
		}

	}

	static Node[][] expandMatrixBy(Node[][] matrix, int number) {
		int oldSize = matrix.length;
		int newSize = oldSize + 2 * number;
		Node[][] result = new Node[newSize][newSize];

		for (int i = 0; i < newSize; i++) {
			for (int j = 0; j < newSize; j++) {
				result[i][j] = new Node(Flag.CLEAN, j, i);
			}
		}

		for (int row = number; row < oldSize + number; row++) {
			for (int col = number; col < oldSize + number; col++) {
				Node node = matrix[row - number][col - number];
				node.x += number;
				node.y += number;
				result[row][col] = node;
			}
		}
		return result;
	}

	static void printRelativeMap() {
		try {
			Thread.sleep(speed);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		try {
			new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
		} catch (InterruptedException | IOException e) {
			e.printStackTrace();
		}

		int scope = Main.scope;
		int xmin = x - scope >= 0 ? x - scope : 0;
		int xmax = x + scope <= map.length ? x + scope : map.length;
		int ymin = y - scope >= 0 ? y - scope : 0;
		int ymax = y + scope <= map.length ? y + scope : map.length;

		for (int i = ymin; i < ymax; i++) {
			for (int j = xmin; j < xmax; j++) {
				System.out.print(map[i][j]);
			}
			System.out.println();
		}
	}

	static void printWholeMap() {
		try {
			Thread.sleep(speed);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		try {
			new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
		} catch (InterruptedException | IOException e) {
			e.printStackTrace();
		}

		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				System.out.print(map[i][j]);
			}
			System.out.println();
		}
	}
}

class Node {
	Flag flag = Flag.CLEAN;
	int x, y;

	public Node(Flag flag, int x, int y) {
		this.flag = flag;
		this.x = x;
		this.y = y;
	}

	@Override
	public String toString() {
		String output = "";

		if (Main.x == x && Main.y == y) {
			output += "[";
		} else {
			output += " ";
		}

		if (flag == Flag.INFECTED) {
			output += "#";
		} else if (flag == Flag.CLEAN) {
			output += ".";
		} else if (flag == Flag.WEAK) {
			output += "W";
		} else {
			output += "F";
		}

		if (Main.x == x && Main.y == y) {
			output += "]";
		} else {
			output += " ";
		}

		return output;
	}
}

enum Flag {
	CLEAN, WEAK, INFECTED, FLAGGED
}
