class PartBSearch implements Search {
  int lastCount;
  
  public Point search(Grid grid, char what) {
    lastCount = 0;
    Point p = new Point(0, 0);
    while (true)
    {    // select random point within grid
        p.x = (int) (Math.random() * grid.size);
        p.y = (int) (Math.random() * grid.size);
        // check if p is what
        if (grid.at(p) == what) {
            return p;
        }
        // if not, increment lastCount
        lastCount++;
    }
  }
}
