public class Grid {
  final int size;
  private char[][] grid;

  Grid(int size) {
    this.size = size;
    this.grid = new char[size][size];
  }

  public char at(Point p) {
    if ((p.x + 1) > size || (p.y + 1) > size) {
      throw new IllegalArgumentException("Coordinate out of bound");
    }
    return grid[p.x][p.y];
  }

  public void set(Point p, char v) {
    if ((p.x + 1) > size || (p.y + 1) > size) {
      throw new IllegalArgumentException("Coordinate out of bound");
    }
    grid[p.x][p.y] = v;
  }
}
