package cortes.luis.genetic;

import java.util.ArrayList;
import java.util.Random;

public class Population {
    private Individual[] individuals;

    public Population(int size) {
        this.individuals = new Individual[size];
    }

    public void addIndividual(int index, Individual individual) {
        this.individuals[index] = individual;
    }

    public Individual getIndividual(int index) {
        return this.individuals[index];
    }

    public Individual getFittest() {
        Individual fittest = this.individuals[0]; // Assume first is fittest

        for (int i = 1; i < this.individuals.length; i++) {
            Individual check = this.individuals[i];
            if (check.getFitness() > fittest.getFitness())
                fittest = check;
        }
        return fittest;
    }

    public int size() {
        return this.individuals.length;
    }

}
