
/**
 * The interface that will represent the foods the user will select from.
 *
 * @author Nathan Hinds
 * @version 1.0
 */

package foodmodel;

public interface Food {

  /**
   * Returns the name of the food.
   */
  public String getName();

  /**
   * Sets the name of the food to a new name.
   */
  public void setName(String newName);

  /**
   * Returns the calorie value of the food.
   */
  public double getCalories();

  /**
   * Sets the calories of the food to a new value.
   */
  public void setCalories(double newCalories);

  /**
   * Returns the grams of fat of the food.
   */
  public double getFat();

  /**
   * Sets the grams fat of the food to a new value.
   */
  public void setFat(double newFat);

  /**
   * Returns the carbohydrates of the food.
   */
  public double getCarbs();

  /**
   * Sets the carbohydrates of the food to a new value.
   */
  public void setCarbs(double newCarbs);

  /**
   * Returns the protein of the food.
   */
  public double getProtein();

  /**
   * Sets the protein of the food to a new value.
   */
  public void setProtein(double newProtein);
}
