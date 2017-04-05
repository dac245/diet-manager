/**
 *JPanel for the Log tab on the GUI
 *@author Jenna Tillotson
 */

package view;

import exercisemodel.Exercise;
import exercisemodel.ExerciseCollection;
import foodmodel.BasicFood;
import foodmodel.Food;
import foodmodel.FoodCollection;
import logcontroller.AddDateListener;
import logcontroller.AddExerciseListener;
import logcontroller.AddFoodDateListener;
import logmodel.Day;
import logmodel.Log;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.Date;
import java.util.Observable;
import java.util.Observer;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class LogView extends JPanel implements Observer {

  // JPanels in the Log tab
  private JPanel jpDay;
  private JPanel jpSelectDay;
  private JPanel jpDate;
  private JPanel jpWeight;
  private JPanel jpCalories;
  private JPanel jpUpdateLog;
  private JPanel jpFood;
  private JPanel jpFoodTable;
  private JPanel jpFoodManipulation;
  private JPanel jpExercises;
  private JPanel jpExercisesTable;
  private JPanel jpExerciseManipulation;

  // Swing components for the Day JPanel
  private JComboBox<Date> jcbDays;
  private JComboBox<String> jcbMonths;
  private JComboBox<String> jcbDates;
  private JComboBox<String> jcbYears;
  private JTextField jtfWeight;
  private JTextField jtfCalories;
  private JButton jbUpdateLog;

  // Swing components for the Food JPanel
  private JComboBox<Food> jcbFood;
  private JTextField jtfServings;
  private JButton jbAddFood;

  // Components for the Food Table
  private JTable jtFoodItems;
  private CustomTableModel foodCustomTableModel;

  // Swing components for the Exercises JPanel
  private JComboBox<Exercise> jcbExercises;
  private JTextField jtfMinutes;
  private JButton jbAddExercise;

  // Components for the Food Table
  private JTable jtExercises;
  private CustomTableModel exercisesCustomTableModel;

  private JPanel jpTotalCals;
  private JTextField jtfTotalCals;

  private Log log;
  private Day day;
  private FoodCollection foodCollection;
  private ExerciseCollection exerciseCollection;

  /**
   * The constructor for the LogView object. Takes in a
   * log object and FoodCollection object.
   * @param Log                The log object.
   * @param FoodCollection     The collection of foods.
   * @param ExerciseCollection The collection of exercises.
   */
  public LogView(Log log, FoodCollection foodCollection, ExerciseCollection exerciseCollection) {
    this.foodCollection = foodCollection;
    foodCollection.addObserver(this);
    this.log = log;
    this.exerciseCollection = exerciseCollection;
    exerciseCollection.addObserver(this);

    foodCollection.addObserver(this);
    exerciseCollection.addObserver(this);
    log.addObserver(this);

    setLayout(new GridLayout(0,1));

    // Day information JPanel
    jpDay = new JPanel(new GridLayout(0,1));
    add(jpDay);

    // Row for selecting a Day object to manipulate
    jpSelectDay = new JPanel();
    jpDay.add(jpSelectDay);

    //dropdown of dates logged
    jpSelectDay.add(new JLabel("Select a Day:", JLabel.RIGHT));
    // Create a JComboBox to store the list of Days
    Vector<Date> listOfDays = new Vector<Date>();
    for (Date day : log.getDates()) {
      listOfDays.add(day);
    }
    jcbDays = new JComboBox<Date>(listOfDays);
    jpSelectDay.add(jcbDays);


    //Row for selecting a Date
    jpDate = new JPanel();
    jpDay.add(jpDate);
    // Month JComboBox
    jpDate.add(new JLabel("Date: ", JLabel.RIGHT));
    String[] months = new String[]{"January", "February", "March", "April",
        "May", "June", "July", "August", "September", "October", "November",
        "December"};
    Vector<String> monthArray = new Vector<String>(months.length);
    for (String month : months) {
      monthArray.add(month);
    }
    jcbMonths = new JComboBox<String>(monthArray);
    jpDate.add(jcbMonths);

    // Day JComboBox
    jpDate.add(new JLabel("/", JLabel.RIGHT));
    String[] days = new String[]{"1","2","3","4","5","6","7","8","9","10",
        "11","12","13","14","15","16","17","18","19","20","21","22","23","24",
        "25","26","27","28","29","30","31"};
    Vector<String> dayArray = new Vector<String>(days.length);
    for (String day : days) {
      dayArray.add(day);
    }
    jcbDates = new JComboBox<String>(dayArray);
    jpDate.add(jcbDates);

    // Year JComboBox
    jpDate.add(new JLabel("/", JLabel.RIGHT));
    String[] years = new String[]{"2016", "2017", "2018", "2019", "2020"};
    Vector<String> yearArray = new Vector<String>(years.length);
    for (String year : years) {
      yearArray.add(year);
    }
    jcbYears = new JComboBox<String>(yearArray);
    jpDate.add(jcbYears);


    // Row for Weight
    jpWeight = new JPanel();
    jpDay.add(jpWeight);

    jpWeight.add(new JLabel("Current weight:", JLabel.RIGHT));
    jtfWeight = new JTextField(10);
    jpWeight.add(jtfWeight);

    // Row for Calorie Limit
    jpCalories = new JPanel();
    jpDay.add(jpCalories);

    jpCalories.add(new JLabel("Calorie limit:", JLabel.RIGHT));
    jtfCalories = new JTextField(10);
    jpCalories.add(jtfCalories);

    // Row for Update Log button
    jpUpdateLog = new JPanel();
    jpDay.add(jpUpdateLog);
    jbUpdateLog = new JButton("Update Log");
    jpUpdateLog.add(jbUpdateLog);

    // when update log button is pressed
    jbUpdateLog.addActionListener(new AddDateListener(log, jcbDates,
        jcbMonths, jcbYears, jtfWeight, jtfCalories));



    // Food JPanel
    jpFood = new JPanel(new BorderLayout());
    add(jpFood);
    //jpFood.setMinimumSize(new Dimension(400,250));

    // Food JPanel label
    jpFood.add(new JLabel("Foods consumed on this day:", JLabel.CENTER), BorderLayout.NORTH);

    // Food items table
    String[] foodColumnNames = {"Food", "Servings"};

    jpFoodTable = new JPanel();
    jpFood.add(jpFoodTable, BorderLayout.CENTER);

    foodCustomTableModel = new CustomTableModel(foodColumnNames);
    jtFoodItems = new JTable(foodCustomTableModel);

    jtFoodItems.setPreferredScrollableViewportSize(new Dimension(450,200));

    JScrollPane jspFoodTableScrollPane = new JScrollPane(jtFoodItems);

    jpFoodTable.add(jspFoodTableScrollPane);

    // Food manipulation row
    jpFoodManipulation = new JPanel();
    jpFood.add(jpFoodManipulation, BorderLayout.SOUTH);

    jpFoodManipulation.add(new JLabel("Add food:", JLabel.RIGHT));

    // Create a JComboBox to store the list of Food
    Vector<Food> listOfFoods = new Vector<Food>();
    for (Food food : foodCollection.getFoods()) {
      listOfFoods.add(food);
    }
    jcbFood = new JComboBox<Food>(listOfFoods);
    jpFoodManipulation.add(jcbFood);

    // Number of servings label
    jpFoodManipulation.add(new JLabel("Servings:", JLabel.RIGHT));
    jtfServings = new JTextField(10);
    jpFoodManipulation.add(jtfServings);

    // Add Food button
    jbAddFood = new JButton("Add");
    jpFoodManipulation.add(jbAddFood);

    // when Food is added
    jbAddFood.addActionListener(new AddFoodDateListener(foodCollection,
        jcbDays, jtfServings, jcbFood, log));



    // JPanel for Exercises
    jpExercises = new JPanel(new BorderLayout());
    add(jpExercises);
    //jpSouth.setMaximumSize(new Dimension(500,200));

    // Exercises label
    jpExercises.add(new JLabel("Exercises performed on this day:",
        JLabel.CENTER), BorderLayout.NORTH);

    // Exercises table
    String [] exerciseColumnNames = {"Exercise", "Time (minutes)"};

    jpExercisesTable = new JPanel();
    jpExercises.add(jpExercisesTable, BorderLayout.CENTER);

    exercisesCustomTableModel = new CustomTableModel(exerciseColumnNames);
    jtExercises = new JTable(exercisesCustomTableModel);

    jtExercises.setPreferredScrollableViewportSize(new Dimension(450,200));

    JScrollPane jspExercisesTableScrollPane = new JScrollPane(jtExercises);

    jpExercisesTable.add(jspExercisesTableScrollPane);


    // Exercises manipulation row
    jpExerciseManipulation = new JPanel();
    jpExercises.add(jpExerciseManipulation, BorderLayout.SOUTH);

    jpExerciseManipulation.add(new JLabel("Add Exercise:", JLabel.RIGHT));

    // Create a JComboBox to store the list of Food
    Vector<Exercise> listOfExercises = new Vector<Exercise>();
    for (Exercise exe : exerciseCollection.getExercises()) {
      listOfExercises.add(exe);
    }
    jcbExercises = new JComboBox<Exercise>(listOfExercises);
    jpExerciseManipulation.add(jcbExercises);

    jpExerciseManipulation.add(new JLabel("Minutes:", JLabel.RIGHT));
    jtfMinutes = new JTextField(10);
    jpExerciseManipulation.add(jtfMinutes);

    jbAddExercise = new JButton("Add");
    jpExerciseManipulation.add(jbAddExercise);

    jbAddExercise.addActionListener(new AddExerciseListener(jcbDays,
        jcbExercises, jtfMinutes, log));

    /*
    jpTotalCals = new JPanel();
    jpTotalCals.add(new JLabel("Total Calories Burned:", JLabel.RIGHT));
    jtfTotalCals = new JTextField(5);
    jtfTotalCals.setEditable(false);
    jtfTotalCals.setText(""); //TOTAL CALORIES BURNED GOES HERE***
    jpTotalCals.add(jtfTotalCals);


    jpExercise.add(jpTotalCals);
    jpSouth.add(jpExercise, BorderLayout.SOUTH);
    add(jpSouth, BorderLayout.SOUTH);
    */
  }

  public void update(Observable observing, Object o) {

    if (observing == foodCollection) {
      Vector<Food> listOfFood = new Vector<Food>(foodCollection.getFoods());
      jcbFood.removeAllItems();
      jcbFood.setModel(new javax.swing.DefaultComboBoxModel<Food>(listOfFood));
    }

    if (observing == exerciseCollection) {
      Vector<Exercise> listOfExercises = new Vector<Exercise>(exerciseCollection.getExercises());
      jcbExercises.removeAllItems();
      jcbExercises.setModel(new javax.swing.DefaultComboBoxModel<Exercise>(listOfExercises));
    }

    if (observing == log) {
      Vector<Date> listOfDates = new Vector<Date>(log.getDates());
      jcbDays.removeAllItems();
      jcbDays.setModel(new javax.swing.DefaultComboBoxModel<Date>(listOfDates));
    }

  }

} //end class LogView
