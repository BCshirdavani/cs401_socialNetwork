import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class MainClient {


	public static void main(String[] args) throws FileNotFoundException {
//		ArrayList thing = new ArrayList(10);
//		thing.add(1);
//		thing.add(5);
//		System.out.println(thing.contains(1));
//		System.out.println(thing.contains(2));
//		System.out.println(thing.size());
//		System.out.println("thing.get(0): " + thing.get(0));
//		System.out.println("thing.get(1): " + thing.get(1));
//		System.out.println("thing.indexOf(5): " + thing.indexOf(5));

		int friendVert = 20;
		int musicVert = 20;
		WeightedDirectedGraph friend_g = new WeightedDirectedGraph(friendVert);
		WeightedDirectedGraph music_g = new WeightedDirectedGraph(musicVert);

		// make friends graph
		Scanner friends = new Scanner(new File("Friends.txt"));
		while(friends.hasNextLine()){
			int v = friends.nextInt();
			int w = friends.nextInt();
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
		Scanner music = new Scanner(new File("Music.txt"));
		while(music.hasNextLine()){
			int v = music.nextInt();
			int w = music.nextInt();
			int weight = music.nextInt();
			DirectedEdge edge = new DirectedEdge(v, w, weight);
			music_g.addEdge(edge);
			System.out.println(edge);
		}

		// Friends of 1 like this
		System.out.println("(1) has friends who like this stuff:");
		for(DirectedEdge w:friend_g.adj(1)){
			int vert = w.to();
			for(DirectedEdge j:music_g.adj(vert)){
				System.out.println(j);
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
		// TODO: sort by value, the map of artists and their weights


	}



}
