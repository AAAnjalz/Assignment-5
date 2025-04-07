import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class CarWashManager {    
  public static void main(String[] args) {
    handleQueue("carwash_details.txt");

  }

  public static void handleQueue(String inFile) { 
    BufferedReader inStream;
    String line, notification;
    String[] words;
    int priority;

    // QueueOfCars  waitingQueue = new CarQueueArrayList();
    QueueOfCars  waitingQueue = new CarQueueLinkedList();
    // QueueOfCars  servicedQueue = new CarQueueArrayList();
    QueueOfCars  servicedQueue = new CarQueueLinkedList();

    try  {
      inStream = new BufferedReader(new FileReader(inFile));
      line = inStream.readLine();
      while (line != null) {
        words = line.split(",");

        if (words.length > 0) {
          if (words[0].equals("arrive") && words.length == 3) {
            priority = Integer.parseInt(words[1]);
            notification = waitingQueue.incrementCarPriority(words[2], priority);
            if (notification != null)
              System.out.println(notification);
          } else if (words[0].equals("service") && words.length == 3) {
            priority = Integer.parseInt(words[1]);
            notification = servicedQueue.incrementCarPriority(words[2], priority);
            if (notification != null)
              System.out.println(notification);
          } else if (words[0].equals("sort") && words.length == 2) {
            if (words[1].equals("arrive")) {
              waitingQueue.sort();
            } else if (words[1].equals("service")) {
              servicedQueue.sort();
            } else {
              System.out.println("Invalid command: " + line);
            }
          } else if (words[0].equals("print") && words.length == 2){
            System.out.println(servicedQueue.toString());
            System.out.println(waitingQueue.toString());
          } else {
            System.out.println("Invalid line: " + line);
          }
        }
        line = inStream.readLine();
      }
      inStream.close();
    } catch (NumberFormatException nfe) {
      System.out.println(nfe);
    } catch (IOException ioe) {
      System.out.println(ioe);
    }
  }
}

class Car implements Comparable<Car>{
  private String model;
  private int priority;
    
  public Car(String model, int priority) {
    this.model = model;
    this.priority = priority;
  }
    
  public String incrementPriority(int increasePriority) {
    String notification = null;
    if (increasePriority < 0) {
      notification = "Priority cannot be negative";
    } else{
      this.priority += increasePriority;
      // notification = "Here are the cars and their priority: " + this.priority+ "-" + this.model; 
    }
        
    return notification;
  }
    
  public String decrementPriority(int decreasePriority) {
    String notification = null;
        
    if (decreasePriority < 0) {
      notification = "Priority cannot be negative";
    } else {
      this.priority -= decreasePriority;
      // notification = "Here are the cars and their priority: " + this.priority+ "-" + this.model; 
    }
        
    return notification;
  }
    
  public boolean matchModel(String model) {
    return this.model.equals(model);
  }

  public int getPriority(){
    return this.priority;
  }

  public String getModel(){
    return this.model;
  }
    
  public String toString() {
    return this.priority + " - " + model;
  }

  @Override
  public int compareTo(Car o) {
    if (this.getPriority() > o.getPriority()) {
        return -1;
    } else if (this.getPriority() < o.getPriority()) {
        return 1;
    } else if (this.getModel().compareTo(o.getModel()) < 0) {
        return -1;
    } else if (this.getModel().compareTo(o.getModel()) > 0) {
        return 1;
    }
    return 0;
}

}

interface QueueOfCars  {
  String incrementCarPriority(String model, int priority);
  String decrementCarPriority(String model, int priority);
  void sort();
}

//ARRAYLISTTT_________________________________________________
// class CarQueueArrayList implements QueueOfCars  {
//   private ArrayList<Car> queue;

//   public CarQueueArrayList() {
//     this.queue = new ArrayList<Car>();
//     // this.queue =  new CarQueueLinkedList();
//   }
    
//   public String incrementCarPriority(String model, int priority) {
//     Car car;
//     String  notification = null;
//     car = find(model);
//     if (car == null) {
//       car = new Car(model, priority);
//       this.queue.add(car);
//     } else {
//       notification = car.incrementPriority(priority);
//     }
        
//     return notification;
//   }
    
//   public String decrementCarPriority(String model, int priority) {
//     Car car;
//     String notification = null;
//     car = find(model);
//     if (car == null) {
//       notification = "Not in queue";
//     } else {
//       notification = car.decrementPriority(priority);
//     }
//     return notification;
//   }
    
//   private Car find(String model) {
//     Car result = null;
//     int pos = 0;
//     while (result == null && pos < this.queue.size()) {
//       if (this.queue.get(pos).matchModel(model))
//         result = this.queue.get(pos);
//       else{
//         pos++;
//       }
//     }
//     return result;
//   }
  
//   public void sort() {
//     // queue.sort();
//     // Collections.so
//   } 
    
//   public String toString() {
//     String result = "";
//     for (int i = 0; i < this.queue.size(); i++) {
//       result += this.queue.get(i);
//       if (i < this.queue.size() - 1){
//         result += "\n";
//       }
//     }
//     return result;
//   }
// }
//ARRAYLISTTT_________________________________________________


  //TODO: Implement your CarQueueLinkedList 

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

    public String toString(){
      Node temp = top;
      String output = "";
      while (top!=null) {
        output += temp.carData.toString() + "\n";
        temp = temp.next;
      }
      return output;
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

  public void add(Car c){
    queue.add(c);
}

    public String toString() {
    String result = "Here are the cars and their priority: \n";
    for (int i = 0; i < this.queue.size(); i++) {
      result += this.queue.get(i) + "\n";
      // if (i < this.queue.size() - 1){
      //   result += "\n";
      // }
      // result += "\n";
  
    }
    return result;
  }

}
