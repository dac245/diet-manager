/**
 * Provides an action for when the user changes the JComboBox which contains
 * the list of Recipes.
 * <p>
 * When creating the Action Listener, one must provide a FoodCollection from
 * which to reference Recipe objects. One must also provide a JComboBox
 * which will contain the list of options they'll be choosing from (i.e. either
 * an already existing Recipe or to create a new Recipe). One must also provide
 * a JTextComponent of their choice for the name of the Recipe specified by the
 * user.
 *
 * @author Brendon Strowe
 * @version 2.0
 */

package foodcontroller;

import foodmodel.FoodCollection;
import foodmodel.Recipe;
import view.CustomTableModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;
import javax.swing.text.JTextComponent;

public class ManipulateRecipeListListener implements ActionListener {

  /**
   * Food collection to read in and change.
   */
  private FoodCollection foodCollection;
  /**
   * JComboBox which contains a list of options the user can take.
   */
  private JComboBox jcbRecipeList;
  /**
   * JTextComponent which contains the BasicFood's name.
   */
  private JTextComponent jtcRecipeName;
  /**
   * The CustomTableModel which which will observe the currently set Recipe.
   */
  private CustomTableModel ingredientsTableModel;

  /**
   * Constructor of ManipulateRecipeListListener object.
   * Takes in a FoodCollection to manipulate, a JComboBox which contains a list
   * of options for what/how to manipulate, and a JTextComponents which
   * contain a way for a user to specify the name of a Recipe object.
   *
   * @param food     FoodCollection to read in and change
   * @param list     JComboBox which contains the options the user can take.
   * @param name     JTextComponent which contains the Recipe's name
   * @param tableModel TableModel which will observe the currently set Recipe
   */
  public ManipulateRecipeListListener(FoodCollection food, JComboBox list,
      JTextComponent name, CustomTableModel tableModel) {
    this.foodCollection = food;
    this.jcbRecipeList = list;
    this.jtcRecipeName = name;
    this.ingredientsTableModel = tableModel;
  } // end constructor

  /**
   * Action Performed
   * Figures out what manipulation the user wants to do, and performs an action
   * based on that choice: Either create a new Recipe or manipulate an
   * existing Recipe.
   */
  public void actionPerformed(ActionEvent e) {
    // If there are items in the option list, then do something
    if (jcbRecipeList.getItemCount() > 0) {
      Recipe selectedItem = (Recipe)jcbRecipeList.getSelectedItem();
      // If the selected item's name is "New...",
      // then we're creating a new Recipe
      if (selectedItem.getName().equals("New...")) {
        jtcRecipeName.setText("");
      } else {
        Recipe selectedRecipe = (Recipe)jcbRecipeList.getSelectedItem();
        jtcRecipeName.setText(selectedRecipe.getName() + "");

        this.ingredientsTableModel.setObservableRecipe(
            (Recipe)foodCollection.getFoodByName(jtcRecipeName.getText()));
      }
    }
  }
} // end class ManipulateRecipeListListener
