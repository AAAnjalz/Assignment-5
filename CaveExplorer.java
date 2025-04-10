/*
 * CoffeeShopSimulator.java
 *
 * COMP 1020 SECTION A01
 * INSTRUCTOR    Lauren Himbeault
 * ASSIGNMENT    Assignment 4
 * @author       Anjal Adhikari, 008031539
 * @version      2025/03/24
 * 
 * Purpose: The program uses a stack-based approach to explore the cave. It starts from a given location, explores in all directions 
 * (up, down, left, right), and marks the path taken. If the treasure is found, it marks the path to the treasure with dots (.). If no 
 * path to the treasure is found, it reports that the treasure is unreachable.The stack is implemented using a linked list based structure.
 */
import java.util.ArrayList;

public class CaveExplorer {
  public static final char[][] CAVE = {
    "####################".toCharArray(),
    "#       #      #  T#".toCharArray(),
    "# ####### ##  ### ##".toCharArray(),
    "#       #          #".toCharArray(),
    "####################".toCharArray()
  };

    public static void main(String[] args) {
        char[][] caveLayout = copyLayout(CAVE);
        exploreCave(caveLayout, new Location(1, 1));
    }

    public static char[][] copyLayout(char[][] layout) {
        // Create a new 2D array with the same number of rows as the original layout
        char[][] copy = new char[layout.length][];
    
        // Iterate through each row
        for (int i = 0; i < layout.length; i++) {
            // Create a new row with the same number of columns as the original row
            copy[i] = new char[layout[i].length];
    
            // Iterate through each column in the current row
            for (int j = 0; j < layout[i].length; j++) {
                // Copy the element from the original layout to the new copy array
                copy[i][j] = layout[i][j];
            }
        }
    
        return copy;
    }
    

    public static void displayCave(char[][] layout) {
        for (char[] row : layout) {
            System.out.println(row);
        }
    }

    public static void exploreCave(char[][] layout, Location start) {
        if (findTreasure(layout, start)) {
            System.out.println("Treasure found!");
        } else {
            System.out.println("No path to the treasure.");
        }
        displayCave(layout);
    }

    public static boolean findTreasure(char[][] layout, Location start) {
        PathStack stack = new PathStackLL();
        PathStack path = new PathStackLL();

        stack.push(start);

        while (!stack.isEmpty()) {
            Location current = stack.pop();
            path.push(current);  // Push the current location to path

            if (layout[current.row()][current.col()] == 'T') {
                // Mark the correct path with '.' if treasure is found
                while (!path.isEmpty()) {
                    Location p = path.pop();
                    if(layout[p.row()][p.col()] != 'T'){
                        layout[p.row()][p.col()] = '.';
                    }
                }
                return true;
            }

            // Mark the current location as part of the path
            if (layout[current.row()][current.col()] == ' ') {
                layout[current.row()][current.col()] = 'X';  // Mark the path
            }
            
            // Explore four directions (up, down, left, right)
            for (Location neighbor : getNeighbors(current)) {
                if (isValidMove(layout, neighbor)) {
                    stack.push(neighbor);
                }
            }
        }
        return false;
    }

    public static ArrayList<Location> getNeighbors(Location loc) {
        ArrayList<Location> neighbors = new ArrayList<>();
        neighbors.add(new Location(loc.row() - 1, loc.col()));  // Up
        neighbors.add(new Location(loc.row() + 1, loc.col()));  // Down
        neighbors.add(new Location(loc.row(), loc.col() - 1));  // Left
        neighbors.add(new Location(loc.row(), loc.col() + 1));  // Right
        return neighbors;
    }

    public static boolean isValidMove(char[][] layout, Location loc) {
        return loc.row() >= 0 && loc.row() < layout.length &&
               loc.col() >= 0 && loc.col() < layout[0].length &&
               layout[loc.row()][loc.col()] != '#' && layout[loc.row()][loc.col()] != 'X';
    }
}

// Interface for stack operations
interface PathStack {
    void push(Location loc);
    Location pop();
    boolean isEmpty();
}

// TODO: Implement PathStackLL using a linked list that implements PathStack interface
class PathStackLL implements PathStack{
    Node top;

    class Node{
        private Location loc;
        private Node next;
        public Node(Location loc, Node next){
            this.loc = loc;
            this.next = next;
        }
    }    

    @Override
    public void push(Location loc) {
        top = new Node(loc, top);
    }

    public Location pop() {
        if (top == null) {
            // System.out.println("Nothing to delete!");  // Optional
            return null;
        }
        Node temp = top;  // Store reference to the current top
        Location loc = temp.loc;  // Extract location data
        top = top.next;  // Move the top pointer to the next node
        return loc;  // Return the popped location
    }
    

    @Override
    public boolean isEmpty() {
        return top == null;
    }
    
}

class Location {
    private int row, col;
    public Location(int r, int c) { 
        this.row = r; 
        this.col = c; 
    }
    public int row() { 
        return row; 
    }
    public int col() { 
        return col; 
    }
}