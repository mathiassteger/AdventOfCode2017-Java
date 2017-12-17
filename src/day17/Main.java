package day17;

import java.util.ArrayList;
import java.util.Arrays;

import org.apache.commons.lang3.ArrayUtils;

import util.ReadInputHelper;

public class Main {
	public static void main(String[] args) {
		int input = 377;
		int pos = 0;
		int[] list = {0};

		
		for (int i = 1; i < 5000000; i++) {
			pos = ((pos + input) % list.length)+1;
			
			list = ArrayUtils.insert(pos, list, i);
			
			if(i % 1000000 == 0)
				System.out.println(i);
			if (i < 5)
				System.out.println(Arrays.toString(list));
		}

		System.out.println(Arrays.toString(list));

		System.out.println(list[ArrayUtils.indexOf(list, 0)+1]);
	}
}
