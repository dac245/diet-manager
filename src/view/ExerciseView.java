/**
 *JPanel for the Exercise tab on the GUI
 *@author Jenna Tillotson
 */

package view;

import exercisecontroller.DeleteExerciseListener;
import exercisecontroller.ManipulateExerciseListListener;
import exercisecontroller.UpdateExerciseListener;
import exercisemodel.Exercise;
import exercisemodel.ExerciseCollection;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.Observable;
import java.util.Observer;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ExerciseView extends JPanel implements Observer {

  //global variables
  private JPanel jpCenter;
  private JPanel jpName;
  private JPanel jpCalories;
  private JPanel jpMutatorButtons;

  private JComboBox<Exercise> jcbExercises;
  private JTextField jtfExerciseName;
  private JTextField jtfCalories;
  private JButton jbUpdate;
  private JButton jbDelete;
  private ExerciseCollection collectionOfExercises;

  /**
   * The constructor for the ExerciseView object.
   * Takes in an ExerciseCollection object.
   * @param ExerciseCollection the collection of exercises.
   */
  public ExerciseView(ExerciseCollection exerciseCollection) {

    //Save FoodCollection for observation
    this.collectionOfExercises = exerciseCollection;
    collectionOfExercises.addObserver(this);

    setLayout(new BorderLayout());

    //Create a JComboBox to store the list of Exercises
    Vector<Exercise> listOfExercises = new Vector<Exercise>();
    listOfExercises.add(new Exercise("New...",0));
    for (Exercise e : collectionOfExercises.getExercises()) {
      listOfExercises.add(e);
    }
    jcbExercises = new JComboBox<Exercise>(listOfExercises);
    add(jcbExercises, BorderLayout.NORTH);

    jpCenter = new JPanel(new GridLayout(0,1));

    jpName = new JPanel();
    jpName.add(new JLabel("Name of Exercise:", JLabel.RIGHT));
    jtfExerciseName = new JTextField(5);
    jpName.add(jtfExerciseName);
    jpCenter.add(jpName);

    jpCalories = new JPanel();
    jpCalories.add(new JLabel("Cals burned per hour by 100lb person:", JLabel.RIGHT));
    jtfCalories = new JTextField(5);
    jpCalories.add(jtfCalories);
    jpCenter.add(jpCalories);

    add(jpCenter, BorderLayout.CENTER);

    jpMutatorButtons = new JPanel();
    add(jpMutatorButtons, BorderLayout.SOUTH);

    jbUpdate = new JButton("Update/Add");
    jpMutatorButtons.add(jbUpdate, BorderLayout.SOUTH);
    jbDelete = new JButton("Delete");
    jpMutatorButtons.add(jbDelete, BorderLayout.SOUTH);

    //Add ActionListeners
    jcbExercises.addActionListener(new ManipulateExerciseListListener(
        exerciseCollection, jcbExercises, jtfExerciseName, jtfCalories));
    jbUpdate.addActionListener(new UpdateExerciseListener(exerciseCollection,
        jtfExerciseName, jtfCalories));
    jbDelete.addActionListener(new DeleteExerciseListener(exerciseCollection,
        jtfExerciseName));

  } //end constructor


  public void update(Observable observing, Object o) {

    //Return on spurious update from unknown observable.
    if (observing != collectionOfExercises) {
      return;
    }

    Vector<Exercise> listOfExercises = new Vector<Exercise>();
    listOfExercises.add(new Exercise("New...",0));
    for (Exercise e : collectionOfExercises.getExercises()) {
      listOfExercises.add(e);
    }
    jcbExercises.removeAllItems();
    jcbExercises.setModel(new javax.swing.DefaultComboBoxModel<Exercise>(listOfExercises));
  }

} //end class ExerciseView
