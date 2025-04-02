public class Test {
    public static void main(String[] args) {
        LinkedList ll = new LinkedList();
        Car car = new Car("ROver", 10);
        Car car1 = new Car("ROver", 19);
        ll.add(car);
        ll.add(car1);
        ll.printList();
        ll.sort();
        ll.printList();
    }
}
