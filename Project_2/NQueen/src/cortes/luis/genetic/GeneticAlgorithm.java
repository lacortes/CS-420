package cortes.luis.genetic;

import cortes.luis.Algorithm;
import java.util.Random;
import java.util.Stack;

public class GeneticAlgorithm implements Algorithm {
    private int popSize;
    private int numOfQueens;
    private int maxFit;
    private int generations;

    public GeneticAlgorithm(int numOfQueens, int popSize) {
        this.numOfQueens = numOfQueens;
        this.popSize = popSize;
        this.maxFit = this.maxFit(numOfQueens);
        this.generations = 0;
    }

    @Override
    public Stack<Individual> solve() {

        Population population = new Population(popSize);
        for (int i = 0; i < population.size(); i++) {
            population.addIndividual(i, new Individual(this.numOfQueens));
        }

        Stack<Individual> stack = new Stack<>();
        Individual answer = geneticAlg(population);

        stack.push(answer);
        return stack;

    }

    public Individual geneticAlg(Population population) {
        while (population.getFittest().getFitness() != maxFit) {
            Population newPop = new Population(population.size());
            for (int i = 0; i < population.size(); i++) {
                Individual firstPick = randomSelect(population);
                Individual secondPick = randomSelect(population);
                Individual child = reproduce(firstPick, secondPick);

                if (Math.random() < 0.15) {
                    child = mutate(child);
                }
                newPop.addIndividual(i, child);
            }
            population = newPop;
            this.generations++;
        }
        return population.getFittest();
    }

    public int getGenerations() {
        return this.generations;
    }

    private Individual randomSelect(Population population) {
        Population tempPop = new Population(population.size()/3);

        for (int i = 0; i < tempPop.size(); i ++) {
            int random = (int) (Math.random() * population.size());
            tempPop.addIndividual(i, population.getIndividual(random));
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
