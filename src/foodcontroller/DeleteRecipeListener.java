/**
 * Provides an action for when the user wishes to delete a Recipe.
 * <p>
 * When creating the Action Listener, one must provide a FoodCollection from
 * which to delete the Recipe. One must also provide a JTextComponent which
 * contains the name of the Recipe to be deleted.
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
import javax.swing.text.JTextComponent;

public class DeleteRecipeListener implements ActionListener {

  /**
   * Food collection to read in and change.
   */
  private FoodCollection foodCollection;
  /**
   * The JTextComponent which contains the name of the Recipe to be deleted.
   */
  private JTextComponent jtcRecipeName;

  /**
   * Constructor of DeleteRecipeListener object.
   * Takes in a String name and uses it to instantiate a new Recipe.
   * The recipe is then later added to the FoodCollection.
   *
   * @param food FoodCollection to read in and change
   * @param name Name of Recipe to be deleted from the FoodCollection
   */
  public DeleteRecipeListener(FoodCollection food, JTextComponent name) {
    this.foodCollection = food;
    this.jtcRecipeName = name;
  } // end constructor

  /**
   * Action Performed.
   * Deletes a Recipe object based on the provided name and
   * removes it from the FoodCollection.
   */
  public void actionPerformed(ActionEvent e) {
    String recipeName = jtcRecipeName.getText();

    // if the Recipe exists in the FoodCollection, delete it.
    if (foodCollection.getFoodByName(recipeName) != null) {
      foodCollection.deleteFood(recipeName);
    }
  } // end actionPerformed()
} // end class DeleteRecipeListener
