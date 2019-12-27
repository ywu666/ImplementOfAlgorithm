package algorithem;

import java.util.*;

class ListNode {
	int data;
	ListNode next;        
	ListNode(int data) { this.data = data; }

	//singly linked list
	public int length() {
		ListNode curr = this;
		int length = 0;
		while (curr != null) {
			length++;
			curr = curr.next;	
		}	
		return length;
	}
	
	public ListNode insertAtTail(ListNode head, int data) {
		ListNode newNode = new ListNode(data);
		if (head == null) return newNode;

		ListNode curr = head;
		while (curr.next != null) { // track to the tail node 
			curr = curr.next;
		}
		curr.next = newNode;
		return head;    	
	}

	public ListNode deleteAtTail(ListNode head) {
		if (head == null || head.next == null) return null;

		ListNode curr = head;
		ListNode prev = curr;

		while (curr.next != null) {
			prev = curr;
			curr = curr.next;
		}

		prev.next = null;
		return head;
	}

	public ListNode insertAthead(ListNode head, int data) {
		ListNode newNode = new ListNode(data);
		//if (head == null) return newNode;
		newNode.next = head;
		return newNode;
	}

	public ListNode deleteAtHead(ListNode head) {
		if (head == null) return null;
		head = head.next;
		return head;
	}

	public ListNode findMiddleNode(ListNode head) {
		if (head == null || head.next == null) return head;
		ListNode fast = head;
		ListNode slow = head;
		while (fast != null && fast.next != null && (fast.next).next != null) {
			slow = slow.next;
			fast = (fast.next).next;
		}
		return slow;
	}

	/**
	 * This method will delete the node at a given position (starting from 1 as the
	 * head position) and return the head of the list. Do nothing if the input position
	 * is out of range.
	 * @param head
	 * @param pos
	 * @return
	 */
	public ListNode deleteAtPosition (ListNode head, int pos) { //assume pos is positive integer
		if (pos == 1) return head == null ? head:head.next;
		ListNode curr = head;
		ListNode prev = curr;
		int count  = 1; // start from the head, assume the head position is 1.
		while (curr != null) {
			if (count == pos) {
				prev.next = curr.next;
				curr.next = null;
				//break
			}
			prev = curr;
			curr = curr.next;
			count++;
		}
		
		//if (count < pos) return head;//the pos is out of range 
		//prev.next = curr.next;
		//curr.next = null;
		return head; 
	}

	public ListNode insertAtPosition(ListNode head, int data, int pos) {
	    ListNode newNode = new ListNode(data);
	    if(head == null && pos == 1) return newNode;
	    ListNode curr = head;
	    ListNode prev = curr;
	    int count = 1;
	    while(curr != null){
	        if(count == pos){
	            break;
	        }
	        count++;
	        prev = curr;
	        curr = curr.next;
	    }
	    
	    if(count < pos){ //Out of Range
	        return head;
	    }else if (count == 1){ //Insert at head
	        newNode.next = head;
	        return newNode;
	    }else { //Insert at middle and tail
	        prev.next = newNode;
	        newNode.next = curr;
	        return head;
	    }
	}

	public ListNode removeDuplicates(ListNode head) { 	    
		Hashtable<Integer,ListNode> table = new Hashtable<>();
		ListNode curr = head;
		ListNode prev = curr;
		while (curr != null) {
			if (table.containsKey(curr.data)) { //find and delete the duplicate nodes
				prev.next = curr.next;
				curr.next = null;
				curr = prev.next;
				if (curr == null) { //Reach the end of the list
					break;
				}else {
					table.put(curr.data, curr);
				}	
			}else {
				table.put(curr.data, curr);
			}
			
			prev = curr;
			curr = curr.next;
		}		
		return head;
	}

	public  ListNode reverseList(ListNode head) { //Use this method, the original head will change, head.nexxt = null;
	    ListNode curr = head;
		ListNode prev = null;
		while (curr != null) {
			ListNode next = curr.next;
			curr.next = prev;
			prev = curr;
			curr = next;
		}    	
		return prev;
	}

	public ListNode reverseInPair(ListNode head) {
		if (head == null) return null;
		ListNode curr = head;    
		while (curr != null && curr.next != null) {
			int temp = curr.data; // swap
			curr.data = curr.next.data;
			curr.next.data = temp;
			curr = (curr.next).next; // next pair 
		}
		return head;
	}

	// this method allowed to use the exact memory
	public ListNode findNthNodeFromEnd(ListNode head, int n) {
		if (head == null) return null;
		Hashtable<Integer,ListNode> table = new Hashtable<>();
		int index = 1;
		while (head != null) {
			table.put(index++, head);
			head = head.next;
		}
		return table.get(table.size() -n + 1);
	}

	//this method will not use the exact memory
	public ListNode findNthNodeFromEnd2(ListNode head, int n) {
		ListNode curr = head;
		int length = (head == null) ? 0:head.length();
		int pos = length - n + 1;
		int count = 0;
		while (curr != null) { //find the Nth node.
			count++;
			if (count == pos) {
				break;
			}
			curr = curr.next;

		}
		return curr;
	}

	public ListNode removeNthFromEnd(ListNode head, int n) {
		if (head == null || head.next == null) return null;
		ListNode curr = head;
		int length = (head == null) ? 0:head.length();
		int count = 0;
		int pos = length - n + 1;
		curr = head;
		ListNode prev = curr;

		while (curr != null) {
			count++;
			if (count == pos) {
				if (pos == 1) {//case: head
					head = head.next;
				}else { //case: tail && middle
					prev.next = curr.next;
					curr.next = null;
				}
			}
			prev = curr;
			curr = curr.next;        
		}  
		return head;
	}

	/**
	 * This method determine if the list is a palindrome. A palindrome is a sequence
	 * that reads the same backward as forward.
	 * @param head
	 * @return Return true if the list is palindrome, otherwise return false.
	 */
	public Boolean isListPalindrome(ListNode head) {
		if(head == null || head.next == null) return true;
	    // Go to the middle node
	    int mid = 0, count = 0;
	    ListNode current = head, midNode = null;
        int length = head.length();
	    mid = length/2;            
	    current = head;
	    while(current != null) {
	        if(count == mid) break;
	        current = current.next;
	        count++;
	    }
	    midNode = current; 
	   
	    // Reverse
	    ListNode p1 = midNode;
	    ListNode p2 = p1.next;
	    while(p1!=null && p2!=null){
	        ListNode temp = p2.next;
	        p2.next = p1;
	        p1 = p2;
	        p2 = temp;  
	    }
	    midNode.next = null;

	    // Compare
	    ListNode cur = (p2==null?p1:p2);
	    ListNode com = head;
	    while(cur != null){
	        if(cur.data != com.data) return false;
	        cur = cur.next;
	        com = com.next;
	    }
	    return true;
	}

	/**
	 * Check whether its length is even or odd in a single pass. 
	 * An Empty list has 0 nodes which makes the number of nodes in it even.
	 * @param head
	 * @return true if the length of the list is even.
	 */
	public Boolean isListEven(ListNode head) {
		int length = 0;
		while (head != null) {
			head = head.next;
			length++;
		}
		return (length%2 == 0);
		/*boolean even = true;
        while(head != null) {
            even = !even;
            head = head.next;
        }

        return even;*/
	}

	public Boolean isCyclic(ListNode head) { //Space complexity O(n)
		Hashtable<ListNode,Integer> table = new Hashtable<ListNode, Integer>();
		while (head != null) {
			if (table.containsKey(head)) {
				return true;
			} else {
				table.put(head,1);
			}
			head = head.next;
		}
		return false;
	}

	public Boolean isCyclic2(ListNode head) { //space complexity is O(1)
		if (head == null) return false;
		ListNode slow = head;
		ListNode fast = head;
		while (fast != null && fast.next != null && (fast.next).next != null) {
			slow = slow.next;
			fast = (fast.next).next;
			if (fast.equals(slow)) {
				return true;
			}   
		}
		return false;
	}

	public ListNode sumTwoLinkedLists(ListNode input1, ListNode input2) {
		int carryComponent = 0;
		ListNode result = new ListNode(0);
		ListNode n = result;
		while (input1 != null || input2 != null) {
			if (input1 != null) {
				carryComponent += input1.data;
				input1 = input1.next;
			}
			if (input2 != null) {
				carryComponent += input2.data;
				input2 = input2.next;
			}

			n.next = new ListNode(carryComponent%10);
			n = n.next;
			carryComponent /= 10;
		}

		if (carryComponent == 1) {
			n.next = new ListNode(1);
		}

		return result.next;
	}

	//Circular linked list
	public ListNode insertAtTailCircural (ListNode head, int data) {
		ListNode newNode = new ListNode(data);
		newNode.next = newNode; //This is important, the newNode after creation need to point to itself
		if (head == null) return newNode;
		ListNode curr = head;
		while (curr.next != head) { // find the tail
			curr = curr.next;
		}
		curr.next = newNode;
		newNode.next = head;
		return head;
	}

	public ListNode deleteAtHeadCircular(ListNode head) {
		if (head == null)  return null;

		ListNode curr = head;
		while (curr.next != head) {
			curr = curr.next;
		}
		curr.next = head.next;
		head.next = null; // this is important due to the test case 4-->*4
		return curr.next;
	}   

	public ListNode deleteAtTailCircular(ListNode head) {
		ListNode curr = head;
		ListNode prev = curr;

		while (curr.next != head) {
			prev =curr;
			curr = curr.next;
		}
		prev.next = head;
		curr.next = null;
		return head;
	}

    //merge
	public ListNode mergeTwoSortedList(ListNode l1, ListNode l2) { //recursion  
		ListNode newHead = null;
		if(l1==null) return l2;
		if(l2==null) return l1;
		if(l1.data <= l2.data) {
			newHead = l1;
			newHead.next = mergeTwoSortedList(l1.next, l2);
		} else {
			newHead = l2;
			newHead.next = mergeTwoSortedList(l2.next, l1);
		}
		return newHead;
	}
	
	public ListNode mergeKLists(ArrayList<ListNode> lists) {
		if (lists.size() == 0) return null;
		PriorityQueue<ListNode> queue = new PriorityQueue<ListNode>(lists.size(), new Comparator<ListNode>() {
			@Override
			public int compare(ListNode o1, ListNode o2) {
				if (o1.data > o2.data) return 1;
				if (o1.data == o2.data) return 0;
				return -1;
			} 
		});

		for (ListNode node:lists) {
			if (node != null) queue.add(node);
		}
		
		ListNode head = new ListNode(0), curr = head;
		while (queue.size()>0) {
			ListNode temp = queue.poll();
			curr.next = temp;
			if (temp.next != null) queue.add(temp.next);
			curr = curr.next;
		}
		return head.next;
	}
}
