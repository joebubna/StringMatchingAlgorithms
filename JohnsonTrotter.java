import java.util.LinkedList;
import java.util.ArrayList;
import java.lang.Math;

public class JohnsonTrotter {

	protected int n;
	protected ArrayList<ArrayList> perms;

	JohnsonTrotter(int n)
	{
		ArrayList<ArrayList> perms = new ArrayList<ArrayList>(GenPermutations.factorial(n));
		this.n = n;

		// Generates array with values 1,2,3,4,...n
		ArrayList<Integer> firstPerm = new ArrayList<Integer>(n);
		for (int i=0; i<n; i++)
			firstPerm.add(i, i+1);
		perms.add(firstPerm);
		
		ArrayList lastPerm = (ArrayList) perms.get(perms.size()-1).clone();
		int largestMobileOffset = getOffsetOfLargestMobile(lastPerm);

		// Heart of Algorithm
		//print( lastPerm ); // Output first permutation.
		while (largestMobileOffset >= 0)
		{			
			reverse(lastPerm, largestMobileOffset);
			swap(lastPerm, largestMobileOffset);
			perms.add(lastPerm);
			//print( lastPerm );

			lastPerm = (ArrayList) perms.get(perms.size()-1).clone();
			largestMobileOffset = getOffsetOfLargestMobile(lastPerm);
		}

		this.perms = perms;

	}

	ArrayList<Integer> get(int i)
	{
		return perms.get(i);
	}

	String getString(int i)
	{
		String s = "";
		ArrayList<Integer> p = perms.get(i);
		for (int j=0; j<n; j++)
			s += Math.abs(p.get(j));
		return s;
	}

	ArrayList<ArrayList> getAll()
	{
		return perms;
	}

	int size()
	{
		return perms.size();
	}

	void reverse(ArrayList<Integer> lastPerm, int k)
	{
		int kvalue = Math.abs(lastPerm.get(k)); // largest Mobile element value.
		for (int i=0; i<n; i++)
		{
			int v = lastPerm.get(i);
			//System.out.println( Math.abs(v)+">"+kvalue );
			if ( Math.abs(v) > kvalue)
				lastPerm.set(i, v*-1);
		}
	}

	void swap(ArrayList<Integer> lastPerm, int k)
	{
		int value = lastPerm.get(k);
		int adjacentValue;
		if (value > 0)
		{
			adjacentValue = lastPerm.get(k-1);
			lastPerm.set(k,adjacentValue);
			lastPerm.set(k-1,value);
		}
		else
		{
			adjacentValue = lastPerm.get(k+1);
			lastPerm.set(k,adjacentValue);
			lastPerm.set(k+1,value);
		}
	}

	int getOffsetOfLargestMobile(ArrayList<Integer> lastPerm) 
	{
		int resultOffset = -1;
		int largestMobile = -1;

		for (int i=0; i<n; i++)
		{
			int value = lastPerm.get(i);
			if ( Math.abs(value) > largestMobile)
			{
				// Check if mobile.
				if (value > 0)
				{
					if (i-1 >= 0)
					{
						if ( Math.abs(lastPerm.get(i-1)) < value )
						{
							largestMobile = value;
							resultOffset = i;
						}
					}
					
				}
				else
				{
					if (i+1 < n)
					{
						if ( Math.abs(lastPerm.get(i+1)) < Math.abs(value) )
						{
							largestMobile = Math.abs(value);
							resultOffset = i;
						}
					}
				}

			}
		}
		return resultOffset;

	} // end getOffsetOfLargestMobile

	void print(ArrayList p)
	{
		String s = "";
		for (int j=0; j<n; j++)
		{
			int c = (Integer) p.get(j);
			s += Math.abs(c);
		}			
		System.out.println( s );
	}



} // end Class
