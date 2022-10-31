class PartASearch implements Search {
  int lastCount;

  public Point search(Grid grid, char what) {
    lastCount = 0;
    for (int j = 0; j < grid.size; j += 1) {
      Point p = new Point(j, 0);
      for (int i = 0; i < grid.size; i += 1) {
        p.y = i;
        lastCount++;// 1 cell checked
        if (grid.at(p) == what) {
          return p;
        }
      }
    }
    return new Point(-1, -1);
  }
}
