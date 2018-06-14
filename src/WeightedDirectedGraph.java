import edu.princeton.cs.algs4.Bag;

public class WeightedDirectedGraph {

	private final int vertices;
	private int edges;
	private Bag<DirectedEdge>[] adj;

	public WeightedDirectedGraph(int vertices) {
		this.vertices = vertices;
		adj = new Bag[vertices];
		for (int v = 0; v < vertices; v++) {
			adj[v] = new Bag<DirectedEdge>();
		}
	}

	public void addEdge(DirectedEdge edge) {
		int from = edge.from();
		int to = edge.to();
		adj[from].add(edge);
		edges++;
	}
	public Iterable<DirectedEdge> adj(int v){
		return adj[v];
	}
	public int getVertices() {
		return vertices;
	}
	public int getEdges() {
		return edges;
	}

}
