/**
 * Given a singly linked list where elements are sorted in ascending order,
 * convert it to a height balanced BST.
 */

public class ConvertSortedListtoBinarySearchTree {
	public TreeNode sortedListToBST(ListNode head) {
		return sortedListToBST(head, null);  // [head, null)
	}

	private TreeNode sortedListToBST(ListNode start, ListNode end) {
		if (start == end) {
			return null;
		} else if (start.next == end) {
			return new TreeNode(start.val);
		} else {
			ListNode fast = start, slow = start;
			while (fast.next != end && fast.next.next != end) {
				fast = fast.next.next;
				slow = slow.next;
			}
			// 此时slow就是start到end范围内tree的root，相当于(start+end)/2
			TreeNode left = sortedListToBST(start, slow);  // [start, slow)
			TreeNode right = sortedListToBST(slow.next, end); // [slow.next, end)
			TreeNode root = new TreeNode(slow.val);
			root.left = left;
			root.right = right;
			return root;
		}
	}
}

// 03/19/15
// 二分法，找到middle的点作root，得到返回的left和right，连起来
// 注意: 这里子程序的范围是[s,e)  所以当s == e的时候表示已经没有node了
public class Solution {
    public TreeNode sortedListToBST(ListNode head) {
        return helper(head, null);
    }
    
    private TreeNode helper(ListNode s, ListNode e){   // 相当于范围在[s, e)
        if(s == e) return null;
        ListNode slow = s;
        ListNode fast = s;
        while(fast != e && fast.next != e){
            slow = slow.next;
            fast = fast.next.next;
        }
        TreeNode mid = new TreeNode(slow.val);
        mid.left = helper(s, slow);  // [s, slow)
        mid.right = helper(slow.next, e);  // [slow.next, e)
        return mid;
    }
}