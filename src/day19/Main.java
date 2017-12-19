package day19;

import java.util.ArrayList;

import util.ReadInputHelper;

public class Main {
	public static void main(String[] args) {
		int x = 0, y = 0;
		int dir = 2; // 0 NORDEN
		int steps = 0;
		
		String rslt = "";
		ArrayList<String> lines = new ReadInputHelper(19).getLines();
		char[][] maze = new char[lines.size()][lines.get(0).length()];
		for (int i = 0; i < lines.size(); i++) {
			for (int j = 0; j < lines.get(i).length(); j++) {
				maze[i][j] = (lines.get(i).charAt(j));
			}
		}

		for (int i = 0; i < maze[0].length; i++) {
			if (maze[0][i] == '|') {
				x = i;
				y = 0;
			}
		}

		while (maze[y][x] != 'K') {
//			try {
//				Thread.sleep(500);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
			System.out.println(x + ", " + y + ", " + dir + ", " + rslt);
			if (dir == 0) {
				if (y - 1 > 0 && maze[y - 1][x] != ' ') {
					y--;
					steps++;
					if ((int) maze[y][x] > 64 && (int) maze[y][x] < 91) {
						rslt += maze[y][x];
					}
				} else {
					if (x - 1 > 0 && maze[y][x - 1] != ' ') {
						steps++;
						dir = 3;
						x--;
						if ((int) maze[y][x] > 64 && (int) maze[y][x] < 91) {
							rslt += maze[y][x];
						}
					} else if (x + 1 < maze[y].length && maze[y][x + 1] != ' ') {
						steps++;
						dir = 1;
						x++;
						if ((int) maze[y][x] > 64 && (int) maze[y][x] < 91) {
							rslt += maze[y][x];
						}
					}
				}
			} else if (dir == 1) {
				if (x + 1 < maze[y].length && maze[y][x + 1] != ' ') {
					x++;
					steps++;
					if ((int) maze[y][x] > 64 && (int) maze[y][x] < 91) {
						rslt += maze[y][x];
					}
				} else {
					if (y - 1 > 0 && maze[y - 1][x] != ' ') {
						dir = 0;
						steps++;
						y--;
						if ((int) maze[y][x] > 64 && (int) maze[y][x] < 91) {
							rslt += maze[y][x];
						}
					} else if (y + 1 < maze.length && maze[y + 1][x] != ' ') {
						dir = 2;
						steps++;
						y++;
						if ((int) maze[y][x] > 64 && (int) maze[y][x] < 91) {
							rslt += maze[y][x];
						}
					}
				}
			} else if (dir == 2) {
				if (y + 1 < maze.length && maze[y + 1][x] != ' ') {
					y++;
					steps++;
					if ((int) maze[y][x] > 64 && (int) maze[y][x] < 91) {
						rslt += maze[y][x];
					}
				} else {
					if (x - 1 > 0 && maze[y][x - 1] != ' ') {
						dir = 3;
						x--;
						steps++;
						if ((int) maze[y][x] > 64 && (int) maze[y][x] < 91) {
							rslt += maze[y][x];
						}
					} else if (x + 1 < maze[y].length && maze[y][x + 1] != ' ') {
						dir = 1;
						x++;
						steps++;
						if ((int) maze[y][x] > 64 && (int) maze[y][x] < 91) {
							rslt += maze[y][x];
						}
					}
				}
			} else if (dir == 3) {
				if (x - 1 > 0 && maze[y][x - 1] != ' ') {
					x--;
					steps++;
					if ((int) maze[y][x] > 64 && (int) maze[y][x] < 91) {
						rslt += maze[y][x];
					}
				} else {
					if (y - 1 > 0 && maze[y - 1][x] != ' ') {
						dir = 0;
						y--;
						steps++;
						if ((int) maze[y][x] > 64 && (int) maze[y][x] < 91) {
							rslt += maze[y][x];
						}
					} else if (y + 1 < maze.length && maze[y + 1][x] != ' ') {
						dir = 2;
						y++;
						steps++;
						if ((int) maze[y][x] > 64 && (int) maze[y][x] < 91) {
							rslt += maze[y][x];
						}
					}
				}
			}
		}

		System.out.println(rslt);
		System.out.println(steps+1);
	}
}
