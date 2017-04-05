/**
 * Provides an action for when the user wishes to create a new BasicFood or
 * update an existing BasicFood.
 * <p>
 * When creating the Action Listener, one must provide a FoodCollection from
 * which to add the new Recipe. One must also provide a JTextComponent of
 * their choice for the name of the BasicFood, calories, fat, carbohydrates,
 * and protein specified by the user.
 *
 * @author Brendon Strowe
 * @version 1.0
 */

package foodcontroller;

import foodmodel.BasicFood;
import foodmodel.FoodCollection;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.text.JTextComponent;

public class BasicFoodUpdateListener implements ActionListener {

  /**
   * Food collection to read in and change.
   */
  private FoodCollection foodCollection;
  /**
   * JTextComponent which contains the BasicFood's name.
   */
  private JTextComponent jtcName;
  /**
   * JTextComponent which contains the BasicFood's calorie count.
   */
  private JTextComponent jtcCalories;
  /**
   * JTextComponent which contains the BasicFood's fat.
   */
  private JTextComponent jtcFat;
  /**
   * JTextComponent which contains the BasicFood's carbs.
   */
  private JTextComponent jtcCarbohydrates;
  /**
   * JTextComponent which contains the BasicFood's protein.
   */
  private JTextComponent jtcProtein;

  /**
   * Constructor of BasicFoodUpdateListener object.
   * Takes in all of the JTextComponent from which it will read in user input.
   * The user input values will be passed on to create a new Basic Food.
   *
   * @param food     FoodCollection to read in and change
   * @param name     JTextComponent which contains the BasicFood's name
   * @param calories JTextComponent which contains the BasicFood's calorie count
   * @param fat      JTextComponent which contains the BasicFood's fat
   * @param carbs    JTextComponent which contains the BasicFood's carbohydrates
   * @param protein  JTextComponent which contains the BasicFood's protein
   */
  public BasicFoodUpdateListener(FoodCollection food, JTextComponent name,
      JTextComponent calories, JTextComponent fat, JTextComponent carbs,
      JTextComponent protein) {
    this.foodCollection = food;
    this.jtcName = name;
    this.jtcCalories = calories;
    this.jtcFat = fat;
    this.jtcCarbohydrates = carbs;
    this.jtcProtein = protein;
  } // end constructor

  /**
   * Action Performed
   * Reads in the currently set values of the GUI elements and uses that to
   * determine to which to add the ingredient.
   */
  public void actionPerformed(ActionEvent e) {
    String foodName = jtcName.getText();
    double calories = Double.parseDouble(jtcCalories.getText());
    double fat = Double.parseDouble(jtcFat.getText());
    double carbs = Double.parseDouble(jtcCarbohydrates.getText());
    double protein = Double.parseDouble(jtcProtein.getText());

    // if the BasicFood does not already exist in the FoodCollection, create it.
    // else, update the existing BasicFood object
    if (foodCollection.getFoodByName(foodName) == null) {
      foodCollection.addFood(new BasicFood(jtcName.getText(), calories, fat, carbs, protein));
    } else {
      BasicFood food = (BasicFood)foodCollection.getFoodByName(foodName);
      food.setCalories(calories);
      food.setFat(fat);
      food.setCarbs(carbs);
      food.setProtein(protein);
    }
    foodCollection.printFoodCollection();

  }

} // end class BasicFoodUpdateListener
