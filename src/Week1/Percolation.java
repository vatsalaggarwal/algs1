package Week1;
// is TileNumber needed?
//is there a way to control arguments for all functions? How did I
//add 1 to all?
public class Percolation {
	class square
	{
		private boolean isOpen;
		private int tileNumber, row, column;
		public square(int row, int column)
		{
			tileNumber = (row-1)+(column-1)*8+1;
			// makes it 1 2 3 4 5 6 7 8 column down, then 9 10 next column down etc
			this.row=row;
			this.column=column;
		}
		
		public boolean isOpen()
		{
			return isOpen;
		}
		
		public boolean leftEdge()
		{
			return row>0;
		}
		
		public boolean rightEdge(int dim)
		{
			return row<dim;
		}
		
		public boolean topEdge()
		{
			return column>0;
		}
		
		public boolean bottomEdge(int dim)
		{
			return column<dim;
		}
		
		
		
		public void setOpen()
		{
			isOpen = true;
		}
		
		public void setClose()
		{
			isOpen=false;
		}
		
		public int tileNumber()
		{
			return tileNumber;
		}
	}
	private square[][] grid;
	private QuickUnionUF nodes;
	private int dim;

	public Percolation(int n)
	{
		grid = new square[n][n];
		dim=n;
		
		for (int i=1;i<=n;i++)
			for(int j=1;j<=n;j++)
				grid[i][j]=new square(i,j); 
		
		nodes = new QuickUnionUF(n*n+2);
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
		if(!isOpen(row, column))
		{
		int i = row - 1, j = column - 1;
		grid[i][j].setOpen();
		if(!grid[i][j].leftEdge())
		{
			grid[i-1][j].setOpen();
			nodes.union(grid[i][j].tileNumber(),grid[i-1][j].tileNumber());
		}
		if(!grid[i][j].rightEdge(dim))
		{
			grid[i+1][j].setOpen();
			nodes.union(grid[i][j].tileNumber(),grid[i+1][j].tileNumber());
		}
		if(!grid[i][j].topEdge())
		{
			grid[i][j-1].setOpen();
			nodes.union(grid[i][j].tileNumber(),grid[i][j-1].tileNumber());
		}
		if(!grid[i][j].bottomEdge(dim))
		{
			grid[i][j+1].setOpen();
			nodes.union(grid[i][j].tileNumber(),grid[i][j+1].tileNumber());
		}
		}
	}
	
	public boolean isFull(int row, int column)
	{
		int i=row-1, j=column-1;
		return nodes.connected(grid[i][j].tileNumber(), 0);
	}
	
	private void connectEnds()
	{
		for (int i=0; i<dim ; i++)
		{
			nodes.union(0,grid[0][i].tileNumber());
			nodes.union(dim*dim+1,grid[dim-1][i].tileNumber());
		}
	}
	
	
}


