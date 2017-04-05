/**
 *JPanel for the Food tab on the GUI
 *@author Jenna Tillotson
 */

package view;

import foodcontroller.AddIngredientToRecipeListener;
import foodcontroller.AddRecipeListener;
import foodcontroller.BasicFoodUpdateListener;
import foodcontroller.DeleteRecipeListener;
import foodcontroller.ManipulateBasicFoodListListener;
import foodcontroller.ManipulateRecipeListListener;
import foodmodel.BasicFood;
import foodmodel.Food;
import foodmodel.FoodCollection;
import foodmodel.Recipe;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.Observable;
import java.util.Observer;
import java.util.Vector;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class FoodView extends JPanel implements Observer {

  private FoodCollection foodCollection;

  // JPanels in the Food tab
  private JPanel jpBasicFood;
  private JPanel jpBasicFoodManipulation;
  private JPanel jpRecipes;
  private JPanel jpRecipeManipulation;
  private JPanel jpIngredientsTable;
  private JPanel jpIngredientManipulation;

  // Swing components for the BasicFoodManipulation JPanel
  private JComboBox<BasicFood> jcbBasicFood;
  private JTextField jtfBasicFoodName;
  private JTextField jtfCalories;
  private JTextField jtfFat;
  private JTextField jtfCarbohydrates;
  private JTextField jtfProtein;
  private JButton jbUpdateBasicFood;
  private JButton jbDeleteBasicFood;

  // Swing components for the RecipeManipulation JPanel
  private JComboBox<Recipe> jcbRecipies;
  private JTextField jtfRecipeName;
  private JButton jbAddRecipe;
  private JButton jbDeleteRecipe;

  // Components for the IngredientsTable JPanel
  private JTable jtRecipeIngredients;
  private CustomTableModel ingredientsCustomTableModel;

  // Swing components for the IngredientManipulation JPanel
  private JComboBox<Food> jcbFood;
  private JTextField jtfQuantity;
  private JButton jbAddIngredient;



  /**
   * The constructor for the FoodView object.
   * Takes in a FoodCollection object.
   * @param FoodCollection the collection of foods.
   */
  public FoodView(FoodCollection collection) {

    //Save FoodCollection for observation
    this.foodCollection = collection;
    foodCollection.addObserver(this);

    setLayout(new BorderLayout());

    // Crreate the JPanel for the BasicFood manipulation GUI elements
    jpBasicFood = new JPanel(new BorderLayout());
    add(jpBasicFood, BorderLayout.NORTH);

    // Add a border to separate the BasicFood manipulation
    //  from the Recipe manipulation.
    jpBasicFood.setBorder(BorderFactory.createMatteBorder(0,0,2,0,Color.BLACK));

    // Create a JComboBox to store the list of BasicFoods
    Vector<BasicFood> listOfBasicFood = new Vector<BasicFood>();
    listOfBasicFood.add(new BasicFood("New...",0,0,0,0));
    for (Food food : foodCollection.getFoods()) {
      if (food instanceof BasicFood) {
        listOfBasicFood.add((BasicFood)food);
      }
    }
    jcbBasicFood = new JComboBox<BasicFood>(listOfBasicFood);
    jpBasicFood.add(jcbBasicFood, BorderLayout.NORTH);


    // Create the JPanel for BasicFood information manipulation
    jpBasicFoodManipulation = new JPanel(new GridLayout(0,2));
    jpBasicFood.add(jpBasicFoodManipulation, BorderLayout.CENTER);

    // BasicFood Name
    jpBasicFoodManipulation.add(new JLabel("Name:", JLabel.RIGHT));
    jtfBasicFoodName = new JTextField();
    jpBasicFoodManipulation.add(jtfBasicFoodName);

    // Calories
    jpBasicFoodManipulation.add(new JLabel("Calories:", JLabel.RIGHT));
    jtfCalories = new JTextField();
    jpBasicFoodManipulation.add(jtfCalories);

    // Fat
    jpBasicFoodManipulation.add(new JLabel("Fat:", JLabel.RIGHT));
    jtfFat = new JTextField();
    jpBasicFoodManipulation.add(jtfFat);

    // Carbohydrates
    jpBasicFoodManipulation.add(new JLabel("Carbohydrates:", JLabel.RIGHT));
    jtfCarbohydrates = new JTextField();
    jpBasicFoodManipulation.add(jtfCarbohydrates);

    // Protein
    jpBasicFoodManipulation.add(new JLabel("Protein:", JLabel.RIGHT));
    jtfProtein = new JTextField();
    jpBasicFoodManipulation.add(jtfProtein);

    // Update button
    jbUpdateBasicFood = new JButton("Update Food Entry");
    jpBasicFoodManipulation.add(jbUpdateBasicFood);

    // Delete button
    jbDeleteBasicFood = new JButton("Delete Food Entry");
    jpBasicFoodManipulation.add(jbDeleteBasicFood);

    // when someone makes a selection in the JComboBox
    jcbBasicFood.addActionListener(new ManipulateBasicFoodListListener(
        foodCollection, jcbBasicFood, jtfBasicFoodName, jtfCalories, jtfFat,
        jtfCarbohydrates, jtfProtein));

    // when someone clicks jbUpdateBasicFood
    jbUpdateBasicFood.addActionListener(new BasicFoodUpdateListener(
        foodCollection, jtfBasicFoodName, jtfCalories, jtfFat,
        jtfCarbohydrates, jtfProtein));



    // Create a JPanel for the Recipe manipulation GUI elements
    jpRecipes = new JPanel(new BorderLayout());
    add(jpRecipes, BorderLayout.SOUTH);

    // Create JPanel for Recipe information manipulation
    jpRecipeManipulation = new JPanel(new GridLayout(0,2));
    jpRecipes.add(jpRecipeManipulation, BorderLayout.NORTH);

    // Select Recipe
    jpRecipeManipulation.add(new JLabel("Select Recipe:", JLabel.RIGHT));

    // Create a JComboBox to store the list of Recipies
    Vector<Recipe> listOfRecipies = new Vector<Recipe>();
    listOfRecipies.add(new Recipe("New..."));
    for (Food food : foodCollection.getFoods()) {
      if (food instanceof Recipe) {
        listOfRecipies.add((Recipe)food);
      }
    }
    jcbRecipies = new JComboBox<Recipe>(listOfRecipies);
    jpRecipeManipulation.add(jcbRecipies);

    // Recipes Name
    jpRecipeManipulation.add(new JLabel("Recipe Name:", JLabel.RIGHT));
    jtfRecipeName = new JTextField();
    jpRecipeManipulation.add(jtfRecipeName);

    // Add/Remove Recipe
    jbAddRecipe = new JButton("Add Recipe");
    jpRecipeManipulation.add(jbAddRecipe);

    jbDeleteRecipe = new JButton("Delete Recipe");
    jpRecipeManipulation.add(jbDeleteRecipe);

    // Recipe table
    String[] columnNames = {"Food", "Amount"};

    jpIngredientsTable = new JPanel();
    ingredientsCustomTableModel = new CustomTableModel(columnNames);
    jtRecipeIngredients = new JTable(ingredientsCustomTableModel);

    jtRecipeIngredients.setPreferredScrollableViewportSize(new Dimension(450,200));

    JScrollPane jspTableScrollPane = new JScrollPane(jtRecipeIngredients);

    jpIngredientsTable.add(jspTableScrollPane);
    jpRecipes.add(jpIngredientsTable, BorderLayout.CENTER);



    // Create a JPanel for the Ingredient manipulation GUI elements
    jpIngredientManipulation = new JPanel();
    jpRecipes.add(jpIngredientManipulation, BorderLayout.SOUTH);


    jpIngredientManipulation.add(new JLabel("Add Ingredient:", JLabel.RIGHT));

    // Create a JComboBox to store the list of all Food
    Vector<Food> listOfFood = new Vector<Food>(foodCollection.getFoods());
    jcbFood = new JComboBox<Food>(listOfFood);
    jpIngredientManipulation.add(jcbFood);

    jpIngredientManipulation.add(new JLabel("x", JLabel.CENTER));

    jtfQuantity = new JTextField("Quantity", 5);
    jpIngredientManipulation.add(jtfQuantity);

    jbAddIngredient = new JButton("Add");
    jpIngredientManipulation.add(jbAddIngredient);


    // when someone makes a selection of a recipe in the JComboBox
    jcbRecipies.addActionListener(new ManipulateRecipeListListener(
        foodCollection, jcbRecipies, jtfRecipeName, ingredientsCustomTableModel));

    // Add Recipe
    jbAddRecipe.addActionListener(new AddRecipeListener(
        foodCollection, jtfRecipeName, ingredientsCustomTableModel));

    // Delete Recipe
    jbDeleteRecipe.addActionListener(new DeleteRecipeListener(
        foodCollection, jtfRecipeName));

    // when an ingredient is added to a recipe
    jbAddIngredient.addActionListener(new AddIngredientToRecipeListener(
        foodCollection, jtfRecipeName, jcbFood, jtfQuantity));

  }

  public void update(Observable observing, Object o) {

    // Return on spurious update from unknown observable.
    if (observing != foodCollection) {
      return;
    }

    Vector<BasicFood> listOfBasicFoods = new Vector<BasicFood>();
    listOfBasicFoods.add(new BasicFood("New...",0,0,0,0));
    for (Food food : foodCollection.getFoods()) {
      if (food instanceof BasicFood) {
        listOfBasicFoods.add((BasicFood)food);
      }
    }
    jcbBasicFood.removeAllItems();
    jcbBasicFood.setModel(new javax.swing.DefaultComboBoxModel<BasicFood>(listOfBasicFoods));

    Vector<Recipe> listOfRecipies = new Vector<Recipe>();
    listOfRecipies.add(new Recipe("New..."));
    for (Food food : foodCollection.getFoods()) {
      if (food instanceof Recipe) {
        listOfRecipies.add((Recipe)food);
      }
    }
    jcbRecipies.removeAllItems();
    jcbRecipies.setModel(new javax.swing.DefaultComboBoxModel<Recipe>(listOfRecipies));

    Vector<Food> listOfFood = new Vector<Food>(foodCollection.getFoods());
    jcbFood.removeAllItems();
    jcbFood.setModel(new javax.swing.DefaultComboBoxModel<Food>(listOfFood));
  }

} //end class FoodView
