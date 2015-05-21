import java.util.*; 

public class BruteForce {
	
	protected ArrayList list;
	public long comparisons;
	
	public BruteForce(ArrayList l)
	{
		this.list = l;
		comparisons = 0;
	}
	
	public int Search( String k )
	{
		int i = 0;
		while ( i+k.length() <= list.size() )
		{
			int j = 0;
			boolean isMatch = true;
			while ( isMatch == true && j < k.length() )
			{
				if (    Character.toLowerCase( (Character)list.get(i+j) ) != 
				        Character.toLowerCase( k.charAt(j) )
				   )
				{
					isMatch = false;
					//System.out.println( list.get(i+j) );
				}
				j += 1;
				comparisons++;
			}
			
			if (isMatch)
				return i;
			else
				i += 1;		
		}
		return -1;
	}

} // end class
