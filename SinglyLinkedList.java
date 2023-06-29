
public class SinglyLinkedList {

    Node head;
    Node tail;

    class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            this.next = null;
        }

        Node(int data, Node next) {
            this.data = data;
            this.next = next;
        }
    }

    public void insert(int data) {
        Node newNode = new Node(data);

        newNode.next = head;
        head = newNode;

        if (tail == null) {
            tail = head;
        }
    }

    public void Printlist() {
        if (head == null) {
            System.out.println("LIST IS EMPTY");
            return;
        }
        Node temp = head;
        while (temp != null) {
            System.out.print(temp.data + " -> ");
            temp = temp.next;
        }
        System.out.println("null");
    }

    public void InsertAtTail(int data) {
        if (tail == null) {
            insert(data);
        }
        Node node = new Node(data);
        tail.next = node;
        tail = node;
    }

    public int size() {
        int s = 0;
        Node temp = head;
        while (temp != null) {
            temp = temp.next;
            s++;
        }
        return s;
    }

    public void InsertAtIndex(int data, int index) {

        if (index == 0) {
            insert(data);
        }
        if (index == size()) {
            InsertAtTail(data);
        }
        Node temp1 = head;
        for (int i = 1; i < index; i++) {
            temp1 = temp1.next;
        }
        Node node = new Node(data, temp1.next);
        temp1.next = node;
    }

    public Node get(int index) {
        Node node = head;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node;
    }

    public void DeleteFirst() {
        head = head.next;
        if (head == null) {
            tail = null;
            return;
        }
    }

    public void DeleteLast() {

        if (size() <= 1) {
            DeleteFirst();
        }

        Node secondlast = get(size() - 2);
        tail = secondlast;
        tail.next = null;
    }

    public void DeleteAtIndex(int index) {
        if (index == 0) {
            DeleteFirst();
        }
        if (index == size() - 1) {
            DeleteLast();
        }
        Node prev = get(index - 1);
        prev.next = prev.next.next;
    }

    public void swap(int n1, int n2) {
        Node prevNode1 = null, prevNode2 = null, node1 = head, node2 = head;

        if (head == null) {
            return;
        }

        if (n1 == n2)
            return;

        while (node1 != null && node1.data != n1) {
            prevNode1 = node1;
            node1 = node1.next;
        }

        while (node2 != null && node2.data != n2) {
            prevNode2 = node2;
            node2 = node2.next;
        }

        if (node1 != null && node2 != null) {

            if (prevNode1 != null)
                prevNode1.next = node2;
            else
                head = node2;

            if (prevNode2 != null)
                prevNode2.next = node1;
            else
                head = node1;

            Node temp = node1.next;
            node1.next = node2.next;
            node2.next = temp;
        }
    }
}