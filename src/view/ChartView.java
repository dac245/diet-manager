/**
 * JPanel for the chart in the overview tab
 * @author Jenna Tillotson
 */

package view;

import logmodel.Day;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import javax.swing.JPanel;


public class ChartView extends JPanel {

  //global variables
  private double [] values = new double[3];
  private Day day;

  public ChartView(Day day) {

    this.day = day;

    /**
     * Call the method that adds the totals to the array.
     */
    this.populateArray(day);

    /**
     *Set preferred and minimum sizes for chart.
     */
    this.setPreferredSize(new Dimension(300,300));
    this.setMinimumSize(new Dimension(150,300));

    /**
     * Call repaint to update chart.
     */
    repaint();

  } //end constructor

  public void paintComponent(Graphics g) {

    // Get height and width of canvas
    Rectangle bounds = g.getClipBounds();
    int w = (int) bounds.getWidth();
    int h = (int) bounds.getHeight();

    //Get width of each bar.
    int barWidth = w / values.length;

    // Determine largest value. This bar will extend to top of canvas.
    double highest = getMaxValue(values);

    // Fill in bars on the graph.
    int xval = 0; //starting x coordinate

    for (int i = 0; i < values.length; i++) {
      g.setColor(whichColor(i)); //get color from whichColor method

      // If the value is highest, it extends to the height of the canvas (h).
      if (values[i] == highest) {
        g.fillRect(xval, 0, barWidth, h);
      } else {
        // If the value is not highest, it extends based on percentage of the highest.
        double percent = values[i] / highest; //ratio of height compared to highest
        double height  = h * percent; //get the percent of the height in pixels
        g.fillRect(xval, h - (int)height, barWidth, (int) height);
      }
      xval += barWidth;
    }
  }

  /**
   *Take the day object and get the totals from the methods and put them into
   * the array.
   * @param Day the current day.
   */
  public void populateArray(Day day) {
    /**
     * Get total carbs, fats, protein from the day object.
     * Put them in their proper spots in the array.
     */
    if (day != null) {
      values[0] = day.getTotalCarbs();
      values[1] = day.getTotalFat();
      values[2] = day.getTotalProtein();
    } else {
      //on start of application, no day is selected so day = null
      values[0] = 0.0;
      values[1] = 0.0;
      values[2] = 0.0;
    }
  }

  /**
   * Change the day object, call populateArray method and repaint chart.
   * @param Day the day object to change to
   */
  public void setDay(Day day) {
    this.day = day;
    populateArray(day);
    repaint();
  }

  /**
   * Determine which color to paint the bar based on which bar it is.
   * Probably shouldn't be hard-coded but I couldn't figure out a better way
   * @param int of position in the array
   * @return Color for the bar
   */
  public Color whichColor(int i) {
    if (i == 0) {
      return Color.RED;
    } else if (i == 1) {
      return Color.GREEN;
    } else {
      return Color.BLUE;
    }
  }

  /**
   * Get the maximum value out of the array.
   * Determines which bar extends to top of canvas.
   * @param the array of values
   * @return the highest double in the array
   */
  public double getMaxValue(double [] values) {
    double max = 0;
    for (int i = 0; i < values.length; i++) {
      if (values[i] > max) {
        max = values[i];
      }
    }
    return max;
  }

} //end class
