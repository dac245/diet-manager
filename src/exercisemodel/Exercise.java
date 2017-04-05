/**
 * An Exercise, which consists of a name and the number of calories expended by
 * the exercise in 1 hour for a 100 pound person.
 *
 * @author Brendon
 * @version 2.0
 */

package exercisemodel;

public class Exercise {

  /**
   * The human-readable name of the exercise.
   */
  private String name;
  /**
   * The number of calories expended by the exercise in 1 hour for a 100 pound
   * person.
   */
  private double caloriesExpended;

  /**
   * Constructor for a new Exercise object. An Exercise object needs a unique
   * name and a value for the calories expended by the exercise in 1 hour for a
   * 100 pound person.
   *
   * @param name Name of the Exercise
   * @param caloriesExpended Value for the calories expended.
   */
  public Exercise(String name, double caloriesExpended) {
    this.name = name;
    this.caloriesExpended = caloriesExpended;
  }

  /**
   * Returns the name of the exercise.
   * @return Exercise name.
   */
  public String getName() {
    return this.name;
  }

  /**
   * Returns the number of calories expended by the exercise in 1 hour for a
   * 100 pound person.
   * @return Calories expended.
   */
  public double getCaloriesExpended() {
    return this.caloriesExpended;
  }

  /**
   * Sets a new number of calories expended by the exercise in 1 hour for a
   * 100 pound person.
   * @param newCaloriesExpended New value for the calories expended.
   */
  public void setCaloriesExpended(double newCaloriesExpended) {
    this.caloriesExpended = newCaloriesExpended;
  }

  /**
   * Returns a String representation of the Exercise.
   * The String representation is simply the name of the exercise.
   * @return Exercise name.
   */
  public String toString() {
    return this.getName();
  }

}
