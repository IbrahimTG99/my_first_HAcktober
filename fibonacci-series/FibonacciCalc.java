import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.lang.Object;
import java.util.Arrays;
import java.io.*;
import java.util.*;
import java.lang.Math;

/**
 *
 * @author sidharth
 */
public class FibonacciCalc {

  /**
   * ***********************************************************************************************
   *
   * PROBLEM SET 1
   * [10 points] fibonacci_exponential: compute nth fibonacci number with an
   * exponential running time
   * [10 points] fibonacci_linear: compute nth fibonacci number with an
   * exponential running time
   * [20 points] fibonacci_log: compute nth fibonacci number with a logarithmic
   * running time
   *
   * [10 points] Plot a graph showing the timings to compute the first 30
   * fibonacci numbers using all three methods. And for the first 45 fibonacci
   * numbers using the linear and logarithmic method.
   * X axis should be for the fibonacci number and y axis should be for time.
   ************************************************************************************************
   */
  public int fibonacci_exponential(int n) {
    if (n <= 1) {
      return n;
    } else {
      return fibonacci_exponential(n - 1) + fibonacci_exponential(n - 2);
    }
  }

  public int fibonacci_linear(int n) {
    int[] fib = new int[n + 1];
    fib[0] = 0;
    fib[1] = 1;
    for (int i = 2; i <= n; i++) {
      fib[i] = fib[i - 1] + fib[i - 2];
    }
    return fib[n];
  }

  public int fibonacci_log(int n) {
    int[][] F = { { 1, 1 }, { 1, 0 } };
    if (n == 0) {
      return 0;
    }
    power(F, n - 1);
    return F[0][0];
  }

  // power function to calculate F raise to power n-1
  public void power(int F[][], int n) {
    int i;
    int M[][] = { { 1, 1 }, { 1, 0 } };
    // n - 1 times multiply the matrix to {{1,0},{0,1}}
    for (i = 2; i <= n; i++) {
      multiply(F, M);
    }
  }

  // multiply function to multiply two matrices
  public void multiply(int F[][], int M[][]) {
    int x = F[0][0] * M[0][0] + F[0][1] * M[1][0];
    int y = F[0][0] * M[0][1] + F[0][1] * M[1][1];
    int z = F[1][0] * M[0][0] + F[1][1] * M[1][0];
    int w = F[1][0] * M[0][1] + F[1][1] * M[1][1];
    F[0][0] = x;
    F[0][1] = y;
    F[1][0] = z;
    F[1][1] = w;
  }

  // plot the graph using time_taken lists
  public void plotGraph(List<List<Long>> time_taken_exponential, List<List<Long>> time_taken_linear,
      List<List<Long>> time_taken_log) {
    System.out.println("Plotting the graph");
    System.out.println("X axis is for the fibonacci number and y axis is for time");
    System.out.println("For the first 30 fibonacci numbers using all three methods");
    for (int i = 0; i < time_taken_exponential.size(); i++) {
      System.out.println("Fibonacci number: " + time_taken_exponential.get(i).get(0));
      System.out.println("Time taken for exponential method: " + time_taken_exponential.get(i).get(1));
      System.out.println("Time taken for linear method: " + time_taken_linear.get(i).get(1));
      System.out.println("Time taken for logarithmic method: " + time_taken_log.get(i).get(1));
    }
  }

  // plot a graph in cli using a 2d array
  // create a matrix of 30*max_time
  // fill the matrix with 0s
  // for each time_taken list, fill the matrix with 1, 2, 3 for exponential,
  // linear and logarithmic methods respectively
  // print the matrix
  public void plotGraph2(List<List<Long>> time_taken_exponential, List<List<Long>> time_taken_linear,
      List<List<Long>> time_taken_log) {
    System.out.println("Plotting the graph");
    System.out.println("X axis is for the fibonacci number and y axis is for time");
    System.out.println("For the first 30 fibonacci numbers using all three methods");
    // convert the time_taken lists to milliseconds
    for (int i = 0; i < time_taken_exponential.size(); i++) {
      time_taken_exponential.get(i).set(1, time_taken_exponential.get(i).get(1) / 1000);
      time_taken_linear.get(i).set(1, time_taken_linear.get(i).get(1) / 1000);
      time_taken_log.get(i).set(1, time_taken_log.get(i).get(1) / 1000);
    }
    // find the max time
    int max_time = 0;
    for (int i = 0; i < time_taken_exponential.size(); i++) {
      if (time_taken_exponential.get(i).get(1) > max_time) {
        max_time = time_taken_exponential.get(i).get(1).intValue();
      }
      if (time_taken_linear.get(i).get(1) > max_time) {
        max_time = time_taken_linear.get(i).get(1).intValue();
      }
      if (time_taken_log.get(i).get(1) > max_time) {
        max_time = time_taken_log.get(i).get(1).intValue();
      }
    }
    // create a matrix of 30*max_time
    int[][] matrix = new int[30][max_time + 1];
    // fill the matrix with 0s
    for (int i = 0; i < 30; i++) {
      for (int j = 0; j < max_time; j++) {
        matrix[i][j] = 0;
      }
    }
    // for each time_taken list, fill the matrix with the time taken
    for (int i = 0; i < time_taken_exponential.size(); i++) {
      for (int j = 0; j < time_taken_exponential.get(i).get(1); j++) {
        matrix[i][j] = 1;
      }
    }
    for (int i = 0; i < time_taken_linear.size(); i++) {
      for (int j = 0; j < time_taken_linear.get(i).get(1); j++) {
        matrix[i][j] = 2;
      }
    }
    for (int i = 0; i < time_taken_log.size(); i++) {
      for (int j = 0; j < time_taken_log.get(i).get(1); j++) {
        matrix[i][j] = 3;
      }
    }
    // print the matrix
    for (int i = 0; i < 30; i++) {
      for (int j = 0; j < max_time + 1; j++) {
        if (matrix[i][j] == 0) {
          System.out.print(" ");
        } else if (matrix[i][j] == 1) {
          System.out.print("1");
        } else if (matrix[i][j] == 2) {
          System.out.print("2");
        } else if (matrix[i][j] == 3) {
          System.out.print("3");
        }
      }
      System.out.println();
    }
  }

  /**
   * ***********************************************************************************************
   *
   * PROBLEM SET 2
   * [20 points] You are climbing a staircase. It takes n steps to reach the top.
   * Each time you can either climb 1 or 2 steps. In how many distinct ways can
   * you climb to the top?
   * Example Input: n = 3 Output: 3 | Explanation: (1 step + 1 step + 1 step), (1
   * step + 2 steps), and (2 steps + 1 step)
   *
   * [5 points] Print out the time take to find solution for n=0 to n=45
   ************************************************************************************************
   */
  int climbStairs(int n) {
    if (n == 0 || n == 1) {
      return 1;
    }
    int[] dp = new int[n + 1];
    dp[1] = 1;
    dp[2] = 2;
    for (int i = 3; i <= n; i++) {
      dp[i] = dp[i - 1] + dp[i - 2];
    }
    return dp[n];
  }

  /**
   * ***********************************************************************************************
   *
   * PROBLEM SET 3
   *
   * [20 points] Given a triangle array, return the minimum path sum from top to
   * bottom.
   * For each step, you may move to an adjacent number of the row below (if you
   * are on index i on the current row, you may move to either index i or index i
   * + 1 on the next row).
   * Input: triangle = [[2],[3,4],[6,5,7],[4,1,8,3]]
   * Output: 11
   * Explanation: The triangle looks like:
   * 2
   * 3 4
   * 6 5 7
   * 4 1 8 3
   * The minimum path sum from top to bottom is 2 + 3 + 5 + 1 = 11.
   * 
   * [5 points]
   * Print out the triangle (only for triangle with 4 levels) and the answer
   * Print out the correct answer for all triangles (from level 1 to 40)
   ************************************************************************************************
   */
  public int minimumTotal(List<List<Integer>> triangle) {

    if (triangle.size() <= 4) {
      System.out.println("The triangle looks like:");
      for (int i = 0; i < triangle.size(); i++) {
        for (int j = 0; j < triangle.get(i).size(); j++) {
          System.out.print(triangle.get(i).get(j) + " ");
        }
        System.out.println();
      }
    }

    int[] dp = new int[triangle.size() + 1];
    for (int i = triangle.size() - 1; i >= 0; i--) {
      List<Integer> tlist = triangle.get(i);
      for (int j = 0; j < tlist.size(); j++) {
        dp[j] = Math.min(dp[j], dp[j + 1]) + tlist.get(j);
      }
    }
    return dp[0];
  }

  public static void main(String[] args) {

    FibonacciCalc ob = new FibonacciCalc();
    // save time taken at each input in a 2d list and draw graph
    List<List<Long>> time_taken_exponential = new ArrayList<>();
    List<List<Long>> time_taken_linear = new ArrayList<>();
    List<List<Long>> time_taken_log = new ArrayList<>();
    // Problem set 1
    System.out.println("Problem set 1");
    System.out.println("Fibonacci exponential");
    for (int i = 1; i <= 30; i++) {
      long startTime = System.nanoTime();
      System.out.println(ob.fibonacci_exponential(i));
      long endTime = System.nanoTime();
      long duration = (endTime - startTime);

      List<Long> temp = new ArrayList<>();
      temp.add((long) i);
      temp.add(duration);
      time_taken_exponential.add(temp);
      // System.out.println("Time taken: " + duration);
    }
    System.out.println("Fibonacci linear");
    for (int i = 1; i <= 30; i++) {
      long startTime = System.nanoTime();
      System.out.println(ob.fibonacci_linear(i));
      long endTime = System.nanoTime();
      long duration = (endTime - startTime);
      List<Long> temp = new ArrayList<>();
      temp.add((long) i);
      temp.add(duration);
      time_taken_linear.add(temp);
      // System.out.println("Time taken: " + duration);
    }
    System.out.println("Fibonacci log");
    for (int i = 1; i <= 30; i++) {
      long startTime = System.nanoTime();
      System.out.println(ob.fibonacci_log(i));
      long endTime = System.nanoTime();
      long duration = (endTime - startTime);
      List<Long> temp = new ArrayList<>();
      temp.add((long) i);
      temp.add(duration);
      time_taken_log.add(temp);
      // System.out.println("Time taken: " + duration);
    }

    // plot graph using time_taken lists
    ob.plotGraph2(time_taken_exponential, time_taken_linear, time_taken_log);

    System.out.println();
    System.out.println("Fibonacci linear for n=0 to n=45");
    for (int i = 1; i <= 45; i++) {
      long startTime = System.nanoTime();
      System.out.println(ob.fibonacci_linear(i));
      long endTime = System.nanoTime();
      long duration = (endTime - startTime);
      System.out.println("Time taken: " + duration);
    }

    System.out.println("Fibonacci log for n=0 to n=45");
    for (int i = 1; i <= 45; i++) {
      long startTime = System.nanoTime();
      System.out.println(ob.fibonacci_log(i));
      long endTime = System.nanoTime();
      long duration = (endTime - startTime);
      System.out.println("Time taken: " + duration);
    }

    System.out.println();
    // Problem set 2
    System.out.println("Problem set 2");
    System.out.println("Climb stairs");
    for (int i = 0; i <= 45; i++) {
      long startTime = System.nanoTime();
      System.out.println("for n=" + i + " ways:" + ob.climbStairs(i));
      long endTime = System.nanoTime();
      long duration = (endTime - startTime);
      System.out.println("Time taken: " + duration);
    }

    System.out.println();
    // Problem set 3
    System.out.println("Problem set 3");
    System.out.println("Minimum total");
    List<List<Integer>> triangle = new ArrayList<>();
    List<Integer> tlist = new ArrayList<>();
    tlist.add(2);
    triangle.add(tlist);
    tlist = new ArrayList<>();
    tlist.add(3);
    tlist.add(4);
    triangle.add(tlist);
    tlist = new ArrayList<>();
    tlist.add(6);
    tlist.add(5);
    tlist.add(7);
    triangle.add(tlist);
    tlist = new ArrayList<>();
    tlist.add(4);
    tlist.add(1);
    tlist.add(8);
    tlist.add(3);
    triangle.add(tlist);
    System.out.println(ob.minimumTotal(triangle));

    // triangle frm level 1 to 40
    for (int i = 1; i <= 40; i++) {
      triangle = new ArrayList<>();
      for (int j = 0; j < i; j++) {
        tlist = new ArrayList<>();
        for (int k = 0; k <= j; k++) {
          tlist.add((int) (Math.random() * 1000) % 1000);
        }
        triangle.add(tlist);
      }
      System.out.println("Minimum total for triangle with " + i + " levels: " + ob.minimumTotal(triangle));
      System.out.println();
    }

  }

}
