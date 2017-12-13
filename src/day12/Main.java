package day12;

import java.util.ArrayList;
import java.util.Arrays;

import util.ReadInputHelper;

public class Main {
	public static void main(String[] args) {
		ArrayList<String> lines = new ReadInputHelper(13).getLines();

		ArrayList<Wall> walls = new ArrayList<>();

		int max = 0;
		for (int i = 0; i < lines.size(); i++) {
			String[] line = lines.get(i).split(": ");
			int name = Integer.parseInt(line[0]);
			if (name > max)
				max = name;
			walls.add(new Wall(Integer.parseInt(line[0]), i, Integer.parseInt(line[1])));
		}

		int sum = 0;
		loop: while (true) {
			for (int i = 0; true; i++) {
				int time = sum + i;
				int index = walls.indexOf(new Wall(i, 0, -1));

				if (index == -1) {
					continue;
				}

				Wall wall = walls.get(index);

				if (wall.getSpos(time) == 0) {
					sum++;
					break;
				}

				if (wall.name >= max) {
					break loop;
				}
			}

		}
		System.out.println(sum);
	}
}

class Wall {
	public int name, depth, size, spos = 0;
	boolean reachedTop = false;
	int[] sps;
	int ceil;

	public Wall(int name, int depth, int size) {
		this.name = name;
		this.depth = depth;
		this.size = size;

		if (size != -1) {
			sps = new int[size + size - 2];
			initSPos();
		}
	}

	public void incScanner() {
		if (size - 1 == spos)
			reachedTop = true;
		if (spos == 0)
			reachedTop = false;

		if (!reachedTop)
			spos++;
		else
			spos--;

	}

	public boolean equals(Object o) {
		Wall other = (Wall) o;
		return name == other.name;
	}

	public void reset() {
		spos = 0;
	}

	public void initSPos() {
		int end1 = size-2;
		int counter = size-2;
		for (int i = 0; i < end1; i++){
			sps[i] = counter--;
		}
		
		counter = 0;
		for(int i = end1; i < sps.length;i++){
			sps[i] = counter++;
		}
		
		System.out.println(Arrays.toString(sps));
	}

	public int getSpos(int index) {
		if (size == 2)
			return index % 2;
		else
			return Math.abs(sps[(index + size-2) % sps.length]);
	}

}