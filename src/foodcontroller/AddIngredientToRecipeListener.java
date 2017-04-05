/**
 * Provides an action for when the user wishes to add Food to a Recipe.
 * <p>
 * When creating the Action Listener, one must provide a FoodCollection from
 * which to reference Food objects. One must also provide a JTextComponent of
 * their choice for the name of the Recipe, a JComboBox which contains the
 * selected Food to add, and another JTextComponent which contains the quantity
 * of the Food ingredient to be added to the Recipe.
 *
 * @author Brendon Strowe
 * @version 2.0
 */

package foodcontroller;

import foodmodel.Food;
import foodmodel.FoodCollection;
import foodmodel.Recipe;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;
import javax.swing.text.JTextComponent;

public class AddIngredientToRecipeListener implements ActionListener {

  /**
   * Food collection to read in and changed.
   */
  private FoodCollection foodCollection;
  /**
   * JTextComponent which contains the Recipe's name.
   */
  private JTextComponent jtcRecipeName;
  /**
   * JComboBox which contains the Food to be added.
   */
  private JComboBox jcbFoodIngredient;
  /**
   * JTextComponent which contains the quantity of ingredient.
   */
  private JTextComponent jtcQuantity;

  /**
   * Constructor of AddIngredientToRecipeListener object.
   * Takes in all of the JComponents from which it will read in user input.
   * The user input values will be passed on to add items to a Recipe.
   *
   * @param food       FoodCollection to read in and changed
   * @param recipeName JTextComponent which contains the Recipe's name
   * @param ingredient JComboBox which contains the selected Food to be added
   * @param quantity   JTextComponent which contains the quantity of ingredient
   */
  public AddIngredientToRecipeListener(FoodCollection food,
      JTextComponent recipeName, JComboBox ingredient,
      JTextComponent quantity) {
    this.foodCollection = food;
    this.jtcRecipeName = recipeName;
    this.jcbFoodIngredient = ingredient;
    this.jtcQuantity = quantity;
  } // end constructor

  /**
   * Action Performed
   * Reads in the currently set values of the GUI elements and uses them to
   * determine which Recipe to which to add the ingredient.
   */
  public void actionPerformed(ActionEvent e) {
    String recipeName = this.jtcRecipeName.getText();
    Recipe recipe = (Recipe)foodCollection.getFoodByName(recipeName);

    Food ingredient = (Food)jcbFoodIngredient.getSelectedItem();

    double quantity = Double.parseDouble(jtcQuantity.getText());

    // Check to make sure the ingreident has not already been added
    if (!recipe.getIngredients().containsKey(ingredient)) {
      recipe.addIngredient(ingredient, quantity);
      
      // If the ingredient has already been added, update the quantity
    } else {
      recipe.deleteIngredient(ingredient);
      recipe.addIngredient(ingredient, quantity);
    }
  }

} // end class AddIngredientToRecipeListener
