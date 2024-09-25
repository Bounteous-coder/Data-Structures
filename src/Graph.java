// Numan Salahuddin - 251264939
// Importing Packages
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Graph {

	private int numNodes;  // Number of nodes in the graph
	private ArrayList<GraphNode> nodes; // List to store graph nodes
	private ArrayList<ArrayList<GraphEdge>> adjacencyList; // representation of the adjacency list of the graph

	public GraphEdge getEdge(GraphNode nodeu, GraphNode nodev) throws GraphException {  // Method to retrieve an edge between two nodes in the graph
		if (!isValidNode(nodeu) || !isValidNode(nodev)) {
			throw new GraphException("Invalid nodes for edge retrieval");
		}

		List<GraphEdge> edges = adjacencyList.get(nodeu.getName());
		for (GraphEdge edge : edges) {
			if (edge.isIncidentOnNode(nodev)) {
				return edge; // Returns the edge if found
			}
		}
		// Does not return if edge not found.
		return null;
	}

	public Graph(int numNodes) {  // Constructor to initialize a graph with given number of nodes.
		this.numNodes = numNodes;
		this.nodes = new ArrayList<>();
		this.adjacencyList = new ArrayList<>();

		for (int i = 0; i < numNodes; i++) {
			nodes.add(new GraphNode(i));
			adjacencyList.add(new ArrayList<>());
		}
	}

	// Method created to insert an edge between 2 nodes in the graph.
	public void insertEdge(GraphNode nodeu, GraphNode nodev, int type, String label) throws GraphException {
		if (!isValidNode(nodeu) || !isValidNode(nodev)) {
			throw new GraphException("Invalid nodes for edge insertion");
		}

		GraphEdge edge = new GraphEdge(nodeu, nodev, type, label);  // Creating new edge between 2 nodes
		adjacencyList.get(nodeu.getName()).add(edge);
		adjacencyList.get(nodev.getName()).add(edge); // Assuming the graph is undirected
	}

	public GraphNode getNode(int u) throws GraphException {  // Method to get a node by its index in the graph
		if (u < 0 || u >= numNodes) {
			throw new GraphException("Invalid node index");
		}
		return nodes.get(u);
	}
	private int labWidth;

	public GraphNode getNode(int row, int col) throws GraphException {  // Method to get a node by its row and column.
		int index = row * (2 * labWidth - 1) + col;  // Calculates the index using row and column.
		if (index < 0 || index >= numNodes) {
			throw new GraphException("Invalid node index");
		}
		return nodes.get(index);
	}

	public Iterator incidentEdges(GraphNode u) throws GraphException {
		if (!isValidNode(u)) {
			throw new GraphException("Invalid node for incident edges");
		}
		return adjacencyList.get(u.getName()).iterator();
	}

	public boolean areAdjacent(GraphNode u, GraphNode v) throws GraphException {
		if (!isValidNode(u) || !isValidNode(v)) {
			throw new GraphException("Invalid nodes for adjacency check");
		}
		return adjacencyList.get(u.getName()).stream().anyMatch(edge -> edge.isIncidentOnNode(v));
	}

	// Private method to check if a given node is valid
	private boolean isValidNode(GraphNode node) {
		return node != null && node.getName() >= 0 && node.getName() < numNodes;
	}
}
