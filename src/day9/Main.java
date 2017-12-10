package day9;

import java.util.ArrayList;

import util.ReadInputHelper;

public class Main {
	public static void main(String[] args) {
		ArrayList<String> lines = new ReadInputHelper(9).getLines();
		String line = lines.get(0);
		//String line = "<{obi!a,<{i<a>";
		char[] temp = line.toCharArray();
		// System.out.println(line);

		for (int i = 0; i < temp.length; i++) {
			if (temp[i] == '!') {
				temp[i] = ' ';
				temp[i + 1] = ' ';
			}
		}

		line = new String(temp);
		line = line.replace(" ", "");

		System.out.println(line);

		temp = line.toCharArray();

		boolean garbage = false;
		
		int garbageCounter = 0;
		
		for (int i = 0; i < temp.length; i++) {
			char c = temp[i];
			if (c == '<') {
				if(!garbage){
					garbageCounter--;
				}
				garbage = true;
				temp[i] = ' ';
				garbageCounter++;
				continue;
			}

			if (garbage) {
				temp[i] = ' ';
				garbageCounter++;
			}
			
			if (c == '>'){
				garbage = false;
				garbageCounter--;
			}
		}
		
		line = new String(temp);
		line = line.replace(" ", "");

		System.out.println(line);
		
		int oC = 0;
		int cC = 0;

		for (int i = 0; i < line.length(); i++) {
			char c = line.charAt(i);
			if (c == '{')
				oC++;
		}

		for (int i = 0; i < line.length(); i++) {
			char c = line.charAt(i);
			if (c == '}')
				cC++;
		}

		System.out.println(oC);
		System.out.println(cC);

		int counter = 0;
		int level = 0;
		for (int i = 0; i < line.length(); i++) {
			char c = line.charAt(i);

			if (c == '{') {
				level++;
				counter += level;
			}

			if (c == '}') {
				if (level > 0)
					level--;
			}
		}
		System.out.println(line);
		System.out.println(counter);
		System.out.println(garbageCounter);
	}
}
