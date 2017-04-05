/**
 * CLASS RESPONSIBILIIES:
 *
 * Handles a User Set Weight and Calorie Limit for a specified Day.
 *
 * The program shall maintain a CSV format log file named exactly log.csv of the
 * food intake, weight, and desired calorie limit on a daily basis.
 *
 * Weight is recorded on a line with the following format: yyyy,mm,dd,w,weight
 *
 * Each food item consumed on a given day is recorded on a line in the following format:
 *
 * yyyy,mm,dd,f,name,count
 *
 * @author Matthew Atwell
 * @version 2.0
 */

package logmodel;

import exercisemodel.Exercise;
import foodmodel.Food;

import java.util.ArrayList;
import java.util.Date;
import java.util.Observable;

public class Day extends Observable {

  /**
   * Current Date.
   */
  private Date javaDate;
  /**
   * Default day Weight.
   */
  private double  weight = 150.00;
  /**
   * Day Calorie Limit.
   */
  private double calorieLimit = 2000;
  /**
   * Food ArrayList.
   */
  private ArrayList<Food> foodsConsumed;
  /**
   * foodServing ArrayList.
   */
  private ArrayList<Double> foodServings;
  /**
   * List of exercises performed.
   */
  private ArrayList<Exercise> exercisesPerformed;
  /**
   * Parallel list of minites the exercises were performed.
   */
  private ArrayList<Double> exerciseDurations;


  /**
   * CONSTRUCTOR.
   * @param newDay Java Date object which is the calendar Date to be used
   * @param dayW Weight to be set for a dayW
   * @param dayC Calorie limit to be set for the day
   */
  public Day(Date newDay, double dayW, double dayC) {

    javaDate = new Date();

    weight = dayW;
    calorieLimit = dayC;
    foodsConsumed = new ArrayList<>();
    foodServings = new ArrayList<>();
    exercisesPerformed = new ArrayList<>();
    exerciseDurations = new ArrayList<>();
  }

  /**
   * Set a new Calorie Limit for the given day.
   * @param newCalLimit new Calorie Limit
   */
  public void setCalories(double newCalLimit) {
    this.calorieLimit = newCalLimit;
  }

  /**
   * Get the Calorie Limit set for a Day.
   * @return Calorie Limit set
   */
  public double getCalories() {
    return this.calorieLimit;
  }

  /**
   * Set a new Weight for the given day.
   * @param newBodyWeight new Weight
   */
  public void setWeight(double newBodyWeight) {
    this.weight = newBodyWeight;
  }

  /**
   * Get the Weight set for a Day.
   * @return Weight set
   */
  public double getWeight() {
    return this.weight;
  }


  /**
   * Get the Date set for a Day.
   * @return Date
   */
  public Date getDate() {
    return this.javaDate;
  }


  /**
   * Add a new Food entry to the Day.
   * Take in a Food item and a number of servings of that Food.
   * @param f Food item
   * @param s Servings
   */
  public void setFood(Food f, Double s) {
    foodsConsumed.add(f);
    foodServings.add(s);
  }

  /**
   * Get the list of consumed foods for a Day.
   * @return List of Food
   */
  public ArrayList<Food> getLoggedFoods() {
    return foodsConsumed;
  }


  /**
   * Add a new Exercise performance to the Day.
   * Take in both an Exercise and the number of minutes it was performed.
   * @param exercise Exercise performed.
   * @param duration Minutes the exercise was performed.
   */
  public void setExercise(Exercise exercise, Double duration) {
    exercisesPerformed.add(exercise);
    exerciseDurations.add(duration);
  }

  /**
   * Get the total calories consumed for the day from Food.
   * @return Calories consumed from Food.
   */
  public double calculateCaloriesConsumed() {

    double calorieCount = 0;

    //Compute total calories consumed for each Food in the Day
    for (Food f : foodsConsumed) {
      double caloriesConsumed = f.getCalories();
      caloriesConsumed *= this.foodServings.get(this.foodsConsumed.indexOf(f));
      calorieCount = calorieCount + caloriesConsumed;
    }

    return calorieCount;
  }

  /**
  * Get the total calories expended for the day from Exercise.
   * @return Calories expended.
   */
  public double calculateCaloriesExpended() {

    double calorieCount = 0;

    //Compute the total calories expended during exercise
    for (Exercise e : this.exercisesPerformed) {
      //Calories per hour burned * weight in 1/100lbs * fractional hours
      double caloriesExpended = e.getCaloriesExpended()
          * (this.getWeight() / 100)
          * (this.exerciseDurations.get(this.exercisesPerformed.indexOf(e)) / 60);
      calorieCount += caloriesExpended;
    }

    return calorieCount;
  }

  /**
   * Get the net calories for the day.
   * @return Net calories
   */
  public double calculateNetCalories() {

    return this.calculateCaloriesConsumed() - this.calculateCaloriesExpended();

  }

  /**
   * Get total carbs consumed in a day.
   * @return double total carbohydrates
   */
  public double getTotalCarbs() {
    double totalCarbs = 0.0;
    for (Food f: foodsConsumed) {
      totalCarbs += f.getCarbs();
    }
    return totalCarbs;
  }

  /**
   * Get total fat consumed in a day.
   * @return double total fats
   */
  public double getTotalFat() {
    double totalFat = 0.0;
    for (Food f: foodsConsumed) {
      totalFat += f.getFat();
    }
    return totalFat;
  }

  /**
   * Get total protein consumed in a day.
   * @return double total protein
   */
  public double getTotalProtein() {

    double totalProtein = 0.0;
    for (Food f: foodsConsumed) {
      totalProtein += f.getProtein();
    }
    return totalProtein;
  }
}
