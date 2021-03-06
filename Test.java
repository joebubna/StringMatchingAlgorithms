import java.util.*;
import java.io.*;
import java.math.*;
import com.google.common.base.Charsets;
import com.google.common.io.Files;
import com.google.common.io.Resources;
import java.net.URL;

public class Test {

	private ArrayList< ArrayList<Character> > texts;
	
	public Test()
	{
		// *************************
		// ADD SIMPLE STRINGS TO LIST
		// *************************
		texts = new ArrayList< ArrayList<Character> >();
		addString("0123456789");
		addString("jim saw me in a barbershop");
		addString("someword1 someword2 someword3 someword4 someword5 someword6 someword7 someword8");
		addString("1someword 2someword 3someword 4someword 5someword 6someword 7someword 8someword");
		
		// Below creates a string of 1000 zeroes.
		char[] chars = new char[1000];
        Arrays.fill(chars, '0');
        String s = new String(chars);
        addString(s);
        
        // *************************
		// ADD "The Secret of the Nagas" book to list.
		// *************************
		String book = "";
		try{
            URL url = Resources.getResource("book.txt");
            book = Resources.toString(url, Charsets.UTF_8);
           
        } catch(IOException e){ e.printStackTrace(); }	       
        addString( book );
        //System.out.println( book.length() );
        //printBookStuff();
		
		// *************************
		// ADD JOHNSON STROTTER STRING TO LIST.
		// *************************
		ArrayList<Character> jt = new ArrayList<Character>();
		JohnsonTrotter alg = new JohnsonTrotter(10);
		for (int j=0; j<alg.size(); j++)
		{
		    String str = alg.getString(j);
		    for(int i=0; i<str.length(); i++)
			    jt.add( str.charAt(i) );
			jt.add( ' ' );
		}
		texts.add( jt );	
		//System.out.println( texts.get(1).size() );	
		//printJTStuff();
		
		
		/*
		BruteForce testCase = new BruteForce(texts.get(1));
        int result = testCase.Search( "21345678910" ); // Last element in string of 10! combinations.
		System.out.println(result);
        */
	}
	
	private void addString( String str )
	{
		ArrayList t = new ArrayList<Character>();
		for(int i=0; i<str.length(); i++)
			t.add( str.charAt(i) );
		texts.add( t );
	}
	
	
	public void runTests() {
		System.out.println(" ");
		System.out.println("Unit-Tests Running...");
		unitTestBruteForce();
		unitTestHorspool();
		unitTestBoyerMoore();
		
		String str = "GOOOOOOOOOOOGLE";
		ArrayList t = new ArrayList<Character>();
		for(int i=0; i<str.length(); i++)
			t.add( str.charAt(i) );	
		BoyerMoore testCase = new BoyerMoore(t);
		testCase.Search( "BOOOO" );
				
		//testSpeeds0();
        //testSpeeds1();
        //testSpeeds2();
        //testSpeeds3();
        //testSpeeds4();
        
        testSpeeds5();
        //testSpeeds6();
        
	}
	
	public void testSpeeds0()
	{
	     System.out.println(" ");
	     System.out.println("#############################################");
	     System.out.println("Testing string '0123456789'");
	     System.out.println("#############################################");
	     
	     printHeader("0");
	     testBruteForce("0", 0, 100000, 100000);
	     testBruteForcePlus1("0", 0, 100000, 100000);
	     testBruteForceRef("0", 0, 100000, 100000);
	     testHorspool("0", 0, 100000, 100000);
	     testBoyerMoore("0", 0, 100000, 100000);
	     
	     printHeader("5");
	     testBruteForce("5", 0, 100000, 100000);
	     testBruteForcePlus1("5", 0, 100000, 100000);
	     testBruteForceRef("5", 0, 100000, 100000);
	     testHorspool("5", 0, 100000, 100000);
	     testBoyerMoore("5", 0, 100000, 100000);
	     
	     printHeader("9");
	     testBruteForce("9", 0, 100000, 100000);
	     testBruteForcePlus1("9", 0, 100000, 100000);
	     testBruteForceRef("9", 0, 100000, 100000);
	     testHorspool("9", 0, 100000, 100000);    
	     testBoyerMoore("9", 0, 100000, 100000);  	     
	}
	
	
	public void testSpeeds1()
	{
	     System.out.println(" ");
	     System.out.println("#############################################");
	     System.out.println("Testing string 'jim saw me in a barbershop'");
	     System.out.println("#############################################");
	     String s;
	     
	     s = "bob";
	     printHeader(s);
	     testBruteForce(s, 1, 100000, 200000);
	     testBruteForcePlus1(s, 1, 100000, 200000);
	     testBruteForceRef(s, 1, 100000, 200000);
	     testHorspool(s, 1, 100000, 200000);
	     testBoyerMoore(s, 1, 100000, 200000);
	     
	     s = "jim";
	     printHeader(s);
	     testBruteForce(s, 1, 100000, 200000);
	     testBruteForcePlus1(s, 1, 100000, 200000);
	     testBruteForceRef(s, 1, 100000, 200000);
	     testHorspool(s, 1, 100000, 200000);
	     testBoyerMoore(s, 1, 100000, 200000);
	     
	     s = "barber";
	     printHeader(s);
	     testBruteForce(s, 1, 100000, 200000);
	     testBruteForcePlus1(s, 1, 100000, 200000);
	     testBruteForceRef(s, 1, 100000, 200000);
	     testHorspool(s, 1, 100000, 200000);  
	     testBoyerMoore(s, 1, 100000, 200000);   	 
	     
	     s = "shop"; 
	     printHeader(s);
	     testBruteForce(s, 1, 100000, 200000);
	     testBruteForcePlus1(s, 1, 100000, 200000);
	     testBruteForceRef(s, 1, 100000, 200000);
	     testHorspool(s, 1, 100000, 200000);   
	     testBoyerMoore(s, 1, 100000, 200000);  
	}
	
	public void testSpeeds2()
	{
	     System.out.println(" ");
	     System.out.println("#############################################");
	     System.out.println("Testing string 'someword1 someword2 someword3 someword4 someword5 someword6 someword7'");
	     System.out.println("#############################################");
	     String s;
	     
	     s = "someword8";
	     printHeader(s);
	     testBruteForce(s, 2, 50000, 200000);
	     testBruteForcePlus1(s, 2, 50000, 200000);
	     testBruteForceRef(s, 2, 50000, 200000);
	     testHorspool(s, 2, 50000, 200000);   
	     testBoyerMoore(s, 2, 50000, 200000);     
	}
	
	public void testSpeeds3()
	{
	     System.out.println(" ");
	     System.out.println("#############################################");
	     System.out.println("Testing string '1someword 2someword 3someword 4someword 5someword 6someword 7someword'");
	     System.out.println("#############################################");
	     String s;
	     
	     s = "8someword";
	     printHeader(s);
	     testBruteForce(s, 3, 50000, 200000);
	     testBruteForcePlus1(s, 3, 50000, 200000);
	     testBruteForceRef(s, 3, 50000, 200000);
	     testHorspool(s, 3, 50000, 200000);  
	     testBoyerMoore(s, 3, 50000, 200000);      
	}
	
	public void testSpeeds4()
	{
	     System.out.println(" ");
	     System.out.println("#############################################");
	     System.out.println("Testing string '1000 zeroes'");
	     System.out.println("#############################################");
	     String s;
	     
	     s = "00001";
	     printHeader(s);
	     testBruteForce(s, 4, 100000, 100000);
	     testBruteForcePlus1(s, 4, 100000, 100000);
	     testBruteForceRef(s, 4, 100000, 100000);
	     testHorspool(s, 4, 100000, 500000);
	     testBoyerMoore(s, 4, 100000, 500000);
	     
	     s = "10000";
	     printHeader(s);
	     testBruteForce(s, 4, 100000, 100000);
	     testBruteForcePlus1(s, 4, 100000, 100000);
	     testBruteForceRef(s, 4, 100000, 100000);
	     testHorspool(s, 4, 100000, 500000);	
	     testBoyerMoore(s, 4, 100000, 500000);        
	}
	
	public void testSpeeds5()
	{
	     System.out.println(" ");
	     System.out.println("#############################################");
	     System.out.println("Testing string 'Book: The Secret of the Nagas'");
	     System.out.println("#############################################");
	     String s;
	     
	     /*
	     s = "The ships set sail"; // Is a string only found in the middle of the book. Pg. 93.
	     printHeader(s);
	     testBruteForce(s, 5, 1000, 1000);
	     testBruteForcePlus1(s, 5, 1000, 1000);
	     testBruteForceRef(s, 5, 1000, 1000);
	     testHorspool(s, 5, 1000, 1000);
	     testBoyerMoore(s, 5, 1000, 1000);
	     */
	     
	     s = "strength of character and determination"; // Found about 2/3rds of the way
	     printHeader(s);
	     testBruteForce(s, 5, 1000, 1000);
	     testBruteForcePlus1(s, 5, 1000, 1000);
	     testBruteForceRef(s, 5, 1000, 1000);
	     testHorspool(s, 5, 1000, 1000);
	     testBoyerMoore(s, 5, 1000, 1000);
	     
	     /*
	     s = "rand";
	     printHeader(s);
	     //testBruteForce(s, 5, 5000, 1000);
	     //testBruteForcePlus1(s, 5, 5000, 1000);
	     //testBruteForceRef(s, 5, 5000, 1000);
	     testHorspool(s, 5, 50000, 10000);
	     testBoyerMoore(s, 5, 50000, 10000);
	    */
	}
	
	public void testSpeeds6()
	{
	     System.out.println(" ");
	     System.out.println("#############################################");
	     System.out.println("Testing string 'Johnson-Trotter Algorithm - 10!'");
	     System.out.println("#############################################");
	     String s;
	     /*
	     s = "21345678910";
	     printHeader(s);
	     testBruteForce(s, 6, 200, 50);
	     testBruteForcePlus1(s, 6, 200, 50);
	     testBruteForceRef(s, 6, 200, 50);
	     testHorspool(s, 6, 200, 50);
	     testBoyerMoore(s, 6, 200, 50);
	     */
	     s = "rand";
	     printHeader(s);
	     //testBruteForce(s, 6, 1000, 50);
	     //testBruteForcePlus1(s, 6, 1000, 50);
	     //testBruteForceRef(s, 6, 1000, 50);
	     testHorspool(s, 6, 1000, 50);
	     testBoyerMoore(s, 6, 1000, 50);	
	}
	
	public void testBruteForce( String key, int textIndex, int runs, int throwaways )
	{ 
	    // "WARMUP" tests
	    speedTestBruteForce(key, textIndex, throwaways);	
	    	
	    long startTime = System.nanoTime();
	    long comps = speedTestBruteForce(key, textIndex, runs);
		long endTime   = System.nanoTime();
		long totalTime = (endTime - startTime)/1000;  // OUTPUT: 6281 ms
		
		long avgComps = comps / runs;
		System.out.printf("%-15.15s  %-15.15s %-15.15s %-15.15s %-15.15s\n", 
		    "Brute-Force", runs, totalTime, comps, avgComps);
	    
	    /* TESTING EXAMPLE OF HOTSPOT COMPILER
	    // "0123456789"
	    long startTime = System.nanoTime();
	    speedTestBruteForce("5", 0, 10000);
	    long endTime   = System.nanoTime();
		long totalTime = (endTime - startTime)/1000;
		System.out.println(totalTime);  // OUTPUT: 15274 ms
		
	    startTime = System.nanoTime();
	    long comps = speedTestBruteForce("5", 0, 100000);
		long avgComps = comps / 100000;
		System.out.println(avgComps);
		endTime   = System.nanoTime();
		totalTime = (endTime - startTime)/1000;  // OUTPUT: 6281 ms
		System.out.println(totalTime);
	    */
	}
	
	public void testBruteForcePlus1( String key, int textIndex, int runs, int throwaways )
	{ 
	    // "WARMUP" tests
	    speedTestBruteForcePlus1(key, textIndex, throwaways);	
	    	
	    long startTime = System.nanoTime();
	    long comps = speedTestBruteForcePlus1(key, textIndex, runs);
		long endTime   = System.nanoTime();
		long totalTime = (endTime - startTime)/1000;  // OUTPUT: 6281 ms
		
		long avgComps = comps / runs;
		System.out.printf("%-15.15s  %-15.15s %-15.15s %-15.15s %-15.15s\n", 
		    "Brute-Force+1", runs, totalTime, comps, avgComps);
    }
    
    public void testBruteForceRef( String key, int textIndex, int runs, int throwaways )
	{ 
	    // "WARMUP" tests
	    speedTestBruteForceRef(key, textIndex, throwaways);	
	    	
	    long startTime = System.nanoTime();
	    long comps = speedTestBruteForceRef(key, textIndex, runs);
		long endTime   = System.nanoTime();
		long totalTime = (endTime - startTime)/1000;  // OUTPUT: 6281 ms
		
		long avgComps = comps / runs;
		System.out.printf("%-15.15s  %-15.15s %-15.15s %-15.15s %-15.15s\n", 
		    "Brute-ForceRef", runs, totalTime, comps, avgComps);
    }
	
	public void testHorspool( String key, int textIndex, int runs, int throwaways )
	{
	    // "WARMUP" tests
	    speedTestHorspool(key, textIndex, throwaways);	
	    	
	    long startTime = System.nanoTime();
	    long comps = speedTestHorspool(key, textIndex, runs);
		long endTime   = System.nanoTime();
		long totalTime = (endTime - startTime)/1000;
		
		long avgComps = comps / runs;
		System.out.printf("%-15.15s  %-15.15s %-15.15s %-15.15s %-15.15s\n", 
		    "Horspool", runs, totalTime, comps, avgComps);
	}
	
	public void testBoyerMoore( String key, int textIndex, int runs, int throwaways )
	{
		// "WARMUP" tests
	    speedTestBoyerMoore(key, textIndex, throwaways);	
	    	
	    long startTime = System.nanoTime();
	    long comps = speedTestBoyerMoore(key, textIndex, runs);
		long endTime   = System.nanoTime();
		long totalTime = (endTime - startTime)/1000;
		
		long avgComps = comps / runs;
		System.out.printf("%-15.15s  %-15.15s %-15.15s %-15.15s %-15.15s\n", 
		    "Boyer-Moore", runs, totalTime, comps, avgComps);
	}
	
	
	
	
	
	
	
    /* ############################################
	//  UNIT TESTS
	#############################################*/
	public void unitTestBruteForce()
	{
		if (
		    // "0123456789"
			testAssertBruteForce("57", 0, -1) &&
			testAssertBruteForceComps("57", 0, 10) &&
			testAssertBruteForce("5", 0, 5) &&
			testAssertBruteForceComps("5", 0, 6) &&
			// "jim saw me in a barbershop"
			testAssertBruteForce("bob", 1, -1) &&
			testAssertBruteForceComps("bob", 1, 26) &&
			testAssertBruteForce("jim", 1, 0) &&
			testAssertBruteForceComps("jim", 1, 3) &&
			testAssertBruteForce("barber", 1, 16) &&
			testAssertBruteForceComps("barber", 1, 22) &&
			testAssertBruteForce("shop", 1, 22) &&
			testAssertBruteForceComps("shop", 1, 27)
			)
			System.out.println("Brute-Force Unit-Test: PASS");
		else
			System.out.println("Brute-Force Unit-Test: FAIL");
	}

    
    

	public void unitTestHorspool()
	{
		if (
		    // "0123456789"
			testAssertHorspool("57", 0, -1) &&
			testAssertHorspoolComps("57", 0, 5) &&
			testAssertHorspool("5", 0, 5) &&
			testAssertHorspoolComps("5", 0, 6) &&
			// "jim saw me in a barbershop"
			testAssertHorspool("bob", 1, -1) &&
			testAssertHorspoolComps("bob", 1, 8) &&
			testAssertHorspool("jim", 1, 0) &&
			testAssertHorspoolComps("jim", 1, 3) &&
			testAssertHorspool("barber", 1, 16) &&
			testAssertHorspoolComps("barber", 1, 12) &&
			testAssertHorspool("shop", 1, 22) &&
			testAssertHorspoolComps("shop", 1, 10)
			)
			System.out.println("Horspool Unit-Test: PASS");
		else
			System.out.println("Horspool Unit-Test: FAIL");
		/*
		String s = "barber";
		alg.genShiftTable( s );
		for (int i=0; i < s.length(); i++)
		    System.out.println( s.charAt(i) + ": " + alg.shiftTable.get(s.charAt(i)) );
		*/
	}
	
	public void unitTestBoyerMoore()
	{
		boolean section1 = false;
		boolean section2 = false;
		boolean section3 = false;
		if (
		    // "0123456789"
			testAssertBoyerMoore("57", 0, -1) &&
			testAssertBoyerMoore("5", 0, 5) &&
			// "jim saw me in a barbershop"
			testAssertBoyerMoore("bob", 1, -1) &&
			testAssertBoyerMoore("jim", 1, 0) &&
			testAssertBoyerMoore("barber", 1, 16) &&
			testAssertBoyerMooreComps("barber", 1, 12) &&
			testAssertBoyerMoore("shop", 1, 22)
			)
			section1 = true;
		
		// Setup for a Good table test
		String str = "ZZZBABCBAB";
		ArrayList t = new ArrayList<Character>();
		for(int i=0; i<str.length(); i++)
			t.add( str.charAt(i) );	
		BoyerMoore testCase1 = new BoyerMoore(t);
		BoyerMoore testCase2 = new BoyerMoore(t);
		
		// Test that the "good" tables generated match those given by Levitin.		
		int result = testCase1.Search( "ABCBAB" ); // Levitin pg.265
		testCase2.Search( "BAOBAB" ); // Levitin pg. 266
		if ( testCase1.goodTable.get(1) == 2 && 
		     testCase1.goodTable.get(2) == 4 &&
		     testCase1.goodTable.get(3) == 4 &&
		     testCase1.goodTable.get(4) == 4 &&
		     testCase1.goodTable.get(5) == 4 &&
		     
		     testCase2.goodTable.get(1) == 2 &&
		     testCase2.goodTable.get(2) == 5 &&
		     testCase2.goodTable.get(3) == 5 &&
		     testCase2.goodTable.get(4) == 5 &&
		     testCase2.goodTable.get(5) == 5 
		   )
		    section2 = true;
		
		// Test that the example that could be "skipped" over
		// from Levitin pg 265-266 doesn't get skipped.
		if ( result == 5 )
		    section3 = true;
			
	    if (section1 && section2)
			System.out.println("BoyerMoore Unit-Test: PASS");
		else
			System.out.println("BoyerMoore Unit-Test: FAIL");
	}

	
	
	
	
	
	
	/* ############################################
	//  ACTUAL RUN IMPLEMENTATIONS
	#############################################*/
	public long speedTestBruteForce(String key, int textIndex, int runs)
	{	    
	    long comps = 0;
	    for (int i=0; i<runs; i++)
	    {
		    BruteForce testCase = new BruteForce( texts.get(textIndex) );
		    if (key == "rand")
		    {
		        long max = texts.get(textIndex).size();
		        int leftOffset = (int)(Math.random() * max);
		        int rightOffset = (leftOffset+50 < max) ? leftOffset+50 : (int)max;
		        List<Character> text = texts.get(textIndex).subList(leftOffset, rightOffset);
		        key = "";
		        for (Character s : text)
                {
                    key += s;
                }
		    }
		    testCase.Search( key );
		    comps += testCase.comparisons;
		}
		return comps;
	}
	
	
	public long speedTestBruteForcePlus1(String key, int textIndex, int runs)
	{	    
	    long comps = 0;
	    for (int i=0; i<runs; i++)
	    {
		    BruteForcePlus1 testCase = new BruteForcePlus1( texts.get(textIndex) );
		    if (key == "rand")
		    {
		        long max = texts.get(textIndex).size();
		        int leftOffset = (int)(Math.random() * max);
		        int rightOffset = (leftOffset+50 < max) ? leftOffset+50 : (int)max;
		        List<Character> text = texts.get(textIndex).subList(leftOffset, rightOffset);
		        key = "";
		        for (Character s : text)
                {
                    key += s;
                }
		    }
		    testCase.Search( key );
		    comps += testCase.comparisons;
		}
		return comps;
	}
	
	
	public long speedTestBruteForceRef(String key, int textIndex, int runs)
	{	    
	    long comps = 0;
	    for (int i=0; i<runs; i++)
	    {
		    BruteForceRef testCase = new BruteForceRef( texts.get(textIndex) );
		    if (key == "rand")
		    {
		        long max = texts.get(textIndex).size();
		        int leftOffset = (int)(Math.random() * max);
		        int rightOffset = (leftOffset+50 < max) ? leftOffset+50 : (int)max;
		        List<Character> text = texts.get(textIndex).subList(leftOffset, rightOffset);
		        key = "";
		        for (Character s : text)
                {
                    key += s;
                }
		    }
		    testCase.Search( key );
		    comps += testCase.comparisons;
		}
		return comps;
	}
	
	public long speedTestHorspool(String key, int textIndex, int runs)
	{	    
	    long comps = 0;
	    for (int i=0; i<runs; i++)
	    {
		    Horspool testCase = new Horspool( texts.get(textIndex) );
		    if (key == "rand")
		    {
		        long max = texts.get(textIndex).size();
		        int leftOffset = (int)(Math.random() * max);
		        int rightOffset = (leftOffset+50 < max) ? leftOffset+50 : (int)max;
		        List<Character> text = texts.get(textIndex).subList(leftOffset, rightOffset);
		        key = "";
		        for (Character s : text)
                {
                    key += s;
                }
		    }
		    testCase.Search( key );
		    comps += testCase.comparisons;
		}
		return comps;
	}
	
	public long speedTestBoyerMoore(String key, int textIndex, int runs)
	{	    
	    long comps = 0;
	    for (int i=0; i<runs; i++)
	    {
		    BoyerMoore testCase = new BoyerMoore( texts.get(textIndex) );
		    if (key == "rand")
		    {
		        long max = texts.get(textIndex).size();
		        int leftOffset = (int)(Math.random() * max);
		        int rightOffset = (leftOffset+50 < max) ? leftOffset+50 : (int)max;
		        List<Character> text = texts.get(textIndex).subList(leftOffset, rightOffset);
		        key = "";
		        for (Character s : text)
                {
                    key += s;
                }
		    }
		    testCase.Search( key );
		    comps += testCase.comparisons;
		}
		return comps;
	}
	
	
	
	
	/* ############################################
	//  ASSERT CASES
	#############################################*/
	private boolean testAssertBruteForce(String key, int textIndex, int expected)
	{
		boolean testPassed = true;
		ArrayList<Character> text = texts.get(textIndex);
		BruteForce testCase = new BruteForce(text);
		int result = testCase.Search( key );
		if (result == expected)
			return true;
		else
			return false;
	}
	
	private boolean testAssertBruteForceComps(String key, int textIndex, int expected)
	{
		boolean testPassed = true;
		ArrayList<Character> text = texts.get(textIndex);
		BruteForce testCase = new BruteForce(text);
		testCase.Search( key );
		int result = (int) testCase.comparisons;
		if (result == expected)
			return true;
		else
			return false;
	}
	
	
	private boolean testAssertHorspool(String key, int textIndex, int expected)
	{
		boolean testPassed = true;
		ArrayList<Character> text = texts.get(textIndex);
		Horspool testCase = new Horspool(text);
		int result = testCase.Search( key );
		if (result == expected)
			return true;
		else
			return false;
	}
	
	private boolean testAssertHorspoolComps(String key, int textIndex, int expected)
	{
		boolean testPassed = true;
		ArrayList<Character> text = texts.get(textIndex);
		Horspool testCase = new Horspool(text);
		testCase.Search( key );
		int result = (int) testCase.comparisons;
		if (result == expected)
			return true;
		else
			return false;
	}
	
	private boolean testAssertBoyerMoore(String key, int textIndex, int expected)
	{
		boolean testPassed = true;
		ArrayList<Character> text = texts.get(textIndex);
		BoyerMoore testCase = new BoyerMoore(text);
		int result = testCase.Search( key );
		if (result == expected)
			return true;
		else
			return false;
	}
	
	private boolean testAssertBoyerMooreComps(String key, int textIndex, int expected)
	{
		boolean testPassed = true;
		ArrayList<Character> text = texts.get(textIndex);
		BoyerMoore testCase = new BoyerMoore(text);
		testCase.Search( key );
		int result = (int) testCase.comparisons;
		if (result == expected)
			return true;
		else
			return false;
	}
	
	
	
	
	/* ############################################
	//  MISC STUFF
	#############################################*/
	public void printJTStuff()
	{
	    /*
	    for (int i=0; i<1000; i++)
            System.out.print( texts.get(1).get(i) );
        System.out.println( " " );
        System.out.println( texts.get(1).size() );
        //System.out.println( GenPermutations.factorial(9) );
        */
        
        // Printing the last elements
        for (int i=texts.get(1).size()-20; i<texts.get(1).size(); i++)
            System.out.print( texts.get(1).get(i) );
        System.out.println( " " );
        
	}
	
	public void printBookStuff()
	{
	    for (int i=0; i<1000; i++)
            System.out.print( texts.get(2).get(i) );
        System.out.println( " " );
	}
	
	public void toFile(ArrayList<Character> text)
	{
	    try {
	        FileWriter writer = new FileWriter("jt.txt"); 
            for(Character str: text) {
              writer.write(str);
            }
            writer.close();
        } catch(IOException e){ e.printStackTrace(); }	
            
	}
	
	public void printHeader(String s)
	{
	    System.out.println(" ");
	    System.out.println("On pattern '" + s + "'");
	    System.out.printf("%-15.15s  %-15.15s %-15.15s %-15.15s %-15.15s\n", 
		    "Algorithm", "Iterations", "Time(ms)", "TotalComps", "AvgComps");
	}
	
	/*
	// ALTERNATE FILE READ METHOD
	try {
	    File fin = new File("book.txt");
        FileInputStream fis = new FileInputStream(fin);
        BufferedReader in = new BufferedReader(new InputStreamReader(fis));
        String aLine = null;
        StringBuffer theText = new StringBuffer((int)fin.length());
        while((aLine = in.readLine()) != null)
        {
            theText.append(aLine);
        }
        book = theText.toString();
    } catch(IOException e){ e.printStackTrace(); }
    */
      
}
