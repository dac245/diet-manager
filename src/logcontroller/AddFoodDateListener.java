/**
 * This class sends the date, food and servings
 * to Day class to store in the .csv file.
 * @author David Camacho
 * @version 1.0
 */

package logcontroller;

import foodmodel.Food;
import foodmodel.FoodCollection;
import logmodel.Day;
import logmodel.Log;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JComboBox;
import javax.swing.JTextField;

public class AddFoodDateListener implements ActionListener {
  /**
   * Day is instantiated with the variable newFoodDate.
   */
  private Day newFoodDate;
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
  private JComboBox<Food> consumedFood;
  /**
   * For the servings JTextField from the GUI to our
   * JTextField variable.
   */
  private JTextField servReceived;
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
   * To add our converted servings JTextField.
   */
  private double doubleServReceived;
  /**
   * To add our converted food JComboBox.
   */
  private Food convConsumedFood;
  /**
   * Get the collection of food.
   */
  private FoodCollection foodCollection;

  /**
   * AddFoodDateListener constructor gets the JComponents
   * from the GUI and adds them to
   * our own JComponent variables.
   */
  public AddFoodDateListener(FoodCollection food, JComboBox<Date> comboDay,
      JTextField servReceived, JComboBox<Food> consumedFood, Log log) {
    this.foodCollection = food;
    this.comboDay = comboDay;
    this.servReceived = servReceived;
    this.consumedFood = consumedFood;
    this.log = log;
  }

  /**
   * When the button is pressed it converts the JComponents
   * variables and passes them to the Day class using
   * setDate and setFood methods.
   */
  public void actionPerformed(ActionEvent e) {

    Food foodSelected = (Food)consumedFood.getSelectedItem();
    Double servings = Double.parseDouble(servReceived.getText());
    Date selectedDate = (Date)comboDay.getSelectedItem();
    Day selectedDay = log.getDaybyDate(selectedDate);
    selectedDay.setFood(foodSelected, servings);

    /*doubleServReceived = Double.parseDouble(servReceived.getText());
    convConsumedFood = (Food)consumedFood.getSelectedItem();
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
      log.setDate(sendDate); // sets Date in Log
      newFoodDate.setFood(convConsumedFood, doubleServReceived);

    } catch (ParseException e1) {
      e1.printStackTrace();
    }*/
  }
}
