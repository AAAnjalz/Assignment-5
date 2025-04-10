/*
 * CoffeeShopSimulator.java
 *
 * COMP 1020 SECTION A01
 * INSTRUCTOR    Lauren Himbeault
 * ASSIGNMENT    Assignment 4
 * @author       Anjal Adhikari, 008031539
 * @version      2025/03/24
 * 
 * Purpose: This code simulates a coffee shop with multiple counters, each managing a queue of customer orders. 
 * It places orders randomly, processes them in a FIFO manner, and tracks orders at each counter using a linked list-based queue.
 */
import java.util.Random;

public class CoffeeShopSimulator {

    public static final int NUM_COUNTERS = 5;
    public static final int NUM_ORDERS = 150;

    public static void main(String[] args) {
        OrderQueue[] orderQueues = new OrderQueueLL[NUM_COUNTERS];
        int randomCounter;
        String orderDetails;
        Random random = new Random();
        int[] counterOrderCount = new int[NUM_COUNTERS]; 

        // Each counter has its own queue
        for (int i = 0; i < NUM_COUNTERS; i++) {
            orderQueues[i] = new OrderQueueLL();
        }

        for (int i = 0; i < NUM_ORDERS; i++) {
            randomCounter = random.nextInt(NUM_COUNTERS + 1);
            
            if (randomCounter == NUM_COUNTERS) {
                // Occasionally process orders for a random counter
                int counterToProcess = random.nextInt(NUM_COUNTERS);
                processOrders(counterToProcess, orderQueues[counterToProcess], counterOrderCount);
            } else {
                // A counter receives a new order
                orderDetails = String.format("[Counter %d - Order #%d: Coffee]", randomCounter, i);
                
                //TODO: place an order at the randomCounter - hint look at placeOrder and counterOrderCount! 


                System.out.printf("New order placed at Counter: %d\n", randomCounter);
            }
        }

        System.out.println("\nAll orders processed.");
        System.out.println();
    }

    public static void processOrders(int counterNum, OrderQueue queue, int[] counterOrderCount) {
        System.out.printf("Processing Orders at Counter #%d\n", counterNum);
        if (queue.isEmpty()) {
            System.out.println("No pending orders at this counter.");
            return;
        }
        // TODO: Process i.e. dequeue and print all the orders in the queue and reset processed order count
        
        while (!queue.isEmpty()) {
            String order = queue.dequeue();
            System.out.println(order);
        }
        
        counterOrderCount[counterNum] = 0;
    }

    public static void placeOrder(int counterNum, String orderDetails, OrderQueue queue) {
        queue.enqueue(orderDetails);
        System.out.printf("New order placed at Counter %d: %s\n", counterNum, orderDetails);
    }
}

interface OrderQueue {
    void enqueue(String s); // Add order
    String dequeue(); // Remove and return order
    String peek(); // Check the next order without removing
    boolean isEmpty(); // Check if the queue is empty
}

class OrderQueueLL implements OrderQueue {
    
    private static class Node {
        String data;
        Node next;
        
        public Node(String data, Node next){
            this.data = data;
            this.next = next;
        }
    }

    private Node first, last;

    // Constructor
    public OrderQueueLL() {
        this.first = null;
        this.last = null;
    }

    public void enqueue(String s) {
        // TODO: Implement enqueue method to add order at the end of the queue
        Node newNode = new Node(s, null);
        if(last == null){
            first = newNode;
            last = newNode;
        }
        last.next = newNode;
        last = newNode;
        
    }

    public String dequeue() {
        // TODO: Implement dequeue method to remove and return the first order
        if(first == null){
            return null;
        }
        String data = first.data;
        first = first.next;

        if(first==null){ //If first is empty then set last to empty as well.
            last = null;
        }
        return data;
        
    }

    public String peek() {
        // TODO: Implement peek method to check the next order without removing
        if(first == null){
            return null;
        }
        return first.data;
        
    }

    public boolean isEmpty() {
        // TODO: Implement method to check if the queue is empty
        return first == null;
        
    }
}


