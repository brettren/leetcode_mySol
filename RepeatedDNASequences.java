// All DNA is composed of a series of nucleotides abbreviated as A, C, G, and T, for example: "ACGAATTCCG". When studying DNA, it is sometimes useful to identify repeated sequences within the DNA.

// Write a function to find all the 10-letter-long sequences (substrings) that occur more than once in a DNA molecule.

// For example,

// Given s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT",

// Return:
// ["AAAAACCCCC", "CCCCCAAAAA"].

public class Solution {
    public List<String> findRepeatedDnaSequences(String s) {
        Set<Integer> words = new HashSet<>();
        Set<Integer> doubleWords = new HashSet<>();
        List<String> ret = new ArrayList<>();
        char[] map = new char[26];
        map['A' - 'A'] = 0;
        map['C' - 'A'] = 1;
        map['G' - 'A'] = 2;
        map['T' - 'A'] = 3;

        for(int i = 0; i <= s.length() - 10; i++) {
            int v = 0;
            for(int j = i; j < i + 10; j++) {
                v <<= 2;
                v |= map[s.charAt(j) - 'A']; // 2个bits表示一个DNA
            }
            if(!words.add(v) && doubleWords.add(v)) {
                ret.add(s.substring(i, i + 10));
            }
        }
        return ret;
    }
}


// hashmap会超过内存限制
public class Solution {
    public List<String> findRepeatedDnaSequences(String s) {
        List<String> ret = new ArrayList<String>();
        int l = s.length();
        if(l < 10) return ret;
        Map<String, Integer> map = new HashMap<String, Integer>();
        for(int i = 0; i <= l-10; i++){
            String tmp = s.substring(i, i+10);
            if(map.containsKey(tmp)){
                map.put(tmp, map.get(tmp)+1);
            }
            else{
                map.put(tmp, 1);
            }
        }
        for(String tmp: map.keySet()){
            if(map.get(tmp) > 1){
                ret.add(tmp);
            }
        }
        return ret;
    }
}

// 03/01/15
public class Solution {
    public List<String> findRepeatedDnaSequences(String s) {
        List<String> ret = new ArrayList<>();
        int l = s.length();
        if(l < 10) return ret;
        int[] map = new int[26];
        map['A' - 'A'] = 0;
        map['C' - 'A'] = 1;
        map['G' - 'A'] = 2;
        map['T' - 'A'] = 3;
        Map<Integer, Integer> hash = new HashMap<>();
        for(int i = 0; i <= l-10; i++){
            int tmp = 0;
            for(int j = i; j < i+10; j++){
                tmp <<= 2;
                tmp |= map[s.charAt(j) - 'A'];
            }
            if(hash.containsKey(tmp)){
                if(hash.get(tmp) == 1){  // 说明是第二次出现，需要加到结果里
                    ret.add(s.substring(i, i+10));
                }
                hash.put(tmp, hash.get(tmp)+1);
            }
            else{
                hash.put(tmp, 1);
            }
        }
        return ret;
    }
}

// 03/07/15
// 每次检查一个substring，都先转化成20个bit，看map里是否存在，如果之前出现过一次就计入结果
// 遍历每个char作开头，往后10位，截取这段来check
public class Solution {
    public List<String> findRepeatedDnaSequences(String s) {
        List<String> ret = new ArrayList<>();
        int l = s.length();
        if(l < 10) return ret;
        Map<Integer, Integer> map = new HashMap<>();
        int[] num = new int[26];
        num['A' - 'A'] = 0;
        num['C' - 'A'] = 1;
        num['G' - 'A'] = 2;
        num['T' - 'A'] = 3;
        
        for(int i = 0; i <= l-10; i++){
            int tmp = 0;
            for(int j = 0; j < 10; j++){
                char c = s.charAt(i+j);
                tmp = tmp * 10 + num[c - 'A'];
            }
            if(!map.containsKey(tmp)){
                map.put(tmp, 1);
            }
            else if(map.get(tmp) == 1){
                ret.add(s.substring(i, i+10));
                map.put(tmp, 2);
            }
            else{
                map.put(tmp, map.get(tmp)+1);
            }
        }
        return ret;
    }
}