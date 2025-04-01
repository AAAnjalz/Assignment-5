public class CarQueueLinkedList {
    Node top;

    public CarQueueLinkedList(){
        this.top = null;
    }

    public void addToBack(Car car ){
        Node temp = top;
        if(temp == null){
            top = new Node(car, top);
        }else{
            while (temp.next!=null) {
                temp = temp.next;
            }
            Node carNode = new Node(car, null);
            temp.next = carNode;
        }

    }

    public void printList(){
        Node temp = top;
        while (temp!=null) {
            System.out.println(temp.carData);
            temp = temp.next;
        }
    }

    public void sort(){
        Node curr = top;
        while (curr != null) {
            Node maxNode = curr;
            Node searchNode = curr.next;
    
            while (searchNode != null) {
                if (searchNode.carData.getPriority() > maxNode.carData.getPriority() ||
                   (searchNode.carData.getPriority() == maxNode.carData.getPriority() &&
                    searchNode.carData.getModel().compareTo(maxNode.carData.getModel()) < 0)) {
                    maxNode = searchNode;
                }
                searchNode = searchNode.next;
            }
    
            if (maxNode != curr) {
                Car temp = curr.carData;
                curr.carData = maxNode.carData;
                maxNode.carData = temp;
            }
            curr = curr.next;
        }
    }
    

    class Node{
        Car carData;
        Node next;

        public Node(Car carData, Node next){
            this.carData = carData;
            this.next =  next;
        }
    }
}
