package cortes.luis.genetic;

import java.util.Arrays;
import java.util.Random;

public class Individual {
    private int[] genes;
    private Random random;

    private int maxFitness;
    private int fitness;

    public Individual(int size) {
        this.random = new Random();
        this.genes = this.generateIndividual(size);
        this.maxFitness = this.highestNonAttacking(size);
        this.fitness = 0;
    }

    public int[] getIndividual() {
        return Arrays.copyOf(this.genes, this.genes.length);
    }

    public int getFitness() {
        if (this.fitness == 0) {
            int temp = 0;
            for (int current = 0; current < this.genes.length; current++) {
                for (int next = current+1; next < this.genes.length; next++) {
                    if (genes[current] == genes[next])
                        temp++;
                    else if (Math.abs(genes[current] - genes[next]) == Math.abs(current - next))
                        temp++;
                }
            }
            this.fitness = maxFitness - temp;
        }
        return this.fitness;
    }

    private int[] generateIndividual(int size) {
        int[] ind = new int[size];
        for (int i = 0; i < ind.length; i++) {
            int randInt = random.nextInt(size);
            ind[i] = randInt;
        }
        return ind;
    }

    private int highestNonAttacking(int size) {
        // Formula to calculate highest number of non attacking queens
        return ((size - 1) * (size)) / 2;
    }
}
