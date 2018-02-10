import java.util.Arrays;
import java.util.Random;

/**
 * Main class that holds the "genes"
 * Also known as chromosome/individual/etc in other genetic algorithms
 */
public class DNA {
    Random random = new Random();

    private int fitness;
    private int[] genes;

    /**
     * Basic constructor
     * @param length length of gene
     */
    public DNA(int length) {
        genes = new int[length];
        for (int i = 0; i < genes.length; i++) {
            if (random.nextInt() % 2 == 0) genes[i] = 1;
            else genes[i] = 0;
        }
    }

    /**
     * Calculates fitness
     * @param target List that is being compared with and what the DNA is evolving towards
     */
    public void calculateFitness(int[] target) {
        int fit = 0;
        for (int i = 0; i < genes.length; i++) {
            if (genes[i] == target[i]) fit++;
        }
        this.fitness = fit;
    }

    /**
     * Simple crossover between two DNA
     * @param partner DNA to crossover with
     * @return Child resulting from the crossover
     */
    public DNA crossover(DNA partner) {
        DNA child = new DNA(genes.length);

        int midpoint = random.nextInt(genes.length);

        for (int i = 0; i < genes.length; i ++) {
            if (i < midpoint) child.genes[i] = genes[i];
            else child.genes[i] = partner.genes[i];
        }

        return child;
    }

    /**
     * Mutates DNA based on a rate
     */
    public void mutate(double mutationRate) {
        int index = random.nextInt(genes.length);
        if (random.nextDouble() <= mutationRate) {
            if (genes[index] == 1) genes[index] = 0;
            else genes[index] = 1;

        }
    }

    public int[] getGenes() {
        return genes;
    }

    public int getFitness() {
        return fitness;
    }

    @Override
    public String toString() {
        return Arrays.toString(genes);
    }
}
