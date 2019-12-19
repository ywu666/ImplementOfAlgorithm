package algorithem;

import java.util.HashMap;

/**
 * A Trie or Prefix Tree an efficient data lookup structure - often used to store large
 * collections of words or dictionaries. 
 * With a Trie, search complexities can be reduced to O(k) where k is the key or word length.
 * @author ywu
 *
 */
class Trie {
	private TrieNode root;

	// Implement these methods : 
	public Trie() {
		this.root = new TrieNode();
	}

	public void insertWord(String word) {
		if (word == null || word.isEmpty()) return ;
		TrieNode curr = root;
		HashMap<Character,TrieNode> children = new HashMap<>();

		for (int i =0;i<word.length();i++) {
			char ch = word.charAt(i);
			if (children.containsKey(ch)) {
				curr = children.get(ch);
			} else {
				TrieNode newNode= new TrieNode(ch);
				children.put(curr.c, newNode);
				curr = newNode;
			}
			children = curr.children;
			if (i == word.length() - 1) curr.isLeaf = true;
		}		
	}

	public Boolean searchWord(String word) {
		TrieNode curr = root;
		HashMap<Character,TrieNode> children = root.children;
		for (int i = 0;i<word.length();i++) {
			char ch = word.charAt(i);
			if (i == word.length()-1) curr.isLeaf = true;
			if (children.containsKey(ch)) {
				curr = children.get(ch);
				children = curr.children;
			}else {
				return false;
			}
		}

		return curr.isLeaf;
	}

	public Boolean searchPrefix(String word) {
		TrieNode curr = root;
		HashMap<Character,TrieNode> childrens = root.children;

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
}
