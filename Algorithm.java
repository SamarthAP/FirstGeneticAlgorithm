import java.util.Random;

/**
 * Main genetic algorithm handles all interactions between Population and DNA
 */
public class Algorithm {

    public static void main(String[] args) {
        Random random = new Random();
        double mutationRate = 0.01; // Change to see its affect on the results
        int popSize = 100; // Change to see its affect on the results
        int[] target = {1, 0, 0, 0, 0, 0, 0, 0}; // Change to see its affect on the results

        int generation = 0;

        // Step 1: Initialize
        Population population = new Population(popSize, target);
        population.calculateFitness();

        // Main loop
        while (true) {
            generation++;

            for (int i = 0; i < popSize; i++) {
                DNA parentA;
                DNA parentB;
                DNA child;

                // Step 2: Selection and 3a: Reproduction (Pick 2 parents)
                parentA = findParent(population, random);
                parentB = findParent(population, random);

                // Step 3b: Reproduction (Crossover)
                child = parentA.crossover(parentB);
                // Step 3c: Reproduction (Mutation)
                child.mutate(mutationRate);
                // Step 3d: Reproduction (Add child to population
                population.pop[i] = child;
            }

            population.calculateFitness();
            System.out.println("Generation: " + generation + " Best: " + bestFitness(population));

            if (population.checkForCompletion()) {
                break;
            }

        }

        System.out.println("Complete!");

    }

    /**
     * Part 2 of the algorithm. Uses fitness from DNA as a probability to find a parent
     */
    private static DNA findParent(Population population, Random random) {

        int index;

        // Count just to make sure it doesn't loop for too long
        int count = 0;

        // Find a random index
        // If a random Double is less than or equal to percentage fitness of that object at the index, return the object
        do {
            index = random.nextInt(population.getSize());
            count++;
        } while (random.nextDouble() > population.pop[index].getFitness()/population.getTargetLength() && count < 1000);
        return population.pop[index];
    }

    /**
     * Finds best fitness in a population and returns as a string to print
     */
    private static String bestFitness(Population pop) {
        DNA best = pop.pop[0];

        for (int i = 0; i < pop.getSize(); i++) {
            if (pop.pop[i].getFitness() >= best.getFitness()) best = pop.pop[i];
        }

        return best.toString() + " Fitness: " + best.getFitness();
    }


}
