package day12;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import util.ReadInputHelper;

public class Main {
	public static void main(String[] args) {
		ArrayList<String> lines = new ReadInputHelper(12).getLines();

		HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();

		for (int i = 0; i < lines.size(); i++) {
			String[] parts = lines.get(i).split(" <-> ");
			Integer id = Integer.parseInt(parts[0]);
			String[] fStrings = parts[1].split(", ");
			ArrayList<Integer> followers = new ArrayList<>();

			for (int j = 0; j < fStrings.length; j++) {
				followers.add(Integer.parseInt(fStrings[j]));
			}
			map.put(i, followers);
		}
		int counter = 0;
		ArrayList<HashSet<Integer>> groups = new ArrayList<>();

		for (int j = 0; j < map.size(); j++) {
			ArrayList<Integer> stack = new ArrayList<>();

			stack.add(j);
			HashSet<Integer> used = new HashSet<>();
			while (!stack.isEmpty()) {

				ArrayList<Integer> fs = map.get(stack.remove(0));

				if (fs.size() > 0 && fs != null)
					for (int i = 0; i < fs.size(); i++) {
						if (!used.contains(fs.get(i))) {
							stack.add(fs.get(i));
						}

						used.add(fs.get(i));
					}

//				if (stack.contains(0)) {
//					counter++;
//					break;
//				}
			}

			groups.add(used);
		}
		
		groups.forEach(e -> {
			System.out.println(e.toString());
		});
		
		System.out.println();
		System.out.println();
		
		ArrayList<HashSet<Integer>> rslt = new ArrayList<>();

		for (int i = 0; i < groups.size(); i++) {
			HashSet<Integer> starter = groups.get(i);
			for (int j = 0; j < groups.size(); j++) {
				if (groups.get(j) != starter)
					if (groups.get(j).containsAll(starter))
						starter.clear();
			}
		}

		for (int i = 0; i < groups.size(); i++) {
			if(groups.get(i).size() > 0){
				rslt.add(groups.get(i));
			}
		}
		
		rslt.forEach(e -> {
			System.out.println(e.toString());
		});

		System.out.println(counter);
		System.out.println(rslt.size());
	}

}
