/**
 * Handles the list of Exercises that the user can select from the GUI.
 * <p>
 * A CSV file will be read in during instantiation and used to populate the
 * collection with Exercise objects. Exercise objects will be stored in an
 * ArrayList where they can then be added or deleted.
 * Observers will be notified whenever a change occurs to the list.
 *
 * @author Brendon Strowe
 * @version 2.0
 */

package exercisemodel;

import java.util.ArrayList;
import java.util.Observable;

public class ExerciseCollection extends Observable {

  /**
   * The list of exercises that are currently in the collection.
   */
  private ArrayList<Exercise> exercises;
  
  /**
   * Default constructor.
   */
  public ExerciseCollection() {
    this.exercises = new ArrayList<Exercise>();
  }

  /**
   * Constructor that takes in an already established array of exercise objects.
   */
  public ExerciseCollection(Exercise[] listOfExercises) {
    for (Exercise e : listOfExercises) {
      exercises.add(e);
    }
  }

  /**
   * Returns the entire list of exercises currently in the collection.
   */
  public ArrayList<Exercise> getExercises() {
    return this.exercises;
  }

  /**
   * Clears the collection of exercises and replaces them
   * with a given array of exercises.
   */
  public void setExercises(Exercise[] newListOfExercises) {
    for (Exercise e : exercises) {
      exercises.remove(e);
    }
    for (Exercise e : newListOfExercises) {
      exercises.add(e);
    }
  }

  /**
   * Adds a new exercise item to the end of the exercises ArrayList after
   * iterating through the collection to ensure that exercises being added
   * don't already exist.
   */
  public void addExercise(Exercise newExercise) {
    for (Exercise e : exercises) {
      if (exercises.get(exercises.indexOf(e)).equals(newExercise)) {
        System.out.println("Exercise already exists");
        return;
      }
    }
    exercises.add(newExercise);
    setChanged();
    notifyObservers();
  }

  /**
   * Deletes a specified Exercise object from the exercises ArrayList.
   */
  public void deleteExercise(String exerciseName) {
    for (Exercise e : exercises) {
      if (exercises.get(exercises.indexOf(e)).getName().toLowerCase().equals(
          exerciseName.toLowerCase())) {
        exercises.remove(e);
        setChanged();
        notifyObservers();
        return;
      }
    }
    System.out.println("No Exercise by name of " + exerciseName + " exists");
  }

  /**
   * Returns a requested Exercise object based on its name.
   */
  public Exercise getExerciseByName(String exerciseName) {
    for (Exercise e : exercises) {
      if (exercises.get(exercises.indexOf(e)).getName().toLowerCase().equals(
          exerciseName.toLowerCase())) {
        return exercises.get(exercises.indexOf(e));
      }
    }
    System.out.println("Exercise not found!");
    return null;
  }
}
