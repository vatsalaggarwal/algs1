package Week1;

public class PercolationStats {

	public PercolationStats(int N, int T)
	{
		
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
	
}
