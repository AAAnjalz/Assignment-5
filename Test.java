public class Test {
    public static void main(String[] args) {
        LinkedList ll = new LinkedList();
        Car car = new Car("ROver", 10);
        Car car1 = new Car("ROver1", 19);
        // ll.add(car);
        // ll.add(car1);
        // ll.printList();
        // ll.sort();
        // ll.printList();
        CarQueueLinkedList cl = new CarQueueLinkedList();
        cl.add(car);
        cl.add(car1);
        // System.out.println(cl.incrementCarPriority("ROver", 20));
        System.out.println(cl.decrementCarPriority("ROver", 9));
        System.out.println(cl);
        // ll.printList();
        
    }
}
