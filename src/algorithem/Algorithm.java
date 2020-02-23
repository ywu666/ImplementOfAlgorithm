package algorithem;

import java.util.*;

public class Algorithm {	

	public static int gcd(int a, int b) { //time complexity = O(log(n)).
		int n = a, m = b; //n always be the larger number.
		if (a < b) {
			n = b;
			m = a;
		}
		
		while(m != 0) {
			int rem = n % m;
			n = m;
			m = rem;
		}
		return n;
	}
	
	//Calculate the sum of pow of all digits, it's a happy number if the final result is 1.
	public static boolean isHappyNumber(int num) {
		int[] digits = getDigits(num);
		int result;
		do {
			result = 0;
			for (int i = 0;i < digits.length;i++) {
				result += digits[i] * digits[i];
			}
			digits = getDigits(result);
			
		}while(result > 9);
		
		return (result == 1);
	}
	
	public static int[] getDigits(int num) {
		String str = String.valueOf(num);
		int[] digits = new int[str.length()];
		for (int i = 0;i < digits.length;i++) {
			digits[i] = num % 10;
			num = num / 10;
		}	
		return digits;
	}

	public static String computeBinary(int val) {
		if (val == 0) return "0";
		String binary = "";
		while (val > 0) {
			binary = val % 2 + binary;	
			val = val / 2;	        
		}	        
		return binary;
	}

	public static String computeBinaryRecursion(int val) { //Time complexity O(n).
		// Base case: value is less than 2
		if (val < 2)
			return Integer.toString(val);
		// Recursive case: binary rep = binary of the header + last digit (odd/even)
		else {
			return computeBinary(val / 2) + computeBinary(val % 2);
		}
	}
	
	public static int[] rotateLeft(int[] arr, int k) {
	    if (arr == null) return null;
	    int actualPos = k % arr.length;
	    reverse(arr,0,arr.length - 1);
	    reverse(arr,0,arr.length - actualPos - 1);
	    reverse(arr,arr.length - actualPos,arr.length - 1);
	    return arr;
	}
	
	public static int reverse(int x) {
		long reverse = 0;
		int max = 0x7fffffff, min = 0x80000000;
		while (x != 0) {
			reverse = reverse * 10 + x % 10;
			x = x / 10;
		}
		
		return (reverse > max || reverse < min) ? 0:(int)reverse;
	}

	public static Boolean isPalindrome(int x) { 
		if (x < 0) return false;
		int reverse = reverse(x);
		return (reverse == x);   
	}

	public static boolean isPowOfTwo(int num) { //Time/Space complexity = O(1)
		return (num & (num - 1)) == 0;		
	}

	private static double fastPow(double x, long n) {
        if (n == 0) {
            return 1.0;
        }
        double half = fastPow(x, n / 2);
        if (n % 2 == 0) {
            return half * half;
        } else {
            return half * half * x;
        }
    }
	
    public static double pow(double x, int n) { //Time complexity =  O(log(n)). 
    	//The previous algorithm will have bug forAlgorithm.pow(2.000,-2147483648). 
    	//the result is infinity rather than 0.
        long N = n;
        if (N < 0) {
            x = 1 / x;
            N = -N;
        }
        
        return fastPow(x, N);
    }

	public static int fib(int n) { //recursion Time complexity = O(2^n), Space complexity = O(1).
		if ( n == 0 ) return 0;
		if (n == 1) return 1;
		return fib(n - 1) + fib(n - 2);
	}

	public static int betterFibonacci(int n) { //Time complexity = O(n).
		int n_2 = 0;
		int n_1 = 1;
		if (n == 0) return n_2;
		if (n == 1) return n_1;
		for (int i = 2;i <= n;i++) {
	        int temp = n_1;
	        n_1 = n_1 + n_2;
	        n_2 = temp;
		}
		return n_1;
	}

	/**
	 * Given two input integers a and b. The method is to determine the number of bits required 
	 * to be swapped to convert a to b.
	 * @param a, b
	 */
	public static int bitSwapRequired(int a, int b) {
		int count = 0;
		for (int c = a^b;c != 0;c = c & (c - 1)) { // ^ = XOR gate 
			count++;
		}
		return count;
	}

	public static int swapOddEvenBit(int x) {
		int odd = (x & 0xaaaaaaaa) >> 1; // bitMask odd and shift right
		int even = (x & 0x5555555) << 1; //bisMask even and shift left
		return odd | even;
	}

	public static void reverse(int[] arr, int left, int right){
	    if (arr == null || arr.length == 1) return ;
	    while(left < right){
	        int temp = arr[left];
	        arr[left] = arr[right];
	        arr[right] = temp;
	        left++;
	        right--;
	    }
	}
	
	/**
	 * Given array [1,2,3,…,n]，then it has n! permutations
	 * Sort permutations and return the Kth permutation.
	 */
	public String getPermutation(int n, int k) {
		 /**
	        for n=4, k=15 find the permutation when k = 15 by using math
	        
	        1 + getPermutation(2,3,4) (3!)         
	        2 + getPermutation(1,3,4) (3!)         3, 1 + getPermutation(2,4)(2!)
	        3 + getPermutation(1,2,4) (3!)-------> 3, 2 + getPermutation(1,4)(2!)-------> 3, 2, 1 + getPermutation(4)(1!)-------> 3214
	        4 + getPermutation(1,2,3) (3!)         3, 4 + getPermutation(1,2)(2!)         3, 2, 4 + getPermutation(1)(1!)
	        
	        Assure the 1st bit:
	            k = 14(from 0 t count )
	            index = k / (n-1)! = 2, the 1st bit of the 15th permutation is 3 
	            //renew k
	            k = k - index*(n-1)! = 2
	       Assure the 2nd bit:
	            k = 2
	            index = k / (n-2)! = 1, the 2nd bit of the 15th permutation is 2
	            //renew k
	            k = k - index*(n-2)! = 0
	        Assure the 3rd bit:
	            k = 0
	            index = k / (n-3)! = 0, the 3rd bit of the 15th permutation is 1
	            //renew k
	            k = k - index*(n-3)! = 0
	        Assure the 4th bit:
	            k = 0
	            index = k / (n-4)! = 0, the 4th bit of the 15th permutation is 4
	        when n=4, the 15th permutation is 3214 
	        **/

		 StringBuilder sb = new StringBuilder();
		 List<Integer> candidates = new ArrayList<>();
		 int[] factorials = new int[n + 1];
		 factorials[0] = 1;
		 int fact = 1;
		 for(int i = 1;i <= n;++i) {
			 candidates.add(i);
			 fact *= i;
			 factorials[i] = fact;
		 }
		 k -= 1;
		 for(int i = n-1;i >= 0;--i) {

			 int index = k / factorials[i];
			 sb.append(candidates.remove(index));
			 k -= index * factorials[i];
		 }
		 return sb.toString();
    }
	 
	public static int findMissingNumber(int[] arr) { // where are is 1 to 10
		int sum = 0;
		for (int i:arr) {
			sum += i;
		}
		return 55 - sum;
	}
	
	//this method will fail if integer is repeated more than 2 times.
	public static String duplicate(int[] numbers){
		TreeSet<Integer> set = new TreeSet<>();
		Arrays.sort(numbers);

		for (int i = 1;i < numbers.length;i++) {
			if (numbers[i] == numbers[i - 1]) {
				set.add(numbers[i]);
			}
		}
		return set.toString();    
	}

	public static int singleNumber(int[] arr) { // return the first number that appears once
		Hashtable<Integer,Integer> table = new Hashtable<Integer,Integer>();
		for (int i:arr) {
			if(table.containsKey(i)) {
				table.put(i,table.get(i) + 1);
			}else {
				table.put(i,1);
			}
		}

		for (int i:arr) {
			if (table.get(i) == 1) {
				return i;
			}
		}
		return 0; // no single number
	}
	
	/*This method return the integer with the maximum number of repetitions.
	 * Time complexity is O(n), the space complexity is O(1). 
	 * The space Complexity for using Hashtable is O(n).
	 */
	public static int getMaxRepetition(int[] arr) {
		int max = arr.length; 
		// Iterate though input array, for every element a[i],
		// increment a[a[i]%k] by k
		for (int i = 0;i< arr.length;i++) {  
			arr[arr[i] % max] += max;
		}
		// Find index of the maximum repeating element
		int maxr = arr[0], result = 0;
		for (int i = 1;i < arr.length;i++) {
			if (arr[i] > maxr) {
				maxr = arr[i];
				result = i;
			}
		}
		return result;
	}
	
	/* Maximum Gain is defined as the maximum difference between 2 elements in a list 
	 * such that the larger element appears after the smaller element. 
	 * If no gain is possible, return 0.
	 */
	public static int maxGain(int[] arr) {
		int maxGain = 0;
		int min = arr[0];
		for (int i = 1;i < arr.length;i++) {
			min = Math.min(min,arr[i]);
			maxGain = Math.max(maxGain,arr[i] - min);
		}	
		return maxGain;
	}
	
	public static int minTriangleDepth(ArrayList<ArrayList<Integer>> input) {
		int height = input.size();
		int outsize = input.get(height - 1).size();
		int[] outBuffer = new int[outsize];

		for (int i = 0;i < outsize;i++) { //initialize the out buffer 
			outBuffer[i] = input.get(height - 1).get(i);
		}

		for (int r = height - 2;r >= 0;r--) {
			ArrayList<Integer> row = input.get(r);
			for (int i = 0;i < row.size();i++) {
				outBuffer[i] = row.get(i) + Math.min(outBuffer[i], outBuffer[i + 1]);
			}
		}
		return outBuffer[0];
	}

	/*
	 * Find 2 numbers index in the array such that they sum up to a specific Number.
	 * Each input has exactly one solution
	 */
	public static int[] coupleSum(int[] numbers, int target) { 
		//Key is the target - arr[i], val is the the index start from one
		Hashtable<Integer,Integer> table = new Hashtable<>();

		for (int i = 0;i < numbers.length;i++) {
			if (table.containsKey(numbers[i])) {
				return new int[] {table.get(numbers[i]),i + 1};
			}else {
				table.put(target - numbers[i],i + 1);
			}
		}	    
		return null;
	}
	
	public static boolean groupSum(int[] arr, int target) {  
		return groupSum(0,arr,target);
	}

	/** Helper method to track input array
	 * @param  start_index   starting index of array
	 * @param  arr           an array of integers
	 * @param  target        target sum
	 * @return boolean       whether target sum can be reached using a subset of arr
	 */
	private static boolean groupSum(int start, int[] arr, int target) {
		if (start >= arr.length) { //base case: There is no number left
			return (target == 0);
		}else {
			//case1: The target including the 1st element. check the remaining
			if (groupSum(start + 1,arr,target - arr[start])) return true;
			//case2: Does not include the first element,check the remaining
			if (groupSum(start + 1,arr,target)) return true;
		}
		return false;
	}

	public static boolean groupSumWithNum(int[] arr, int must_have, int target) {
		return groupSumWithNum(0,arr,must_have,target);
	}

	//this is a help method 
	private static boolean groupSumWithNum(int start_index,int[] arr, int must_have, int target) { //recursion
		if (start_index >= arr.length) return target == 0;
		//case1: The array contains the must_have
		if (arr[start_index] == must_have) {
			return groupSumWithNum(start_index + 1,arr,must_have,target - must_have);
		}else{
			//case2: Include the first element, check the remaining
			if (groupSumWithNum(start_index + 1,arr,must_have,target - arr[start_index])) return true;
			//case3: does not include the first element, check the remaining
			if (groupSumWithNum(start_index + 1,arr,must_have,target)) return true;
		}
		return false;
	}

	/*
	 * determine if it is possible to split the array into 
	 * two parts such that the sum of all elements in each part is the same.
	 */
	public static boolean splitArray(int[] arr) {
        if (arr.length == 0) return false;
        int arr_sum = 0; // calculate the sun of array
        for (int item:arr) 
            arr_sum += item;
        
        // If sum is an odd number, it is impossible to split the array of elements evenly. 
        if (arr_sum % 2 != 0)   return false;
        // If sum is an even number, check if the summation to half is possible. 
        return groupSum(0,arr,arr_sum / 2);
    }

	/**
	 * Linear-time algorithm.
	 */
	public static int maxSubSum(int[] arr) {
		int maxSum = 0, thisSum = 0;
		for (int i = 0;i < arr.length;i++) {
			thisSum += arr[i];
			if(thisSum > maxSum) maxSum = thisSum;
			if(thisSum < 0) thisSum = 0;
		}
		return maxSum;
	}
	
	/**
	 * Valid combination means not left open.
	 * @param pairs The number of pairs of parenthesis.
	 * @return Return all valid combinations of n-pairs of parentheses.
	 */
	public static ArrayList<String> combParenthesis(int pairs){
		ArrayList<String> list = new ArrayList<>();
		if(pairs > 0) createParenthesis(pairs,pairs,"",list);
		return list;
	}
	
	private static void createParenthesis(int left,int right, String temp, ArrayList<String> list) {
		if(left == 0 && right == 0) {
			list.add(temp);
		}else {
			//insert left parenthesis until we have not used up all left parenthesis
			if(left > 0) createParenthesis(left - 1,right,temp + "(",list);
			//insert right parenthesis as long as it won't lead syntax error(more right than left)
			if(right > left) createParenthesis(left,right - 1,temp + ")",list);
		}
	}
	
	//search algorithm
	public static Boolean binarySearch(int[] arr, int n){ // Time complexity = O(log(n)).
		Arrays.sort(arr);
		int low = 0;
		int high = arr.length;
		while (low < high) {
			int mid = low + (high - low) / 2;
			if (arr[mid] > n) high = mid - 1;
			if (arr[mid] < n) low = mid + 1;
			if (arr[mid] == n) return true; 
		}
		return false;
	}

	/*
	 * Given an integer array containing the available denominations of coins 
	 * in descending order.This method to compute the number of possible ways 
	 * of representing a monetary amount in cents.
	 */
	public static int makeChange(int[] coins, int amount) {
		if (coins != null && coins.length > 0 && amount >= 0) {
			return makeChange(coins,amount,0);
		}else {
			return 0;
		}
	}

	private static int makeChange(int[] coins, int amount, int curr_coin_index) {
		int next_coin_index;
		if (curr_coin_index < coins.length - 1) {
			next_coin_index = curr_coin_index + 1;
		}else { // The curr_index == the last index of the array
			return coins[curr_coin_index];
		}
		int res = 0; // Calculate the number of combinations way
		for (int i = 0;i * coins[curr_coin_index] <= amount;i++) {
			res += makeChange(coins,amount - i * coins[curr_coin_index],next_coin_index);
		}
		return res;
	}
	
	//The difference of all ascending pairs in the sequence.
	//Only added the positive difference
	public static int maxProfit(int[] prices){
		int minprice = Integer.MAX_VALUE;
		int maxprofit = 0;
		for (int i = 0; i < prices.length; i++) {
			if (prices[i] < minprice)
				minprice = prices[i];
			else if (prices[i] - minprice > maxprofit)
				maxprofit = prices[i] - minprice;
		}
		return maxprofit;
	}

	enum Index{
		BAD,GOOD,UNKNOWN;
	}
	
	/**
	 * Give you an positive int array, you are on the head of the array.
	 * Every element in the array replaces the length you can jump. 
	 * @param nums
	 * @return True if you can arrive the end of list.
	 */
	public boolean canJump(int[] nums) {
		Index[] memo = new Index[nums.length];
		for (int i = 0; i < memo.length; i++) {
			memo[i] = Index.UNKNOWN;
		}
		memo[memo.length - 1] = Index.GOOD;

		for (int i = nums.length - 2; i >= 0; i--) {
			int furthestJump = Math.min(i + nums[i], nums.length - 1);
			for (int j = i + 1; j <= furthestJump; j++) {
				if (memo[j] == Index.GOOD) {
					memo[i] = Index.GOOD;
					break;
				}
			}
		}
		return memo[0] == Index.GOOD;
	}
	
	public static ArrayList<ArrayList<Integer>> generatePascalTriangle(int numRows) {
		ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
		if (numRows == 0) return result;
		ArrayList<Integer> prev = new ArrayList<Integer>();
		prev.add(1);
		result.add(prev);

		for (int i = 2;i <= numRows;i++) {
			ArrayList<Integer> curr = new ArrayList<>();

			curr.add(1); // add First 
			for (int j = 0;j < prev.size() - 1;j++) { //add Middle which is the combination of prev level
				curr.add(prev.get(j) + prev.get(j + 1)); 
			}
			curr.add(1); //add last

			result.add(curr);
			prev = curr;
		}
		return result;
	}
	
	/**
	 * Find the contiguous subsequence that has the maximum sum among all
	 * subsequences in the array 
	 * @param arr = An array of Integer
	 * @return  res[0] = max sum, res[1] = start index of max subsequence, res[2] = end index of max subsequence.
	 */
	public static int[] maxContSequence(int[] arr) {
		int curr_sart_index = 0,curr_end_index = 0, curr_sum = 0;
		int max_start_index = 0, max_end_index = -1, max_sum = 0;

		if (arr.length > 0) {
			curr_sum = arr[0];
			max_sum = arr[0];
			max_end_index = 0;
		}
		for (int i = 1;i< arr.length;i++) {
			int sum = curr_sum + arr[i];
		    // If the maximum sum plus the current item is less than the item
            // Then we should set maximum sum to be the current item
			if (arr[i] > sum) {  
				curr_sart_index = i;
				curr_end_index = i;
				curr_sum = arr[i];
			}else {    // Otherwise, include the current item into our subsequence
				curr_end_index++;
				curr_sum += arr[i];
			}

			if (curr_sum > max_sum) {   //Update the global max subsequence
				max_sum = curr_sum;
				max_start_index = curr_sart_index;
				max_end_index = curr_end_index;
			}
		}
		
		int[] result = {max_sum,max_start_index,max_end_index};
		return result;
	}

	//merge algorithm 
	public static int[] merge(int[] arrLeft, int[] arrRight){ //merge 2 sorted lists
		int leftlength = arrLeft.length;
		int rightlength = arrRight.length;
		int left = 0, right = 0, merge = 0;
		int[] arrResult = new int[leftlength + rightlength];

		while (left < leftlength && right < rightlength) {
			if (arrLeft[left] < arrRight[right] ) {
				arrResult[merge++] = arrLeft[left++];
			}else {
				arrResult[merge++] = arrRight[right++];
			}
		}
		// merge the elements that was left in the arrLeft
		while (left < leftlength) {
			arrResult[merge++] = arrLeft[left++];
		}
		//merge the elements that was left in the arrRight
		while (right < rightlength) {
			arrResult[merge++] = arrRight[right++];
		}
		return arrResult;
	}

	//sort Algorithm	
	public static void merge(int[] arr, int left, int right, int mid) {
		int leftLength = mid - left + 1;
		int rightLength = right - mid;
		
		//Create tempt arrays, copy data to tempt arrays
		int[] arrLeft = new int[leftLength]; 
		int[] arrRight = new int[rightLength];
		for(int i = 0;i < leftLength;i++) {
			arrLeft[i] = arr[left + i];
		}
		for(int i = 0;i < rightLength;i++) {
			arrRight[i] = arr[mid + 1 + i];
		}
		
		//Merge the tempt arrays to array
		int l = 0, r = 0, merge = left;
		while(l < leftLength && r < rightLength) {
			if(arrLeft[l] <= arrRight[r]) {
			    arr[merge++] = arrLeft[l++];
			}else{
				arr[merge++] = arrRight[r++];
			}
		}
		
		while(l < leftLength) {
			arr[merge++] = arrLeft[l++];
		}
		while(r < rightLength) {
			arr[merge++] = arrRight[r++];
		}
	}

	private static void mergeSort(int[] arr, int left, int right) {	
		if(left < right) {
			int mid = (left + right) / 2;
			mergeSort(arr,left,mid);
			mergeSort(arr,mid + 1,right);	
			merge(arr,left,right,mid);
		}
	}
	
	public static int[] mergeSort(int[] arr) {
		mergeSort(arr,0,arr.length - 1);
		return arr;
	}
	
	public static int[] selectionSort(int[] arr) {//improved performance over bubble sort
		for (int i = 0; i < arr.length - 1; i++) {
			int min_index = i;
			for (int j = i + 1;j < arr.length;j++) {
				if (arr[j] < arr[min_index]) {
					min_index = j;
				}
			}
			//swap
			int temp = arr[min_index];
			arr[min_index] = arr[i];
			arr[i] = temp;
		}
		return arr;
	}

	public static int[] bubbleSort(int[] arr) {
		for (int i = 0;i < arr.length;i++) {
			for (int j = i;j < arr.length;j++) {
				if (arr[i] > arr[j]) { //swap one find a greater number
					int temp = arr[i];
					arr[i] = arr[j];
					arr[j] = temp;
				}
			}
		}
		return arr;
	}
	
	/**
	 * A Binary Heap is a Complete Binary Tree where items are stored in a special order 
	 * such that value in a parent node is greater(or smaller) than the values in its two children nodes.
	 * This heapSort is a max heap.
	 * @param arr
	 */
	public static void heapSort(int[] arr) {
		int length = arr.length;
		for(int i = length / 2 - 1;i >= 0;i--) { // build the heap
			heapify(arr,length,i);
		}
		
		//One by one extract and element from heap
		for(int i = length - 1;i >= 0;i--) { 
			int temp = arr[0]; // swap the max to the start of array
			arr[0] = arr[i];
			arr[i] = temp;
			
			//call max heapify on the reduced heap
			heapify(arr,i,0);
		}
	}
	
	private static void heapify(int[] arr, int n, int i) {
		int largest = i;       //Initialize the largest as root
		int left = 2 * i;      //The left node
		int right = 2 * i + 1; //The right node
		
		if(left < n && arr[left] > arr[largest]) largest = left;
		if(right < n && arr[right] > arr[largest]) largest = right;
		if(largest != i) { // if the largest is not the root
			int swap = arr[i];
			arr[i] = arr[largest];
			arr[largest] = swap;
			
			//recursively heapify the sub-tree
			heapify(arr,n,largest); 
		}	
	}
}
