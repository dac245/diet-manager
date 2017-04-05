/**
 * Hashmaps Java Date & User defined dates
 * @author Matthew Atwell
 */

package logmodel;

import foodmodel.BasicFood;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Observable;

public class Log extends Observable {

  public Log(){}

  // Key = Date , Value = Day
  private HashMap<Date, Day> dateMap = new HashMap<Date, Day>();

  //Current Date
  private Date currentDay = new Date();

  // set Date
  public void setDate(Date d) {
    currentDay = d;
    setChanged();
    notifyObservers(currentDay);
  }

  // Function Returning Date
  public Date getDate() {
    return this.currentDay;
  }

  public ArrayList<Date> getDates() {
    ArrayList<Date> dates = new ArrayList<Date>();
    for (Object dateObj : this.dateMap.keySet()) {
      Date date = (Date)dateObj;
      dates.add(date);
    }
    return dates;
  }

  // Function getDayByDate ( Java.Date)
  public Day getDaybyDate(Date d) {

    Day reqDay = dateMap.get(d);
    return reqDay;
  }

  // Have a Date that you want to add the new Day
  public void addDateToLog(Date d, double weight, double calLimit) {

    if (dateMap.containsKey(d)) {
      //get Logged Date via key (d)
      dateMap.get(d);

      //update Day Object in LogMap  with new weight and calLimit
      Day updatedInfo = new Day(d,weight,calLimit);
      dateMap.put(d,updatedInfo);
    } else {
      //create new Day Object( date D, Weight w, calLimit )
      Day newInfo = new Day(d,weight,calLimit);
      dateMap.put(d, newInfo);
    }
    // KeySet only returns the set of keys in hashmap.
    //we can get the values by iteratiing over the keys
    for (Date dateKey: dateMap.keySet()) {

      String key = dateKey.toString();
      String value = dateMap.get(dateKey).toString();
      System.out.println(key + " " + value);

    }
    setChanged();
    notifyObservers();
  }


  public void printHashMap() {
    System.out.println("CURRENT DAYS WITH FOOD:");
    for (Date d : dateMap.keySet()) {
      System.out.println("------------------------------------------------");
      System.out.println("looping...");
      Day dayInfo =  dateMap.get(d);

      // Print Date with Days in Console
      System.out.println("key: " + d + " value: " + dateMap.get(d));
    }

  }

}
