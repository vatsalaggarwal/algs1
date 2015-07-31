public class PercolationStats {
private Percolation[] perc;
private int[] openSites; //array containing the number of OpenSites
private int dim;

	public PercolationStats(int N, int T)
	{
		if (N <= 0 || T <= 0)
			throw new java.lang.IllegalArgumentException();
		
		dim = N;
		
		perc = new Percolation[T];
		openSites = new int[T];
		for (int i = 0; i < T; i++)
		{
			perc[i] = new Percolation(N);
		}
		runSimulation();
	}
	
	public double mean()
	{
		return StdStats.mean(openSites);
	}
	
	public double stddev()
	{
		return StdStats.stddev(openSites);
	}
	
	public double confidenceLo()
	{
		return mean() - 1.96 * stddev() / Math.sqrt(perc.length);
	}
	
	public double confidenceHi()
	{
		return mean() + 1.96 * stddev() / Math.sqrt(perc.length);
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
			{
				perc[i].open(randomRow, randomColumn);
				openSites[i]++;
			}
		}
		}
	}
	
}
