package algorithem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeSet;

class Trie {
	private TrieNode root;

	public Trie() {
		this.root = new TrieNode();
	}
	public void insertWord(String word) {
		if (word == null || word.equals("")) return ;
		TrieNode curr = root;
		HashMap<Character,TrieNode> childrens = curr.children;
		for (int i = 0; i < word.length(); i++) {
			char ch = word.charAt(i);

			if (childrens.containsKey(ch)) {
				curr = childrens.get(ch);
			} else {
				TrieNode newNode = new TrieNode(ch);
				childrens.put(ch, newNode);
				curr = newNode;
			}        
			childrens = curr.children;
			if (i == word.length() - 1) curr.isLeaf = true;                 
		}       
	}

	public Boolean searchWord(String word) {
		TrieNode curr = root;
		HashMap<Character,TrieNode> childrens = curr.children;

		for (int i = 0; i < word.length(); i++) {
			char ch = word.charAt(i);
			if (i == word.length() - 1) curr.isLeaf = true;
			if (childrens.containsKey(ch)) {
				curr = childrens.get(ch);
				childrens = curr.children;
			} else {
				return false;
			}
		}
		return curr.isLeaf;
	}

	public Boolean searchPrefix(String word) {
		//if (word == null || word.equals("")) return false;
		TrieNode curr = root;
		HashMap<Character,TrieNode> childrens = curr.children;

		for (int i = 0; i < word.length(); i++) {
			char ch = word.charAt(i);
			if (i == word.length() - 1) curr.isLeaf = true;
			if (childrens.containsKey(ch)) {
				curr = childrens.get(ch);
				childrens = curr.children;
			} else {
				return false;
			}
		}     
		return true;
	}
     
	public ArrayList<String> boggleByot(char[][] board, ArrayList<String> dictionary){
	    Trie prefixTree = new Trie();
	    for(String word: dictionary){
	        prefixTree.insertWord(word);
	    }
	    return boggleSearchWithDict(board,prefixTree);
	}
	
	public ArrayList<String> boggleSearchWithDict(char[][] board, Trie dictionary){
		TreeSet<String> output = new TreeSet<String>();
		int rows = board.length;
		int cols = board[0].length;

		for (int i = 0; i < rows; i++) {
			for (int j = 0;j < cols; j++) {
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
	
	
	/**
	 * TrieNode class that represents one node of a Trie, and a partially complete Trie class.
	 */
class TrieNode { //inner class
	    Character c;
	    Boolean isLeaf = false;
	    HashMap<Character, TrieNode> children = new HashMap<>();
	    public TrieNode() {}
	    public TrieNode(Character c) { this.c = c;}   
	}
}
