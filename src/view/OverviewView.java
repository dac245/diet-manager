/**
 *JPanel for the Overview Tab
 *@author Jenna Tillotson
 */

package view;

import logmodel.Day;
import logmodel.Log;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.Date;
import java.util.Observable;
import java.util.Observer;
import java.util.Vector;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class OverviewView extends JPanel implements Observer {

  //global variables
  private JComboBox<Date> jcbDates;
  private JTextField jtfCalsConsumed;
  private JTextField jtfCalsBurned;
  private JTextField jtfNetCals;
  private JTextField jtfGoalMet;
  private JPanel jpNorth;
  private JPanel jpCenter;
  private JPanel jpSouth;
  private JPanel jpLabels;
  private ChartView cv;
  private Day day;
  private Log log;

  /**
   * The constructor for the OverviewView object.
   * Creates the overview tab for the GUI.
   */
  public OverviewView(Log log) {

    this.log = log;
    log.addObserver(this);

    setLayout(new BorderLayout());

    //north panel
    jpNorth = new JPanel();
    // Create a JComboBox to store the list of Days
    Vector<Date> listOfDays = new Vector<Date>();
    for (Date day : log.getDates()) {
      listOfDays.add(day);
    }
    jcbDates = new JComboBox<Date>(listOfDays);
    jpNorth.add(jcbDates);
    add(jpNorth, BorderLayout.NORTH);

    //center panel
    jpCenter = new JPanel(new GridLayout(0,2));

    jpCenter.add(new JLabel("Calories consumed: ", JLabel.RIGHT));
    jtfCalsConsumed = new JTextField(5);
    jtfCalsConsumed.setEditable(false);
    jtfCalsConsumed.setText(""); //Will be updated when jcbDates changes
    jpCenter.add(jtfCalsConsumed);

    jpCenter.add(new JLabel("Calories burned: ", JLabel.RIGHT));
    jtfCalsBurned = new JTextField(5);
    jtfCalsBurned.setEditable(false);
    jtfCalsBurned.setText(""); //Will be updated when jcbDates changes
    jpCenter.add(jtfCalsBurned);

    jpCenter.add(new JLabel("Net calories: ", JLabel.RIGHT));
    jtfNetCals = new JTextField(5);
    jtfNetCals.setEditable(false);
    jtfNetCals.setText(""); //Will be updated when jcbDates changes
    jpCenter.add(jtfNetCals);

    jpCenter.add(new JLabel("Calorie goal met? ", JLabel.RIGHT));
    jtfGoalMet = new JTextField(5);
    jtfGoalMet.setEditable(false);
    jtfGoalMet.setText(""); //Will be updated when jcbDates changes
    jpCenter.add(jtfGoalMet);

    add(jpCenter, BorderLayout.CENTER);

    day = null;
    cv = new ChartView(day);
    jpSouth = new JPanel(new BorderLayout());
    jpSouth.add(new JLabel("Intake Totals", JLabel.CENTER), BorderLayout.NORTH);
    jpSouth.add(cv, BorderLayout.CENTER);

    jpLabels = new JPanel(new GridLayout(1,3));
    jpLabels.add(new JLabel("Fats", JLabel.CENTER));
    jpLabels.add(new JLabel("Carbohydrates", JLabel.CENTER));
    jpLabels.add(new JLabel("Protein", JLabel.CENTER));
    jpSouth.add(jpLabels, BorderLayout.SOUTH);
    add(jpSouth, BorderLayout.SOUTH);

    jcbDates.addActionListener(new UpdateOverviewListener(jcbDates, jtfCalsConsumed,
        jtfCalsBurned, jtfNetCals, jtfGoalMet, cv, log));

  } //end constructor

  public void update(Observable observing, Object o) {

    if (observing == log) {
      Vector<Date> listOfDates = new Vector<Date>(log.getDates());
      jcbDates.removeAllItems();
      jcbDates.setModel(new javax.swing.DefaultComboBoxModel<Date>(listOfDates));
    }

  }

} //end class OverviewView
