
import cosc343.assig2.Creature;
import java.util.Random;

/**
 * The MyCreate extends the cosc343 assignment 2 Creature. Here you implement
 * creatures chromosome and the agent function that maps creature percepts to
 * actions.
 *
 * @author
 * @version 1.0
 * @since 2017-04-05
 */
public class MyCreature extends Creature {

    // Random number generator
    Random rand = new Random();
    float[] chromosome = new float[11];
    int fitness;
    int avgFitness;

    /* Empty constructor - might be a good idea here to put the code that 
   initialises the chromosome to some random state   
  
   Input: numPercept - number of percepts that creature will be receiving
          numAction - number of action output vector that creature will need
                      to produce on every turn
     */
    public MyCreature(int numPercepts, int numActions) {
        float min = 0f;
        float max = 11f;
        for(int i = 0; i < chromosome.length; i++){
            float random = min + rand.nextFloat() * (max - min);
            chromosome[i] = random;
            //System.out.println("chromosome" +i+" value" + chromosome[i]);
        }

    }

    /* This function must be overridden by the MyCreature class, because it implements
     the AgentFunction which controls creature behavoiur.  This behaviour
     should be governed by the model (that you need to come up with) that is
     parameterise by the chromosome.  
     Input: percepts - an array of percepts
            numPercepts - the size of the array of percepts depend on the percept
                          chosen
            numExpectedAction - this number tells you what the expected size
                                of the returned array of percepts should bes
     Returns: an array of actions 
     */
    @Override
    public float[] AgentFunction(int[] percepts, int numPercepts, int numExpectedActions) {

        // This is where your chromosome gives rise to the model that maps
        // percepts to actions.  This function governs your creature's behaviour.
        // You need to figure out what model you want to use, and how you're going
        // to encode its parameters in a chromosome.
        // At the moment, the actions are chosen completely at random, ignoring
        // the percepts.  You need to replace this code.
        float actions[] = new float[numExpectedActions];
        
       // for (int i = 0; i < 9; i++){
       //     actions[i] = 2f;
       // }
       // actions[9] = 3f;
       // actions[10] = 1f;
            for (int i = 0; i < numPercepts; i++) {
                for(int j = 0; j < numExpectedActions; j++){

                    if (i != 4) {
                    //if nothing on square
                    if (percepts[i] == 0) {
                        actions[j] += chromosome[j];

                        //if monster on square
                    } else if (percepts[i] == 1) {
                        actions[j] += chromosome[j]; //chromosome[1];

                        //if other creature on square
                    } else if (percepts[i] == 2) {
                        //chromosome[2];
                        actions[j] += chromosome[j];
                    }
                    //if food on square
                    else if (percepts[i] == 3) {
                        actions[j] += chromosome[j];
                    }
                } else {
                    //if no food on current sqaure
                    if (percepts[i] == 0) {
                        actions[j] += chromosome[j];
                    }
                    //if green strawberry on current square
                    if (percepts[i] == 1) {
                        actions[j] += chromosome[j];
                    }
                    //if red strawberry on current square
                    if (percepts[i] == 2) {
                        actions[j] += chromosome[j];
                    }
                }
            }
        }
            
            /*
            switch (percepts[i]) {
                
                case 0: {
                    if(i == )
                    actions[9] += 1f;
                    break;
                }

                case 1: {
                    if (i == 4) {
                        if (super.getEnergy() < 25) {
                            actions[i] += 5f; //chromosome[i];
                        }
                        
                    break;
                    }
                }

                case 2: {
                    if (i == 4) {
                        actions[i] += 7f;//chromosome[i];
                    }

                }

                case 3: {
                    actions[i] += 10f; //chromosome[i];
                    break;
                }
                
                case 4: {
                    
                }
            }

        }
            */

        return actions;
    }

    /**
     * The output of the agent function must consist of an array of 11 numbers,
     * each corresponding to a weight on different action. The engine will chose
     * the action corresponding to the largest number in the set of 11. So, the
     * absolute values of the action vector are of no importance, but rather
     * relative values decide which of the 11 actions is taken. • y 0 to y 8 -
     * the first 9 numbers correspond to 9 possible choices of movement: up,
     * down, left, right, up-left, up-right, down-left, down-right and staying
     * in the same square. The order of this actions given here is not
     * necessarily the order of the actions seen by the engine. • y 9 - is
     * related to the action of eating. If that action is taken, a strawberry
     * will be eaten, if one is currently present on the same square that the
     * creature is standing on • y 10 - is an explorative action, or a decision
     * to make a random movement. Figure 2: Actions format. Remember, there is
     * no invalid format for actions. Only one action can be taken by the
     * creature, and its the one, for which y j is the largest. Your percept to
     * actions model should 4discover on its own what output it needs to produce
     * based on the selection in the genetic
algorithm.*
     */
    void addToFitness(int input) {
        this.fitness = input;
    }

    int getFitness() {
        return this.fitness;
    }

}
