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

	public static boolean isPermutation(String str1, String str2) {
		if (str1.length() != str2.length()) return false;
		List<Character> list = new ArrayList<Character>();

		for (char ch: str1.toCharArray()) { //store all the char in list
			list.add(ch);
		}

		for (char ch:str2.toCharArray()) {
			if (!list.contains(ch)) {
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

	public static double pow(double x, int n) {
		double result  = 1;	     
		for (int i = 0; i < Math.abs(n); i++) {
			result = result*x;
		}       
		if (n > 0) {
			return result;
		}else {
			return 1/result;
		} 
	}

	public static double powRecursive(double x, int n) {
		if (n < 0) return powRecursive(1/x, -n);
		if (n == 0) return 1.0;
		if (n%2 == 1) return x * powRecursive(x, n-1);
		return powRecursive(x*x, n/2);
	}

	public static int fib(int n) { //recursion
		if ( n == 0 ) return 0;
		if (n == 1) return 1;
		return fib(n-1) + fib(n-2);
	}

	public static int betterFibonacci(int n) {
		int n_2 = 0;
		int n_1 = 1;
		if (n == 0) return n_2;
		if (n == 1) return n_1;
		int output = 0;
		for (int i=2;i<=n;i++) {
			output = n_1 + n_2;
			n_2 = n_1;
			n_1 = output;
		}
		return output;
	}
}
