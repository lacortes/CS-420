package cortes.luis;

import java.util.PriorityQueue;
import java.util.Stack;

public class Main {

    public static void main(String[] args) throws CloneNotSupportedException {
	// write your code here
        Queen queen1 = new Queen(4, 0);
        Queen queen2 = new Queen(5, 1);
        Queen queen3 = new Queen(6,2);
        Queen queen4 = new Queen(3,3);
        Queen queen5 = new Queen(4, 4);
        Queen queen6 = new Queen(5, 5);
        Queen queen7 = new Queen(6, 6);
        Queen queen8 = new Queen(5, 7);

        Queen[] test2 = {queen1, queen2, queen3, queen4, queen5, queen6, queen7, queen8};

        Algorithm hillClimbing = new HillClimbing(8, 25);
        Stack<State> answer = hillClimbing.solve();

        while (!answer.isEmpty()) {
            State look = answer.pop();
            BoardUtil.displayBoard(look.getBoard());
            System.out.println("Heurustic: "+look.getHeurustic()+"\n");
        }


     }

}
