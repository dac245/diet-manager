/**
 * This class sends the date and exercises to
 * the Day class so it can add those
 * new values to the .csv files.
 * @author David Camacho
 * @version 1.0
 */

package logcontroller;

import exercisemodel.Exercise;
import logmodel.Day;
import logmodel.Log;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.text.JTextComponent;

public class AddExerciseListener implements ActionListener {
  /**
   * Day is instantiated with the variable newFoodDate.
   */
  private Day newExerDate;
  /**
   * Log of Day objects already in existance.
   */
  private Log log;
  /**
   * The Date is instantiated to convert the given year,
   * month and date to a Date object.
   */
  private Date convDate;
  /**
   * For the day JComboBox from the GUI to our
   * JComboBox variable.
   */
  private JComboBox<Date> comboDay;
  /**
   * For the month JComboBox from the GUI to our
   * JComboBox variable.
   */
  private JComboBox comboMonth;
  /**
   * For the year JComboBox from the GUI to our
   * JComboBox variable.
   */
  private JComboBox comboYear;
  /**
   * For the Food JComboBox from the GUI to our
   * JComboBox variable.
   */
  private JComboBox<Exercise> exerciseName;
  /**
   * For the servings JTextField from the GUI to our
   * JTextField variable.
   */
  private JTextField exerciseLength;
  /**
   * To add our converted day JComboBox.
   */
  private int intComboDay;
  /**
   * To add our converted month JComboBox.
   */
  private int intComboMonth;
  /**
   * To add our converted year JComboBox.
   */
  private String stringComboYear;
  /**
   * To add our converted JTextField to an Exercise.
   */
  private Exercise exerName;

  public AddExerciseListener(JComboBox<Date> comboDay, JComboBox<Exercise> exerciseName,
      JTextField exerciseLength, Log log) {
    this.comboDay = comboDay;
    this.exerciseName = exerciseName;
    this.exerciseLength = exerciseLength;
    this.log = log;
  }

  public void actionPerformed(ActionEvent e) {

    Exercise exerciseSelected = (Exercise)exerciseName.getSelectedItem();
    Double exerciseMins = Double.parseDouble(exerciseLength.getText());
    Date selectedDate = (Date)comboDay.getSelectedItem();
    Day selectedDay = log.getDaybyDate(selectedDate);
    selectedDay.setExercise(exerciseSelected, exerciseMins);

    /*exerName = (Exercise)exerciseName.getSelectedItem();
    exerLength = Double.parseDouble(exerciseLength.getText());
    intComboDay = comboDay.getSelectedIndex();
    intComboMonth = comboMonth.getSelectedIndex();
    stringComboYear = (String)comboYear.getSelectedItem();
    intComboDay++;
    intComboMonth++;

    //CAST INTS TO STRING FOR SDF
    String dayString = Integer.toString(intComboDay);
    String monthString = Integer.toString(intComboMonth);
    String yearString = stringComboYear;

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    try {
      Date sendDate = sdf.parse(dayString + "/" + monthString + "/" + yearString);
      log.setDate(sendDate);
      newExerDate.setExercise(exerName, exerLength);
    } catch (ParseException e1) {
      e1.printStackTrace();
    }*/
  }
}
