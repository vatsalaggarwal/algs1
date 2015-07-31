
public class Percolation {

	private boolean[][] grid;
//	private int[][] gridNumber;
	private WeightedQuickUnionUF nodes, nodes2;
	private int dim;
	
	public Percolation(int n)
	{
		if (n <= 0)
			throw new java.lang.IllegalArgumentException();
		dim = n;
		nodes = new WeightedQuickUnionUF(n*n+1);
		nodes2 = new WeightedQuickUnionUF(n*n+2);
		grid = new boolean[n][n];
		// should i use gridnumber array? only if number of accesses are more than N^2?
		for (int i = 0; i < n; i++)
		{
			nodes.union(0, gridNumber(0,i));
			nodes2.union(0, gridNumber(0,i));
			nodes2.union(n*n+1, gridNumber(n-1,i));
		}
	}
	
	public boolean percolates()
	{
		return nodes2.connected(0, dim*dim+1);
	}
	
	public boolean isFull(int row, int column)
	{
		if (isOpen(row,column))
			return nodes.connected( gridNumber( rNum(row), cNum(column) ), 0 );
		else
			return false;
	}
	
	public boolean isOpen(int row, int column)
	{
		return grid[rNum(row)][cNum(column)];
	}
	
	private void setOpen(int row, int column)
	{
		grid[rNum(row)][cNum(column)] = true;
	}
	
	public void open(int row, int column)
	{
		row = rNum(row);
		column = cNum(column);
		if (!isOpen(row + 1, column + 1))
		{
			setOpen(row + 1,column + 1);
			
			if (row >= 1)
				if(isOpen(row - 1 + 1, column + 1))
				{
					nodes.union(gridNumber(row - 1, column), gridNumber(row, column));
					nodes2.union(gridNumber(row - 1, column), gridNumber(row, column));
				}
			if (column >= 1)
				if(isOpen(row + 1, column - 1 + 1))
				{
					nodes.union(gridNumber(row, column - 1), gridNumber(row, column));
					nodes2.union(gridNumber(row, column - 1), gridNumber(row, column));
				}
			if (row <= dim - 2)
				if(isOpen(row + 1 + 1, column + 1))
				{
					nodes.union(gridNumber(row + 1, column), gridNumber(row, column));
					nodes2.union(gridNumber(row + 1, column), gridNumber(row, column));
				}
			if (column <= dim - 2)
				if(isOpen(row + 1, column + 1 + 1))
				{
					nodes.union(gridNumber(row, column + 1), gridNumber(row, column));
					nodes2.union(gridNumber(row, column + 1), gridNumber(row, column));
				}
		}
	}
	
	private int rNum(int row)
	{
		return row - 1;
	}
	
	private int cNum(int column)
	{
		return column - 1;
	}
	
	private int gridNumber(int row, int column)
	{
		return (row) + (column) * dim + 1;
	}
	
	public static void main(String[] args)
	{
		Percolation[] perc = new Percolation[1];
		perc[0] = new Percolation(6);
		System.out.print(!perc[0].percolates());
		while(!perc[0].percolates())
		{
			return;
		}
	}
	
}
