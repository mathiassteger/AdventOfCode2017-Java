package day21;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import org.apache.commons.lang3.StringUtils;

import util.ReadInputHelper;

public class Main {
	static HashMap<String, char[][]> convert = new HashMap<>();

	public static void main(String[] args) {
		ArrayList<String> temp = new ReadInputHelper(21).getLines();
		String start = ".#./..#/###";
		for (int i = 0; i < temp.size(); i++) {
			String[] temp2 = temp.get(i).split(" => ");

			char[][] to = stoc(temp2[1]);

			convert.put(temp2[0], to);
		}

		for (int i = 0; i < 18; i++) {
			String[] parts = start.split("/");
			if (parts.length % 2 == 0) {
				String[] resultA = new String[(parts.length / 2) * 3];

				for (int j = 0; j < resultA.length; j++) {
					resultA[j] = "";
				}

				for (int j = 0; j < parts.length; j += 2) {
					for (int k = 0; k < parts.length; k += 2) {
						String subA = sub(k, j, parts, 2);

						subA = getRotation(subA);

						char[][] subC = convert.get(subA);

						if (subC == null) {
							return;
						}

						for (int l = 0; l < subC.length; l++) {
							resultA[j + (l + (j / 2))] += new String(subC[l]);
						}
					}

				}

				char[][] resultC = new char[(parts.length / 2) * 3][(parts.length / 2) * 3];

				for (int j = 0; j < resultA.length; j++) {
					String s = resultA[j];
					resultC[j] = s.toCharArray();
				}

				start = ctos(resultC);
			} else {
				String[] resultA = new String[(parts.length / 3) * 4];

				for (int j = 0; j < resultA.length; j++) {
					resultA[j] = "";
				}

				for (int j = 0; j < parts.length; j += 3) {
					for (int k = 0; k < parts.length; k += 3) {
						String subA = sub(k, j, parts, 3);

						subA = getRotation(subA);

						char[][] subC = convert.get(subA);

						if (subC == null) {
							return;
						}

						for (int l = 0; l < subC.length; l++) {
							resultA[j + (l + (j / 3))] += new String(subC[l]);
						}
					}

				}

				char[][] resultC = new char[resultA.length][resultA.length];

				for (int j = 0; j < resultA.length; j++) {
					String s = resultA[j];
					resultC[j] = s.toCharArray();
				}

				start = ctos(resultC);

			}
		}

		System.out.println(StringUtils.countMatches(start, '#'));
	}

	static String getRotation(String subA) {

		for (int l = 0; l < 4 && !convert.containsKey(subA); l++) {
			char[][] subC = stoc(subA);
			spin(subC);
			subA = ctos(subC);
		}
		if (convert.containsKey(subA))
			return subA;

		if (!convert.containsKey(subA)) {
			char[][] subC = stoc(subA);
			subC = mirrorHorizontally(subC);
			subA = ctos(subC);
		}

		if (convert.containsKey(subA))
			return subA;
		if (!convert.containsKey(subA)) {
			char[][] subC = stoc(subA);
			subC = mirrorHorizontally(subC);
			subC = mirrorVertically(subC);
			subA = ctos(subC);
		}

		if (convert.containsKey(subA))
			return subA;
		if (!convert.containsKey(subA)) {
			char[][] subC = stoc(subA);
			subC = mirrorVertically(subC);
			subC = transposeMatrix(subC);
			subA = ctos(subC);
		}

		if (convert.containsKey(subA))
			return subA;

		if (!convert.containsKey(subA)) {
			char[][] subC = stoc(subA);
			subC = transposeMatrix(subC);
			subC = transposeMatrix2(subC);
			subA = ctos(subC);
		}

		if (convert.containsKey(subA))
			return subA;

		return "";
	}

	static void printM(String s) {
		String[] temp = s.split("/");
		System.out.println();

		for (String t : temp) {
			System.out.println(t);
		}

		System.out.println();
	}

	static String sub(int x, int y, String[] parts, int size) {
		String result = "";

		for (int i = 0; i < size; i++) {
			result += parts[y + (1 * i)].substring(x, x + size) + "/";
		}

		return result.substring(0, result.length() - 1);
	}

	static String ctos(char[][] c) {
		String rslt = "";
		for (char[] cs : c) {
			rslt += new String(cs) + "/";
		}
		return rslt.substring(0, rslt.length() - 1);
	}

	static char[][] stoc(String s) {
		String[] fromParts = s.split("/");
		char[][] from = new char[fromParts.length][fromParts[0].length()];

		for (int j = 0; j < fromParts.length; j++) {
			String f = fromParts[j];
			for (int k = 0; k < f.length(); k++) {
				from[j][k] = f.charAt(k);
			}
		}

		return from;
	}

	static void spin(char[][] a) {
		int n = a.length;
		for (int i = 0; i < n / 2; i++) {
			for (int j = i; j < n - i - 1; j++) {
				String tmp = "" + a[i][j];
				a[i][j] = a[j][n - i - 1];
				a[j][n - i - 1] = a[n - i - 1][n - j - 1];
				a[n - i - 1][n - j - 1] = a[n - j - 1][i];
				a[n - j - 1][i] = tmp.charAt(0);
			}
		}
	}

	static char[][] mirrorHorizontally(char[][] cs) {
		int width = cs.length, height = cs.length;
		char[][] copy = new char[width][height];

		int index = 0;
		for (int i = cs.length - 1; i >= 0; i--) {
			copy[index] = cs[i];
			index++;
		}
		return copy;
	}

	static char[][] mirrorVertically(char[][] cs) {
		int width = cs.length;
		int height = cs.length;

		char[][] out = new char[height][width];
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				out[i][width - j - 1] = cs[i][j];
			}
		}
		return out;
	}

	public static char[][] transposeMatrix(char[][] m) {
		char[][] temp = new char[m[0].length][m.length];
		for (int i = 0; i < m.length; i++)
			for (int j = 0; j < m[0].length; j++)
				temp[j][i] = m[i][j];
		return temp;
	}

	public static char[][] transposeMatrix2(char[][] a) {
		int n = a.length;
		for (int i = 0; i < a.length; i++) {
			for (int j = 1; j < a[0].length; j++) {
				char k = a[n - j - 1][n - i - 1];

				a[n - j - 1][n - i - 1] = a[i][j];

				a[i][j] = k;

			}
		}
		return a;
	}
}
