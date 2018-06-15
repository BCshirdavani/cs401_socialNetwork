import edu.princeton.cs.algs4.Bag;

import java.util.*;

public class WeightedDirectedGraph {

	private final int vertices;
	private int edges;
	private Bag<DirectedEdge>[] adjFromHereOut;
	private Bag<DirectedEdge>[] adjInToHere;


	public WeightedDirectedGraph(int vertices) {
		this.vertices = vertices;
		adjFromHereOut = new Bag[vertices];
		for (int v = 0; v < vertices; v++) {
			adjFromHereOut[v] = new Bag<DirectedEdge>();
//			adjInToHere[v] = new Bag<DirectedEdge>();
		}
	}


	public void addEdge(DirectedEdge edge) {
		int from = edge.from();
		int to = edge.to();
		adjFromHereOut[from].add(edge);
//		adjInToHere[to].add(edge);
		edges++;
	}


	public Iterable<DirectedEdge> adj(int v){
		return adjFromHereOut[v];
//		return adjFromHereOut.get(v);
	}

	public int getVertices() {
		return vertices;
	}

	public int getEdges() {
		return edges;
	}

}
