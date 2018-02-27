package cortes.luis;

import cortes.luis.hill.climbing.*;
import cortes.luis.genetic.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Stack;

public class Main {

    public static void main(String[] args) throws CloneNotSupportedException {
        final int RESTARTS = 25;
        final int POP_SIZE = 50;
        int numOfQueens = 15;
        int iterations = 1;

        double hillPass = 0;
        double geneticPass = 0;
        double genNum = 0;

        for (int i = 0; i < iterations; i++) {
            System.out.println("****   Num of Queens: " + numOfQueens + "    " + "****");

            Algorithm hillClimbing = new HillClimbing(numOfQueens, RESTARTS);
            Stack<State> answer = hillClimbing.solve();  // Run hill climbing

            State look = answer.pop();
            System.out.println("RANDOM RESTART HILL CLIMBING: ");
            BoardUtil.displayBoard(look.getBoard());
            System.out.println("Heurustic: " + look.getHeurustic() + "\n");

            if (look.getHeurustic() == 0)
                hillPass++;


            GeneticAlgorithm gene = new GeneticAlgorithm(numOfQueens, POP_SIZE);
            Stack<Individual> fittest = gene.solve();  // Run genetic Algorithm

            Individual fit = fittest.pop();
            System.out.println("GENETIC ALGORITHM: ");
            BoardUtil.displayQueens(fit.getGenes());
            System.out.println("Max Fittnes: " + fit.getFitness());
            System.out.println("Generations: " + gene.getGenerations());
            genNum += gene.getGenerations();

            if (fit.getFitness() == fit.getMaxFitness())
                geneticPass++;

            System.out.println();
        }

        double avgHillPass = hillPass / iterations;
        double avgGenePass = geneticPass / iterations;
        double avgGen = genNum / iterations;

        System.out.println("AVG HILL PASS: "+avgHillPass);
        System.out.println("AVG GENETIC PASS: "+avgGenePass);
        System.out.println("AVG GENERATIONS: "+avgGen);
     }
}
