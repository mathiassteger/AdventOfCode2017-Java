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
			System.out.print(part + " ");
			System.out.println(move + arg1 + "/" + arg2);

			moves.add(new Move(move, arg1, arg2));
		}

		char[] ps = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p' };

		for (int j = 0; j < 1000000000; j++) {
			for (Move move : moves) {
				switch (move.move) {
				case "s":
					for (int i = 0; i < Integer.parseInt(move.arg1); i++) {
						char last = ps[ps.length - 1];
						System.arraycopy(ps, 0, ps, 1, ps.length - 1);
						ps[0] = last;
					}
					break;
				case "x":
					char c1 = ps[Integer.parseInt(move.arg1)];
					char c2 = ps[Integer.parseInt(move.arg2)];

					ps[Integer.parseInt(move.arg1)] = c2;
					ps[Integer.parseInt(move.arg2)] = c1;
					break;
				case "p":
					char c3 = move.arg1.charAt(0);
					char c4 = move.arg2.charAt(0);

					int pos1 = ArrayUtils.indexOf(ps, c3);
					int pos2 = ArrayUtils.indexOf(ps, c4);

					ps[pos1] = c4;
					ps[pos2] = c3;
					break;
				}

			}
		}

		System.out.println(new String(ps));

	}
}

class Move {
	public String move;
	public String arg1;
	public String arg2;

	public Move(String move, String arg1, String arg2) {
		this.move = move;
		this.arg1 = arg1;
		this.arg2 = arg2;
	}
}
