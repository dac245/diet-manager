/**
 * This class sends the date, calories and weight to
 * the Day class so it can add those
 * new values to the .csv files.
 * @author David Camacho
 * @version 1.0
 */

package logcontroller;

import logmodel.Day;
import logmodel.Log;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.text.JTextComponent;

public class AddDateListener implements ActionListener {

  /**
   * Day is instantiated with the variable newDate.
   */
  private Day newDay;
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
  private JComboBox comboDay;
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
   * For the weight JTextField from the GUI to our
   * JTextField variable.
   */
  private JTextField weightReceived;
  /**
   * For the calorie JTextField from the GUI to our
   * JTextField variable.
   */
  private JTextField calsReceived;
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
   * To add our converted weight JTextField.
   */
  private double doubleWeightReceived;
  /**
   * To add our converted calorie JTextField.
   */
  private double doubleCalsReceived;

  /**
   * Instantiate a Place to Save.
   */
  //private saveLogController saveLog = new saveLogController();



  /**
   * AddDateListener constructor gets all the values from the
   * button pressed and adds it to our own variables we made.
   */
  public AddDateListener(Log log, JComboBox comboDay, JComboBox comboMonth,
      JComboBox comboYear, JTextField weightReceived, JTextField calsReceived) {
    this.log = log;
    this.comboDay = comboDay;
    this.comboMonth = comboMonth;
    this.comboYear = comboYear;
    this.weightReceived = weightReceived;
    this.calsReceived = calsReceived;
  }

  /**
   * When the button is pressed this method is called to convert
   * the JComponent variables and pass those newly converted
   * values to the appropriate Day methods.
   */
  public void actionPerformed(ActionEvent e) {
    doubleCalsReceived = Double.parseDouble(calsReceived.getText());
    doubleWeightReceived = Double.parseDouble(weightReceived.getText());

    intComboDay = comboDay.getSelectedIndex();
    intComboMonth = comboMonth.getSelectedIndex();
    stringComboYear = (String)comboYear.getSelectedItem() ;

    intComboDay++;
    intComboMonth++;



    //CAST INTS TO STRING FOR SDF
    String dayString = Integer.toString(intComboDay);
    String monthString = Integer.toString(intComboMonth);
    String yearString = stringComboYear;



    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    try {

      Date sendDate = sdf.parse(dayString + "/" + monthString + "/" + yearString);

      log.addDateToLog(sendDate, doubleWeightReceived, doubleCalsReceived);
      System.out.println(sendDate);

    } catch (ParseException e1) {

      e1.printStackTrace();

    }
    //Create New Day with collected Data
    newDay = new Day(convDate,doubleWeightReceived,doubleCalsReceived);

    System.out.println(doubleWeightReceived);
    System.out.println(doubleCalsReceived);
    /*try {
      saveLog.saveDate(newDay);
    } catch (IOException e1) {
      e1.printStackTrace();
    }*/

    //PRINT ENTIRE LOG
    log.printHashMap();

  }
}
