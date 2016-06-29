// Given a string, we can “shift” each of its letter to its successive letter, for example: “abc” -> “bcd”. 
// We can keep “shifting” which forms the sequence:

// 1
// "abc" -> "bcd" -> ... -> "xyz"
// Given a list of strings which contains only lowercase alphabets, group all strings that belong to the 
// same shifting sequence.

// For example,

// given: ["abc", "bcd", "acef", "xyz", "az", "ba", "a", "z"], Return:

// [
//   ["abc","bcd","xyz"],
//   ["az","ba"],
//   ["acef"],
//   ["a","z"]
// ]
// Note: For the return value, each inner list’s elements must follow the lexicographic order

// set 首字母为offset，后面每个char和他比较得到的diff放入StringBuffer，当做key
public class Solution {
    public List<List<String>> groupStrings(String[] strings) {
        List<List<String>> ret = new ArrayList<>();
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strings) {
            int offset = str.charAt(0) - 'a';
            StringBuffer tmp = new StringBuffer();
            for (int i = 0; i < str.length(); i++) {
                char c = (char) (str.charAt(i) - offset);
                if (c < 'a') {
                    c += 26;
                }
                tmp.append(c);
            }
            String key = tmp.toString();
            if (!map.containsKey(key)) {
                List<String> list = new ArrayList<String>();
                map.put(key, list);
                ret.add(list);
            }
            map.get(key).add(str);
        }
        for (List<String> list: ret) {
            Collections.sort(list);
        }
        return ret;
    }
}













