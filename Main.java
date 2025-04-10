import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        list.add("apple");
        list.add("carrot");
        list.add(0,"duriot");
        list.add("cassava");
        for(int i=list.size()-1;i>=0;i--){
            if(list.get(i).startsWith("c")){
                list.remove(i);
            }
        }
        System.out.println(list);
    }
}
