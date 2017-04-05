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
 * @version 2.0
 */

package exercisecontroller;

import exercisemodel.Exercise;
import exercisemodel.ExerciseCollection;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;
import javax.swing.text.JTextComponent;

public class ManipulateExerciseListListener implements ActionListener {

  /**
   * Exercise collection to read in and change.
   */
  private ExerciseCollection exerciseCollection;
  /**
   * JComboBox which contains a list of options the user can take.
   */
  private JComboBox jcbExerciseList;
  /**
   * JTextComponent which contains the Exercise's name.
   */
  private JTextComponent jtcName;
  /**
   * JTextComponent which contains the Exercise's calories expended value.
   */
  private JTextComponent jtcCaloriesExpended;

  /**
   * Constructor of ManipulateExerciseListListener object.
   * Takes in an ExerciseCollection to manipulate, a JComboBox which contains a
   * list of options for what/how to manipulate, and a set of JTextComponents
   * which contain a way for a user to manipulate the properties of Exercise
   * objects.
   *
   * @param exercises ExerciseCollection to read in and change
   * @param list      JComboBox which contains the options the user can take.
   * @param name      JTextComponent which contains the Exercises's name
   * @param calories  JTextComponent which contains the Exercises's calories
   */
  public ManipulateExerciseListListener(ExerciseCollection exercises,
      JComboBox list, JTextComponent name, JTextComponent calories) {
    this.exerciseCollection = exercises;
    this.jcbExerciseList = list;
    this.jtcName = name;
    this.jtcCaloriesExpended = calories;
  } // end constructor

  /**
   * Action Performed
   * Figures out what manipulation the user wants to do, and performs an action
   * based on that choice: Either create a new Exercise or manipulate an
   * existing Exercise.
   */
  public void actionPerformed(ActionEvent e) {
    // If there are items in the option list, then do something
    if (jcbExerciseList.getItemCount() > 0) {
      Exercise selectedItem = (Exercise)jcbExerciseList.getSelectedItem();
      // If the selected item's name is "New...",
      // then we're creating a new Exercise
      if (selectedItem.getName().equals("New...")) {
        jtcName.setText("");
        jtcCaloriesExpended.setText("");
      } else {
        Exercise selectedExercise =
            (Exercise)jcbExerciseList.getSelectedItem();
        jtcName.setText(selectedExercise.getName() + "");
        jtcCaloriesExpended.setText(selectedExercise.getCaloriesExpended() + "");
      }
    }
  }
} // end class ManipulateBasicFoodListListener
