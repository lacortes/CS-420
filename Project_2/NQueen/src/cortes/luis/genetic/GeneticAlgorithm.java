package cortes.luis.genetic;

import cortes.luis.Algorithm;
import cortes.luis.hill.climbing.State;
import org.omg.PortableInterceptor.INACTIVE;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Random;
import java.util.Stack;

public class GeneticAlgorithm implements Algorithm {
    private int size;
    private int maxFit;

    public GeneticAlgorithm(int size) {
        this.size = size;
        this.maxFit = this.maxFit(size);
    }

    @Override
    public Stack<State> solve() {

        Population population = new Population(50);
        for (int i = 0; i < population.size(); i++) {
            population.addIndividual(i, new Individual(8));
        }
        int[] test =  {3, 7, 0, 2, 5, 1, 6, 4};
        Individual superFit = new Individual(test);
        System.out.println("TEST: "+superFit);
        System.out.println("FIT: "+superFit.getFitness());
        System.out.println(this.maxFit);

        System.out.println("fittest: "+population.getFittest());

//        Individual answer = geneticAlg(population);
//        System.out.println("answer: "+answer);


        return null;
    }

    public Individual geneticAlg(Population population) {
        int counter = 0;
        while (population.getFittest().getFitness() != maxFit) {
            System.out.println("Gen: "+counter);
            Population newPop = new Population(population.size());
            for (int i = 0; i < population.size(); i++) {
                System.out.println("\tIndex: "+i);
                Individual firstPick = randomSelect(population);
                Individual secondPick = randomSelect(population);
                Individual child = reproduce(firstPick, secondPick);

                if (Math.random() < 0.15) {
                    child = mutate(child);
                }
                System.out.println("Child: "+child);
                newPop.addIndividual(i, child);
            }
            population = newPop;
            counter++;
        }
        return population.getFittest();
    }

    private Individual randomSelect(Population population) {
        Population tempPop = new Population(population.size()/3);

        for (int i = 0; i < tempPop.size(); i ++) {
            int random = (int) (Math.random() * population.size());
            tempPop.addIndividual(i, population.getIndividual(random));
            System.out.println("\tSelecting: "+population.getIndividual(random));
        }

        Individual fit = tempPop.getFittest();
        return fit;
    }

    private Individual reproduce(Individual first, Individual second) {
        Random random = new Random();
        int[] x = first.getGenes();
        int[] y = second.getGenes();
        int n = x.length;
        int c = random.nextInt((n+1));

        int[] combined = new int[n];

        // Get genes from first: 0 to c
        for (int i = 0; i < c; i++) {
            combined[i] = x[i];
        }

        // Get genes from second: c+1 to (n-1)
        for (int i = c; i < n; i++) {
            combined[i] = y[i];
        }
        return new Individual(combined);
    }

    private Individual mutate(Individual child) {
        Random random = new Random();
        int[] childGenes = child.getGenes();
        int index = random.nextInt(childGenes.length);
        int gene = random.nextInt(childGenes.length);
        childGenes[index] = gene;
        return new Individual(childGenes);
    }

    private int maxFit(int size) {
        return ((size - 1) * (size)) / 2;
    }
}
