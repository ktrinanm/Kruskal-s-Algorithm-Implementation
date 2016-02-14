

public class Edge implements Comparable<Edge>
{
	int start, end, weight;
	
	public int compareTo(Edge compareEdge)
	{
		return (this.weight - compareEdge.weight);
	}
}
