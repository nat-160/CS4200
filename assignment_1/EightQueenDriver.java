import java.util.Scanner;
public class EightQueenDriver{
    public static void main(String[] args){
        System.out.print("Enter position of first queen: ");
        Scanner input = new Scanner(System.in);
        Board b;
        try{
            int x = Integer.parseInt(input.nextLine());
            b = new Board(x);
        }catch(Exception e){
            b = new Board();
        }
        
        while(!b.isSolved()){
            System.out.println(b.next());
        }
        System.out.println(b.toString());
    }
}