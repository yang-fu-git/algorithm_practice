package de.unistuttgart.dsass2022.ex09.p4;

import java.util.*;


/**
 * This class implements a non-deterministic-finite-state-automata.
 * The states are enumerated by the natural numbers including the 0.
 * For each state, there is a map containing the assignment of the literals to its possible next states.
 * Since this is a non-deterministic automata, we have a set of possible next states given a state and a literal.
 * By definition, the start-state is always state 0, however the end-state can be defined. Also there is exactly one
 * start- end end-state. Since we can simulate multiple start-/end-states states by epsilon transitions
 * Transitions, that are not possible (leaving the path to an end-state) can either be defined as an empty set
 * or as a set containing -1. So you can interpret the state -1 as a catch-state, which we cannot leave.
 */

public class NFA {


    Set<String> alphabet;

    ArrayList<HashMap<String, Set<Integer>>> transition;
    int endState;

    public NFA(Set<String> alphabet, ArrayList<HashMap<String, Set<Integer>>> transition, int endState) {
        this.alphabet = alphabet;
        this.transition = transition;
        this.endState = endState;
    }


    public Set<String> getAlphabet() {
        return this.alphabet;
    }

    public ArrayList<HashMap<String, Set<Integer>>> getTransition() {
        return this.transition;
    }

    public int getEndState() {
        return this.endState;
    }

    /**
     * This method reads a string and checks it using the defined transition table
     *
     * @param text to check
     * @return true, if after reading the whole string the automata is in a final state
     */
    public boolean readString(String text) {
        return readString(text, 0);
    }


    /*
     * This method checks a string for a given state recursively.
     * I.e. checking which literals in the alphabet can be matched next and then performing all transitions
     * defined by the transition table
     */
    private boolean readString(String text, int state) {

        boolean reachesEnd = false;
        if (text.length() == 0) {
            if (this.endState == state) return true;
            else {
                for (int s : transition.get(state).get("")) {
                    if (s == -1) continue;
                    reachesEnd = reachesEnd || readString("", s);
                }
            }
        } else {

            for (String c : alphabet) {
                if (c.compareTo(text.substring(0, c.length())) == 0) {

                    for (int s : transition.get(state).get(c)) {
                        if (s == -1) continue;
                        reachesEnd = reachesEnd || readString(text.substring(c.length()), s);
                    }
                }
            }
        }


        return reachesEnd;
    }


    /**
     * This is a helper function you might find helpful to create deep copies of the transition tables.
     * I.e. t2 is appended to t1, transition sets are created for each literal in the alphabet, if there is no transition in
     * t2 for a literal, there is a transition added to state -1. offset is added to the transitions
     * since t2 might not start at index 0 at t1, so the automata doesn't get changed. ommitFirst defines,
     * how many transitions from the beginning shall be omitted.
     *
     * @param t1         the table to append to
     * @param t2         the table to append
     * @param alphabet   the alphabet to create the transitions for
     * @param offset     for state transitions
     * @param ommitFirst omit the first n transitions
     */
    private static void addTransition(ArrayList<HashMap<String, Set<Integer>>> t1, ArrayList<HashMap<String, Set<Integer>>> t2, Set<String> alphabet, int offset, int ommitFirst) {

        for (int i = ommitFirst; i < t2.size(); i++) {
            HashMap<String, Set<Integer>> map = new HashMap<>();
            for (String c : alphabet) {
                Set<Integer> set = new HashSet<>();
                if (t2.get(i).containsKey(c)) {
                    for (int s : t2.get(i).get(c))
                        if (s != -1)
                            set.add(s + offset);
                        else set.add(-1);
                }
                map.put(c, set);
            }
            t1.add(map);
        }

    }


    /**
     * This method takes two NFAs and creates a new NFA, which is a concatenation of nfa1, followed by nfa2.
     * nfa1 and nfa2 shall not be altered to reduce side-effects. I.e. a deep copy of its data structures is required.
     * You might find the method addTransition() to be helpful.
     * Also, consider that the two NFA's might not be defined for the same alphabet.
     * In the lecture the end-state of the first automata is the start-state of the second automata. However, you might
     * also use an epsilon transition.
     *
     * @param nfa1 first NFA
     * @param nfa2 second NFA
     * @return the concatenation nfa1nfa2
     */

    public static NFA concat(NFA nfa1, NFA nfa2) {
        Set<String> alphabet = new HashSet<>();
        ArrayList<HashMap<String, Set<Integer>>> transition = new ArrayList<>();
        int endState = nfa2.getEndState() + nfa1.getTransition().size();
        alphabet.retainAll(nfa1.getAlphabet());
        alphabet.retainAll(nfa2.getAlphabet());
        addTransition(transition, nfa1.getTransition(), alphabet, 0, 0);
        transition.get(nfa1.getEndState()).putIfAbsent("", new HashSet<>());
        transition.get(nfa1.getEndState()).get("").add(transition.size());
        addTransition(transition, nfa2.getTransition(), alphabet, transition.size(), 0);
        return new NFA(alphabet, transition, endState);
    }


    /**
     * This method creates a new NFA by a disjunction of nfa1 and nfa2.
     * nfa1 and nfa2 shall not be altered to reduce side-effects. I.e. a deep copy of its data structures is required.
     * You might find the method addTransition() helpful.
     * Also consider, that the two NFA's might not be defined for the same alphabet and/or the alphabet might
     * not contain the empty string.
     *
     * @param nfa1 first NFA
     * @param nfa2 second NFA
     * @return nfa1|nfa2
     */
    public static NFA disjunction(NFA nfa1, NFA nfa2) {
        Set<String> alphabet = new HashSet<>();
        alphabet.retainAll(nfa1.getAlphabet());
        alphabet.retainAll(nfa2.getAlphabet());
        ArrayList<HashMap<String, Set<Integer>>> transition = new ArrayList<>();
        int endState = nfa1.getTransition().size() + nfa2.getTransition().size() + 1;
        HashMap<String, Set<Integer>> startNode = new HashMap<>(), endNode = new HashMap<>();
        startNode.put("", new HashSet<>(Arrays.asList(1, nfa1.getTransition().size() + 1)));
        addTransition(transition, new ArrayList<>(Arrays.asList(startNode)), alphabet, 0, 0);
        addTransition(transition, nfa1.getTransition(), alphabet, 1, 0);
        transition.get(nfa1.getEndState() + 1).putIfAbsent("", new HashSet<>());
        transition.get(nfa1.getEndState() + 1).get("").add(endState);
        addTransition(transition, nfa2.getTransition(), alphabet, 1 + nfa1.getTransition().size(), 0);
        transition.get(nfa2.getEndState() + 1 + nfa1.getTransition().size()).putIfAbsent("", new HashSet<>());
        transition.get(nfa2.getEndState() + 1 + nfa1.getTransition().size()).get("").add(endState);
        addTransition(transition, new ArrayList<>(Arrays.asList(endNode)), alphabet,
                1 + nfa1.getTransition().size() + nfa2.getTransition().size(), 0);
        return new NFA(alphabet, transition, endState);
    }

    /**
     * This method creates a new NFA as a kleene closure of nfa.
     * nfa shall not be altered to reduce side-effects. I.e. a deep copy of its data structures is required.
     * You might find the method addTransition() helpful.
     * Consider, that the alphabet of nfa might not contain the empty string.
     *
     * @param nfa
     * @return
     */

    public static NFA repetition(NFA nfa) {
        Set<String> alphabet = nfa.getAlphabet();
        ArrayList<HashMap<String, Set<Integer>>> transition = new ArrayList<>();
        int endState = nfa.getTransition().size() + 1;
        HashMap<String, Set<Integer>> startNode = new HashMap<>(), endNode = new HashMap<>();
        startNode.put("", new HashSet<>(Arrays.asList(1, endState)));
        addTransition(transition, new ArrayList<>(Arrays.asList(startNode)), alphabet, 1, 0);
        addTransition(transition, nfa.getTransition(), alphabet, 1, 0);
        //add transition from nfa end to {nfa start and final end}
        transition.get(nfa.getEndState() + 1).putIfAbsent("", new HashSet<>());
        transition.get(nfa.getEndState() + 1).get("").add(endState);
        transition.get(nfa.getEndState() + 1).get("").add(1);
        addTransition(transition, new ArrayList<>(Arrays.asList(endNode)), alphabet,
                1 + nfa.getTransition().size(), 0);
        return new NFA(alphabet, transition, endState);
    }


}
