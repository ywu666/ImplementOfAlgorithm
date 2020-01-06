package algorithem;

import java.util.*;

public class StringAlgorithm {
	
	//This is a help method for getPermutations to insert char at specific location
	public static String insert(String word, char insert, int i) {
		return word.substring(0,i) + insert + word.substring(i);
	}

	public static String insertPairStar(String str) { //recursion
		if(str == null || str.length() <= 1) return str;
		return str.charAt(0) + 
				(str.charAt(0) == str.charAt(1) ? "*" : "") +
				insertPairStar(str.substring(1));
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
	
	public static String reverse(String str) {
		if (str == null || str.isEmpty()) return str;
		String inputStr = str;
		String outputStr = "";
		for (int i = inputStr.length() -1; i>=0;i--) {
			outputStr += inputStr.charAt(i);
		}
		return outputStr;
	}
	
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
	
	public static boolean isPalindrom(String str) {
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
		for (char c : str1.toCharArray()) {
			letters[c]++;
		}

		for (char c:str2.toCharArray()) {
			if (--letters[c] < 0) {
				return false;
			}
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
	
	public static Character firstNonRepeatedCharacter(String str) {
		if(str == null || str.isEmpty() == true) return null;
		Hashtable<Character,Integer> table = new Hashtable<>();
		for(char ch:str.toCharArray()){
			if(table.containsKey(ch)){
				table.put(ch,table.get(ch)+1);
			}else{
				table.put(ch,1);
			}
		}

		for(char ch:str.toCharArray()){
			if(table.get(ch) == 1) return ch;
		}
		return null;
	}
	
	//Given a list of String, this method will remove the duplicate words in the list.
	public static ArrayList<String> removeDuplicates(List<String> input) {
		TreeSet<String> treeSet = new TreeSet<String>();
		treeSet.addAll(input); 
		//This will remove the duplicates, and the result will sort alphabetically.
		ArrayList<String> newList = new ArrayList<String>(treeSet);
		return newList;

	}
	
	public static boolean areAllCharactersUnique(String str){
		if(str == null || str.length() <= 1) return true;

		// 2^8 = 256. Covers all characters in ASCII. Make Checker array
		boolean[] checker_array = new boolean[256];

		// Loop across all characters in String. 
		for(int i=0;i<str.length();i++){
			// Check the position specified by the character's 8 bit value. Since this is cast as an int, it will be a numerical value!
			int position = str.charAt(i);
			if(checker_array[position]) {
				return false;
			}else {
				checker_array[position] = true;
			}
		}
		// Return true if no duplicates
		return true;
	}
	
	public String longestPalSubstr(String str){ 
		if(str == null || str.length() < 2) return str;
		int len = str.length();

		// memo[start][finish] is true if the String is a palindrome
		// between .charAt(start) and .charAt(finish)
		boolean[][] memo = new boolean[len][len];
		int maxSubstrLen = 1;
		int maxSubstrStartIndex = 0;

		// Mark all length 1 substrings as palindromes.
		for(int i = 0; i < len; i++){
			memo[i][i] = true;
		}

		// Selectively mark all length 2 substrings as palindromes
		// in a single pass.
		for(int i = 0; i < len-1; i++){
			if(str.charAt(i) == str.charAt(i+1)){
				memo[i][i+1] = true;
				maxSubstrLen = 2;
				maxSubstrStartIndex = i;
			}
		}

		// Scan for substrings of length > 2 and length < len. Remember that 
		// memo[l][] represents the starting
		// character str.charAt(l) and memo[][l] represents the ending character
		// in our check.
		for(int l = 3; l <= len; l++){
			// Run the check until you reach the end of the String. i is the 
			// start index.
			for(int i = 0; i + l-1 < len; i++ ){
				// j is the end index.
				int j = i + l-1;

				// In a String "abba", if we're at the second "b" -> "abb",
				// "abba" is a palindrome only if "bb" were a palindrome and "a" == "a"
				// at the opposite ends of the String. Can be translated to the following
				// condition using the memo pad:
				if(memo[i+1][j-1] && str.charAt(i) == str.charAt(j)){
					memo[i][j] = true;
					maxSubstrLen = l;
					maxSubstrStartIndex = i;
				}
			}
		}
		return str.substring(maxSubstrStartIndex, maxSubstrStartIndex + maxSubstrLen);
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
			int i = 1;
			while (i <= 3) {
				if (remaining.length() < i) break;
				String IpToAppend= remaining.substring(0,i);
				String successor = remaining.substring(i);
				if (IpToAppend.length() > 0) {
					int num = Integer.parseInt(IpToAppend);
					if (num <= 255) stack.addFirst(new IpLevelNode(currlevel+1,IpToAppend ,predecessor,successor));
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
	
	private static String open = "([{";
	private static String close = ")]>}";
	public static boolean isBalanced(String input) {
		return isBalanced(input,"");
	}

	//These methods are all help methods for isBalances()method above
	private static boolean isBalanced(String input, String stack) { //Time complexity = O(n).
		if (input.isEmpty()) { // base case : input string is empty
			return stack.isEmpty();
		}else if (isOpen(input.charAt(0))) { //base case 2: is open case
			return isBalanced(input.substring(1),input.charAt(0) + stack);
		}else if (isClose(input.charAt(0))) {
			return (!stack.isEmpty() && isMatching(stack.charAt(0), input.charAt(0)) && isBalanced(input.substring(1), stack.substring(1)));
		}
		return isBalanced(input.substring(1),stack);
	}
	private static boolean isOpen (char ch) {
		return open.indexOf(ch) != -1;
	}
	private static boolean isClose(char ch) {
		return close.indexOf(ch) != -1;
	}	
	private static boolean isMatching(char charopen, char charclose) {
		return open.indexOf(charopen) == close.indexOf(charclose);
	}

	//Time complexity is O(n) by using stack. 
	//Transfer expression from infix to suffix.
	public static List<String> parseToSuffixExpression(String expression) {
		return parseToSuffixExpression(expressionToList(expression));
	}

	private static List<String> parseToSuffixExpression(List<String> expressionList) {
		Stack<String> opStack = new Stack<>(); //Create a stack to save the operators
		List<String> suffixList = new ArrayList<>(); 
		for(String item : expressionList){
			if(isOperator(item)){ //push the item if the stack is empty or top is "(", or the priority is higher than top.
				if(opStack.isEmpty() || "(".equals(opStack.peek()) || priority(item) > priority(opStack.peek())){
					opStack.push(item);
				}else { //pop operators from the stack until meets the end of stack or "(" or priority is larger.
					while (!opStack.isEmpty() && !"(".equals(opStack.peek())){
						if(priority(item) <= priority(opStack.peek())){
							suffixList.add(opStack.pop());
						}
					}
					opStack.push(item); //Otherwise, push the new operator into the stack
				}
			}else if(isNumber(item)){
				suffixList.add(item);
			}else if("(".equals(item)){
				opStack.push(item);
			}else if(")".equals(item)){ //pop the operators between ()
				while (!opStack.isEmpty()){
					if("(".equals(opStack.peek())){
						opStack.pop();
						break;
					}else {
						suffixList.add(opStack.pop());
					}
				}
			}else {
				throw new RuntimeException("Illegal operator!");
			}
		}
		while (!opStack.isEmpty()){ // pop all remaining operators into the suffix
			suffixList.add(opStack.pop());
		}
		return suffixList;
	}

	private static boolean isOperator(String op){
		return op.equals("+") || op.equals("-") || op.equals("*") || op.equals("/");
	}

	private static boolean isNumber(String num){
		return num.matches("\\d+");
	}

	// Get the priority of the operator.
	private static int priority(String op){
		if(op.equals("*") || op.equals("/")){
			return 1;
		}else if(op.equals("+") || op.equals("-")){
			return 0;
		}
		return -1;
	}

	private static List<String> expressionToList(String expression) {
		int index = 0;
		List<String> list = new ArrayList<>();
		do{
			char ch = expression.charAt(index);
			if(ch < 47 || ch > 58){ //isOperator, push to list directly
				index ++ ;
				list.add(ch+"");
			}else if(ch >= 47 && ch <= 58){ //isNumber, check if the number is more than one digit
				String str = "";
				while (index < expression.length() && expression.charAt(index) >=47 && expression.charAt(index) <= 58){
					str += expression.charAt(index);
					index ++;
				}
				list.add(str);
			}
		}while (index < expression.length());
		return list;
	}
}
