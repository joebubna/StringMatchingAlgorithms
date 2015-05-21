import java.util.*; 

public class Horspool {
	
	protected ArrayList list;
	public HashMap shiftTable;
	public long comparisons;
	
	public Horspool(ArrayList l)
	{
		this.list = l;	
		shiftTable = new HashMap<Character, Integer>();
		comparisons = 0;
	}
	
	public int Search( String k )
	{
		genShiftTable( k );
		int i = k.length()-1;
		while ( i < list.size() )
		{
			int j = 0;
			boolean isMatch = true;
			while ( isMatch == true && j < k.length() )
			{
				//if (list.get(i-j) != k.charAt( k.length()-1-j ))
				//System.out.println( (Character) list.get(i-j) + ":" + k.charAt( k.length()-1-j ) );
				if (    Character.toLowerCase( (Character) list.get(i-j)) != 
				        Character.toLowerCase(k.charAt( k.length()-1-j ))
				   )
					isMatch = false;
				else
				    j += 1;
				comparisons++;
			}
			
			if (isMatch)
				return i-k.length()+1;
			else
			{
				int shift = k.length();
				if( shiftTable.containsKey( list.get(i) ))
				    shift = (Integer) shiftTable.get(list.get(i));
				i = i + shift;
			}
		    
		}
		return -1;
	}
	
	public void genShiftTable( String k )
	{
	    for (int i=0; i < k.length()-1; i++)
	        shiftTable.put( k.charAt(i), k.length()-1-i );
	}

} // end class
