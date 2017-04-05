/**
 * A data model for building custom JTables.
 *
 * Model takes in a string array which is used as the column headers in the
 * table. The model observs an internal data structure and updates itself to
 * match the internal data structure whenevr that changes.
 *
 * @author Brendon Strowe
 * @version 2.0
 */

package view;

import foodmodel.Food;
import foodmodel.Recipe;

import java.util.Observable;
import java.util.Observer;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

public class CustomTableModel extends AbstractTableModel implements Observer {
  /**
   * List of table colum headers.
   */
  private String[] columnNames;
  /**
   * Data set for table. Data to be displayed in the table body.
   */
  private Object[][] data = new Object[1][2];
  /**
   * Recipe object to observe the ingredients.
   */
  private Recipe recipeToObserve;

  public CustomTableModel(String[] columnNames) {
    this.columnNames = columnNames;
  }

  public Object getValueAt(int row, int col) {
    return data[row][col];
  }

  public int getColumnCount() {
    return columnNames.length;
  }

  public int getRowCount() {
    return data.length;
  }

  public String getColumnName(int col) {
    return columnNames[col];
  }

  /**
   * Set which Recipe object should be observed for its Ingredients.
   *
   * @param recipeToObserve Recipe that has the ingredients to be observed.
   */
  public void setObservableRecipe(Recipe recipeToObserve) {
    recipeToObserve.deleteObservers();
    this.recipeToObserve = recipeToObserve;
    recipeToObserve.addObserver(this);
    this.update(recipeToObserve, new Object());
  }

  /**
   * When the data for the table is updated, reflect it in the table itself.
   */
  public void update(Observable observing, Object o) {

    // If we're observing the recipe ingredient table:
    if (observing == recipeToObserve) {
      // Clear out and create a new table data set
      //  which will reflect the ingredient data
      this.data = null;
      this.data = new Object[recipeToObserve.getIngredients().size()][2];

      // iterate through the ingredients in the recipe and populate the Table's
      //  data set.
      int row = 0;
      for (Object ingredientObj : recipeToObserve.getIngredients().keySet()) {
        Food ingredient = (Food)ingredientObj;
        data[row][0] = ingredient.getName();
        data[row][1] = recipeToObserve.getIngredients().get(ingredient);
        row++;
      }

      fireTableDataChanged();

    } else {
      return;
    }
  } // end update()
} // end class CustomTableModel
