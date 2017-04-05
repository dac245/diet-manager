/**
 *Class which holds the GUI and all of its parts.
 *@author Jenna Tillotson
 */

package view;

import exercisemodel.ExerciseCollection;
import foodmodel.FoodCollection;
import logmodel.Log;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class View extends JFrame {

  //global variables
  private JMenuBar jmb;
  private JMenu jmFile;
  private JMenuItem jmiHelp;
  private JMenuItem jmiSave;
  private JTabbedPane jtp;
  private JPanel logTab; //adding or viewing a log
  private JPanel foodTab; //adding or viewing food and recipes
  private JPanel overviewTab; //overview of a specific date
  private JPanel exerciseTab; //adding or viewing an exercise

  private ExerciseCollection collectionOfExercises;
  private FoodCollection collectionOfFood;
  private Log log;

  /**
   * The constructor for the view object. It needs to have
   * an ExerciseCollection, FoodCollection, and Log object passed in.
   * @param ExerciseCollection the collection of exercises
   * @param FoodCollection     the collection of foods
   * @param Log                the log
   */
  public View(ExerciseCollection collectionOfExercises,
              FoodCollection collectionOfFood, Log log) {

    this.collectionOfExercises = collectionOfExercises;
    this.collectionOfFood = collectionOfFood;
    this.log = log;

    setTitle("Diet Tracker");

    //menu bar
    jmb = new JMenuBar();
    jmFile = new JMenu("File");
    jmb.add(jmFile);
    jmiHelp = new JMenuItem("Help");
    jmFile.add(jmiHelp);
    jmiSave = new JMenuItem("Save");
    jmFile.add(jmiSave);

    //tabbed pane
    jtp = new JTabbedPane();
    overviewTab = new OverviewView(this.log);
    jtp.add("Overview", overviewTab);
    logTab = new LogView(this.log, this.collectionOfFood, this.collectionOfExercises);
    jtp.add("Log", logTab);
    foodTab = new FoodView(this.collectionOfFood);
    jtp.add("Foods", foodTab);
    exerciseTab = new ExerciseView(this.collectionOfExercises);
    jtp.add("Exercise", exerciseTab);


    //add gui parts to frame
    setJMenuBar(jmb);
    add(jtp);

    //GUI viewing settings
    setSize(500,700);
    setVisible(true);
    setLocationRelativeTo(null);
    setDefaultCloseOperation(EXIT_ON_CLOSE);

  } //end constructor

} //end class View
