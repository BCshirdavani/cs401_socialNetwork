//import edu.princeton.cs.algs4.Bag;

import java.util.ArrayList;
import java.util.List;

public class UWDirectedGraphCustom {
	private int vertices;
	private int edges;
	private List<List<Integer>> adj;

	public UWDirectedGraphCustom(int vertices) {
		this.vertices = vertices;
		adj = new ArrayList<List<Integer>>(vertices);
		for (int i = 0; i < adj.size(); i++) {
			adj.add(new ArrayList());
		}
	}

	public void createDirectedEdge(int v, int w) {
		adj.get(v).add(w);
		edges++;
	}

	public Iterable<Integer> adj(int v){
		return adj.get(v);
	}
	public int getVertices() {
		return vertices;
	}

	public int getEdges() {
		return edges;
	}
	public UWDirectedGraphCustom reverse() {
		UWDirectedGraphCustom rg = new UWDirectedGraphCustom(vertices);
		for(int v=0;v<vertices;v++) {
			for(int w:adj(v)) {
				rg.createDirectedEdge(w, v);
			}
		}
		return rg;
	}

	public String toString() {
		StringBuilder str = new StringBuilder();
		for (int v = 0; v < vertices; v++) {
			str.append(v + ":");
			for(int w:adj[v]) {
				str.append(w + " ");
			}
			str.append("\n");
		}
		return str.toString();
	}

}
