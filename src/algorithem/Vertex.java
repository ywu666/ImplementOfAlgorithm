package algorithem;

import java.util.*;

class Vertex implements Comparable<Vertex> {
	public final String name;
	public Edge[] adjacencies;
	public double minDistance = Double.POSITIVE_INFINITY;
	public Vertex previous; // Previous optimal path node

	public Vertex(String name) {
		this.name = name; 
	}

	public String toString() { 
		return name; 
	}

	public int compareTo(Vertex other) {
		return Double.compare(minDistance, other.minDistance);
	}
	
	public static List<Vertex> getShortestPath(Vertex source, Vertex target) {
		computePaths(source);	
		return getShortestPathTo(target);
	}
	
	public static List<Vertex> getShortestPathTo(Vertex target) {
		List<Vertex> path = new ArrayList<Vertex>();
		for (Vertex vertex = target; vertex != null; vertex = vertex.previous) {
			path.add(vertex);
		}
		
		Collections.reverse(path);
		return path;
	}
	
	//These are help functions to compute shortest path and store in the vertex
	public static void computePaths(Vertex source) {
		source.minDistance = 0;
		PriorityQueue<Vertex> q = new PriorityQueue<>();
		q.add(source);
		while (!q.isEmpty()) {
			Vertex u = q.poll();
			for (Edge e:u.adjacencies) {
				Vertex v = e.target;
				double distanceThroughU = u.minDistance + e.weight;
				if (distanceThroughU < v.minDistance) {
					q.remove(v);
					v.minDistance = distanceThroughU;
					v.previous =u;
					q.add(v);
				}
			}
		}
	}
	
	
	class Edge { //Inner class
		public final Vertex target; // Target node of the edge
		public final double weight; // Edge weight

		public Edge(Vertex target, double weight) { 
			this.target = target; 
			this.weight = weight; 
		}
	}
}


