package Week1;

public class QuickFind {

	private int idLength;
	private int[] id;
	
	
	public QuickFind(int n)
	{
		idLength = n;
		for(int i=0;i<n;i++)
		{
			id[i]=i;
		}
	}
	
	public void union(int a, int b)
	{
		int aID = id[a], bID = id[b];
		for (int i=0; i<idLength; i++)
		{
			if(id[i]==aID)
				id[i]=bID;
		}
	}

	public boolean isConnected(int a, int b)
	{
		return id[a]==id[b];
	}
	
}
