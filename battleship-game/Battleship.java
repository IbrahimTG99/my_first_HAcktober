// takes input from file in the form of: (x,y)(x,y)(x,y)(x,y)(x,y)(x,y)(x,y)(x,y)
// ship has 5 cells and carrier has 3 cells
// the first 5 coordinates are for ship and the next 3 are for carrier
// places the ship and carrier on a grid of size 25 x 25
// after that it independently searches for the ship and carrier using the search algorithm
// and prints the number of cells checked
// this is the code for the whole game

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.List;

public class Battleship {
  public static void main(String[] args) throws FileNotFoundException
  {
    int gameNumber = 1;
    Scanner sc = new Scanner(new File("input.txt"));
    // while loop to go over each input line in the file until file is empty
    while (sc.hasNextLine()){
      String line = sc.nextLine();
      String[] coordinates = line.split("\\(|\\)|\\,");
      int[] x = new int[coordinates.length];
      int[] y = new int[coordinates.length];
      int j = 0;
      for (int i = 0; i < coordinates.length; i++) {
        if (coordinates[i].equals(""))
          continue;
        if (j % 2 == 0)
          x[j / 2] = Integer.parseInt(coordinates[i]);
        else
          y[j / 2] = Integer.parseInt(coordinates[i]);
        j++;
      }
      int size = 25;
      Grid grid = new Grid(size);
      for (int i = 0; i < 5; i++) {
        grid.set(new Point(x[i], y[i]), 'S');
      }
      for (int i = 5; i < 8; i++) {
        grid.set(new Point(x[i], y[i]), 'C');
      }

      List<Point> points = new java.util.ArrayList<Point>();
      int checks = 0;
      PartASearch partASearch = new PartASearch();
      PartBSearch partBSearch = new PartBSearch();
      PartCSearch partCSearch = new PartCSearch();

      System.out.println("Game " + gameNumber + ":");
      System.out.println("Horizontal Search:");
      Point p = partASearch.search(grid, 'S');
      // call checkaround method to check the cells around the ship
      checkAround(grid, p, size, 'S', points);
      checks += partASearch.lastCount;
      p = partASearch.search(grid, 'C');
      checkAround(grid, p, size, 'C', points);
      checks += partASearch.lastCount;
      // print the number of cells checked
      System.out.println("Total checks: " + checks);
      // print the range of cells in points
      System.out.println("Carrier found" + ": (" + points.get(0).x + ", " + points.get(0).y + ") to (" + points.get(4).x + ", " + points.get(4).y + ") Submarine found" + ": (" + points.get(5).x + ", " + points.get(5).y + ") to (" + points.get(7).x + ", " + points.get(7).y + ")");
      System.out.println();

      // reset List
      points = new java.util.ArrayList<Point>();
      // reset checks
      checks = 0;

      System.out.println("Random Search:");
      p = partBSearch.search(grid, 'S');
      checkAround(grid, p, size, 'S', points);
      checks += partBSearch.lastCount;
      p = partBSearch.search(grid, 'C');
      checkAround(grid, p, size, 'C', points);
      checks += partBSearch.lastCount;
      System.out.println("Total checks: " + checks);
      System.out.println("Carrier found" + ": (" + points.get(0).x + ", " + points.get(0).y + ") to (" + points.get(4).x + ", " + points.get(4).y + ") Submarine found" + ": (" + points.get(5).x + ", " + points.get(5).y + ") to (" + points.get(7).x + ", " + points.get(7).y + ")");
      System.out.println();

      // reset List
      points = new java.util.ArrayList<Point>();
      // reset checks
      checks = 0;

      System.out.println("Strategic Search:");
      p = partCSearch.search(grid, 'S');
      checkAround(grid, p, size, 'S', points);
      checks += partCSearch.lastCount;
      p = partCSearch.search(grid, 'C');
      checkAround(grid, p, size, 'C', points);
      checks += partCSearch.lastCount;
      System.out.println("Total checks: " + checks);
      System.out.println("Carrier found" + ": (" + points.get(0).x + ", " + points.get(0).y + ") to (" + points.get(4).x + ", " + points.get(4).y + ") Submarine found" + ": (" + points.get(5).x + ", " + points.get(5).y + ") to (" + points.get(7).x + ", " + points.get(7).y + ")");
      System.out.println();
      gameNumber++;
    }
    sc.close();
  }

  // function to check all the cells around a given cell
  public static void checkAround(Grid grid, Point p, int size, char what, List<Point> points) {
    if (p.x == -1 || p.y == -1)
      return;
    int objectSize = 0;
    if (what == 'S')
      objectSize = 5;
    else
      objectSize = 3;
    points.add(new Point(p.x, p.y));
    for (int i = 1; i < objectSize; i++) {
      if (p.x - i >= 0) {
        if (grid.at(new Point(p.x - i, p.y)) == what) {
          // append at the end of the array
          points.add(new Point(p.x - i, p.y));
        }
      }
      if (p.x + i < size) {
        if (grid.at(new Point(p.x + i, p.y)) == what) {
          points.add(new Point(p.x + i, p.y));
        }
      }
      if (p.y - i >= 0) {
        if (grid.at(new Point(p.x, p.y - i)) == what) {
          points.add(new Point(p.x, p.y - i));
        }
      }
      if (p.y + i < size) {
        if (grid.at(new Point(p.x, p.y + i)) == what) {
          points.add(new Point(p.x, p.y + i));
        }
      }
    }
    // sort th List of points using insertion sort
    for (int i = 1; i < points.size(); i++) {
      Point key = points.get(i);
      int j = i - 1;
      while (j >= 0 && points.get(j).x > key.x) {
        points.set(j + 1, points.get(j));
        j--;
      }
      points.set(j + 1, key);
    }
    // insertion sort for y
    for (int i = 1; i < points.size(); i++) {
      Point key = points.get(i);
      int j = i - 1;
      while (j >= 0 && points.get(j).y > key.y) {
        points.set(j + 1, points.get(j));
        j--;
      }
      points.set(j + 1, key);
    }


  }

}
