/**
 * Interacts with a "population" of DNA
 */
public class Population {

    // Main population with all DNA
    DNA[] pop;

    private int size;
    private int[] TARGET;

    /**
     * Creates a population of DNA
     * @param size population size
     * @param target The set of DNA that is being evolved towards
     */
    public Population(int size, int[] target) {
        TARGET = target;
        this.size = size;
        pop = new DNA[size];

        // Create a DNA for each cell
        for (int i = 0; i < this.size; i++) {
            pop[i] = new DNA(TARGET.length);
        }
    }

    /**
     * Calculates fitness for each DNA in the population
     */
    public void calculateFitness() {
        for (int i = 0; i < size; i++) {
            pop[i].calculateFitness(TARGET);
        }
    }

    /**
     * Checks if population has created a DNA that matches the target
     */
    public boolean checkForCompletion() {
        boolean check = false;

        for (int i = 0; i < size; i++) {
            if (pop[i].getFitness() == TARGET.length) check = true;
        }

        return check;

    }

    public int getTargetLength() {
        return TARGET.length;
    }

    public int getSize() {
        return size;
    }
}
