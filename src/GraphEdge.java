// Numan Salahuddin - 251264939

// Definition of a class named 'GraphEdge' to represent an edge in a graph
public class GraphEdge {
	// Private instance variables to store edge properties
	private int type;           // Type of the edge
	private GraphNode node1;    // First endpoint node of the edge
	private GraphNode node2;    // Second endpoint node of the edge
	private String label;       // Additional label information for the edge

	// Constructor to initialize the GraphEdge with its properties
	public GraphEdge(GraphNode node1, GraphNode node2, int type, String label) {
		this.node1 = node1;
		this.node2 = node2;
		this.type = type;
		this.label = label;
	}

	// Getter method
	public int getType() {  // To retrieve the type of the edge
		return type;
	}

	// Getter method
	public GraphNode firstEndpoint() { // To retrieve the first endpoint node of the edge
		return node1;
	}

	// Getter method
	public GraphNode secondEndpoint() { // To retrieve the second endpoint node of the edge
		return node2;
	}

	// Getter method
	public String getLabel() {  // To retrieve the label of the edge
		return label;
	}

	// Getter method
	public GraphNode getNode1() {   // To retrieve the first endpoint node of the edge
		return node1;
	}

	// Getter method
	public GraphNode getNode2() {   // To retrieve the second endpoint node of the edge
		return node2;
	}

	// Method to get the endpoint node that is not equal to the provided node
	public GraphNode getOtherNode(GraphNode node) {
		if (node.equals(node1)) {
			return node2;
		} else if (node.equals(node2)) {
			return node1;
		} else {
			// Throw an exception if provided node is not part of this edge
			throw new IllegalArgumentException("Provided node is not part of this edge.");
		}
	}

	// Method to check if the edge is incident on a given node
	public boolean isIncidentOnNode(GraphNode node) {
		return node.equals(node1) || node.equals(node2);
	}
}