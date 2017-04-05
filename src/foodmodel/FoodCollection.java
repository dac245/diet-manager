/**
 * Handles the list of foods that the user can select from the GUI.
 * <p>
 * A CSV file will be read in durng instansiation and assigned to the foods
 * ArrayList where foods can then be added or deleted.
 * Observers will be notified whenever a change occurs to the list.
 *
 * @author Nathan Hinds
 * @version 1.0
 */

package foodmodel;

import java.util.ArrayList;
import java.util.Observable;

public class FoodCollection extends Observable {

  /**
   * The list of foods that are currently in the collection.
   */
  private ArrayList<Food> foods = new ArrayList<Food>();

  /**
   * Default constructor.
   */
  public FoodCollection() {

  }

  /**
   * Constructor that takes in an already established array of food objects.
   */
  public FoodCollection(Food[] startFoods) {
    for (int i = 0; i < startFoods.length; i++) {
      foods.add(startFoods[i]);
    }
  }

  /**
   * Returns the entire list of foods currently in the collection.
   */
  public ArrayList<Food> getFoods() {
    return this.foods;
  }

  /**
   * Clears the collection of foods and replaces them
   * with a given array of foods.
   */
  public void setFoods(Food[] newFoods) {
    for (int i = 0; i < newFoods.length; i++) {
      foods.remove(i);
      foods.add(newFoods[i]);
    }
  }

  /**
   * Adds a new food item to the end of the foods ArrayList after
   * iterating through the collection to ensure that food being added
   * doesn't already exist.
   */
  public void addFood(Food newFood) {
    for (int i = 0; i < foods.size(); i++) {
      if (foods.get(i).equals(newFood)) {
        System.out.printf("Food already exists");
        break;
      }
    }
    foods.add(newFood);
    setChanged();
    notifyObservers();
  }

  /**
   * Deletes a specified food object from the foods ArrayList.
   */
  public void deleteFood(String foodName) {
    for (int i = 0; i < foods.size(); i++) {
      if (foods.get(i).getName().toLowerCase().equals(foodName.toLowerCase())) {
        foods.remove(i);
        setChanged();
        notifyObservers();
        break;
      }
    }
    System.out.printf("No food by name of " + foodName + " exists");
  }

  /**
   * Returns a requested food object based on its name.
   */
  public Food getFoodByName(String foodName) {
    for (int i = 0; i < foods.size(); i++) {
      if (foods.get(i).getName().toLowerCase().equals(foodName.toLowerCase())) {
        return foods.get(i);
      }
    }
    System.out.printf("Food not found");
    return null;
  }

  public void printFoodCollection() {

    for (Food f : foods) {
      System.out.println(f.getName());
    }
  }
}
