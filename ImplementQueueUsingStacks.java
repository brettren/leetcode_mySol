
// pop() 和 peek(), 只有s2为empty的时候才需要把s1的全部移过来
// 只要s2不为empty，那就一直能保持最早的那个element

// 注意如果为empty，防止exception出现
class MyQueue {
	Stack<Integer> s1 = new Stack<>();
	Stack<Integer> s2 = new Stack<>();

    // Push element x to the back of queue.
    public void push(int x) {
        s1.push(x);
    }

    // Removes the element from in front of queue.
    public void pop() {
    	if(!s2.isEmpty()){
    		s2.pop();
    		return;
    	} 
        while(!s1.isEmpty()){
        	s2.push(s1.pop());
        }
        if(!s2.isEmpty()) s2.pop();
    }

    // Get the front element.
    public int peek() {
    	if(!s2.isEmpty()) return s2.peek();
        while(!s1.isEmpty()){
        	s2.push(s1.pop());
        }
        return s2.isEmpty() ? null : s2.peek();
    }

    // Return whether the queue is empty.
    public boolean empty() {
        return s1.isEmpty() && s2.isEmpty();
    }
}