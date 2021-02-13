package utils;

import dk.brics.automaton.Automaton;
import dk.brics.automaton.RegExp;
import dk.brics.automaton.State;
import dk.brics.automaton.Transition;

import java.util.List;
import java.util.Random;

public class Xeger {

    private final Automaton automaton;
    private Random random;

    /**
     * Constructs a new instance, accepting the regular expression and the randomizer.
     *
     * @param regex  The regular expression. (Not <code>null</code>.)
     * @param random The object that will randomize the way the String is generated. (Not <code>null</code>.)
     * @throws IllegalArgumentException If the regular expression is invalid.
     */
    public Xeger(String regex, Random random) {
        assert regex != null;
        assert random != null;
        this.automaton = new RegExp(regex).toAutomaton();
        this.random = random;
    }

    /**
     * As {@link Xeger#Xeger(String, Random)}, creating a {@link Random} instance
     * implicityly.
     */
    public Xeger(String regex) {
        this(regex, new Random());
    }

    /**
     * Generates a random String that is guaranteed to match the regular expression passed to the constructor.
     */
    public String generate() {
        StringBuilder builder = new StringBuilder();
        generate(builder, automaton.getInitialState());
        return builder.toString();
    }

    private void generate(StringBuilder builder, State state) {
        List<Transition> transitions = state.getSortedTransitions(false);
        if (transitions.size() == 0) {
            assert state.isAccept();
            return;
        }
        int nroptions = state.isAccept() ? transitions.size() : transitions.size() - 1;
        int option = Xeger.getRandomInt(0, nroptions, random);
        if (state.isAccept() && option == 0) {          // 0 is considered stop
            return;
        }
        // Moving on to next transition
        Transition transition = transitions.get(option - (state.isAccept() ? 1 : 0));
        appendChoice(builder, transition);
        generate(builder, transition.getDest());
    }

    private void appendChoice(StringBuilder builder, Transition transition) {
        char c = (char) Xeger.getRandomInt(transition.getMin(), transition.getMax(), random);
        builder.append(c);
    }

    public Random getRandom() {
        return random;
    }

    public void setRandom(Random random) {
        this.random = random;
    }

    /**
     * Generates a random number within the given bounds.
     *
     * @param min The minimum number (inclusive).
     * @param max The maximum number (inclusive).
     * @param random The object used as the randomizer.
     * @return A random number in the given range.
     */
    static int getRandomInt(int min, int max, Random random) {
        // Use random.nextInt as it guarantees a uniform distribution
        int maxForRandom=max-min+1;
        return random.nextInt(maxForRandom) + min;
    }
}
