/**
 * Provides an action for when the user wishes to create a new Recipe.
 * <p>
 * When creating the Action Listener, one must provide a FoodCollection from
 * which to add the new Recipe. One must also provide a JTextComponent which
 * contains the name of the new Recipe to be created.
 *
 * @author Brendon Strowe
 * @version 2.0
 */

package foodcontroller;

import foodmodel.Food;
import foodmodel.FoodCollection;
import foodmodel.Recipe;
import view.CustomTableModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.text.JTextComponent;

public class AddRecipeListener implements ActionListener {

  /**
   * Food collection to read in and change.
   */
  private FoodCollection foodCollection;
  /**
   * The JTextComponent which contains the name of the Recipe to be added.
   */
  private JTextComponent jtcRecipeName;
  /**
   * The CustomTableModel which which will observe the currently set Recipe.
   */
  private CustomTableModel ingredientsTableModel;

  /**
   * Constructor of AddRecipeListener object.
   * Takes in a String name and uses it to instantiate a new Recipe.
   * The recipe is then later added to the FoodCollection.
   *
   * @param food FoodCollection to read in and change
   * @param name Name of Recipe to be created and added to the FoodCollection
   * @param tableModel TableModel which will observe the currently set Recipe
   */
  public AddRecipeListener(FoodCollection food, JTextComponent name,
      CustomTableModel tableModel) {
    this.foodCollection = food;
    this.jtcRecipeName = name;
    this.ingredientsTableModel = tableModel;
  } // end constructor

  /**
   * Action Performed.
   * Creates a new Recipe objects based on the provided name and
   * adds it to the FoodCollection.
   */
  public void actionPerformed(ActionEvent e) {
    String recipeName = jtcRecipeName.getText();

    // if the Recipe does not already exist in the FoodCollection, create it.
    // Set the GUI to observe this Recipe.
    if (foodCollection.getFoodByName(recipeName) == null) {
      foodCollection.addFood(new Recipe(jtcRecipeName.getText()));

      this.ingredientsTableModel.setObservableRecipe(
          (Recipe)foodCollection.getFoodByName(jtcRecipeName.getText()));
    }
  } // end actionPerformed()
} // end class AddRecipeListener
