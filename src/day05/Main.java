package day05;

import java.util.ArrayList;
import util.ReadInputHelper;

public class Main {
	public static void main(String[] args) {
		ArrayList<String> lines = new ReadInputHelper(5).getLines();
		int[] maze = new int[lines.size()];
		int position = 0;
		int counter = 0;

		for (int i = 0; i < lines.size(); i++) {
			String line = lines.get(i);

			maze[i] = Integer.parseInt(line);
		}

		while (position >= 0 && position < maze.length) {
			int newPos = maze[position];
			if (maze[position] >= 3)
				maze[position]--;
			else
				maze[position]++;
			position += newPos;
			counter++;
		}

		System.out.println(counter);
	}
}
