package com.employee.interview;

import java.util.*;
import java.util.stream.Collectors;

//public class Interview1 {
//
//    public static void main(String args[]) {
//        List<Integer> list = Arrays.asList(10, 20, 20, 4, 45, 45, 45, 99, 99);
//        Map<Integer, Long> dupNumbers = list.stream().filter(num -> Collections.frequency(list, num) > 1).collect(Collectors.groupingBy(x -> x, Collectors.counting()));
//
//        System.out.println(dupNumbers);
//    }
//}

class Node {

    int data;
    Node next = null;

    public Node(int data) {
        this.data = data;
//        this.next = null;
    }
}

public class Interview1 {

    static Node head = null;
    public static Node add(int data){
//        Node node = head;
        Node newNode = new Node(data);
        if(head == null){
            head = newNode;
        } else {
            Node itr = head;
            while(itr.next != null){
                itr = itr.next;
            } //address of [6, address -7],

            itr.next = newNode;
        }
        return head;
    }
    public static void printLinkedList(){
        Node itr = head;
        while(itr != null){
            System.out.println(itr.data);
            itr = itr.next;
        }
    }

    public static void remove(int num){
        if(head.data == num){
            head = head.next;
        }
        Node itr = head.next;
        Node pre = head;
        while(itr != null){
            if(itr.data==num){
                pre.next = itr.next;
            }
            itr = itr.next;
        }
    }

    public static void main(String arrd[]){
        add(6);
        add(7);
        add(8);
//        printLinkedList();
        remove(7);
        printLinkedList();

    }
}

