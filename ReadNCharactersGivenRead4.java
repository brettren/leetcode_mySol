// The API: int read4(char *buf) reads 4 characters at a time from a file.
// The return value is the actual number of characters read. For example, it returns 3 if there is only 3 characters left in the file.
// By using the read4 API, implement the function int read(char *buf, int n) that reads n characters from the file.
// Note:
// The read function will only be called once for each test case.

public class Solution {
/* The read4 API is defined in the parent class Reader4.
      int read4(char[] buf); */
	   /**
	    * @param buf Destination buffer
	    * @param n   Maximum number of characters to read
	    * @return    The number of characters read
	    */
	   	public int read(char[] buf, int n) {
	      	char[] buffer = new char[4];
	      	int readBytes = 0;
	      	boolean eof = false;
	      	while (!eof && readBytes < n) {  //最后一次读出来的buff长度有可能等于4，所以需要加上 readBytes < n
	          	int sz = read4(buffer);
	          	if (sz < 4) eof = true;  // 说明读完了
	          	int bytes = Math.min(n - readBytes, sz); 
	          	System.arraycopy(buffer /* src */, 0 /* srcPos */, buf /* dest */, readBytes /* destPos */, bytes /* length */);
	      		readBytes += bytes;
			}
	      	return readBytes;
	   }
}


// 03/20/15
// n-total可以知道还需要读几个，并且和读到的l比较
// 如果还没读到n就没有char可以读了， l = 0
// l = 0表示没有可读的了，或者已经读完n个char了
// total表示一共读到的chars
public class Solution extends Reader4 {
	public int read(char[] buf, int n) {
		char[] _buf = new char[4];
		int total = 0;
		while(true){
			int l = read4(_buf);
			l = Math.min(n - total, l);  // 这里要考虑到两种情况，一是实际char的数量小于n，或是实际char数量大于n
			for(int i = 0; i < l; i++){
				buf[total++] = _buf[i];
			}
			if(l == 0) break;
		}
		return total;
	}
}
















