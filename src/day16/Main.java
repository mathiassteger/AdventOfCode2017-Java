package day16;

import java.util.ArrayList;

import org.apache.commons.lang3.ArrayUtils;

import util.ReadInputHelper;

public class Main {
	public static void main(String[] args) {
		ArrayList<String> lines = new ReadInputHelper(16).getLines();
		String[] parts = lines.get(0).split(",");
		ArrayList<Move> moves = new ArrayList<>();

		for (int i = 0; i < parts.length; i++) {
			String part = parts[i];

			String[] parts2 = part.split("/");

			String move = "" + parts2[0].charAt(0);

			String arg1 = parts2[0].substring(1, parts2[0].length());
			String arg2 = "";
			if (part.length() > 3) {
				arg2 = parts2[1];
			}

			moves.add(new Move(move, arg1, arg2));
		}

		char[] ps = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p' };

		char c3, c4;
		int pos1, pos2;
		for (int j = 0; j < 1000000000; j++) {
			for (Move move : moves) {
				switch (move.move) {
				case "s":
					ArrayUtils.shift(ps, move.start);
					break;
				case "x":
					ArrayUtils.swap(ps, move.pos1, move.pos2);
					break;
				case "p":
					c3 = move.arg1;
					c4 = move.arg2;
					
					pos1 = ArrayUtils.indexOf(ps, c3);
					pos2 = ArrayUtils.indexOf(ps, c4);

					ps[pos1] = c4;
					ps[pos2] = c3;
					break;
				}

			}

			if (j % 1000000 == 0)
				System.out.println(j);
		}

		System.out.println(new String(ps));

	}
}

class Move {
	public String move;
	public int pos1, pos2;
	public int start;
	public char arg1 = ' ', arg2 = ' ';

	public Move(String move, String arg1, String arg2) {
		this.move = move;
		switch (this.move) {
		case "s":
			start = Integer.parseInt(arg1);
			break;
		case "x":
			pos1 = Integer.parseInt(arg1);
			pos2 = Integer.parseInt(arg2);
			break;
		case "p":
			this.arg1 = arg1.charAt(0);
			this.arg2 = arg2.charAt(0);
			break;
		}
	}
}
