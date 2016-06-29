// Given a list of non negative integers, arrange them such that they form 
// the largest number.

// For example, given [3, 30, 34, 5, 9], the largest formed number is 9534330.

// Note: The result may be very large, so you need to return a string instead of 
// an integer.

class StringComparator implements Comparator<String> {
    public int compare(String a, String b) {
        if (a.length() == b.length()) {
            return b.compareTo(a);
        } else {
            String ab = a + b;  //3 30
            String ba = b + a;  //30 3
            return ba.compareTo(ab);
        }
    }
}

public class Solution {
    public String largestNumber(int[] num) {
        StringBuffer sbuf = new StringBuffer();
        ArrayList<String> numstrings = new ArrayList<String>(num.length);

        for (int i : num) numstrings.add(String.valueOf(i));
        Collections.sort(numstrings,  new StringComparator()); // 从大到小排序

        for (String s : numstrings) sbuf.append(s);

        String res = sbuf.toString();
        if (res.length() > 0 && res.charAt(0) == '0') return "0";

        return res;
    }
}


// 03/04/15
// 比较"3" "30", 就是比较"330" "303"
public class Solution {
    public String largestNumber(int[] num) {
        int l = num.length;
        StringBuffer sb = new StringBuffer();
        String[] arr = new String[l];
        for(int i = 0; i < l; i++){
            arr[i] = String.valueOf(num[i]);
        }
        Arrays.sort(arr, new Comparator<String>(){
            public int compare(String s1, String s2){
                String s1s2 = s1 + s2;
                String s2s1 = s2 + s1;
                return s2s1.compareTo(s1s2);
            }
        });
        for(String s: arr){
            sb.append(s);
        }
        if(sb.length() > 1 && sb.charAt(0) == '0') return "0"; // 全是0的string
        return sb.toString();
    }
}