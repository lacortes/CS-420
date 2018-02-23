package cortes.luis;

import cortes.luis.hill.climbing.*;
import cortes.luis.genetic.*;

import java.util.Arrays;
import java.util.Stack;

public class Main {

    public static void main(String[] args) throws CloneNotSupportedException {
        final int RESTARTS = 25;
        final int POP_SIZE = 50;
        int numOfQueens = 21;


        for (int i = 0; i < 100; i++) {

            System.out.println("****   Num of Queens: "+numOfQueens+"    "+"****");

            Algorithm hillClimbing = new HillClimbing(numOfQueens, RESTARTS);
            Stack<State> answer = hillClimbing.solve();  // Run hill climbing

            State look = answer.pop();
            System.out.println("RANDOM RESTART HILL CLIMBING: ");
            BoardUtil.displayBoard(look.getBoard());
            System.out.println("Heurustic: " + look.getHeurustic() + "\n");

            GeneticAlgorithm gene = new GeneticAlgorithm(numOfQueens, POP_SIZE);
            Stack<Individual> fittest = gene.solve();  // Run genetic Algorithm

            Individual fit = fittest.pop();
            System.out.println("GENETIC ALGORITHM: ");
            BoardUtil.displayQueens(fit.getGenes());
            System.out.println("Max Fittnes: " + fit.getFitness());
            System.out.println("Generations: "+gene.getGenerations());

            System.out.println();
        }

     }

}
