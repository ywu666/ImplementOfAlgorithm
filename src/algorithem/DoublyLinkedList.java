package algorithem;

class DoublyLinkedNode {
	int data;
	DoublyLinkedNode next;
	DoublyLinkedNode prev;
	public DoublyLinkedNode(int data) { this.data = data; }

	public DoublyLinkedNode deleteAtPos(DoublyLinkedNode head, int pos) {
		if (head == null )return null;
		if (head.next == null)  return  (pos == 1) ? null:head;
		DoublyLinkedNode curr = head;
		DoublyLinkedNode prev = curr;
		int count =1;
		while(curr != null) {
			if (pos == count) {
				break;	
			}
			prev = curr;
			curr = curr.next;
			count++;
		}

		if (count < pos) { // pos over the size of the list
			return head;
		}else if (count == 1) { // delete at head
			DoublyLinkedNode temp = curr;
			curr.next.prev = null;
			curr = curr.next;
			temp.next = null;
			return curr;
		}else if (curr.next == null) { //delete at tail
			prev.next = null;
			curr.prev = null;
			return head;
		}else { //delete at pos
			prev.next = curr.next;
			curr.next.prev = prev;
			curr.next = null;
			curr.prev = null;
			return head;
		}
	}   

	public DoublyLinkedNode insertAtHead(DoublyLinkedNode head, int data) {
		DoublyLinkedNode newNode = new DoublyLinkedNode (data);
		if (head == null) {
			return newNode;
		}else {
			newNode.next = head;
			head.prev = newNode;
		}

		return head;
	}

	public DoublyLinkedNode insertAtPos(DoublyLinkedNode head, int data, int pos) {
		DoublyLinkedNode newNode = new DoublyLinkedNode (data);
		if (head == null && pos == 1) {
			head = newNode;
		}else {
			DoublyLinkedNode curr = head;
			DoublyLinkedNode prev = curr;
			int count = 1;
			while (curr != null) {
				if (pos == count) {
					break;	
				}
				prev = curr;
				curr = curr.next;
				count++;
			}

			if (count < pos) { // pos over the size of the list
				return head;
			}else if (count == 1) {   // insert at head
				newNode.next = head;
				head.prev = newNode;
				head = newNode;
			}else if (curr != null) { //insert at middle 
				prev.next = newNode;
				newNode.prev = prev;
				newNode.next = curr;
				curr.prev = newNode;
			}else {                  //insert at tail
				prev.next = newNode;
				newNode.prev = prev;
			}
		}
		return head;
	}
	
}


