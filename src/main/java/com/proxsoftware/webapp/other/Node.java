package com.proxsoftware.webapp.other;

/**
 * Created by Proxima on 13.07.2016.
 */
public class Node {
    int data;
    private Node next;

    public Node(int data, Node next) {
        this.next = next;
        this.data = data;
    }

    public Node(int data) {
        this.data = data;
    }

    Node insertIntTail(Node head, int data) {
        Node nodeForRetern = head;
        while (true) {
            if (head.next == null) {
                head.next = new Node(data);
                return nodeForRetern;
            }
        }
    }

    static void printNods(Node head) {
        Node elem = head;
        for (; elem.next != null; ) {
            System.out.println(elem.data);
            elem = elem.next;
        }
    }

    public static void main(String[] args) {
        Node node = new Node(1, new Node(2, new Node(3,null)));
        printNods(node);
    }
}

