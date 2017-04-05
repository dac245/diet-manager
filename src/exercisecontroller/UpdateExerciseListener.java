/**
 * Provides an action for when the user wishes to create a new Exercise or
 * update an existing Exercise.
 * <p>
 * When creating the Action Listener, one must provide an ExerciseCollection
 * from where to add the new Exercise. One must also provide a JTextComponent
 * of their choice for the name of the Exercise and calories expended specified
 * by the user.
 *
 * @author Brendon Strowe
 * @version 2.0
 */

package exercisecontroller;

import exercisemodel.Exercise;
import exercisemodel.ExerciseCollection;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.text.JTextComponent;

public class UpdateExerciseListener implements ActionListener {

  /**
   * Exercise collection to read in and change.
   */
  private ExerciseCollection exerciseCollection;
  /**
   * JTextComponent which contains the Exercises's name.
   */
  private JTextComponent jtcName;
  /**
   * JTextComponent which contains the Exercises's calories expended value.
   */
  private JTextComponent jtcCaloriesExpended;

  /**
   * Constructor of UpdateExerciseListener object.
   * Takes in all of the JTextComponent from which it will read in user input.
   * The user input values will be passed on to create a new Exercise.
   *
   * @param exercises ExerciseCollection to read in and change
   * @param name      JTextComponent which contains the Exercises's name
   * @param calories  JTextComponent which contains the Exercises's calories
   */
  public UpdateExerciseListener(ExerciseCollection exercises,
      JTextComponent name, JTextComponent calories) {
    this.exerciseCollection = exercises;
    this.jtcName = name;
    this.jtcCaloriesExpended = calories;
  } // end constructor

  /**
   * Action Performed
   * Reads in the currently set values of the GUI elements and uses that to
   * determine to which to add the ingredient.
   */
  public void actionPerformed(ActionEvent e) {
    String exerciseName = jtcName.getText();
    double caloriesExpended = Double.parseDouble(jtcCaloriesExpended.getText());

    // if the Exercise does not already exist in the ExerciseCollection,
    //   create it.
    // else, update the existing Exercise object
    if (exerciseCollection.getExerciseByName(exerciseName) == null) {
      exerciseCollection.addExercise(new Exercise(jtcName.getText(),
          caloriesExpended));
    } else {
      Exercise exercise = (Exercise)exerciseCollection.getExerciseByName(
          exerciseName);
      exercise.setCaloriesExpended(caloriesExpended);
    }
  }

} // end class UpdateExerciseListener
