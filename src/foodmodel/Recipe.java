/**
 * A food object the user can select that will contain other food objects.
 *
 * @author Nathan Hinds
 * @version 1.0
 */

package foodmodel;

import java.util.HashMap;
import java.util.Observable;

public class Recipe extends Observable implements Food {

  /**
   * The name of the recipe.
   */
  private String name;

  /**
   * A map of the ingredients contained in the recipe and
   * the amount of servings needed.
   */
  private HashMap<Food, Double> ingredients;

  /**
   * The calorie value of the recipe.
   */
  private double calories;

  /**
   * The amount of fat in the recipe.
   */
  private double fat;

  /**
   * The amount of carbohydrates in the recipe.
   */
  private double carbs;

  /**
   * The amount of protein in the recipe.
   */
  private double protein;

  /**
   * Default constructor.
   */
  public Recipe() {}

  /**
   * Constructor that assigns the name of the recipe and
   * creates and empty map for its ingredients.
   */
  public Recipe(String name) {
    this.name = name;
    this.ingredients = new HashMap<Food, Double>();
  }

  /**
   * Returns the name of the recipe.
   */
  public String getName() {
    return this.name;
  }

  /**
   * Sets the name of the recipe to a new name.
   */
  public void setName(String newName) {
    this.name = newName;
  }

  /**
   * Returns the ingredients of the recipe.
   */
  public HashMap getIngredients() {
    return this.ingredients;
  }

  /**
   * Sets the ingredients of the recipe to those of a different map.
   */
  public void setIngredients(HashMap<Food, Double> foods) {
    this.ingredients = foods;
    setChanged();
    notifyObservers();
  }

  /**
   * Returns the calories of the recipe after calculating
   * the total amount from the foods it contains.
   */
  public double getCalories() {
    double totalCals = 0;

    for (Food key : ingredients.keySet()) {
      totalCals += key.getCalories();
    }

    this.setCalories(totalCals);
    return this.calories;
  }

  public void setCalories(double newCal) {
    this.calories = newCal;
  }

  /**
   * Returns the fat of the recipe after calculating
   * the total amount from the foods it contains.
   */
  public double getFat() {
    double totalFat = 0;

    for (Food key : ingredients.keySet()) {
      totalFat += key.getFat();
    }

    this.setFat(totalFat);
    return this.fat;
  }

  public void setFat(double newFat) {
    this.fat = newFat;
  }

  /**
   * Returns the carbohydrates of the recipe after calculating
   * the total amount from the foods it contains.
   */
  public double getCarbs() {
    double totalCarbs = 0;

    for (Food key : ingredients.keySet()) {
      totalCarbs += key.getCarbs();
    }

    this.setCarbs(totalCarbs);
    return this.carbs;
  }

  /**
   * Sets the carbohydrates of the recipe to a new value.
   */
  public void setCarbs(double newCarbs) {
    this.carbs = newCarbs;
  }

  /**
   * Returns the protein of the recipe after calculating
   * the total amount from the foods it contains.
   */
  public double getProtein() {
    double totalProtein = 0;

    for (Food key : ingredients.keySet()) {
      totalProtein += key.getProtein();
    }

    this.setProtein(totalProtein);
    return this.protein;
  }

  /**
   * Sets the protein of the recipe to a new value.
   */
  public void setProtein(double newProtein) {
    this.protein = newProtein;
  }

  /**
   * Adds a food object and its number of servings to the recipe.
   */
  public void addIngredient(Food foodItem, Double servings) {
    this.ingredients.put(foodItem, servings);
    setChanged();
    notifyObservers();
  }

  /**
   * Deletes an already existing recipe from the recipe.
   */
  public void deleteIngredient(Food foodItem) {
    if (this.ingredients.containsValue(foodItem)) {
      this.ingredients.remove(foodItem);
      setChanged();
      notifyObservers();
    } else {
      System.out.printf("Ingredient does not exist");
    }
  }

  public String toString() {
    return this.getName();
  }

}
