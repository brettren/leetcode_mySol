// Write a program to find the n-th ugly number.

// Ugly numbers are positive numbers whose prime factors only include 2, 3, 5. 
// For example, 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 is the sequence of the first 10 ugly numbers.

// Note that 1 is typically treated as an ugly number.


// 1 2 3 4 5 6 10 
public class Solution {
    public int nthUglyNumber(int n) {
        if(n == 0) return 0;
        PriorityQueue<Long> heap = new PriorityQueue<>();
        Set<Long> visited = new HashSet<>();
        heap.offer((long)1);
        visited.add((long)1);
        while(n > 1){
            long tmp = heap.poll();
            if(!visited.contains(tmp*2)){
                heap.offer(tmp*2);
                visited.add(tmp*2);
            }
            if(!visited.contains(tmp*3)){
                heap.offer(tmp*3);
                visited.add(tmp*3);
            }
            if(!visited.contains(tmp*5)){
                heap.offer(tmp*5);
                visited.add(tmp*5);
            }
            n--;
        }
        return heap.peek().intValue();
    }
}