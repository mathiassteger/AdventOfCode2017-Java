package day14;

import java.util.ArrayList;
import java.util.Arrays;

import org.apache.commons.lang3.ArrayUtils;

public class Main {
	public static void main(String[] args) {
		String input = "amgozmfv";
		//String input = "flqrgnkx";
		KnotHash knot = new KnotHash();
		Fragment[][] grid = new Fragment[128][128];
		int[][] check = new int[128][128];
		
		ArrayList<String> bin = new ArrayList<>();

		for (int i = 0; i < 128; i++) {
			String rslt = "";
			String c = input + "-" + i;
			String hash = knot.hash(c);
			for (char ch : hash.toCharArray()) {
				int k = Integer.parseInt("" + ch, 16);
				String binNr = Integer.toBinaryString(k);

				String temp = "" + binNr;
				while (temp.length() < 4) {
					temp = "0" + temp;
				}

				rslt += temp;
			}
			bin.add(rslt);
		}

		// int sum = 0;
		// for (String s : bin) {
		// sum += StringUtils.countMatches(s, "1");
		// }
		// System.out.println(sum);
		
		for (int i = 0; i < 128; i++) {
			String temp = bin.get(i);
			for (int j = 0; j < 128; j++) {
				grid[i][j] = new Fragment(Integer.parseInt("" + temp.charAt(j)));
				check[i][j] = Integer.parseInt("" + temp.charAt(j));
			}
		}
		
		for(int[] i : check){
			System.out.println(Arrays.toString(i));
		}

		for (int i = 0; i < 128; i++) {
			for (int j = 0; j < 128; j++) {
				Fragment temp = grid[i][j];

				if (i < grid.length - 1) {
					Fragment temp1 = grid[i + 1][j];
					if (!temp.Neighbours.contains(temp1)) {
						temp.Neighbours.add(temp1);
					}
				}
				if (i > 0) {
					Fragment temp2 = grid[i - 1][j];
					if (!temp.Neighbours.contains(temp2)) {
						temp.Neighbours.add(temp2);
					}
				}

				if (j > 0) {
					Fragment temp3 = grid[i][j - 1];
					if (!temp.Neighbours.contains(temp3)) {
						temp.Neighbours.add(temp3);
					}
				}
				if (j < 127) {
					Fragment temp4 = grid[i][j + 1];
					if (!temp.Neighbours.contains(temp4)) {
						temp.Neighbours.add(temp4);
					}
				}
			}
		}

		int group = 1;
		for (Fragment[] fs : grid) {
			for (Fragment f : fs) {
				if (f.group > 0 || f.used < 1) {
					continue;
				} else {
					f.setGroup(group);
					group++;
				}
			}
		}

		for (Fragment[] fs : grid) {
			for (Fragment f : fs) {
				if (f.used == 1)
					System.out.print("#");
				else
					System.out.print(".");
			}
			System.out.println();
		}

		for (Fragment[] fs : grid) {
			for (Fragment f : fs) {
				if (f.group > 0) {
					System.out.print(f.group + "\t");
				} else {
					System.out.print(".\t");
				}
			}
			System.out.println();
		}
		System.out.println(group-1);
	}
}

class Fragment {
	public ArrayList<Fragment> Neighbours = new ArrayList<Fragment>();
	public int used;
	public int group = -1;

	public Fragment(int used) {
		this.used = used;
	}

	public void setGroup(int group) {
		if (this.group > 0 || used < 1) {
			return;
		}

		this.group = group;
		for (Fragment neighbour : Neighbours) {
			neighbour.setGroup(group);
		}
	}
}

class KnotHash {
	public String hash(String string) {
		char[] input = string.toCharArray();
		int[] lengths = new int[input.length + 5];
		int[] numbers = new int[256];
		int[] suffix = { 17, 31, 73, 47, 23 };
		for (int i = 0; i < input.length; i++) {
			lengths[i] = (int) input[i];
		}

		for (int i = 0; i < 5; i++) {
			int index = input.length + i;
			lengths[index] = suffix[i];
		}

		for (int i = 0; i < numbers.length; i++) {
			numbers[i] = i;
		}

		int cIndex = 0;
		int skip = 0;
		for (int k = 0; k < 64; k++) {
			for (int i = 0; i < lengths.length; i++) {
				int length = lengths[i];
				ArrayList<Integer> temp = new ArrayList<>();

				for (int j = 0; j < length; j++) {
					int current = (cIndex + j) % numbers.length;

					temp.add(new Integer(numbers[current]));
				}

				Integer[] tempA = temp.toArray(new Integer[temp.size()]);
				int[] tempB = new int[tempA.length];
				for (int j = 0; j < length; j++) {
					tempB[j] = tempA[j].intValue();
				}

				ArrayUtils.reverse(tempB);
				for (int j = 0; j < tempB.length; j++) {
					int current = (cIndex + j) % numbers.length;
					numbers[current] = tempB[j];
				}

				cIndex = (cIndex + length + skip) % numbers.length;
				skip++;

			}
		}

		int[] temp = new int[16];

		for (int index = 0; index < 16; index++) {
			int i = index * 16;
			temp[index] = numbers[i] ^ numbers[i + 1] ^ numbers[i + 2] ^ numbers[i + 3] ^ numbers[i + 4]
					^ numbers[i + 5] ^ numbers[i + 6] ^ numbers[i + 7] ^ numbers[i + 8] ^ numbers[i + 9]
					^ numbers[i + 10] ^ numbers[i + 11] ^ numbers[i + 12] ^ numbers[i + 13] ^ numbers[i + 14]
					^ numbers[i + 15];
		}

		String resulta = "";

		for (int i = 0; i < temp.length; i++) {
			String temp2 = Integer.toHexString(temp[i]);
			if (temp2.length() < 2) {
				temp2 = "0" + temp2;
			}

			resulta += temp2;
		}

		return resulta;
	}
}
