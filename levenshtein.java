package distance;

import java.util.*;
import java.io.*;

public class LevenshteinDistance {
	public static void main(String [] args) 
			throws FileNotFoundException{
    
    
    //-----------------------------------
    //    !!! insert file path below !!!!
    Set<String> words = new HashSet<String>(readSetWords("INSERT FILE NAME"));
    //-----------------------------------
   
    
		Map<String, LinkedList<String>> Word_Map = new HashMap <String, LinkedList<String>>();
		
		for(String word : words) {
			for(String word2 : words) {
				if(Ldistance(word,word2) == 1) {
					if(Word_Map.get(word) == null) {
						Word_Map.put(word, new LinkedList<String>());
						Word_Map.get(word).add(word2);
					}
					else {
						Word_Map.get(word).add(word2);
					}
				}
			}
		}
		System.out.println(Word_Map);
		System.out.println(mapWalk(Word_Map,"stoke", "these"));
	}
	
	
	public static Set<String> readSetWords(String filename)
			throws FileNotFoundException{
				Set<String> words = new HashSet<String>();
				Scanner in = new Scanner(new File(filename));
				
				in.useDelimiter("[^a-zA-Z[']]+");
				while(in.hasNext()) {
					words.add(in.next().toLowerCase());
				}
			return words;
		}
	
	
	public static int Ldistance(String str1, String str2) {
		char [] chars1 = str1.toCharArray();
		char [] chars2 = str2.toCharArray();
		int similarity = 0;
		
		if(str1.length() == str2.length()) {
			for(int i = 0; i< chars1.length; i++) {
					if(chars1[i] == chars2[i]) {
						similarity++;
					}
				}
		}
		else {
			return -1;
		}
		
		return chars1.length-similarity;
	}
	
	public static boolean find_Path(Map<String, LinkedList<String>> word_map, String word, String word2) {
		if(word_map.get(word) != null) {
			
		}
		
		
		return false;
	}
	
	public static int mapWalk(Map<String,LinkedList<String>> map1, String word, String word2) {
		int count = 1;
		boolean found = false;
		
		if(map1.get(word).contains(word2)) {
			return count;
		}
		LinkedList<String> temp = new LinkedList<String>(map1.get(word));
		Iterator<String> it = temp.iterator();
		
		
		ArrayList<String> searching = new ArrayList<String>();
		
		while(it.hasNext()) {
			searching.add(it.next());
		}
		
		ArrayList<String> searched = new ArrayList<String>(searching);
		
		while(searching.size()>0) {
			count++;
			int size = searching.size();
			
			for(int i = 0; i< size; i++) {
				LinkedList<String> templist = new LinkedList<String>(map1.get(searching.get(i)));
				Iterator<String> it2 = templist.iterator();
				searching.remove(searching.get(i));
				i--;
				size--;
				
				while(it2.hasNext()) {
					String temp2 = it2.next();
					
					if(!searched.contains(temp2)) {
						searched.add(temp2);
						searching.add(temp2);
					}
				}
			 }
			for(String str : searching) {
				
				if(str.equals(word2)) {
					return count;
				}
			}
		}
		return -1;
	}
}
