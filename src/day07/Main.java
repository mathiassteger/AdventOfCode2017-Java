package day07;

import java.util.ArrayList;
import java.util.Collections;

import util.ReadInputHelper;

public class Main {
	public static void main(String[] args) {
		ArrayList<String> lines = new ReadInputHelper(6).getLines();

		ArrayList<Program> programs = new ArrayList<>();
		ArrayList<Program> allFollowers = new ArrayList<>();
		ArrayList<Program> programsWithFollowers = new ArrayList<>();
		ArrayList<Program> leafs = new ArrayList<>();

		for (int i = 0; i < lines.size(); i++) {
			// System.out.println(lines.get(i));
			String[] temp1 = lines.get(i).split(" -> ");
			String[] program_weight = temp1[0].split(" ");
			String name = getName(program_weight);
			int w = getWeight(program_weight);
			String[] obj_followers = getFollowers(temp1);

			programs.add(new Program(name, w, obj_followers));
		}

		for (int k = 0; k < programs.size(); k++) {
			Program e = programs.get(k);
			if (e.hasFollowers) {
				programsWithFollowers.add(e);
				String[] temp = e.followers;
				for (int j = 0; j < temp.length; j++) {
					int index = programs.indexOf(new Program(temp[j], 0, new String[0]));
					allFollowers.add(programs.get(index));
					e.followers_obj.add(programs.get(index));
					programs.get(index).parent = e;
				}
			} else {
				leafs.add(e);
			}
		}

		for (int k = 0; k < programs.size(); k++) {
			Program e = programs.get(k);
			if (e.hasFollowers) {
				for (int l = 0; l < e.followers_obj.size(); l++) {
					e.follower_weights[l] = e.followers_obj.get(l).weight;
				}
			}
		}

		ArrayList<Program> union = new ArrayList<>();
		ArrayList<Program> results = new ArrayList<>();
		union.addAll(allFollowers);
		union.addAll(programsWithFollowers);

		union.forEach(p -> {
			int temp = Collections.frequency(union, p);
			if (temp == 1 && p.hasFollowers) {
				results.add(p);
			}
		});

		Program base = results.get(0);

		

		base.setLayer(-1);
		base.setParentWeight();

		int min = 0;
		int j = min;
		
		while (true) {
			ArrayList<Program> temp = new ArrayList<>();
				
			for (int l = 0; l < programs.size(); l++) {
				Program p = programs.get(l);
				if (p.layer == j) {
					temp.add(p);
				}
			}

			if (temp.size() < 1) {
				break;
			}
			
			Collections.sort(temp);
			
			int start = temp.get(0).totalWeight;
			for (int k = 0; k < temp.size(); k++) {
				if (!(start == temp.get(k).totalWeight)) {
					System.out.print("TotalWeight:\t");
					temp.forEach(integer -> {
						System.out.print(integer.totalWeight + integer.weight + "\t");
					});
					System.out.println();
					System.out.print("Name:\t\t");
					temp.forEach(integer -> {
						System.out.print(integer.name + "\t");
					});
					System.out.println();
					System.out.print("Layer:\t\t");
					temp.forEach(integer -> {
						System.out.print(integer.layer + "\t");
					});
					
					System.out.println();
					System.out.print("Parent:\t\t");
					temp.forEach(integer -> {
						System.out.print(integer.parent.name + "\t");
					});
					System.out.println();
					System.out.println();
					break;
				}
			}
			j++;
		}
		
		int resultIndex = programs.indexOf(new Program("drjmjug", 0, new String[0]));
		
		Program result = programs.get(resultIndex);
		System.out.print("The problem is: ");
		System.out.print(result.name + ", ");
		System.out.println("Weight: " + result.weight);
	}

	static String getName(String[] array) {
		return array[0].split(" ")[0].trim();
	}

	static int getWeight(String[] program_weight) {
		program_weight[1] = program_weight[1].replace('(', ' ');
		return Integer.parseInt(program_weight[1].replace(')', ' ').trim());
	}

	static String[] getFollowers(String[] array) {
		String[] tempo = {};
		if (array.length > 1) {
			tempo = array[1].split(" ");
			for (int k = 0; k < tempo.length; k++) {
				String temp = tempo[k];
				temp = temp.replace(',', ' ');
				tempo[k] = temp.trim();
			}
		}
		return tempo;
	}

}

class Program implements Comparable<Program>{
	public String name;
	public int weight;
	public String[] followers;
	public ArrayList<Program> followers_obj = new ArrayList<>();
	public int[] follower_weights;
	public boolean hasFollowers;
	public int totalWeight = 0;
	public Program parent;
	public int layer = 0;

	public Program(String name, int weight, String[] followers) {
		this.name = name;
		this.weight = weight;
		this.followers = followers;
		follower_weights = new int[followers.length];
		this.hasFollowers = followers.length > 0;
	}

	boolean evenWeights() {
		if (hasFollowers) {
			int start = followers_obj.get(0).totalWeight;

			for (int i = 0; i < followers_obj.size(); i++) {
				Program p = followers_obj.get(i);
				if (p.totalWeight != start)
					return false;
			}
		}
		return true;
	}

	void setLayer(int layer) {
		this.layer = ++layer;

		if (!hasFollowers)
			return;

		for (int i = 0; i < followers_obj.size(); i++) {
			followers_obj.get(i).setLayer(this.layer);
		}
	}

	int setParentWeight() {
		if (hasFollowers) {
			followers_obj.forEach(e -> {
				totalWeight += e.setParentWeight();
			});
		} 
		
		return totalWeight + this.weight;
	}

	public boolean equals(Object other) {
		Program o = (Program) other;
		return this.name.equals(o.name);
	}
	
	@Override
	public int compareTo(Program p) {
	  return this.parent.name.compareTo(p.parent.name);
	}
}