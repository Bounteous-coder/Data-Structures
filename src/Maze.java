// Numan Salahuddin - 251264939
// Importing necessary packages for file handling and data structures
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

// Public class maze defined and it represents the maze.
public class Maze {
	// Declaring private class variables
	private Graph graph;            // This Graph to represent the maze
	private GraphNode entrance;     // Entrance of the maze
	private GraphNode exit;         // Exit point of the maze
	private int labWidth;           // Width of  maze
	private int labLength;          // Length of  maze

	// Constructor for file name of the maze
	public Maze(String mazeFile) throws MazeException {
		try {
			// Reading the maze file
			BufferedReader input = new BufferedReader(new FileReader(mazeFile));

			// Reading the size, width, and length of the maze from the file
			int roomSize = Integer.parseInt(input.readLine());
			labWidth = Integer.parseInt(input.readLine());
			labLength = Integer.parseInt(input.readLine());

			// Calculating the number of nodes in the graph
			int numNodes = (2 * labWidth - 1) * (2 * labLength - 1);

			// Creating a new Graph
			this.graph = new Graph(numNodes);

			// Creating the maze structure by processing the file
			this.createMaze(input);


			input.close();
		} catch (IOException | GraphException e) {
			// Handling exceptions related to file reading or graph creation
			throw new MazeException("Error reading maze file");
		}
	}

	// Helper method to create the maze from the input file
	private void createMaze(BufferedReader input) throws IOException, GraphException {
		// Creating a 2D array to represent the maze
		char[][] lab = new char[2 * labLength - 1][2 * labWidth - 1];

		for (int row = 0; row < 2 * labLength - 1; ++row) {
			String line = input.readLine();
			for (int i = 0, col = 0; i < line.length(); ++i) {

				lab[row][i] = line.charAt(i);
				switch (line.charAt(i)) {
					case 's':
						entrance = graph.getNode(row / 2, i / 2);
						break;
					case 'x':
						exit = graph.getNode(row / 2, i / 2);
						break;
					case 'c':
						if (row % 2 == 0) {
							if (i > 0 && lab[row][i - 1] == 'c') {
								GraphNode node1 = graph.getNode(row / 2, (i - 1) / 2);
								GraphNode node2 = graph.getNode(row / 2, i / 2);
								graph.insertEdge(node1, node2, 0, ""); // Assuming corridor type is 0
							}
						} else {
							if (lab[row - 1][i] == 'c') {
								GraphNode node1 = graph.getNode((row - 1) / 2, i / 2);
								GraphNode node2 = graph.getNode(row / 2, i / 2);
								graph.insertEdge(node1, node2, 0, ""); // Assuming corridor type is 0
							}
						}
						break;
				}
			}
		}
	}

	// Method to find a solution using Breadth-First Search
	public Iterator<GraphEdge> solve() {
		Queue<GraphNode> queue = new LinkedList<>();
		Set<GraphNode> visited = new HashSet<>();
		Map<GraphNode, GraphNode> parentMap = new HashMap<>();
		List<GraphEdge> path = new ArrayList<>();

		// Adding the entrance to the queue and marking it as visited
		queue.add(entrance);
		visited.add(entrance);

		// Performing Breadth-First Search
		while (!queue.isEmpty()) {
			GraphNode current = queue.poll();

			// Checking if the exit is reached
			if (current.equals(exit)) {
				// Reconstructing the path from exit to entrance
				GraphNode node = current;
				while (parentMap.containsKey(node)) {
					GraphNode parent = parentMap.get(node);
					try {
						GraphEdge edge = graph.getEdge(node, parent);
						path.add(edge);
					} catch (GraphException e) {
						e.printStackTrace();
					}
					node = parent;
				}
				Collections.reverse(path);
				return path.iterator();
			}

			try {
				// Exploring neighbors and adding them to the queue if not visited
				Iterator<GraphEdge> edges = graph.incidentEdges(current);
				while (edges.hasNext()) {
					GraphEdge edge = edges.next();
					GraphNode neighbor = edge.getOtherNode(current);

					if (!visited.contains(neighbor)) {
						visited.add(neighbor);
						parentMap.put(neighbor, current);
						queue.add(neighbor);
					}
				}
			} catch (GraphException e) {
				e.printStackTrace();
			}
		}

		return Collections.emptyIterator(); // No path found
	}
}
