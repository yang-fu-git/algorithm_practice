package de.unistuttgart.dsass2022.ex05.p2;

public interface IPrefixTree {

	/**
	 * Inserts a string in the prefix tree
	 * 
	 * Hints:
	 * - Its a good option to implement this method in a recursive way
	 * - Note that there is a special case when you insert a word into an empty tree
	 * - Using the method String.substring may be helpful, read the documentation (https://docs.oracle.com/javase/7/docs/api/java/lang/String.html)
	 * - You don't need to care about Epsilon-transitions 
	 * 
	 * @param word the string which is inserted
	 */
	public void insert(String word);
	
	/**
	 * Returns the amount of strings which are saved in the prefix tree
	 * @return size of the prefix tree
	 */
	public int size();
}
