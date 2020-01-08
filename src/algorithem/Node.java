package algorithem;

import java.util.*;


class Node {
	public String data; // data element
	public boolean visited = false; // flag to track the already visited node
	public List<Node> adjacentNodes = new LinkedList<Node>(); // adjacency list

	public Node(String data){
		this.data = data;
	}

	public void addAdjacentNode(final Node node){
		adjacentNodes.add(node);
		node.adjacentNodes.add(this);
	}

	public boolean breathFirstSearch(Node root, String data) {
		if (data == null || root == null) return false;
		Queue<Node> queue = new LinkedList<>();
		queue.add(root);
		root.visited = true;
		
		while (!queue.isEmpty()) {
			Node node = queue.poll();
			if (node.data != null && data.equals(node.data)) {
				return true;
			}else {
				for (Node adj:node.adjacentNodes) {
					if (!adj.visited) {
						queue.add(adj);
						adj.visited = true;
					}
				}
			}
		}
		return false;		
	} 
	
	public boolean depthFirstSearch(Node rootNode, String data){
		if (data == null || rootNode == null) return false;		
		Stack<Node> stack = new Stack<Node>();
		stack.add(rootNode);
		rootNode.visited = true;
		
		while(!stack.isEmpty()){
			Node node = stack.pop();
			if(node.data != null && node.data.equals(data)) {
				return true;
			}
			for(Node adj:node.adjacentNodes){
				if(!adj.visited){
					stack.push(adj);
					adj.visited = true;
				}
			}
		}              
		return false;
	}
}
