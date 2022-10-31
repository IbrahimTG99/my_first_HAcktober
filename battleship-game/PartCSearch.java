class PartCSearch implements Search {
  int lastCount;

  public Point search(Grid grid, char what) {
    lastCount = 0;
    int jump = 1;
    // if what is 'S', set jump to 5 else set jump to 3
    if (what == 'S') {
      jump = 3;
    }else{
      jump = 2;
    }
    for (int j = 0; j < grid.size; j += 1) {
      Point p = new Point(j, 0);
      for (int i = 0; i < grid.size; i += (j % jump + 1)) {
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
