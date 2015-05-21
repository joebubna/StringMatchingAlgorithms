import java.util.*; 

public class BoyerMoore {
	
	protected ArrayList list;
	public HashMap badTable;
	public HashMap goodTable;
	public long comparisons;
	
	public BoyerMoore(ArrayList l)
	{
		this.list = l;	
		comparisons = 0;		
	}
	
	public int Search( String k )
	{
		genBadTable( k );
		genGoodTable( k );
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
				// Calculate value of D1.
				int badShift = k.length();
				if( badTable.containsKey( list.get(i-j) ))
				    badShift = (Integer) badTable.get(list.get(i-j));
				int d1 = Math.max(badShift - j, 1);
				
				// Calculate value of D2.
				int d2 = 0;
				if (j > 0)
				    d2 = (Integer) goodTable.get(j);
				
				// Calculate actual shift amount.
				i = i + Math.max(d1, d2);
			}
		    
		}
		return -1;
	}
	
	public void genGoodTable( String k )
	{
	    int mapSize = (int)(k.length() * 1.4);
	    goodTable = new HashMap<Character, Integer>(mapSize);
	    //System.out.println("Pattern: " + k);
	    //System.out.println("Matched Chars, Shift Distance");
	    //System.out.println("k, d");
	    for (int j=1; j < k.length(); j++)
	    {
	        int shift = calcGoodShift(j,k);
	        goodTable.put( j, shift );
	        //System.out.println(j + ", " + shift);
	    }
	}
	
	public int calcGoodShift( int j, String k )
	{
	    String suffix = k.substring( k.length()-j, k.length() );
	    Character preChar = k.charAt( k.length()-j-1 );
	    
	    //System.out.println( suffix + ", PreChar: " + preChar );
	    
	    // Search for suffix with diff preChar.
	    int i = k.length();
	    boolean found = false;
	    while (i > 0 && i-j >= 0 && found == false)
	    {
	        //System.out.println( k.substring(i-j, i) );
	        if ( suffix.equals(k.substring(i-j, i)) )
	        { 
	            //System.out.println( ">> " + k.charAt(i-j-1) );
	            if (i-j-1 == -1)
	                found = true;
	            else if (k.charAt(i-j-1) != preChar)
	                found = true;
	            else
	                i--;
	        }
	        else
	            i--;
	    }
	    
	    // If another occurance of the suffix (w/diff preChar) was found
	    // then input the shift value into the table.
	    if (found == true)
	        return k.length() - i;
	    
	    // If no valid occurance of the suffix was found, then
	    // check substrings of the suffix against the prefix.
	    for (int l=j-1; l > 0; l--)
	    {
	        int len = suffix.length();
	        String suffSub = suffix.substring(len-l, len);
	        String prefSub = k.substring(0,l);
	        //System.out.println( "Prefix: " + prefSub + ", Suffix: " + suffSub );
	        if ( prefSub.equals(suffSub) )
	            return k.length()-l;
	    }
	    
	    // Else return full length of Pattern.
	    return k.length();
	}
	
	public void genBadTable( String k )
	{
	    badTable = new HashMap<Character, Integer>(50);
	    for (int i=0; i < k.length()-1; i++)
	        badTable.put( k.charAt(i), k.length()-1-i );
	}

} // end class
