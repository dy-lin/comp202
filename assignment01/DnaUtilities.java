public class DnaUtilities {
  public static boolean isValidBase(char base) {
    if(base == 'A'  || base == 'C' || base == 'G' || base == 'T') { //base can be any of the four so use "or"
      return true;
    } else {
      return false;
    }
  }
  public static void main(String[] args) {
    System.out.println(isValidBase('F')); //invalid base
    System.out.println(isValidBase('c')); //invalid base
    System.out.println(isValidBase('A')); //should be valid base
    System.out.println(isValidBase('3')); //invalid bae
    System.out.println(watsonCrickComplement('C')); //test if complement is given
    System.out.println(watsonCrickComplement('2')); //invalid base, should return 2
    System.out.println(watsonCrickComplement('e')); //invalid base, should return e
    System.out.println(watsonCrickComplement('T')); //vaid bae, should return A
    System.out.println(watsonCrickTripletComplement("CGC")); //length is 3, all valid baes, should return GCG
    System.out.println(watsonCrickTripletComplement("DDDD")); //should return empty string because length is not 3 and not valid bases
    System.out.println(watsonCrickTripletComplement("aCa")); //should return empty string because lowercase a is invalid base
  }
  public static char watsonCrickComplement(char base) {
    boolean validOrNot = isValidBase('C'); //if valid then gives complement, if not then gives original
    if(validOrNot == true && base == 'A') {
      return 'T';
    } else if(validOrNot == true && base == 'C') {
      return 'G';
    } else if(validOrNot == true && base == 'G') {
      return 'C';
    } else if(validOrNot == true && base == 'T') {
      return 'A';
    } else { //cannot end with an "else if" or else gives "missing return statement error"
      return base;
    }
  }
  public static String watsonCrickTripletComplement(String dnaSequence) {
    int length = dnaSequence.length();
    char ltrOne = dnaSequence.charAt(0); //gives first base
    char ltrTwo = dnaSequence.charAt(1); //gives second base
    char ltrThree = dnaSequence.charAt(2); //gives third base
    boolean codon0 = isValidBase(ltrOne); //runs previous method to check validity
    boolean codon1 = isValidBase(ltrTwo);
    boolean codon2 = isValidBase(ltrThree);
      if(length != 3) {
      return "";
      } else if(codon0 == false || codon1 == false || codon2 == false) { //invalid bases
        return "";
      } else {
      char base1 = watsonCrickComplement(ltrOne);
      char base2 = watsonCrickComplement(ltrTwo);
      char base3 = watsonCrickComplement(ltrThree);
      String finalCodon = ""; //use empty string to create string with characters
      finalCodon = finalCodon + base1 + base2 + base3; //add on characters to make a string
      return finalCodon;
    }
  }
}