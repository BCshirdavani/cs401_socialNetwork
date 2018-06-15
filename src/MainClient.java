import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.*;

import static java.lang.Integer.parseInt;

public class MainClient {


	public static void main(String[] args) throws FileNotFoundException {

		// TODO: load real data now
		// TODO: add hash table to connect names with ID's
///Users/shimac/Documents/ComputerSci/cs401_algo/hw04/cs401_socialNetwork/dataset/artists.dat

		int friendVert = 2101;	// was 1892
		int musicVert = 17632;
		WeightedDirectedGraph friend_g = new WeightedDirectedGraph(friendVert);
		WeightedDirectedGraph music_g = new WeightedDirectedGraph(musicVert);

		// make friends graph
		Scanner fr_scan = new Scanner(new File("dataset/user_friends.dat"));
//		fr_scan.useDelimiter("\t");
		fr_scan.nextLine();
		while(fr_scan.hasNextLine()){
//			int v = fr_scan.nextInt();
//			int w = fr_scan.nextInt();
			int v = parseInt(fr_scan.next());
			int w = parseInt(fr_scan.next());
			System.out.println("v: " + v + ", w: " + w + "\t\t edges: " + friend_g.getEdges() + " vert: " + friend_g.getVertices());
			DirectedEdge edge = new DirectedEdge(v, w, 0);
			friend_g.addEdge(edge);
			System.out.println(edge);
		}

		// who is friends with 1
		System.out.println("adjascent from (1): ");
		for(DirectedEdge w:friend_g.adj(1)){
			System.out.println(w);
		}

		// make music graph
		System.out.println("music graph");
		Scanner mus_scan = new Scanner(new File("dataset/user_artists.dat"));
		mus_scan.nextLine();
		while(mus_scan.hasNextLine()){
//			int v = mus_scan.nextInt();
//			int w = mus_scan.nextInt();
			int v = parseInt(mus_scan.next());
			int w = parseInt(mus_scan.next());
//			int weight = mus_scan.nextInt();
			int weight = parseInt(mus_scan.next());
			DirectedEdge edge = new DirectedEdge(v, w, weight);
			music_g.addEdge(edge);
			System.out.println(edge);
		}

		// Friends of 1 like this
		System.out.println("\t(2) has friends who like this stuff:");
		for(DirectedEdge w:friend_g.adj(2)){
			int vert = w.to();
			for(DirectedEdge j:music_g.adj(vert)){
				System.out.println("\t\t friend of (2): " + j);
			}
		}

		// Global music rank
		TreeMap<Integer, Integer> musicMap = new TreeMap<Integer, Integer>();
		musicMap.put(11,0);
		musicMap.put(12,0);
		musicMap.put(13,0);
		for(int i = 1; i <= 3; i++){
			for(DirectedEdge w:music_g.adj(i)){
				int old = musicMap.get(w.to());
				int newData = (int)w.weight();
				musicMap.put(w.to(), old + newData);
			}
		}
		System.out.println("printing musicMap:\n" + musicMap);
		// TODO: program works...but very sensitive to index bounds in loops referencing adj[]

		// sorting the top artists
		// put scores into array list
		ArrayList<Integer> scoreArray = new ArrayList<Integer>();
		for(Map.Entry<Integer,Integer> entry : musicMap.entrySet()){
			scoreArray.add(entry.getValue());
		}
		System.out.println(scoreArray);
		Collections.sort(scoreArray);
		Collections.reverse(scoreArray);
		System.out.println(scoreArray);
		// use top 10 values, and get those keys from the map
		for(int i = 0; i < 2; i++){
			int findVal = scoreArray.get(i);
			for(Map.Entry<Integer,Integer> entry : musicMap.entrySet()){
				if(findVal == entry.getValue()){
					System.out.println("rank " + (i+1) + ": " +  entry);
				}
			}
		}
	}



}
