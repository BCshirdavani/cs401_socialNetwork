import edu.princeton.cs.algs4.ST;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.*;

import static java.lang.Integer.parseInt;

public class MainClient {


	public static void main(String[] args) throws FileNotFoundException {

		// TODO: add hash table to connect names with ID's

		int friendVert = 2101;	// was 1892
		int musicVert = 17632;
		WeightedDirectedGraph friend_g = new WeightedDirectedGraph(friendVert);
		WeightedDirectedGraph music_g = new WeightedDirectedGraph(musicVert);

		// make friends graph
		Scanner fr_scan = new Scanner(new File("dataset/user_friends.dat"));
		fr_scan.nextLine();
		while(fr_scan.hasNextLine()){

			int v = parseInt(fr_scan.next());
			int w = parseInt(fr_scan.next());
			DirectedEdge edge = new DirectedEdge(v, w, 0);
			friend_g.addEdge(edge);
		}

		// make music graph
		Scanner mus_scan = new Scanner(new File("dataset/user_artists.dat"));
		mus_scan.nextLine();
		while(mus_scan.hasNextLine()){
			int v = parseInt(mus_scan.next());
			int w = parseInt(mus_scan.next());
			int weight = parseInt(mus_scan.next());
			DirectedEdge edge = new DirectedEdge(v, w, weight);
			music_g.addEdge(edge);
		}

		// make symbol table
		ST symbolTable = new ST<Integer, String>();
		Scanner art_scan = new Scanner(new File("dataset/artists.dat"));
		art_scan.useDelimiter("\t");
		String garbage = art_scan.nextLine();
		System.out.println("garbage: " + garbage);
		while(art_scan.hasNextLine()){
			String line = art_scan.nextLine();
			String[] parts = line.split("\t");
			int ID = parseInt(parts[0]);
			String name = parts[1];
			symbolTable.put(ID, name);
		}


		System.out.println("*****************************************");
		// TEST FUNCITONS
		Functions func = new Functions(friend_g, music_g, symbolTable);
//		func.listFriends(46);
//		func.commonFriends(46,58);
//		func.listArtists(2, 275);
		func.listTop10();
		func.recommend10(66);




	}



}
