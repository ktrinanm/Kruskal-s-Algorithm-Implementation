//Why number 3? Because it was the 4th implementation that I had tried before I learned github

import java.util.*;
import java.lang.*;
import java.io.*;

public class KGraph3
{
	int numV, numE;
	Edge [] edge;
	
	public KGraph3(int v, int e)
	{
		numV = v;
		numE = e;
		
		edge = new Edge[e];
		
		for(int i = 0; i < e; i++)
		{
			edge[i] = new Edge();
		}
	}
	
	public int find(SubSets [] ss, int element)
	{
		
		if(ss[element].parent != element)
		{
			ss[element].parent = find(ss, ss[element].parent);
		}
		
		return ss[element].parent;
	}
	
	public void union(SubSets [] ss, int setOne, int setTwo)
	{
		int rootOne = find(ss, setOne);
		int rootTwo = find(ss, setTwo);
		
		if(ss[rootOne].rank < ss[rootTwo].rank)
		{
			ss[rootOne].parent = rootTwo;
		}
		else if(ss[rootOne].rank > ss[rootTwo].rank)
		{
			ss[rootTwo].parent = rootOne;
		}
		else
		{
			ss[rootTwo].parent = rootOne;
			ss[rootOne].rank++;
		}
	}
	
	public void kruskal()
	{
		Edge [] result = new Edge[numV];
		int i = 0;
		int j = 0;
		
		for(j=0; j < numV; j++)
		{
			result[j] = new Edge();
		}	
		
		Arrays.sort(edge);
		
		SubSets [] ss = new SubSets[numV];
		
		for(j=0; j < numV; j++)
		{
			ss[j] = new SubSets();
		}	
		
		for(int m = 0; m < numV; m++)
		{
			ss[m].parent = m;
			ss[m].rank = 0;
		}
		
		j = 0;
		
		while(i < numV-1)
		{
			Edge ne = new Edge();
			ne = edge[j++];
			
			int x = find(ss, ne.start);
			int y = find(ss, ne.end);
			
			if(x != y)
			{
				result[i++] = ne;
				union(ss, x, y);
			}
		}
		
		System.out.println("Edges that got used: ");
		
		for(j = 0; j < i; j++)
		{
			System.out.println("From Vertex " + result[j].start + " To Vertex " + result[j].end + " has the weight of " + result[j].weight);
		}
	}
}
