public class Test {
    public static void main(String[] args) {
        CarQueueLinkedList cl = new CarQueueLinkedList();
        Car car = new Car("Focus", 12);
        Car car1 = new Car("Accord", 12);
        cl.addToBack(car);
        cl.addToBack(car1);
        // cl.printList();
        cl.sort();
        cl.printList();
    }
}
