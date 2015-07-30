//package Week1;

public class PercolationStats {
private Percolation[] perc;
private int[] openSites;
private int dim, noOfMaps;

	public PercolationStats(int N, int T)
	{
		if (N <= 0 || T <= 0)
			throw new java.lang.IllegalArgumentException();
		
		dim = N;
		noOfMaps = T;
		
		perc = new Percolation[T];
		for (int i = 0; i < T; i++)
		{
			perc[i] = new Percolation(N);
		}
		runSimulation();
	}
	
	public double mean()
	{
		return 0;
	}
	
	public double stddev()
	{
		return 0;
	}
	
	public double confidenceLo()
	{
		return 0;
	}
	
	public double confidenceHi()
	{
		return 0;
	}
	
	public static void main(String[] args)
	{
		PercolationStats stats = new PercolationStats(Integer.parseInt(args[0]),Integer.parseInt(args[1]));
		System.out.println("mean = " + stats.mean());
		System.out.println("stddev = " + stats.stddev());
		System.out.println("95% confidence interval = " + stats.confidenceLo() + ", " +stats.confidenceHi());
	}
	
	private void runSimulation()
	{
		int len = perc.length;
		int randomRow, randomColumn; //probabilities multiply anyways
		for (int i = 0; i < len; i++)
		{
		while(!perc[i].percolates())
		{
			randomRow = StdRandom.uniform(0, dim - 1);
			randomColumn = StdRandom.uniform(0, dim - 1);
			if(!perc[i].isOpen(randomRow, randomColumn))
				perc[i].open(randomRow, randomColumn);
		}
	//	openSites = perc[i].noOfOpenSites();
		}
	}
	
}
