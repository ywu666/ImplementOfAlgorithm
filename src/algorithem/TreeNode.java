package algorithem;

import java.util.*;

public class TreeNode {
	int data;
	TreeNode left;
	TreeNode right;

	TreeNode() {}

	TreeNode(int data) { this.data = data; }

	TreeNode(int data, TreeNode left, TreeNode right) {
		this.data = data;
		this.left = left;
		this.right = right;
	}

	public TreeNode insert(TreeNode root, int data) {
		if (root == null) return new TreeNode(data);
		if (root.data > data) insert(root.left,data);
		if (root.data < data) insert (root.right, data);
		return root;
	}

	public int sum(TreeNode root) {
		if (root == null) return 0;
		return sum(root.left) + sum(root.right) + root.data;
	}

	public int sumItr(TreeNode root) {
		int sum = 0;
		if (root == null) return sum;

		Queue<TreeNode> q = new LinkedList<TreeNode>();
		q.add(root);
		while (!q.isEmpty()) {
			TreeNode node = q.poll();
			sum +=node.data;
			if (node.left != null) q.add(node.left);
			if (node.right != null) q.add(node.right);
		}
		return sum;
	}

	public int size(TreeNode root) {
		if (root == null) return 0;
		return size(root.left) + size(root.right) + 1;
	}

	public int diamter(TreeNode root) {
		return 0;
	}

	public int findHeight(TreeNode root) {
		if (root == null) return 0;
		int left = findHeight(root.left);
		int right = findHeight(root.right);
		if (left > right) {
			return left+1;
		}else {
			return right+1;
		}
	}

	public TreeNode findMin(TreeNode root) {
		if(root == null) return null;
		if(root.left == null) return root;
		return findMin(root.left);
	}

	public int findMax(TreeNode root) { //recursion
		if (root != null) {
			return Math.max(root.data, Math.max(findMax(root.left), findMax(root.right)));
		}
		return Integer.MIN_VALUE;    
	}

	public int findMaxItr(TreeNode root) {
		if (root == null) return Integer.MIN_VALUE;
		Queue<TreeNode> q = new LinkedList<>();
		q.add(root);
		int max = root.data;
		while (!q.isEmpty()) {
			TreeNode node = q.poll();
			if (node.data > max) {
				max = node.data;
			}
			if (node.left != null) q.add(node.left);
			if (node.right != null) q.add(node.right);
		}
		return max;
	}

	public TreeNode findNode(TreeNode root, int val) {
		if (root != null) {
			Queue<TreeNode> q = new LinkedList<TreeNode>();
			q.add(root);
			while (!q.isEmpty()) {
				TreeNode node = q.poll();
				if (node.data == val) {
					return node;
				}
				if (node.left != null) q.add(node.left);
				if (node.right != null) q.add(node.right);
			}
		}
		return null;
	}

	public TreeNode findDeepest(TreeNode root) {
		TreeNode deepest = null;
		if (root != null) {
			Queue<TreeNode> q = new LinkedList<TreeNode>();
			q.add(root);
			while (!q.isEmpty()) {
				deepest = q.poll();
				if (deepest.left != null) q.add(deepest.left);
				if (deepest.right != null) q.add(deepest.right);
			}
		}
		return deepest;
	}

	//LCA = least common ancestor node = both n1 and n2 as descendants
	//and the paths from N->n1 and N->n2 are the shortest possible
	public TreeNode findLCA(TreeNode root, TreeNode a, TreeNode b) {
		if (root == null || a == null || b == null) return null;
		if (root == a || root == b) return root;

		TreeNode left = findLCA(root.left,a,b);
		TreeNode right = findLCA(root.right,a,b);
		if (left != null && right != null) return root;
		return (left != null ? left:right);

	}

	public TreeNode findLCA(TreeNode root,int a, int b) {
		if (root == null) return null;
		if (root.data == a || root.data == b) return root;

		TreeNode left = findLCA(root.left,a,b);
		TreeNode right = findLCA(root.right,a,b);
		if (left != null && right != null) return root;
		return (left != null ? left:right);
	}

	/**
	 * @param root of the binary Tree.
	 * @return The kth largest for a given binary tree.
	 */
	public TreeNode findKthLargest(TreeNode root, int k) {
		if (root == null) return null;
		int rightSize = 1;

		if (root.right != null) {
			rightSize = size(root.right) + 1;
		}
		//3 conditions
		if (k == rightSize) return root;
		if (k < rightSize) return findKthLargest(root.right,k);
		return findKthLargest(root.left, k - rightSize);

	}

	public int findMaxSumLevel(TreeNode root) { // the root i level 0
		if (root == null) return -1;
		int currlvl = 0, maxlvl = 0;
		int currSum = 0, maxSum = 0;
		Queue<TreeNode> q = new LinkedList<TreeNode>();
		q.add(root);
		q.add(null);   //indicated for the end of first level
		while (!q.isEmpty()) {
			TreeNode node = q.poll();

			if (node == null) {
				if (currSum > maxSum) {
					maxSum = currSum;
					maxlvl = currlvl;
				}
				currSum = 0; //reset
				if (!q.isEmpty()) {
					q.add(null); //indicate the end of the level 
				}
				currlvl++; // begin a new level
			}else {
				currSum +=node.data;
				if (node.left != null) q.add(node.left);
				if (node.right != null) q.add(node.right);
			}
		}
		return maxlvl;		
	}

	//half nodes are the nodes with exactly one children.
	public int numberOfHalfNodes(TreeNode root) {
		if (root == null) return 0;

		Queue<TreeNode> q = new LinkedList<TreeNode>();
		q.add(root);
		int count = 0;

		while (!q.isEmpty()) { //iterate the tree and count the half nodes
			TreeNode node = q.poll();
			if (node.left == null && node.right != null) {
				count++;
			} else if (node.left != null && node.right == null) {
				count++;
			}

			if (node.left != null) q.add(node.left);
			if (node.right != null) q.add(node.right);
		}

		return count;
	}

	public int numberOfFullNodes(TreeNode root) { 
		int count = 0;
		if (root == null )return count;

		Queue<TreeNode> q = new LinkedList<TreeNode>();
		q.add(root);
		while (!q.isEmpty()) {
			TreeNode node = q.poll();
			if (node.left != null && node.right != null) count++;
			if (node.left != null) q.add(node.left);
			if (node.right != null) q.add(node.right);
		}
		return count;	
	}

	public int numberOfLeaves(TreeNode root) {  //recursion
		/* int count = 0;
		    if (root == null) return count;

		    Queue<TreeNode> q = new LinkedList<>();
		    q.add(root);

		    while(!q.isEmpty()) {
		        TreeNode node = q.poll();
		        if (node.left == null && node.right == null) count ++;
		        if (node.left != null) q.add(node.left);
		        if (node.right != null) q.add(node.right);
		    }

		    return count;*/
		if(root == null) return 0;
		if(root.left == null && root.right == null) return 1;            
		return numberOfLeaves(root.left) + numberOfLeaves(root.right); 
	}

	public int maxSumpath(TreeNode root) {
		return 0;
	}

	public static ArrayList<Integer> preorderList = new ArrayList<>();
	public void preorder (TreeNode root) {
		if (root != null) {
			preorderList.add(root.data);
			preorder(root.left);
			preorder(root.right);

		}
	}

	public ArrayList<Integer> preorderItr(TreeNode root) {                   
		ArrayList<Integer> preorderedList =  new ArrayList<Integer>();
		Stack<TreeNode> s = new Stack<TreeNode>(); // use stack rather than queue

		if (root == null){return preorderedList;}

		s.push(root);
		while(!s.isEmpty()) {
			root = s.pop();
			preorderedList.add(root.data);
			if (root.right != null) {s.push(root.right);}
			if (root.left  != null) {s.push(root.left);}
		}
		return preorderedList;
	}

	public static ArrayList<Integer> postorderList = new ArrayList<>();
	public void postorder (TreeNode root) {

	}

	public ArrayList<Integer> inorderItr(TreeNode root) {
		ArrayList<Integer> inorder = new ArrayList<>();
		Stack<TreeNode> stack = new Stack<TreeNode>();
		while (true) {
			while (root != null) {
				stack.push(root);
				root = root.left;
			}
			if (stack.isEmpty()) break;
			root = stack.pop();
			inorder.add(root.data);
			root = root.right;
		}
		return inorder;
	}

	public ArrayList<Integer> levelorder(TreeNode root) {
		ArrayList<Integer> list = new ArrayList<>();
		return list;
	}

	public ArrayList<Integer> levelorderRev(TreeNode root) { 
		ArrayList<Integer> list = new ArrayList<>();
		if(root == null) return list; 

		Queue<TreeNode> q = new LinkedList<TreeNode>();
		Stack<TreeNode> s = new Stack<>();
		q.add(root);

		while (!q.isEmpty()) {
			TreeNode node = q.poll();
			s.push(node);
			if (node.right != null) q.add(node.right);
			if (node.left != null) q.add(node.left);

		}

		while (!s.isEmpty()) {
			list.add(s.pop().data);
		}
		return list;
	}

	public ArrayList<ArrayList<Integer>> printLevelByLevel(TreeNode root) {
		return null;
	}

	public int pathLengthFromRoot(TreeNode root, int val) {
		if(root == null) return 0;
		int out = 0;
		if ((root.data == val) || (out = pathLengthFromRoot(root.left,val))> 0 || (out = pathLengthFromRoot(root.right,val)) > 0) {
			return out + 1;
		}		
		return 0;
	}

	public int getNodeDistance(TreeNode root, int n1, int n2) { //ignore the given keys are all exist.
		int lca = findLCA(root,n1,n2).data;
		return (pathLengthFromRoot(root,n1) + pathLengthFromRoot(root,n2) - 2) - 2*(pathLengthFromRoot(root,lca) - 1);
	}

	public boolean isIdentical(TreeNode root1, TreeNode root2) { //recursion
		if (root1 == null && root2 == null) return true;
		if (root1 == null && root2 != null) return false;
		if (root1 != null && root2 == null) return false;
		return (root1.data == root2.data) && isIdentical(root1.left, root2.left) && isIdentical(root1.right,root2.right);
	}

	/**
	 * Given a String, the null is represent in *. Each node is separate by ","
	 * The order of the tree is from the root to lead (left-right)
	 * This method will contract a tree.
	 * @param str of nodes in level order.
	 * @return The root of the tree.
	 */
	public TreeNode decompressTree(String str) {
		String[] nodesSplit = str.split(",");
		List<Integer> list = new ArrayList<Integer>();
		//initialize the list with the tree data
		for (int i = 0; i < nodesSplit.length; i++) {
			Integer value = nodesSplit[i].equals("*") ? null:Integer.valueOf(nodesSplit[i]);		
			list.add(value);
		}

		TreeNode root = list.get(0) == null ? null :new TreeNode(list.get(0));
		Queue<TreeNode> q = new LinkedList<>();
		q.add(root);
		int i =0, size = list.size();	

		while (i < size) { //iterate the tree
			TreeNode node = q.poll();
			if (node != null) { // Contract the tree
				Integer leftVal = i+1 < size ? list.get(i+1):null;
				Integer rightVal = i+2 < size ? list.get(i+2):null;
				TreeNode leftNode = leftVal == null ? null:new TreeNode(leftVal);
				TreeNode rightNode = rightVal == null ? null:new TreeNode(rightVal);
				node.left = leftNode;
				node.right = rightNode;

				if (node.left != null) q.add(node.left);
				if (node.right != null) q.add(root.right);	
			}			
			i += 2;
		}
		return root;
	}

	/**
	 * @param root of a binary tree
	 * @return The root of the mirrored tree.
	 */
	public TreeNode mirror(TreeNode root) {
		//if (root == null) return null;
		TreeNode temp = null;

		if (root != null) {
			mirror(root.left);
			mirror(root.right);
			//swap the left and right
			root = root.left;
			root.left = root.right;
			root.right  = temp;
		}
		return root;
	}

	public boolean isMirror(TreeNode root1, TreeNode root2) {	//recursion              
		//case when both or one of the tree is null
		if(root1 == null && root2 == null) return true;
		if(root1 == null || root2 == null) return false;
		//if both trees are not null
		if(root1.data != root2.data) return false;
		return (isMirror(root1.left, root2.right) && isMirror(root1.right, root2.left));
	}


}   