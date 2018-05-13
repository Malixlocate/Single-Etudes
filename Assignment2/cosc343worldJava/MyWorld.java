
import cosc343.assig2.World;
import cosc343.assig2.Creature;
import java.util.*;
import org.w3c.dom.css.Counter;
import java.util.concurrent.ThreadLocalRandom;

/**
 * The MyWorld extends the cosc343 assignment 2 World. Here you can set some
 * variables that control the simulations and override functions that generate
 * populations of creatures that the World requires for its simulations.
 *
 * @author
 * @version 1.0
 * @since 2017-04-05
 */
public class MyWorld extends World {

    /* Here you can specify the number of turns in each simulation
   * and the number of generations that the genetic algorithm will 
   * execute.
     */
    private final int _numTurns = 100;
    private final int _numGenerations = 10;
    private int totalFitness = 0;

    /* Constructor.  
   
     Input: worldType - specifies which simulation will be running
            griSize - the size of the world
            windowWidth - the width (in pixels) of the visualisation window
            windowHeight - the height (in pixels) of the visualisation window
            repeatableMode - if set to true, every simulation in each
                             generation will start from the same state
     */
    public MyWorld(int worldType, int gridSize, int windowWidth, int windowHeight, boolean repeatableMode) {
        // Initialise the parent class - don't remove this
        super(worldType, gridSize, windowWidth, windowHeight, repeatableMode);

        // Set the number of turns and generations
        this.setNumTurns(_numTurns);
        this.setNumGenerations(_numGenerations);

    }

    /* The main function for the MyWorld application

     */
    public static void main(String[] args) {
        // Here you can specify the grid size, window size and whether to run
        // in repeatable mode or not
        int gridSize = 24;
        int windowWidth = 1600;
        int windowHeight = 900;
        boolean repeatableMode = false;

        /* Here you can specify world type - there are two to
         choose from: 1 and 2.  Refer to the Assignment2 instructions for
         explanation of the world type formats.
         */
        int worldType = 1;

        // Instantiate MyWorld object.  The rest of the application is driven
        // from the window that will be displayed.
        MyWorld sim = new MyWorld(worldType, gridSize, windowWidth, windowHeight, repeatableMode);
    }


    /* The MyWorld class must override this function, which is
     used to fetch a population of creatures at the beginning of the
     first simulation.  This is the place where you need to  generate
     a set of creatures with random behaviours.
  
     Input: numCreatures - this variable will tell you how many creatures
                           the world is expecting
                            
     Returns: An array of MyCreature objects - the World will expect numCreatures
              elements in that array     
     */
    @Override
    public MyCreature[] firstGeneration(int numCreatures) {

        int numPercepts = this.expectedNumberofPercepts();
        int numActions = this.expectedNumberofActions();

        // This is just an example code.  You may replace this code with
        // your own that initialises an array of size numCreatures and creates
        // a population of your creatures
        MyCreature[] population = new MyCreature[numCreatures];
        for (int i = 0; i < numCreatures; i++) {
            population[i] = new MyCreature(numPercepts, numActions);
        }
        return population;
    }

    /* The MyWorld class must override this function, which is
     used to fetch the next generation of creatures.  This World will
     proivde you with the old_generation of creatures, from which you can
     extract information relating to how they did in the previous simulation...
     and use them as parents for the new generation.
  
     Input: old_population_btc - the generation of old creatures before type casting. 
                              The World doesn't know about MyCreature type, only
                              its parent type Creature, so you will have to
                              typecast to MyCreatures.  These creatures 
                              have been participating in a simulation and their state
                              can be queried to evaluate their fitness
            numCreatures - the number of elements in the old_population_btc
                           array
                        
                            
  Returns: An array of MyCreature objects - the World will expect numCreatures
           elements in that array.  This is the new population that will be
           used for the next simulation.  
     */
    @Override
    public MyCreature[] nextGeneration(Creature[] old_population_btc, int numCreatures) {
        // Typcast old_population of Creatures to array of MyCreatures
        MyCreature[] old_population = (MyCreature[]) old_population_btc;
        // Create a new array for the new population
        MyCreature[] new_population = new MyCreature[numCreatures];

        // Here is how you can get information about the old creatures and how
        // well they did in the simulation
        float avgLifeTime = 0f;
        int nSurvivors = 0;
        for (MyCreature creature : old_population) {
            // The energy of the creature.  This is zero if a creature starved to
            // death, non-negative otherwise.  If this number is non-zero, but the 
            // creature is dead, then this number gives the energy of the creature
            // at the time of death.
            int energy = creature.getEnergy();
            // This querry can tell you if the creature died during the simulation
            // or not.  
            boolean dead = creature.isDead();

            if (dead) {
                // If the creature died during simulation, you can determine
                // its time of death (in units of turns)
                int timeOfDeath = creature.timeOfDeath();
                avgLifeTime += (float) timeOfDeath;
                creature.addToFitness(timeOfDeath);
            } else {
                nSurvivors += 1;
                avgLifeTime += (float) _numTurns;
                creature.addToFitness(_numTurns + energy);
            }

            totalFitness += creature.fitness;

        }
        // Right now the information is used to print some stats...but you should
        // use this information to access creatures' fitness.  It's up to you how
        // you define your fitness function.  You should add a print out or
        // some visual display of the average fitness over generations.
        avgLifeTime /= (float) numCreatures;
        totalFitness /= (float) numCreatures;

// Compute the total weight of all items together
        System.out.println("Simulation stats:");
        System.out.println("  Survivors    : " + nSurvivors + " out of " + numCreatures);
        System.out.println("  Avg life time: " + avgLifeTime + " turns");
        System.out.println("  Avg fitness: " + totalFitness);

        // Having some way of measuring the fitness, you should implement a proper
        // parent selection method here and create a set of new creatures.  You need
        // to create numCreatures of the new creatures.  If you'd like to implement
        // elitism, you can use old creatures in the next generation.  This
        // example code uses all the creatures from the old generation in the
        // new generation.
        //Tournament selection
        MyCreature[] breeders = findBreeders(old_population);

        //Randomly selects two different breeders from the breeders population,
        // and then combines there chromosomes to create a new child that is added to the new population
        for (int i = 0; i < numCreatures; i++) {

            MyCreature newChild = makeChild(breeders, breeders.length);

            //10% chance of mutating a random gene in child chromosome
            int mutateIndex = -1 + (int) (Math.random() * (11) + 1);
            int mutateChance = -1 + (int) (Math.random() * (101) + 1);
            float mutateValue = rand.nextFloat() * (1 + 1);

            if (mutateChance < 10) {
                newChild.chromosome[mutateIndex] += mutateValue;
            }

            new_population[i] = newChild;
             //System.out.println(new_population[i].chromosome[0]);

        }
/*
        if (new_population[i].chromosome[0] == old_population[i].chromosome[0]) {
            breeders = findBreeders(old_population);
            newChild = makeChild(breeders, breeders.length);
            new_population[i] = newChild;
        }
        */
        
    

    return new_population ;
}

public MyCreature getRandom(MyCreature[] array) {
        int rand = new Random().nextInt(array.length);
        return array[rand];
    }

    public MyCreature findBestOfSubset(MyCreature[] old_population) {
        int size = old_population.length / 5;
        MyCreature[] subset = new MyCreature[size];
        //generate subset randomly selecting from old population
        for (int j = 0; j < subset.length; j++) {
            MyCreature randomSelect = getRandom(old_population);
            
            int count = j + 1;
            while (count != 0) {
                if (randomSelect != subset[count - 1]) {
                    count--;
                } else if (randomSelect == subset[count - 1]) {
                    randomSelect = findBestOfSubset(old_population);

                }
            }
            subset[j] = randomSelect;
        
            
            System.out.println("subset "+ j + " chromosome 0 value = " + subset[j].chromosome[0]);
            System.out.println("#############################");
        }
        //find best individual of each subset
        int max = subset[0].fitness;
        int maxIndex = 0;
        for (int j = 1; j < subset.length; j++) {
            if (max < subset[j].fitness) {
                max = subset[j].fitness;
                maxIndex = j;
            }

        }
       System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
       System.out.println("randomly picked creaure chrmosome 0 value: " + subset[maxIndex].chromosome[0]);
       System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        return subset[maxIndex];
    }

    public MyCreature makeChild(MyCreature[] breeders, int breederSize) {
        
        int parent1Index = -1 + (int) (Math.random() * (breederSize) + 1);
        int parent2Index = -1 + (int) (Math.random() * (breederSize) + 1);

        while (parent2Index == parent1Index) {
            parent2Index = -1 + (int) (Math.random() * (breederSize) + 1);
        }

        MyCreature parent1 = breeders[parent1Index];
        MyCreature parent2 = breeders[parent2Index];

        /*
        System.out.println("parent1Index: " + parent1Index + "    parent2Index: " + parent2Index);
        System.out.println("parent1 chromosome 0 :" + parent1.chromosome[0]);
        System.out.println("parent2 chromosome 0 :" + parent2.chromosome[0]);
        System.out.println("################################################################");
         */
        MyCreature newChild = new MyCreature(super.expectedNumberofPercepts(), super.expectedNumberofActions());

        int splitIndex = (int) (Math.random() * (10) + 1);

        

        for (int c = 0; c < newChild.chromosome.length; c++) {
            if (c < splitIndex) {
                newChild.chromosome[c] = parent1.chromosome[c];
            } else if (c >= splitIndex) {
                newChild.chromosome[c] = parent2.chromosome[c];
            }

        }
        System.out.println("**************************************************");
        System.out.println("newChild reutrned from makeChild chromosome 0 value: " +newChild.chromosome[0]);
        System.out.println("**************************************************");
        return newChild;
    }

    public MyCreature[] findBreeders(MyCreature[] old_population) {
        int breederMin = 5;
        int breederMax = 10;
        int breederSize = breederMin + (int) (Math.random() * ((breederMax - breederMin) + 1));
        MyCreature[] breeders = new MyCreature[breederSize];

        //finds the best candidates from subsets of old population and,
        //adds them to new breeder population if they're not already in there.
        for (int i = 0; i < breederSize; i++) {
            
            MyCreature nextCandidate = findBestOfSubset(old_population);
            
            int count = i + 1;
            while (count != 0) {
                if (nextCandidate != breeders[count - 1]) {
                    count--;
                } else if (nextCandidate == breeders[count - 1]) {
                    nextCandidate = findBestOfSubset(old_population);

                }
            }
            breeders[i] = nextCandidate;
        }
        for(int j = 0; j < breeders.length; j++){
            System.out.println("Breeder "+j+" chromosome 0 value: "+breeders[j].chromosome[0]);
        }
        return breeders;
    }

}

//Find between 5-10 of the best elites from the old population.
/**
 * int eliteMin = 5; int eliteMax = 10; int eliteSize = eliteMin +
 * (int)(Math.random() * ((eliteMax-eliteMin) +1)); MyCreature elites[] = new
 * MyCreature[eliteSize]; int max, index; for (int j = 0; j < elites.length;
 * j++) { max = fitnessWeights[0]; index = 0; for(int i = 1; i < numCreatures;
 * i++){ if(max < fitnessWeights[i]){ max = fitnessWeights[i]; index = i; } }
 * System.out.println(old_population[index].chromosome[0]); elites[j] =
 * old_population[index]; fitnessWeights[index] = Integer.MIN_VALUE; }
 *
 */
//Stuck because elites all have the same value chromosomes even though they are randomly picked
/*   
        

        
 */
/**
 * compare chromosomes for 10th member of old and new populations for(int i = 0;
 * i < 11; i++){ System.out.println(i +"old : "+
 * old_population[10].chromosome[i]); System.out.println(i +"new : "+
 * new_population[10].chromosome[i]); }
 *
 */
/**
 * checks elites array for correct values for (MyCreature elite : elites) {
 * System.out.println(elite.getFitness()); }
 *
 */
// Return new population of cratures
// MyCreature newChild = new MyCreature(super.expectedNumberofPercepts(),super.expectedNumberofActions());
/*
            //Take chromosome from a random parent in breeding pool for each chromosome
            for(int c = 0; c < newChild.chromosome.length; c++){
                int parentIndex = -1 +(int)(Math.random() * (breederSize) +1);
                MyCreature parent = breeders[parentIndex];
                newChild.chromosome[c] = parent.chromosome[c];
            }
 */
// if breeder population all have fitness below 90 then take breeders from neely generated population.
        /*
        int fitnessCheck = 0;
        for (int i = 0; i < breeders.length; i++) {
            if (breeders[i].fitness < 90) {
                fitnessCheck++;
            }
        }

        if (fitnessCheck == breeders.length) {
            old_population = firstGeneration(numCreatures);
        }
*/
