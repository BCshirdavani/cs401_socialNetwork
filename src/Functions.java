import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.ST;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;


public class Functions {
    private WeightedDirectedGraph g_friends;
    private WeightedDirectedGraph g_music;
    private ST<Integer,String> SymTab_artID;


    public Functions(WeightedDirectedGraph friends, WeightedDirectedGraph music, ST table){
        this.g_friends = friends;
        this.g_music = music;
        this.SymTab_artID = table;
    }

    //	****************** LIST FRIENDS ***************************
    public void listFriends(int user){
        System.out.println(user + " has the following friends: ");
        for(DirectedEdge w:g_friends.adj(user)){
            System.out.println("\t" + w);
        }
    }

    //	****************** COMMON FRIENDS *************************
    public void commonFriends(int user1, int user2){
        ArrayList<Integer> friendsOf_1 = new ArrayList<Integer>();
        ArrayList<Integer> friendsOf_2 = new ArrayList<Integer>();
        for(DirectedEdge edge : g_friends.adj(user1)){
            friendsOf_1.add(edge.to());
        }
        for(DirectedEdge edge : g_friends.adj(user2)){
            friendsOf_2.add(edge.to());
        }
        System.out.println(user1 + " and " + user2 + " have the following friends in common:");
        for(int i = 0; i < friendsOf_1.size(); i++){
//            System.out.println("friendsOf_1.get(" + i + "): " + friendsOf_1.get(i));
            if(friendsOf_2.contains(friendsOf_1.get(i))){
                System.out.println("\t" + friendsOf_1.get(i));
            }
        }
    }

    //	****************** LIST ARTISTS *************************
    public void listArtists(int user1, int user2){
        ArrayList<Integer> artOf_1 = new ArrayList<Integer>();
        ArrayList<Integer> artOf_2 = new ArrayList<Integer>();
        for(DirectedEdge edge : g_music.adj(user1)){
            artOf_1.add(edge.to());
        }
        for(DirectedEdge edge : g_music.adj(user2)){
            artOf_2.add(edge.to());
        }
        System.out.println(user1 + " and " + user2 + " both listen to these artists:");
        for(int i = 0; i < artOf_1.size(); i++){
            if(artOf_2.contains(i)){
                System.out.println("\t" + i);
            }
        }
    }

    // *********** TOP 10 GLOBAL *****************************************
    public void listTop10(){
        TreeMap<Integer, Integer> musicMap = new TreeMap<Integer, Integer>();
        for(int i = 0; i < g_music.getVertices(); i++){
            for(DirectedEdge edge : g_music.adj(i)){
                Integer artKey = edge.to();
                Integer newW = (int)edge.weight();
                //	update old value if found in map
                if(musicMap.containsKey(artKey)){
                    Integer oldW = musicMap.get(artKey);
                    musicMap.put(artKey,(newW + oldW));
                }
                //	add to map if does not exist
                else if (!(musicMap.containsKey(artKey))){
                    musicMap.put(artKey, newW);
                }
            }
        }
        ArrayList<Integer> scoreArray = new ArrayList<Integer>();
        for(Map.Entry<Integer,Integer> entry : musicMap.entrySet()){
            scoreArray.add(entry.getValue());
        }
        Collections.sort(scoreArray);
        Collections.reverse(scoreArray);
        // use top 10 values, and get those keys from the map
        System.out.println("Top 10 Ranking is:");
        System.out.printf("%-22s%-22s%-22s%-22s\n", "Rank", "Name","ID","Count");
        for(int i = 0; i < 10; i++){
            int findVal = scoreArray.get(i);
            for(Map.Entry<Integer,Integer> entry : musicMap.entrySet()){
                if(findVal == entry.getValue()){
//                    System.out.println("\trank " + (i+1) + ": ID:" +  entry.getKey() + "\t\t" +  SymTab_artID.get(entry.getKey()));
//                    System.out.println("\trank " + (i+1) + ": " + SymTab_artID.get(entry.getKey()) + "\t\tID: " + entry.getKey() + "\t\tCount: " + entry.getValue());
                    System.out.printf("%-22s%-22s%-22s%-22s\n", (i+1), SymTab_artID.get(entry.getKey()),entry.getKey(),entry.getValue());

                }
            }
        }
    }

    // *********** RECOMMEND 10 *****************************************
    public void recommend10(int user){
        TreeMap<Integer, Integer> musicMap = new TreeMap<Integer, Integer>();
        // for each friend in my adj list
        for(DirectedEdge edge : g_friends.adj(user)){
            // for each of their music adj list
            for(DirectedEdge artistEdge : g_music.adj(edge.to())){
                int artKey = artistEdge.to();
                int newW = (int)artistEdge.weight();
                // update old weight if artist found
                if (musicMap.containsKey(artKey)){
                    int oldW = musicMap.get(artKey);
                    musicMap.put(artKey, (oldW + newW));
                }
                // add to map if does not exist
                else if (!(musicMap.containsKey(artKey))){
                    musicMap.put(artKey, newW);
                }
            }
        }
        ArrayList<Integer> scoreArray = new ArrayList<Integer>();
        for(Map.Entry<Integer,Integer> entry : musicMap.entrySet()){
            scoreArray.add(entry.getValue());
        }
        Collections.sort(scoreArray);
        Collections.reverse(scoreArray);
        System.out.println("friends of " + user + " recommend the following:");
        System.out.printf("%-22s%-22s%-22s%-22s\n", "Rank", "Name","ID","Count");
        for(int i = 0; i < 10; i++){
            int findVal = scoreArray.get(i);
            for(Map.Entry<Integer,Integer> entry : musicMap.entrySet()){
                if(findVal == entry.getValue()){
//                    System.out.println("\trank " + (i+1) + ": " +  entry + "\t\t" +  SymTab_artID.get(entry.getKey()));
                    System.out.printf("%-22s%-22s%-22s%-22s\n", (i+1), SymTab_artID.get(entry.getKey()),entry.getKey(),entry.getValue());
                }
            }
        }
    }



}
