// Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.

// push(x) -- Push element x onto stack.
// pop() -- Removes the element on top of the stack.
// top() -- Get the top element.
// getMin() -- Retrieve the minimum element in the stack. Retrieve是检索到，而不是remove

class MinStack {
	Stack<Integer> s1;
	Stack<Integer> s2;
	
	public MinStack() {
		s1 = new Stack<Integer>();	
		s2 = new Stack<Integer>();		
	}
	
	public void push(int x){
		if (x <= getMin()) {  //如果新的data小于等于原min，那就放进去，注意等于也要放
			s2.push(x);
		}
		s1.push(x);
	}
	
	public void pop() {
		int value = s1.pop();
		if (value == getMin()) {  // pop的时候如果相等在stack2里也要pop
			s2.pop();			
		}
		return;
	}

	public int top() {
    	return s1.peek();
	}

	public int getMin() {
		if (s2.isEmpty()) {
			return Integer.MAX_VALUE;
		} else {
			return s2.peek();
		}
	}
}


// 01/16/15
class MinStack {
    
    Stack<Integer> s1;
    Stack<Integer> s2;
    
    public MinStack(){
        s1 = new Stack<Integer>();
        s2 = new Stack<Integer>();
    }
    
    public void push(int x) {
        s1.push(x);
        if(s2.isEmpty() || x <= s2.peek()){
            s2.push(x);
        }
    }

    public void pop() {
        int t = s1.pop();
        if(!s2.isEmpty() && t == s2.peek()){
            s2.pop();
        }
    }

    public int top() {
        return s1.peek();
    }

    public int getMin() {
        return s2.peek();
    }
}

// 02/11/15
class MinStack {
    
    Stack<Integer> s1 = new Stack<Integer>();
    Stack<Integer> s2 = new Stack<Integer>();
    
    public void push(int x) {
        s1.push(x);
        if(s2.isEmpty() || s2.peek() >= x){
            s2.push(x);
        }
    }

    public void pop() {
        if(!s1.isEmpty()){
            int tmp = s1.pop();
            if(tmp == s2.peek()){
                s2.pop();
            }
        } 
    }

    public int top() {
        return s1.peek();
    }

    public int getMin() {
        return s2.peek();
    }
}
