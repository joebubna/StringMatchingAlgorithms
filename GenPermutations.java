import java.util.*;

public class GenPermutations {

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        tests();
        long endTime   = System.currentTimeMillis();
		long totalTime = (endTime - startTime)/1000;
		System.out.println(totalTime);
    }

    public static void tests()
	{
		int testSize = 5;
		boolean sizePass = true;
		boolean uniquePass = true;

		for (int i=1; i<testSize; i++)
		{
			JohnsonTrotter alg = new JohnsonTrotter(i);
			if (alg.size() != factorial(i))
				sizePass = false;
			
			HashSet set = new HashSet<String>(alg.size());
			for (int j=0; j<alg.size(); j++)
				set.add(alg.getString(j));

			if (set.size() != alg.size())
				uniquePass = false;
		}

		System.out.println( "" );

		System.out.print( "Size Test: " );
		if (sizePass == true)
			System.out.print( "PASS" );
		else
			System.out.print( "FAIL" );

		System.out.println( "" );

		System.out.print( "Unique Test: " );
		if (uniquePass == true)
			System.out.print( "PASS" );
		else
			System.out.print( "FAIL" );

		System.out.println( "" );
	}

	public static int factorial(int n) 
	{
       int result = 1;
       for (int i = 1; i <= n; i++) {
           result = result * i;
       }
       return result;
	}

}