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
//			System.out.println("v: " + v + ", w: " + w + "\t\t edges: " + friend_g.getEdges() + " vert: " + friend_g.getVertices());
			DirectedEdge edge = new DirectedEdge(v, w, 0);
			friend_g.addEdge(edge);
//			System.out.println(edge);
		}

//		//	****************** LIST FRIENDS ********************************
//		// who is friends with 2
//		System.out.println("adjascent from (2): ");
//		for(DirectedEdge w:friend_g.adj(2)){
//			System.out.println(w);
//		}
//
//
//		//	****************** COMMON FRIENDS ******************************
//		System.out.println("common friends (2) and (275): ");
//		for(DirectedEdge w:friend_g.adj(2)){
//			System.out.println(w);
//		}




		// make music graph
//		System.out.println("music graph");
		Scanner mus_scan = new Scanner(new File("dataset/user_artists.dat"));
		mus_scan.nextLine();
		while(mus_scan.hasNextLine()){
			int v = parseInt(mus_scan.next());
			int w = parseInt(mus_scan.next());
			int weight = parseInt(mus_scan.next());
			DirectedEdge edge = new DirectedEdge(v, w, weight);
			music_g.addEdge(edge);
//			System.out.println(edge);
		}

//		// Friends of 2 like this
//		System.out.println("\t(2) has friends who like this stuff:");
//		for(DirectedEdge w:friend_g.adj(2)){
//			int vert = w.to();
//			for(DirectedEdge j:music_g.adj(vert)){
//				System.out.println("\t\t friend of (2): " + j);
//			}
//		}

		System.out.println("*****************************************");
		// TEST FUNCITONS
		Functions func = new Functions(friend_g, music_g);
//		func.listFriends(46);
//		func.commonFriends(46,58);
//		func.listArtists(2, 275);
		func.listTop10();
		func.recommend10(66);


//
//
//		// *********** TOP 10 GLOBAL *****************************************
//		// Global music rank scores to map
//		TreeMap<Integer, Integer> musicMap = new TreeMap<Integer, Integer>();
//		// for each vertex
//			// for each adj edge
//				// add to sum for the artist pointing to
//		for(int i = 0; i < music_g.getVertices(); i++){
//			for(DirectedEdge edge : music_g.adj(i)){
//				Integer artKey = edge.to();
//				Integer newW = (int)edge.weight();
//				//	update old value if found in map
//				if(musicMap.containsKey(artKey)){
//					Integer oldW = musicMap.get(artKey);
//					musicMap.put(artKey,(newW + oldW));
//				}
//				//	add to map if does not exist
//				else if (!(musicMap.containsKey(artKey))){
//					musicMap.put(artKey, newW);
//				}
//			}
//		}
//		// sorting the top artists
//		// put scores into array list
//		ArrayList<Integer> scoreArray = new ArrayList<Integer>();
//		for(Map.Entry<Integer,Integer> entry : musicMap.entrySet()){
//			scoreArray.add(entry.getValue());
//		}
////		System.out.println(scoreArray);
//		Collections.sort(scoreArray);
//		Collections.reverse(scoreArray);
////		System.out.println(scoreArray);
//		// use top 10 values, and get those keys from the map
//		for(int i = 0; i < 10; i++){
//			int findVal = scoreArray.get(i);
//			for(Map.Entry<Integer,Integer> entry : musicMap.entrySet()){
//				if(findVal == entry.getValue()){
//					System.out.println("rank " + (i+1) + ": " +  entry);
//				}
//			}
//		}
//
//
//		// ************** RECOMMEND 10 ********************
//		// for each friend
//			// for each adj artist
//				// add sum to artist map
//


	}



}
