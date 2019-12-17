package algorithem;

import java.util.*;

class ListNode {
	int data;
	ListNode next;        
	ListNode(int data) { this.data = data; }

	//singly linked list
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
	public ListNode deleteAtPosition (ListNode head, int pos) {
		return head;
	}
    
	public ListNode insertAtPosition(ListNode head, int data, int pos) {
		ListNode newNode = new ListNode(data);
		if (head == null) return newNode;
		
		int count = 0;
		ListNode curr = head;
		ListNode prev = curr;

		while (curr != null) {
			count++;
			if (count == pos) {break;}
			prev = curr;
			curr = curr.next;
		}
		
		if (count == 1) { // insert at head
			newNode.next = head;
			head = newNode;
		}else { // insert at the middle && tail
			prev.next = newNode;
			newNode.next = curr;
		}
		return head;
	}
	
	public ListNode removeDuplicates(ListNode head) { 
		return head;
	}

	public ListNode reverseList(ListNode head) {
		ListNode prev = null;
		while (head != null) {
			ListNode next = head.next;
			head.next = prev;
			prev = head;
			head = next;
		}    	
		return prev;
	}

	// this method allowed to use the exact memory
	public ListNode findNthNodeFromEnd(ListNode head, int n) {
		return head;
	}

	//this method will not use the exact memory
	public ListNode findNthNodeFromEnd2(ListNode head, int n) {
	  return null;
	}
	
	public ListNode removeNthFromEnd(ListNode head, int n) {
		 if (head == null || head.next == null) return null;
		    ListNode curr = head;
		    int length = 0;
		    
		    while (curr != null) {
		        curr = curr.next;
		        length++;
		    }
		    
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
	 *  that reads the same backward as forward.
	 * @param head
	 * @return Return true if the list is palindrome, otherwise return false.
	 */
	public Boolean isListPalindrome(ListNode head) {
		return false;
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
	public ListNode insertAtTailCircural (ListNode head) {
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
}
