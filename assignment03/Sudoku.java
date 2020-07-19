import java.util.Arrays;
public class Sudoku {
  public static void main(String[] args) {
    int[] a = {3,4,1,6,2,5,2,7,8,9};
    int[] b = sort(a);
    int[][] c = {{1,2,3},{4,5,6},{7,8,9}};
    int[][] sudoku = {{5,3,4,6,7,8,9,1,2},{6,7,8,1,9,5,3,4,8},{1,9,8,3,4,2,5,6,7},{8,5,9,7,6,1,4,2,3},{4,2,6,8,5,3,7,9,1},{7,1,3,9,2,4,8,5,6},{9,6,1,5,3,7,2,8,4},{2,8,7,4,1,9,6,3,5},{3,4,5,2,8,6,1,7,9}};
    boolean sudokuSolution = isSudoku(sudoku); //call to isSudoku
    System.out.println(sudokuSolution);
    //System.out.println(isRowValid(sudoku));
  }
  
  public static boolean isSudoku(int[][] solution) {
    boolean answer = false;
    if (isNumberValid(solution) == true && isLengthValid(solution) == true) {
      if (isRowValid(solution) == true && isColumnValid(solution) == true) {
        if (isSubGridValid(solution) == true) {
          answer = true;
        }
      }
    }
    return answer;
  }
  
  public static boolean isColumnValid(int[][] solution) {
    boolean[] column = new boolean[solution.length];
    boolean answer = true;
    for (int i=0; i < solution.length; i++) {
      column[i] = uniqueEntries(getColumn(solution,i+1));
      if (column[i] == false) {
        answer = false;
        break;
      }
    }
    return answer;
  }
  
  public static boolean isRowValid(int[][] solution) {
    boolean[] row = new boolean[solution.length];
    boolean answer = true;
    for (int i = 0; i < solution.length; i++) {
      row[i] = uniqueEntries(solution[i]);
      if (row[i] == false) {
        answer = false;
        break;
      }
    }
    return answer;
  }
  
  public static boolean isSubGridValid(int[][] solution) {
    boolean[] sub = new boolean[solution.length];
    boolean answer = true;
    int k=0;
    for (int i=0; i < solution.length; i=i+3) {
      for (int j=0; j <solution.length; j=j+3) {
        sub[k] = uniqueEntries(flatten(subGrid(solution,i,j,3)));
        if (sub[k] == false) {
          answer = false;
          break;
        }
        k++;
      }
    }
    return answer;
  }
         
  public static boolean isNumberValid(int[][] sudoku) {
    boolean answer = false;
    if (isLengthValid(sudoku) == true) {
      int[] flattened = flatten(sudoku);
      for (int i=0;i<flattened.length;i++) {
        if (flattened[i] >= 1 && flattened[i] <=9) {
          answer = true;
        }
      }
    }
    return answer;
  }
  public static boolean isLengthValid(int[][] sudoku) {
    boolean answer = true;
    for (int i =0; i < sudoku.length; i++) {
      if (sudoku.length != 9 || sudoku[i].length != 9) {
        answer = false;
      }
    }
    return answer;
  }
    
  public static int[] sort(int[] array) {
    int[] a = new int[array.length];
    for (int i=0; i<array.length;i++) {
      a[i] = array[i];
    }
    int temp;
    for (int i=0; i < a.length; i++) {
      for (int j=0; j < a.length-1; j++) {
        if (a[j] > a[j+1]) {
          temp = a[j+1];
          a[j+1] = a[j];
          a[j] = temp;
        }
      }
    }
    return a;
  }
  
  public static boolean uniqueEntries(int[] array) {
    int[] b = sort(array);
    boolean answer = true;
    for (int i=0; i < b.length-1; i++) {
        if (b[i] == b[i+1]) {
          answer = false;
        }
    }
    return answer;
  }
  
  public static int[] getColumn(int[][] array, int j) { //made j user friendly-- 2nd row is index 1 of array.
    int l = j-1;
    int k = 0;
    int[] column = new int[array.length];
    for (int i=0; i<array.length; i++) {
        column[k] = array[i][l];
        k++;
      }
    return column;
    }
  
  public static int[] flatten(int[][] array) {
    int k=0;
    int[] flattened = new int[(array.length)*(array.length)];
    for (int i=0; i < array.length; i++) {
      for (int j=0; j < array.length; j++) {
      flattened [k] = array[i][j];
      k++;
      }
    }
    return flattened;
  }
  
  public static int[][] subGrid(int[][] array, int i, int j, int m) { //currently does not work for any other numbers
    int[][] newGrid = new int[m][m];
    int x = 0;
    int y = 0;
    int temp = j;
    while (x < m) {
          while (y < m) {
            newGrid[x][y] = array[i][j];
            j++;
            y++;
          }
        j = temp;
        y = 0;
        i++;
        x++;
    }
    return newGrid;
  }
}
  