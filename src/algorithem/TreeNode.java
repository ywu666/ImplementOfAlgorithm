package algorithem;

import java.util.*;

class TreeNode {
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

	public int size (TreeNode root) {
		if (root == null) return 0;
		return size(root.left) + size(root.right) + 1;
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
		return 0;
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

	public static ArrayList<Integer> preorderList = new ArrayList<>();
	public void preorder (TreeNode root) {
		if (root != null) {
			preorderList.add(root.data);
			preorder(root.left);
			preorder(root.right);

		}
	}

	public static ArrayList<Integer> postorderList = new ArrayList<>();
	public void postorder (TreeNode root) {

	}

	public ArrayList<Integer> inorderItr(TreeNode root) {
		ArrayList<Integer> inorder = new ArrayList<>();
		Stack<TreeNode> stack = new Stack<TreeNode>();
		while (true) {
			if (root != null) {
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

	public int pathLengthFromRoot(TreeNode root, int val) {
		if(root == null) return 0;
		int out =0;
		if ((root.data == val) || (out = pathLengthFromRoot(root.left,val))> 0 || (out = pathLengthFromRoot(root.left,val)) > 0) {
			return out + 1;
		}		
		return 0;
	}


	public boolean isIdentical(TreeNode root1, TreeNode root2) {
		return false;
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
	/**
	 * @param root of a binary tree
	 * @return The root of the mirroed tree.
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
}   
