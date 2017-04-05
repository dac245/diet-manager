/**
 * A simple food object the user can select that cannot contain other foods.
 *
 * @author Nathan Hinds
 * @version 1.0
 */

package foodmodel;

public class BasicFood implements Food {

  /**
   * The name of the food.
   */
  private String name;

  /**
   * The calorie value of the food.
   */
  private double calories;

  /**
   * The amount grams of fat in the food.
   */
  private double fat;

  /**
   * The amount carbohydrates in the food.
   */
  private double carbs;

  /**
   * The amount of protein in the food.
   */
  private double protein;

  /**
   * Default constructor.
   */
  public BasicFood() {

  }

  /**
   * Constructor that assigns the name of the food and
   * the starting nutritional value.
   */
  public BasicFood(String startName, double startCal, double startFat,
                   double startCarbs, double startProtein) {
    this.name = startName;
    this.calories = startCal;
    this.fat = startFat;
    this.carbs = startCarbs;
    this.protein = startProtein;
  }

  /**
   * Returns the name of the food.
   */
  public String getName() {
    return this.name;
  }

  /**
   * Sets the name of the food to a new name.
   */
  public void setName(String newName) {
    this.name = newName;
  }

  /**
   * Returns the calorie value of the food.
   */
  public double getCalories() {
    return this.calories;
  }

  /**
   * Sets the calories of the food to a new value.
   */
  public void setCalories(double newCal) {
    this.calories = newCal;
  }

  /**
   * Returns the amount of fat in the food.
   */
  public double getFat() {
    return this.fat;
  }

  /**
   * Sets the fat of the food to a new value.
   */
  public void setFat(double newFat) {
    this.fat = newFat;
  }


  /**
   * Returns the amount of carbohydrates in the food.
   */
  public double getCarbs() {
    return this.carbs;
  }

  /**
   * Sets the carbohdrates of the food to a new value.
   */
  public void setCarbs(double newCarbs) {
    this.carbs = newCarbs;
  }

  /**
   * Returns the amount of protein in the food.
   */
  public double getProtein() {
    return this.protein;
  }

  /**
   * Sets the protein of the food to a new value.
   */
  public void setProtein(double newProtein) {
    this.protein = newProtein;
  }

  public String toString() {
    return this.getName();
  }

}
