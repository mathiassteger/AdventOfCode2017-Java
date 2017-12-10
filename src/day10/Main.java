package day10;

import java.util.ArrayList;
import java.util.Arrays;

import org.apache.commons.lang3.ArrayUtils;

public class Main {
	public static void main(String[] args) {
		char[] input = "120,93,0,90,5,80,129,74,1,165,204,255,254,2,50,113".toCharArray();
		//char[] input = "1,2,3".toCharArray();
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

		// int[] lengths = {3, 4, 1, 5};
		//
		// int[] numbers = {0, 1, 2, 3, 4};

		for (int i = 0; i < numbers.length; i++) {
			numbers[i] = i;
		}

		int cIndex = 0;
		int skip = 0;
		for (int k = 0; k < 64; k++) {
			for (int i = 0; i < lengths.length; i++) {
				int length = lengths[i];
				System.out.println("numbers" + Arrays.toString(numbers));
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

				System.out.println("subs" + Arrays.toString(tempB));
				ArrayUtils.reverse(tempB);
				System.out.println("subs" + Arrays.toString(tempB));
				for (int j = 0; j < tempB.length; j++) {
					int current = (cIndex + j) % numbers.length;
					numbers[current] = tempB[j];
				}

				System.out.println("numbers" + Arrays.toString(numbers));

				cIndex = (cIndex + length + skip) % numbers.length;
				System.out.println("cIndex" + cIndex);
				skip++;
				System.out.println("skip" + skip);
				System.out.println();
				System.out.println();

			}
		}
		
		
		int[] temp = new int[16];
		
		for (int index = 0; index < 16; index++){
			int i = index *	16;
			temp[index] = numbers[i] ^ numbers[i+1] ^numbers[i+2] ^numbers[i+3] ^numbers[i+4] ^numbers[i+5] ^numbers[i+6] ^numbers[i+7] ^numbers[i+8] ^numbers[i+9] ^numbers[i+10] ^numbers[i+11] ^numbers[i+12] ^numbers[i+13] ^numbers[i+14] ^numbers[i+15];
		}
		
		String resulta = "";
		System.out.println(Arrays.toString(temp));
		
		for(int i =0; i < temp.length; i++){
			String temp2 = Integer.toHexString(temp[i]);
			if(temp2.length() < 2){
				temp2 = "0"+temp2;
			}
			
			resulta += temp2;
		}
		
		System.out.println(resulta);
	}
}
