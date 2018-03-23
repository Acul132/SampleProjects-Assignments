import java.util.*;
public class mark{
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        double total = 0;
        double top,bottom;
        for(int i = 0; i < 5; i++){
            System.out.print("Top Points: ");
            top = scan.nextDouble();
            System.out.print("\nBottom Points: ");
            bottom = scan.nextDouble();
            total += (top/bottom)*6;
        }
        for(int i = 0; i < 2; i++){
            System.out.print("Top Points: ");
            top = scan.nextDouble();
            System.out.print("\nBottom Points: ");
            bottom = scan.nextDouble();
            total += (top/bottom)*10;
        }
        total*=2;
        System.out.println("Mark: " + total);
    }
}
