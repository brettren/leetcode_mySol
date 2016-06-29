// The API: int read4(char *buf) reads 4 characters at a time from a file.

// The return value is the actual number of characters read. For example, it returns 3 if there is only 3 characters left in the file.

// By using the read4 API, implement the function int read(char *buf, int n) that reads n characters from the file.

// The read function can be called multiple times for each test case.
// 所以上一次没读的需要保存下来

/* The read4 API is defined in the parent class Reader4.
int read4(char[] buf); */

public class Solution extends Reader4 {

  	Queue<Character> queue = new LinkedList<Character>();

	/**
	* @param buf Destination buffer
	* @param n   Maximum number of characters to read
	* @return    The number of characters read
	*/
	public int read(char[] buf, int n) {
		char[] _buf = new char[4];
		int total = 0;
		while(true){
			int l = read4(_buf);
			for(int i = 0; i < l; i++){
				queue.add(_buf[i]);
			}

			l = Math.min(n - total, queue.size());  // 这里要考虑到两种情况，一是实际char的数量小于n，或是实际char数量大于n

			for(int i = 0; i < l; i++){
				buf[total++] = queue.poll();
			}

			if(l == 0) break;
		}

		return total;
	}
}


// 03/01/15
// 就是把read4多读出来的存起来， 下次先读这段剩余的
public class Solution extends Reader4 {

  	Queue<Character> queue = new LinkedList<Character>();

	public int read(char[] buf, int n) {
		char[] _buf = new char[4];
		int total = 0;
		while(true){
			int l = read4(_buf);
			for(int i = 0; i < l; i++){
				queue.add(_buf[i]);  // 先存入queue
			}
			l = Math.min(n - total, queue.size());  // 这里要考虑到两种情况，一是实际char的数量小于n，或是实际char数量大于n
			for(int i = 0; i < l; i++){
				buf[total++] = queue.poll();  // 如果上次有剩下的就先读取
			}
			if(l == 0) break;
		}
		return total;
	}
}

// 03/08/15
// 这里需要用一个queue来存上次剩下的内容，先read4放入queue，再比较后面还需要读几个或者说还能读几个，取最小值，再读完这个数量的值，继续下一个loop
// 先把read4读到的全部放入queue中，然后再比较得到需要read的个数，从queue里一个个取出来读入buf
// 保证这次没读读入buf的也保存下来，给下次调用read时继续读
public class Solution extends Reader4 {

  	Queue<Character> queue = new LinkedList<Character>();

  	public int read(char[] buf, int n) {
  		int[] _buf = new int[4];
  		int num = 0;
  		while(true){
	  		int l = read4(_buf);
	  		for (int i = 0; i < l; i++) {
	  			queue.add(_buf[i]);
	  		}
	  		l = Math.min(n-num, queue.size()); // 注意，因为queue里面有上次剩下未读入的，所以要比较queue的size
	  		if(l == 0) break;
	  		for (int i = 0; i < l; i++) {
	  			buf[num++] = queue.remove();
	  		}
	  	}
	  	return num;
  	}
}























