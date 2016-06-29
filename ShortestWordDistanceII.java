// now you are given the list of words and your method will be called repeatedly many times with 
// different parameters. How would you optimize it?

// Design a class which receives a list of words in the constructor, and implements a method that 
// takes two words word1 and word2 and return the shortest distance between these two words in the list.

// For example,
// Assume that words = ["practice", "makes", "perfect", "coding", "makes"].

// Given word1 = "coding”, word2 = "practice”, return 3.
// Given word1 = "makes", word2 = "coding", return 1.

// Note:
// You may assume that word1 does not equal to word2, and word1 and word2 are both in the list.

public class WordDistance{
	Map<String, List<Integer>> map;

	public WordDistance(String[] words){
		map = new HashMap<>();
		for(int i = 0; i < words.length; i++){
			if(map.containsKey(words[i])){
				map.get(words[i]).add(i);
			}
			else{
				List<Integer> pos = new ArrayList<>();
				pos.add(i);
				map.put(words, pos);
			}
		}
	}

	public int shortestDistance(String word1, String word2) {
		List<Integer> list1 = map.get(word1);
		List<Integer> list2 = map.get(word2);
		int dist = Integer.MAX_VALUE;
		int i = 0, j = 0;
		while(i < list1.length() && j < list2.length()){
			dist = Math.min(dist, Math.abs(i-j));
			if(i < j) i++;
			else j++;
		}
		return dist;
	}
}
