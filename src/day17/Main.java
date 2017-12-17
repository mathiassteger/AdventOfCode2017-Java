package day17;

import java.util.Arrays;

import org.apache.commons.lang3.ArrayUtils;


public class Main {
	public static void main(String[] args) {
		int input = 377;
		int pos = 0;
		int[] list = {0};

		
		for (int i = 1; i < 5000000; i++) {
			pos = ((pos + input) % list.length)+1;
			
			list = ArrayUtils.insert(pos, list, i);
		}

		System.out.println(Arrays.toString(list));

		System.out.println(list[ArrayUtils.indexOf(list, 0)+1]);
	}
}
