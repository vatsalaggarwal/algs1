public class PercolationOne {
	
	private class Square
	{
		private boolean isOpen;
		private int tileNumber, row, column;
		public Square(int i, int j)
		{
			tileNumber = i+j*dim+1;
			// makes it 1 2 3 4 5 6 7 8 column down, then 9 10 next column down etc
			row=i;
			column=j;
		}
		
		public boolean isOpen()
		{
			return isOpen;
		}
		
		public boolean leftEdge()
		{
			return row == 0;
		}
		
		public boolean rightEdge()
		{
			return row == dim - 1;
		}
		
		public boolean topEdge()
		{
			return column == 0;
		}
		
		public boolean bottomEdge()
		{
			return column == dim - 1;
		}
		
		
		
		public void setOpen()
		{
			isOpen = true;
		}
		
		public int tileNumber()
		{
			return tileNumber;
		}
	}
	private Square[][] grid;
	private WeightedQuickUnionUF nodes;
	private int dim;

	public PercolationOne(int n)
	{
		grid = new Square[n][n];
		dim = n;
		
		for (int i = 0; i < n; i++)
			for(int j = 0; j < n; j++)
				grid[i][j] = new Square(i,j); 
		
		nodes = new WeightedQuickUnionUF(n*n+2);
		connectEnds();
		dim = n;
	}

	public boolean isOpen(int row, int column)
	{
		int i = row - 1, j = column - 1;
		return grid[i][j].isOpen();
	}
	
	public boolean percolates()
	{
		return nodes.connected(0, dim*dim+1);
	}

	public void open(int row, int column)
	{
		int i = row - 1, j = column - 1;
		if (!grid[i][j].isOpen())
		{
		grid[i][j].setOpen();
		if (!grid[i][j].leftEdge())
		{
			if (grid[i-1][j].isOpen())
			nodes.union(grid[i][j].tileNumber(), grid[i-1][j].tileNumber());
		}
		if (!grid[i][j].rightEdge())
		{
			if (grid[i+1][j].isOpen())
			nodes.union(grid[i][j].tileNumber(), grid[i+1][j].tileNumber());
		}
		if (!grid[i][j].topEdge())
		{
			if (grid[i][j-1].isOpen())
			nodes.union(grid[i][j].tileNumber(), grid[i][j-1].tileNumber());
		}
		if (!grid[i][j].bottomEdge())
		{
			if (grid[i][j+1].isOpen())
			nodes.union(grid[i][j].tileNumber(), grid[i][j+1].tileNumber());
		}
		}
	}
	
	public boolean isFull(int row, int column)
	{
		int i = row - 1, j = column - 1;
		if (grid[i][j].isOpen())
			return nodes.connected(grid[i][j].tileNumber(), 0);
		else
			return false;
		
	}
	
	private void connectEnds()
	{
		for (int i = 0; i < dim; i++)
		{
			nodes.union(0, grid[0][i].tileNumber());
			nodes.union(dim * dim + 1, grid[dim - 1][i].tileNumber());
		}
	}

	public static void main(String[] args)
	{
		PercolationOne perc = new PercolationOne(6);
		perc.open(1, 6);
		perc.open(2, 6);
		perc.open(3, 6);
		perc.open(4, 6);
		perc.open(5, 6);
		perc.open(5, 5);
		perc.open(4, 4);
		perc.open(3, 4);
		perc.open(2, 4);
		perc.open(2, 3);
		perc.open(2, 2);
		perc.open(2, 1);
		perc.open(3, 1);
		perc.open(4, 1);
		perc.open(5, 1);
		perc.open(5, 2);
		perc.open(6, 2);
		//perc.open(5, 4);
		System.out.print(perc.percolates());
	}
	
}


