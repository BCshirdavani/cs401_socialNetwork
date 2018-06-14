import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainClient {


	public static void main(String[] args){
		ArrayList thing = new ArrayList(10);
		thing.add(1);
		thing.add(5);
		System.out.println(thing.contains(1));
		System.out.println(thing.contains(2));
		System.out.println(thing.size());
		System.out.println("thing.get(0): " + thing.get(0));
		System.out.println("thing.get(1): " + thing.get(1));
		System.out.println("thing.indexOf(5): " + thing.indexOf(5));

	}



}
