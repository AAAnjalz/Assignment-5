
class LinkedList{
    Node top;
    int size;

    public LinkedList(){
        this.top = null;
        this.size = 0;
    }

    public void add(Car car ){
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
      size++;

  }

    public Car get(int size){
      Node temp = top;
      int currPos = 0;
      if(size >=0){
        while (temp!=null && currPos < size ) {
            temp = temp.next;
            currPos++;
        }
        if(temp!=null){
          return temp.carData;
        }
      }
        System.out.println("Invalid index!");
        return null;

    }

    public void printList(){
        Node temp = top;
        while (temp!=null) {
            System.out.println(temp.carData);
            temp = temp.next;
        }
    }

    public int size(){
      return this.size;
    }

    //Selection sort.
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
class CarQueueLinkedList implements QueueOfCars{
    private LinkedList queue;
  
    public CarQueueLinkedList() {
      this.queue =  new LinkedList();
    }
      
    public String incrementCarPriority(String model, int priority) {
      Car car;
      String  notification = null;
      car = find(model);
      if (car == null) {
        car = new Car(model, priority);
        this.queue.add(car);
      } else {
        notification = car.incrementPriority(priority);
      }
          
      return notification;
    }
      
    public String decrementCarPriority(String model, int priority) {
      Car car;
      String notification = null;
      car = find(model);
      if (car == null) {
        notification = "Not in queue";
      } else {
        notification = car.decrementPriority(priority);
      }
      return notification;
    }
      
    private Car find(String model) {
      Car result = null;
      int pos = 0;
      while (result == null && pos < this.queue.size()) {
        if (this.queue.get(pos).matchModel(model))
          result = this.queue.get(pos);
        else{
          pos++;
        }
      }
      return result;
    }
    
    public void sort() {
    queue.sort();
    } 
      
    public String toString() {
      queue.printList();
      return "";
    }
  }
  