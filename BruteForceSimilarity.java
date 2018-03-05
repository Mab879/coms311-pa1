// LEAVE THIS FILE IN THE DEFAULT PACKAGE
//  (i.e., DO NOT add 'package cs311.pa1;' or similar)

// DO NOT MODIFY THE EXISTING METHOD SIGNATURES
//  (you may, however, add member fields and additional methods)

// DO NOT INCLUDE LIBRARIES OUTSIDE OF THE JAVA STANDARD LIBRARY
//  (i.e., you may include java.util.ArrayList etc. here, but not junit, apache commons, google guava, etc.)

/**
* @author Hugh Potter
*/

public class BruteForceSimilarity
{
	// member fields and other member methods
	public String stringOne;
	public String stringTwo;
	public int shingleLength;

	public BruteForceSimilarity(String s1, String s2, int sLength)
	{
		stringOne = s1;
		stringTwo = s2;
		shingleLength = sLength;
	}

	public float lengthOfS1()
	{
		throw new UnsupportedOperationException();
	}

	public float lengthOfS2()
	{
		throw new UnsupportedOperationException();
	}

	public float similarity()
	{
		throw new UnsupportedOperationException();
	}
}