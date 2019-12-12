package algorithem;

import java.util.*;

public class Algorithem {

	public static boolean isStrPalindrom(String str) {
		if (str == null || str.equals("")) return true;

		for (int i = 0; i < str.length()/2; i++) {
			if (str.charAt(i) != str.charAt(str.length() - 1 - i)) {
				return false;
			}
		}
		return true;
	}

	//this method will fail if integer is repeated more than 2 times.
	public static String duplicate(int[] numbers){
		TreeSet<Integer> set = new TreeSet<>();
		Arrays.sort(numbers);

		for (int i = 1; i < numbers.length; i++) {
			if (numbers[i] == numbers[i - 1]) {
				set.add(numbers[i]);
			}
		}
		return set.toString();    
	}
	
	public static int singleNumber(int[] A) {
		return 0;
	}
	
	public static String computeBinary(int val) {
	    if (val == 0) return "0";
	    String binary = "";
	    while (val > 0) {
	        int remainder = val % 2;
	        val = val/2;
	        binary = remainder + binary;	        
	    }	        
	    return binary;
	}
	
	public static int reverseInt(int x) {
		return 0;
	}
	
	public static int findMissingNumber(int[] arr) { // where are is 1 to 10
	    int sum = 0;
	  for (int i = 0; i<arr.length; i++) {
	      sum += arr[i];
	  }
	  return 55 - sum;
	}
	
}
