package algorithem;

import java.util.HashMap;

/**
 * TrieNode class that represents one node of a Trie, and a partially complete Trie class.
 * @author ywu
 *
 */
class TrieNode {
    Character c;
    Boolean isLeaf = false;
    HashMap<Character, TrieNode> children = new HashMap<>();
    public TrieNode() {}
    public TrieNode(Character c) {
        this.c = c;
    }
    
    
}

