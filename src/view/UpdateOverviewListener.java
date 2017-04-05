/**
 *Gets the selected date in the JComboBox and updates fields
 *@author Jenna Tillotson
 */

package view;

import logmodel.Day;
import logmodel.Log;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import javax.swing.JComboBox;
import javax.swing.JTextField;

public class UpdateOverviewListener implements ActionListener {

  private JComboBox jcbDates;
  private JTextField jtfCalsConsumed;
  private JTextField jtfCalsBurned;
  private JTextField jtfNetCals;
  private JTextField jtfGoalMet;
  private ChartView cv;

  private Log log;
  private Day day;

  /**
   *Constructor for the UpdateOverViewListener object.
   *@param JComboBox   Holds the collection of logged dates.
   *@param JTextField  Displays calories consumed.
   *@param JTextField  Displays calories burned.
   *@param JTextField  Displays net calories.
   *@param JTextField  Displays whether goal was met or not.
   */
  public UpdateOverviewListener(JComboBox jcbDates, JTextField jtfCalsConsumed,
      JTextField jtfCalsBurned, JTextField jtfNetCals, JTextField jtfGoalMet, ChartView cv,
      Log log) {
    this.jcbDates = jcbDates;
    this.jtfCalsConsumed = jtfCalsConsumed;
    this.jtfCalsBurned = jtfCalsBurned;
    this.jtfNetCals = jtfNetCals;
    this.jtfGoalMet = jtfGoalMet;
    this.cv = cv;
    this.log = log;
  }

  /**
   * When the selected item in the JComboBox is changed,
   * It triggers this method to update the JTextFields accordingly.
   */
  public void actionPerformed(ActionEvent ae) {

    //get the date from the JComboBox
    Date date = (Date) jcbDates.getSelectedItem();
    day = log.getDaybyDate(date);

    /**
     *Send the day object to the chartview
     */
    cv.setDay(day);

    /**
     *Take the day info and update the fields
     */
    jtfCalsConsumed.setText(day.calculateCaloriesConsumed() + "");
    jtfCalsBurned.setText(day.calculateCaloriesExpended() + "");
    jtfNetCals.setText(day.calculateNetCalories() + "");

    /**
     *If net calories is zero or less, goal was met.
     *If net calories is more than zero, goal was not met.
     */
    if (day.calculateNetCalories() <= 0) {
      jtfGoalMet.setText("Yes");
    } else {
      jtfGoalMet.setText("No");
    }
  }
}
