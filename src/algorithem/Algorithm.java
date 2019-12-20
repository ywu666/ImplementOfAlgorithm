package algorithem;

import java.util.*;

public class Algorithm {

	public static String compressString(String text) {
		int length = text.length();
		if (length >= 2) {
			StringBuilder compress = new StringBuilder();
			char lastChar = text.charAt(0);
			int count = 1;
			for (int i = 1; i<length; i++) {
				if (text.charAt(i) == lastChar) {		        	  
					count++;
				}else {
					compress.append(lastChar);
					if(count > 1) {
						compress.append(count);
					}
					lastChar = text.charAt(i);
					count = 1;
				}
			}
			compress.append(lastChar);
			if(count > 1) compress.append(count);
			if (compress.length() < length) return compress.toString();
		}
		return text;
	}

	public static boolean isAnagram(String input1, String input2) { // the inputs are also lower case
		if (input1 == null && input2 == null) return false;
		if (input1.equals(input2)) return true;
		if (input1.length() != input2.length()) return false;

		int[] buffer = new int[26]; 
		for (int i = 0; i < input1.length(); i++) {
			buffer[input1.charAt(i) - 'a']++;
			buffer[input2.charAt(i) - 'a']--;
		}

		for (int i = 0; i < buffer.length; i++) {
			if (buffer[i] != 0) {
				return false;
			}
		}    
		return true;
	}

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
		/*if (str1.length() != str2.length()) return false;
				List<Character> list = new ArrayList<Character>();

				for (char ch: str1.toCharArray()) { //store all the char in list
					list.add(ch);
				}

				for (char ch:str2.toCharArray()) {
					if (!list.contains(ch)) {
						return false;
					}
				}
				return true;*/

		// improve the method
		if (str1.length() != str2.length()) {
			return false;
		}

		int[] letters = new int[256];
		char[] str1_arr = str1.toCharArray();
		for (char c : str1_arr) {
			letters[c]++;
		}

		for (char c:str2.toCharArray()) {
			if (--letters[c] < 0) {
				return false;
			}
		}
		return true;

	}
	
	/**
	 * Two strings are isomorphic if the letters in one string can be remapped 
	 * to get the second string. Remapping a letter means replacing all 
	 * occurrences of it with another letter.
	 * @param str
	 * @return True if the 2 string are isomorphic
	 */
	public static boolean isIsomorphic(String str1, String str2) {
		if (str1.length() != str2.length()) return false;
		Hashtable<Character, Integer> table1 = new Hashtable<Character,Integer>();
		Hashtable<Character, Integer> table2 = new Hashtable<Character,Integer>();
		for (int  i=0;i<str1.length();i++) {
			Character ch1 = str1.charAt(i);
			Integer val1 = table1.containsKey(ch1)? table1.get(ch1) +1 :1; 
			table1.put(ch1,val1);
			
			Character ch2 = str2.charAt(i);
			Integer val2 = table2.containsKey(ch2) ? table2.get(ch2) +1 :1; 
			table2.put(ch2,val2);
			if (table1.get(ch1) != table2.get(ch2)) return false;
		}
		return true;	
	}

	public static ArrayList<String> getPermutations(String str) {
		ArrayList<String> permutations = new ArrayList<String>();
		if (str == null) return null;
		if (str.equals("")) {
			permutations.add("");
			return permutations;
		}

		char first = str.charAt(0);
		ArrayList<String> list = getPermutations(str.substring(1));

		for (String word: list) {
			for (int i =0;i<= word.length();i++) {
				permutations.add(insert(word,first,i));
			}
		}
		return permutations;
	}
	//list all possible combinations and permutations of its characters
	public static ArrayList<String> getCombPerms(String str) {
		ArrayList<String> permutations = new ArrayList<String>();
		if (str == null) return null;
		if (str.equals("") || str.length() ==1) {
			permutations.add(str);
			return permutations;
		}

		char first = str.charAt(0);
		permutations.add(first+""); //convert to string type
		ArrayList<String> list = getCombPerms(str.substring(1));
		for (String word: list) {
			for (int i =0;i<= word.length();i++) {
				permutations.add(insert(word,first,i));
			}
		}
		permutations.addAll(list); // add all the combination 
		return permutations;
	}

	//This is a help method for getPermutations to insert char at specific location
	public static String insert(String word, char insert, int i) {
		return word.substring(0,i) + insert + word.substring(i);
	}

	public static String insertPairStar(String s) { //recursion
		if(s == null || s.length() <= 1) return s;
		return s.charAt(0) + 
				(s.charAt(0)==s.charAt(1) ? "*" : "") +
				insertPairStar(s.substring(1));
	}

	//This method will replace the ' ' in String a with the specific pattern b.
	public static String replace(String a, String b) {
		StringBuffer str = new StringBuffer();

		for (int i=0;i<a.length();i++) {
			if (a.charAt(i) == ' ') {
				str.append(b);
			} else {
				str.append(a.charAt(i));
			}
		}
		return str.toString();
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

	//Given a list of String, this method will remove the duplicate words in the list.
	public static ArrayList<String> removeDuplicates(List<String> input) {
		TreeSet<String> treeSet = new TreeSet<String>();
		treeSet.addAll(input);
		ArrayList<String> newList = new ArrayList<String>(treeSet); 

		return newList;

	}

	public static int singleNumber(int[] A) { // return the first number that appears once
		Hashtable<Integer, Integer> table = new Hashtable<Integer, Integer>();
		for (int i =0;i < A.length;i++) {
			if(table.contains(A[i])) {
				table.put(A[i], table.get(A[i])+1);
			}else {
				table.put(A[i], 1);
			}
		}

		for (int i =0;i < A.length;i++) {
			if (table.get(A[i]) == 1) {
				return A[i];
			}
		}
		return 0;
	}

	public static boolean areAllCharactersUnique(String str){
		if (str == null || str.equals("")) return true;
		for ( int i = 0; i < str.length(); i++) {
			for (int j = i + 1; j < str.length(); j++) {
				if ( str.charAt(i) == str.charAt(j)) {
					return false;
				}
			}
		}	    
		return true;
	}

	/* Maximum Gain is defined as the maximum difference between 2 elements in a list 
	 * such that the larger element appears after the smaller element. 
	 * If no gain is possible, return 0.
	 */
	public static int maxGain(int[] arr) {
		int maxGain = 0;
		int min = arr[0];
		for (int i=1;i<arr.length;i++) {
			 min = Math.min(min, arr[i]);
		     maxGain = Math.max(maxGain, arr[i] - min);
		}	
		return maxGain;
	}

	public static String computeBinary(int val) {
		if (val == 0) return "0";
		String binary = "";
		while (val > 0) {
			binary = val % 2 + binary;	
			val = val/2;	        
		}	        
		return binary;
	}

	public static String computeBinaryRecursion(int val) {
		// Base case: value is less than 2
		if (val < 2)
			return Integer.toString(val);
		// Recursive case: binary rep = binary of the header + last digit (odd/even)
		else {
			return computeBinary(val/2)+computeBinary(val%2);
		}
	}

	public static int reverseInt(int x) {
		int reverse = 0;
		while (x != 0) {
			reverse = reverse*10 + x%10;
			x = x/10;
		}
		return reverse;
	}

	public static String reverseStr(String str) {
		if (str == null || str.isEmpty()) return str;
		String inputStr = str;
		String outputStr = "";
		for (int i = inputStr.length() -1; i>=0;i--) {
			outputStr +=inputStr.charAt(i);
		}
		return outputStr;
	}

	public static Boolean isIntPalindrome(int x) { 
		if (x < 0) return false;
		int reverse = reverseInt(x);
		return (reverse == x);   
	}

	public static int findMissingNumber(int[] arr) { // where are is 1 to 10
		int sum = 0;
		for (int i = 0; i<arr.length; i++) {
			sum += arr[i];
		}
		return 55 - sum;
	}

	public static boolean isPowOfTwo(int num) {
		if ((num & (num-1)) == 0) {
			return true;
		}else {
			return false;
		}
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

	/**
	 * Given two input integers a and b. The method is to determine the number of bits required 
	 * to be swapped to convert a to b.
	 * @param a, b
	 */
	public static int bitSwapRequired(int a, int b) {
		int count =0;
		for (int c = a^b;c !=0;c = c&(c-1)) { // ^ = XOR gate 
			count++;
		}
		return count;
	}

	public static int swapOddEvenBit(int x) {
		int odd = (x & 0xaaaaaaaa)>>1; // bitMask odd and shift right
		int even = (x & 0x5555555)<<1; //bisMask even and shift left
		return odd | even;
	}

	public static ArrayList<String> generateIPAddrs(String input) {

		class IpLevelNode{//local class
			int level = 0;
			String predecessor;
			String successor;
			public IpLevelNode(int level, String ipToAppend, String predecessor, String successor) {
				this.level = level;
				this.successor = successor;
				if (level == 0) {
					this.predecessor = ipToAppend;
				}else {
					this.predecessor = predecessor+ "." + ipToAppend;
				}
			}
		}

		ArrayList<String> out = new ArrayList<>();
		Deque<IpLevelNode> stack = new LinkedList<>();
		//push 3 possibilities onto the stack
		stack.addFirst(new IpLevelNode(0, input.substring(0,1),"",input.substring(1)));
		stack.addFirst(new IpLevelNode(0, input.substring(0,2),"",input.substring(2)));
		stack.addFirst(new IpLevelNode(0, input.substring(0,3),"",input.substring(3)));
		while(!stack.isEmpty()) {
			IpLevelNode node = stack.removeFirst();
			int currlevel = node.level;
			String predecessor = node.predecessor;
			String remaining = node.successor;
			if (currlevel == 3 && remaining.length() == 0) {
				out.add(node.predecessor);
				continue;
			}
			int i =1;
			while (i <=3) {
				if (remaining.length() < i) break;
				String IpToAppend= remaining.substring(0,i);
				String successor = remaining.substring(i);
				if (IpToAppend.length() > 0) {
					int num = Integer.parseInt(IpToAppend);
					if (num <=255) stack.addFirst(new IpLevelNode(currlevel+1,IpToAppend ,predecessor,successor));
				}
				i++;
			}
		}
		return out;
	}

	public static int longestNRSubstringLen(String input) {
		if (input == null || input.equals("")) return 0;
		Hashtable<Character, Integer> charTable = new Hashtable<>();
		int i = 0, prev =0;

		for (char ch:input.toCharArray()) {
			if (!charTable.containsKey(ch)) {
				charTable.put(ch, i++);
			}else {
				prev = Math.max(prev,charTable.size());
				//i = charTable.get(ch);
				charTable.clear();
			}
		}
		return Math.max(prev,charTable.size());		
	}

	public static int minTriangleDepth(ArrayList<ArrayList<Integer>> input) {
		int height = input.size();
		int outsize = input.get(height -1).size();
		int[] outBuffer = new int[outsize];

		for (int i=0; i<outsize;i++) { //initialize the out buffer 
			outBuffer[i] = input.get(height-1).get(i);
		}

		for (int r= height-2;r >=0; r--) {
			ArrayList<Integer> row = input.get(r);
			for (int i =0;i<row.size();i++) {
				outBuffer[i] = row.get(i) + Math.min(outBuffer[i], outBuffer[i+1]);
			}
		}
		return outBuffer[0];
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
	public static boolean groupSum(int start, int[] arr, int target) {
		if (start >= arr.length) { //base case: There is no number left
			return (target == 0);
		}else {
			//case1: The target including the 1st element. check the remaining
			if (groupSum(start+1, arr, target- arr[start])) return true;
			//case2: Does not include the first element,check the remaining
			if (groupSum(start+1,arr,target)) return true;
		}
		return false;
	}

	public static boolean boggleSearch(char[][] board, String word){
		return true;
	}

	public ArrayList<String> boggleSearchWithDict(char[][] board, Trie dictionary){
		TreeSet<String> output = new TreeSet<String>();
		int rows = board.length;
		int cols = board[0].length;

		for (int i = 0; i<rows; i++) {
			for (int j=0;j < cols; j++) {
				search(i,j,board,dictionary,"",output);
			}
		}
		return new ArrayList<>(output);
	}

	/**
	 * This method is a help method for boggleSearchWithDict.
	 * @param r = row of the board
	 * @param c = column of the board
	 * @param board = board you want to search
	 * @param dictionary = the dictionary that is provided
	 * @param prefix 
	 * @param output
	 */
	public static void search(int r, int c, char[][] board, Trie dictionary, String prefix, TreeSet<String> output) {
		int rows = board.length;
		int cols = board[0].length;

		if (r > (rows - 1) || r < 0 || c> (cols-1) || c < 0 || !dictionary.searchPrefix(prefix) || board[r][c] == '@') {
			return ;
		}

		char ch = board[r][c];
		String s = prefix + ch;
		if (dictionary.searchWord(s)) {
			output.add(s);
		}

		board[r][c] = '@'; // Marked as visited

		search(r+1,c,board,dictionary,s,output); // check up
		search(r-1,c,board,dictionary,s,output); // check down
		search(r,c+1,board,dictionary,s,output); // check left
		search(r,c-1,board,dictionary,s,output); // check down

		board[r][c] = ch; // unmark the board node
	}


	private static String open = "([{";
	private static String close = ")]>}";
	public static boolean isBalanced(String input) {
		return isBalanced(input,"");
	}

	//These methods are all help methods for isBalances()method above
	public static boolean isBalanced(String input, String stack) {
		if (input.isEmpty()) { // base case : input string is empty
			return stack.isEmpty();
		}else if (isOpen(input.charAt(0))) { //base case 2: is open case
			return isBalanced(input.substring(1),input.charAt(0) + stack);
		}else if (isClose(input.charAt(0))) {
			return (!stack.isEmpty() && isMatching(stack.charAt(0), input.charAt(0)) && isBalanced(input.substring(1), stack.substring(1)));
		}
		return isBalanced(input.substring(1),stack);
	}
	public static boolean isOpen (char ch) {
		return open.indexOf(ch) != -1;
	}
	public static boolean isClose(char ch) {
		return close.indexOf(ch) != -1;
	}	
	public static boolean isMatching(char charopen, char charclose) {
		return open.indexOf(charopen) == close.indexOf(charclose);
	}

	//search algorithm
	public static Boolean binarySearch(int[] arr, int n){
		int low =0;
		int high = arr.length;
		while (low < high) {
			int mid = low + (high-low)/2;
			if (arr[mid] > n) high = mid -1;
			if (arr[mid] < n) low = mid + 1;
			return true; // if arr[mid] == n;
		}
		return false;
	}
	// java.util.* and java.util.streams.* have been imported for this problem.
	// You don't need any other imports.

	public static  ArrayList<String> getStringsFromNums(String digits) {
	    //Create the phone key mapping 
	    HashMap<Character,String> mapping = new HashMap<>();
	    mapping.put('2',"abc");
	    mapping.put('3',"def");
	    mapping.put('4',"ghi");
	    mapping.put('5',"jkl");
	    mapping.put('6',"mno");
	    mapping.put('7',"pqrs");
	    mapping.put('8',"tuv");
	    mapping.put('9',"wxyz");
	    
	    class PhoneNode { //local class
	        String word;
	        int digitCount;
	        
	        PhoneNode(String word,int digitCount) {
	            this.word = word;
	            this.digitCount = digitCount;
	        }
	    }
	    
	    //declare the Stack
	    ArrayList<String> out = new ArrayList<>();
	    Deque<PhoneNode> stack = new LinkedList<>();
	    int len = digits.length();
	    for (Character ch: mapping.get(digits.charAt(0)).toCharArray()) { // push the first node on the stack
	        stack.addFirst(new PhoneNode(String.valueOf(ch),1));
	    }
	    
	    //classic DFS
	    while (!stack.isEmpty()) {
	    	PhoneNode node = stack.removeFirst();
	    	if (node.digitCount == len) {
	    		out.add(node.word);
	    	} else {
	    		for (Character ch: mapping.get(digits.charAt(node.digitCount)).toCharArray()) {
	    			stack.addFirst(new PhoneNode(node.word+ch,node.digitCount+1));
	    		}
	    	}
	    }
	   return out;
	}
	
	/*
	 * Given an integer array containing the available denominations of coins 
	 * in descending order.This method to compute the number of possible ways 
	 * of representing a monetary amount in cents.
	 */
	public static int makeChange(int[] coins, int amount) {
          if (coins != null && coins.length >0 && amount >=0) {
        	  return makeChange(coins,amount,0);
          }else {
        	  return 0;
          }
	}
	
	public static int makeChange(int[] coins, int amount, int curr_coin_index) {
		int next_coin_index;
		if (curr_coin_index < coins.length -1) {
			next_coin_index = curr_coin_index +1;
		}else { // The curr_index == the last index of the array
			return coins[curr_coin_index];
		}
		int res = 0; // Calculate the number of combinations way
		for (int i = 0;i*coins[curr_coin_index] <=amount;i++) {
			res += makeChange(coins,amount -i*coins[curr_coin_index],next_coin_index);
		}
		return res;
	}
	
	/**
	 * Given two Strings, a and b, write a method - editDistance that 
	 * returns the minimum number of operations needed to transform a into b. 
	 * The following character operations are allowed :
	 *a) Replace character
	 *b) Insert character
	 *c) Delete character
	 * @param a
	 * @param b
	 * @return
	 */
	public int editDistance(String a, String b) {
		int lengthA = a.length();
		int lengthB = b.length();
		int[][] memo = new int [lengthA+1][lengthB+1];
		for (int i=0;i<=lengthA;i++) memo[i][0] = i;
		for (int i=0;i<=lengthB;i++) memo[0][i] = i;
		for (int i = 1;i<=lengthA;i++) {
			char cha = a.charAt(i);
			for (int j=0;j<=lengthB;j++) {
				char chb = b.charAt(j);
				if (cha == chb) {
					memo[i][j] = memo[i-1][j-1];
				}else {
					int replaceDist = memo[i][j];
					int insertDist = memo[i][j-1] + 1;
					int delateDist = memo[i-1][j] + 1;
					int minDist = Math.min(replaceDist, Math.min(insertDist, delateDist));
					memo[i][j] = minDist;
				}
			}
		}
		return memo[lengthA][lengthB];
	}

	public static boolean groupSumWithNum(int[] arr, int must_have, int target) {
		return groupSumWithNum(0,arr,must_have,target);
	}

	//this is a help method 
	public static boolean groupSumWithNum(int start_index,int[] arr, int must_have, int target) { //recursion
		if (start_index >= arr.length) return target == 0;
		//case1: The array contains the must_have
		if (arr[start_index] == target) {
			return groupSumWithNum(start_index + 1,arr,must_have,target- must_have);
		} else {
			//case2: Include the first element, check the remaining
			if (groupSumWithNum(start_index + 1,arr,must_have,target- arr[start_index])) return true;
			//case3: does not include the first element, check the remaining
			if (groupSumWithNum(start_index + 1,arr,must_have,target- must_have)) return true;
		}
		return false;
	}
	
	//merge algorithm 
	public static int[] merge(int[] arrLeft, int[] arrRight){ //merge 2 sorted lists
		int leftlength = arrLeft.length;
		int rightlength = arrRight.length;
		int left = 0, right =0, merge =0;
		int[] arrResult = new int[leftlength+rightlength];
		
		while (left<leftlength && right<rightlength) {
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
        //merge teh elements that was left in the arrRight
		while (right < rightlength) {
			arrResult[merge++] = arrRight[right++];
		}
		return arrResult;
	}
	
	//sort Algorithm
	public static int[] selectionSort(int[] arr) {//improved performance over bubble sort
		for (int i = 0; i<arr.length - 1; i++) {
			int min_index = i;
			for (int j = i+1;j<arr.length;j++) {
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
		for (int i= 0;i<arr.length;i++) {
			for (int j = i;j<arr.length;j++) {
				if (arr[i] > arr[j]) { //swap one find a greater number
					int temp = arr[i];
					arr[i] = arr[j];
					arr[j] = temp;
				}
			}
		}
		return arr;
	}
}
