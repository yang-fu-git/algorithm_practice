package de.unistuttgart.dsass2022.ex06.p2;

public interface IEdge<E> {

  /**
   * Returns the tail (source) vertex of the directed edge.
   * 
   * @return the tail (source) vertex of the edge
   */
  public int getSource();

  /**
   * Returns the head (destination) vertex of the directed edge.
   * 
   * @return the head (destination) vertex of the directed edge
   */
  public int getDestination();

  /**
   * Returns the weight of the directed edge.
   * 
   * @return the weight of the directed edge
   */
  public double getWeight();

}
