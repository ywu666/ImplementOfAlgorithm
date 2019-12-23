package algorithem;


class Vertex implements Comparable<Vertex> {
    public final String name;
    public Edge[] adjacencies;
    public double minDistance = Double.POSITIVE_INFINITY;
    public Vertex previous; // Previous optimal path node

    public Vertex(String argName) {
        name = argName; 
    }

    public String toString() { 
        return name; 
    }

    public int compareTo(Vertex other) {
        return Double.compare(minDistance, other.minDistance);
    }
}

class Edge {
    public final Vertex target; // Target node of the edge
    public final double weight; // Edge weight

    public Edge(Vertex argTarget, double argWeight) { 
        target = argTarget; 
        weight = argWeight; 
    }
}
