package algorithem;

import java.util.*;


class Node {
	public String data; // data element
	public boolean visited=false; // flag to track the already visited node
	public List<Node> adjacentNodes = new LinkedList<Node>(); // adjacency list

	public Node(String data){
		this.data = data;
	}

	public void addAdjacentNode(final Node node){
		adjacentNodes.add(node);
		node.adjacentNodes.add(this);
	}

	public boolean breathFirstSearch(Node root,String data) {
		if (data == null || root == null) return false;
		Queue<Node> queue = new LinkedList<>();
		queue.add(root);
		root.visited = true;
		
		while (!queue.isEmpty()) {
			Node node = queue.poll();
			if (node.data != null && data.equals(node.data)) {
				return true;
			}else {
				for (Node n:node.adjacentNodes) {
					if (!n.visited) {
						queue.add(n);
						n.visited = true;
					}
				}
			}
		}
		return false;		
	} 
}
