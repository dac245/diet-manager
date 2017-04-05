import exercisemodel.ExerciseCollection;
import foodmodel.FoodCollection;
import logmodel.Log;
import view.View;

public class Main {
  public static void main(String [] args) {
    ExerciseCollection exerciseCollection = new ExerciseCollection();
    FoodCollection foodCollection = new FoodCollection();
    Log log = new Log();
    //SaveStateController saveSystemState = new SaveStateController();

    new View(exerciseCollection, foodCollection, log);

  }
}
