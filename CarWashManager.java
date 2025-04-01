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

    QueueOfCars  waitingQueue = new CarQueueArrayList();
    QueueOfCars  servicedQueue = new CarQueueArrayList();

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
            //TODO: print out the linked list contents
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

class Car {
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
    }
        
    return notification;
  }
    
  public String decrementPriority(int decreasePriority) {
    String notification = null;
        
    if (decreasePriority < 0) {
      notification = "Priority cannot be negative";
    } else {
      this.priority -= decreasePriority;
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
}

interface QueueOfCars  {
  String incrementCarPriority(String model, int priority);
  String decrementCarPriority(String model, int priority);
  void sort();
}

class CarQueueArrayList implements QueueOfCars  {
  private ArrayList<Car> queue;
  public CarQueueArrayList() {
    this.queue = new ArrayList<Car>();
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
    // To be implemented in your linked list implementation 
  } 
    
  public String toString() {
    String result = "";
    for (int i = 0; i < this.queue.size(); i++) {
      result += this.queue.get(i);
      if (i < this.queue.size() - 1){
        result += "\n";
      }
    }
    return result;
  }

  //TODO: Implement your CarQueueLinkedList 
}