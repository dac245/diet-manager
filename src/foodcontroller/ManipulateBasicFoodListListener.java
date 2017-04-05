/**
 * Provides an action for when the user changes the JComboBox which contains
 * the list of BasicFoods.
 * <p>
 * When creating the Action Listener, one must provide a FoodCollection from
 * which to reference BasicFood objects. One must also provide a JComboBox
 * which will contain the list of options they'll be choosing from (i.e. either
 * an already existing BasicFood or to create a new BasicFood). One must also
 * provide One must also provide a JTextComponent of their choice for the name
 * of the BasicFood, calories, fat, carbohydrates, and protein specified by the
 * user.
 *
 * @author Brendon Strowe
 * @version 1.0
 */

package foodcontroller;

import foodmodel.BasicFood;
import foodmodel.FoodCollection;
import foodmodel.Recipe;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;
import javax.swing.text.JTextComponent;

public class ManipulateBasicFoodListListener implements ActionListener {

  /**
   * Food collection to read in and change.
   */
  private FoodCollection foodCollection;
  /**
   * JComboBox which contains a list of options the user can take.
   */
  private JComboBox jcbBasicFoodList;
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
   * Constructor of ManipulateBasicFoodListListener object.
   * Takes in a FoodCollection to manipulate, a JComboBox which contains a list
   * of options for what/how to manipulate, and a set of JTextComponents which
   * contain a way for a user to manipulate the properties of BasicFood objects.
   *
   * @param food     FoodCollection to read in and change
   * @param list     JComboBox which contains the options the user can take.
   * @param name     JTextComponent which contains the BasicFood's name
   * @param calories JTextComponent which contains the BasicFood's calorie count
   * @param fat      JTextComponent which contains the BasicFood's fat
   * @param carbs    JTextComponent which contains the BasicFood's carbohydrates
   * @param protein  JTextComponent which contains the BasicFood's protein
   */
  public ManipulateBasicFoodListListener(FoodCollection food, JComboBox list,
      JTextComponent name, JTextComponent calories, JTextComponent fat,
      JTextComponent carbs, JTextComponent protein) {
    this.foodCollection = food;
    this.jcbBasicFoodList = list;
    this.jtcName = name;
    this.jtcCalories = calories;
    this.jtcFat = fat;
    this.jtcCarbohydrates = carbs;
    this.jtcProtein = protein;
  } // end constructor

  /**
   * Action Performed
   * Figures out what manipulation the user wants to do, and performs an action
   * based on that choice: Either create a new BasicFood or manipulate an
   * existing BasicFood.
   */
  public void actionPerformed(ActionEvent e) {
    // If there are items in the option list, then do something
    if (jcbBasicFoodList.getItemCount() > 0) {
      BasicFood selectedItem = (BasicFood)jcbBasicFoodList.getSelectedItem();
      // If the selected item's name is "New...",
      // then we're creating a new BasicFood
      if (selectedItem.getName().equals("New...")) {
        jtcName.setText("");
        jtcCalories.setText("");
        jtcFat.setText("");
        jtcCarbohydrates.setText("");
        jtcProtein.setText("");
      } else {
        BasicFood selectedBasicFood =
            (BasicFood)jcbBasicFoodList.getSelectedItem();
        jtcName.setText(selectedBasicFood.getName() + "");
        jtcCalories.setText(selectedBasicFood.getCalories() + "");
        jtcFat.setText(selectedBasicFood.getFat() + "");
        jtcCarbohydrates.setText(selectedBasicFood.getCarbs() + "");
        jtcProtein.setText(selectedBasicFood.getProtein() + "");
      }
    }
  }
} // end class ManipulateBasicFoodListListener
