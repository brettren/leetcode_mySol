// Implement the following operations of a stack using queues.

// push(x) -- Push element x onto stack.
// pop() -- Removes the element on top of the stack.
// top() -- Get the top element.
// empty() -- Return whether the stack is empty.
// Notes:
// You must use only standard operations of a queue -- which means only push to back, 
// peek/pop from front, size, and is empty operations are valid.
// Depending on your language, queue may not be supported natively. You may simulate 
// a queue by using a list or deque (double-ended queue), as long as you use only standard 
// operations of a queue.
// You may assume that all operations are valid (for example, no pop or top operations will 
// be called on an empty stack)


// push的时候放入q2，再把q1的全部放入q2，交换q1 q2名字；pop直接从q1里取
// 重点是保证q2为empty，q1维持一个stack的order

// q1: 3 2 1
// q2: 

class MyStack {

	public Queue<Integer> q1 = new LinkedList<>();
	public Queue<Integer> q2 = new LinkedList<>();

    // Push element x onto stack.
    public void push(int x) {
        q2.add(x);
        while(!q1.isEmpty()){
        	q2.add(q1.remove());
        }
        Queue<Integer> tmp = q1;
    	q1 = q2;
    	q2 = tmp;
    }

    // Removes the element on top of the stack.
    public void pop() {
        q1.remove();
    }

    // Get the top element.
    public int top() {
        return q1.peek();
    }

    // Return whether the stack is empty.
    public boolean empty() {
        return q1.isEmpty();
    }
}




