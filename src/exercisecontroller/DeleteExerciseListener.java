/**
 * Provides an action for when the user wishes to delete an Exercise.
 * <p>
 * When creating the Action Listener, one must provide an ExerciseCollection
 * from where to add the new Exercise. One must also provide a JTextComponent
 * of their choice for the name of the Exercise.
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

public class DeleteExerciseListener implements ActionListener {

  /**
   * Exercise collection to read in and change.
   */
  private ExerciseCollection exerciseCollection;
  /**
   * JTextComponent which contains the Exercises's name.
   */
  private JTextComponent jtcName;

  /**
   * Constructor of DeleteExerciseListener object.
   * Takes in all of the JTextComponent from which it will read in user input.
   * The user input values will be passed on to create a new Exercise.
   *
   * @param exercises ExerciseCollection to read in and change
   * @param name      JTextComponent which contains the Exercises's name
   */
  public DeleteExerciseListener(ExerciseCollection exercises,
      JTextComponent name) {
    this.exerciseCollection = exercises;
    this.jtcName = name;
  } // end constructor

  /**
   * Action Performed
   * Reads in the currently set values of the GUI elements and uses that to
   * determine which Exercise to delete.
   */
  public void actionPerformed(ActionEvent e) {
    String exerciseName = jtcName.getText();

    // delete the specified Exercise object
    exerciseCollection.deleteExercise(exerciseName);
  }

} // end class UpdateExerciseListener
