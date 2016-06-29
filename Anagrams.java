

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Given an array of strings, return all groups of strings that are anagrams.
 * 
 * Note: All inputs will be in lower-case.
 */

//就是给你n个string 然后返回里面互为anagram的词 比方说 abc def bca 就返回 abc bca
//不要陷入误区 不用写判断2个string是否为anagram的方法                                                          
///用hashmap做 key是sorted后的string value是 Arraylist<String> 存放互为anagram但是的原始string
//比方说 key： abcd  value里是 bcda  和 dbca

public class Anagrams {
	public ArrayList<String> anagrams(String[] strs) {
		HashMap<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();
		ArrayList<String> result = new ArrayList<String>();
		ArrayList<String> temp;
		for(String s:strs){
			char[] chars=s.toCharArray();
			Arrays.sort(chars); // 对每个string都sort
			String key=new String(chars);  // sort以后的string
			//key is sorted word
			//如果map里没存这个sorted word
			if(!map.containsKey(key)){
	    		temp=new ArrayList<String>();
	    		temp.add(s);//注意 Arraylist里加的 是原始的未排序s 不是anagram
				map.put(key,temp);
			}
			else{
				map.get(key).add(s);
			}
		}
		//因为map里的key是sorted过后的词 
		//每个sorted词看看他的原始s有几个 大于2个的话就是互为anagram 
		//
		for(String s:map.keySet()){
		      //是value 那个arraylist
			if(map.get(s).size()>1){  // 有anagram的都放入result list里
				//遍历 arraylist
				for(String string:map.get(s)){
					result.add(string);
				}
			}
		}
		return result;
	}
}


// 01/03/15
public class Solution {
    public List<String> anagrams(String[] strs) {
        int l = strs.length;
        
        HashMap<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();
        ArrayList<String> ret = new ArrayList<String>();
        ArrayList<String> tmp;
        
        for(String s: strs){
            char[] chars = s.toCharArray();
            Arrays.sort(chars);
            String key = new String(chars);
            if(map.containsKey(key)){
                map.get(key).add(s);
            }
            else{
                tmp = new ArrayList<String>();
                tmp.add(s);
                map.put(key, tmp);
            }
        }
        
        for(String s: map.keySet()){
            if(map.get(s).size() > 1){
                ret.addAll(map.get(s));
            }
        }
        return ret;
    }
}


// 02/06/15
public class Solution {
    public List<String> anagrams(String[] strs) {
        List<String> ret = new ArrayList<String>();
        int l = strs.length;
        if(l == 0) return ret;
        Map<String, List<String>> map = new HashMap<String, List<String>>();
        for(String s: strs){
            char[] c = s.toCharArray();
            Arrays.sort(c);
            String t = new String(c);
            if(map.containsKey(t)){
                map.get(t).add(s);
            }
            else{
                map.put(t, new ArrayList<String>());
                map.get(t).add(s);
            }
        }
        for(String t: map.keySet()){
            if(map.get(t).size() > 1){  // 如果只有一个值，不能当做group放入ret
                ret.addAll(map.get(t));
            }
        }
        return ret;
    }
}

// 03/12/15
// 用map来存group，key是排序后的string，list是排序后能match key的group strings
// 然后再根据keySet来把每个group的string放入return list
public class Solution {
    public List<String> anagrams(String[] strs) {
        List<String> ret = new ArrayList<String>();
        int l = strs.length;
        if(l == 0) return ret;
        Map<String, List<String>> map = new HashMap<String, List<String>>();
        for(int i = 0; i < l; i++){
            String tmp = strs[i];
            char[] chars = tmp.toCharArray();
            Arrays.sort(chars);  // 这里sort只能对array进行，string不能sort
            tmp = new String(chars);
            if(!map.containsKey(tmp)){
                map.put(tmp, new ArrayList<String>());
            }
            map.get(tmp).add(strs[i]);
        }
        for(String s: map.keySet()){
            if(map.get(s).size() < 2) continue;
            ret.addAll(map.get(s));
        }
        return ret;
    }
}

// 08/24/15

// follow up: if can't use sort, we can use array[] to record occurance of chars in each String
// Map<Map<Character, Integer>, List<String>>
public class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> ret = new ArrayList<>();
        if(strs == null || strs.length == 0) return ret;
        Arrays.sort(strs);
        Map<String, List<String>> map = new HashMap<>();
        for(String s: strs){
            char[] chars = s.toCharArray();
            Arrays.sort(chars);
            String key = new String(chars);
            if(!map.containsKey(key)){
                List<String> list = new ArrayList<>();
                map.put(key, list);
                ret.add(list);
            }
            map.get(key).add(s);
        }
        return ret;
    }
}