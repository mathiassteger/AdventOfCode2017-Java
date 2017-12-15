package day15;


import org.apache.commons.lang3.StringUtils;

public class Main {
	public static void main(String[] args) {
		// Generator A starts with 679
		// Generator B starts with 771

		long previousA = 679, previousB = 771;
		// long previousA = 65, previousB = 8921;
		int factorA = 16807, factorB = 48271;
		int mod = 2147483647;
		int sum = 0;

		for (int i = 0; i < 5000000; i++) {
			previousA = (previousA * factorA) % mod;
			while (previousA % 4 != 0) {
				previousA = (previousA * factorA) % mod;
			}
			previousB = (previousB * factorB) % mod;

			while (previousB % 8 != 0) {
				previousB = (previousB * factorB) % mod;
			}

			// System.out.println(previousA);
			// System.out.println(previousB);
			String binNrA = Long.toBinaryString(previousA);
			String binNrB = Long.toBinaryString(previousB);

			String revA = StringUtils.reverse(binNrA);
			String revB = StringUtils.reverse(binNrB);

			// System.out.println(revA + ", " + revA.length());
			// System.out.println(revB+ ", " + revA.length());
			// System.out.println();

			boolean same = true;
			for (int j = 0; j < 16; j++) {
				if (revA.charAt(j) != revB.charAt(j)) {
					same = false;
					break;
				}
			}

			if (!same) {
				continue;
			}

			sum++;
		}

		System.out.println(sum);

	}
}
