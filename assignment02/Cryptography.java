//Diana Lin -- Section 001 -- Assignment 2
import java.util.Arrays;
import java.util.Random;
public class Cryptography {
  public static void main(String[] args) {
    System.out.println(caesarEncrypt("hello", 3)); //Question 1
    System.out.println(caesarDecrypt("khoor", 3)); //Question 2
    System.out.println(crackCipher("cat", 3)); //Question 3
    char[] array = {'A', 'B', 'C', 'D', 'E', 'F'}; //initialization for Question 4
    shuffle(array); //Question 4 method call
    System.out.println(Arrays.toString(array)); //Question 5
    System.out.println(permuteEncrypt("dog")); //Question 6
  }
  public static String caesarEncrypt(String originalMessage, int shift) {
    int length = originalMessage.length(); 
    int counter = 0;
    String newMessage = "";
    while (counter < length) {
      char letter = originalMessage.charAt(counter);
      char character = (char) (letter + shift); //type casting of the whole addition fixes the compiler error (discussion board reply)
      //int unicode = (int) letter + shift; had to go from char > unicode > char because of compiler error (loss of precision)
      //char character = (char) unicode;
      newMessage = newMessage + character;
      counter++;
    }
  return newMessage; //want to return newMessage but it is out of scope;
  }
   public static String caesarDecrypt(String encoded, int shift) {
    int length = encoded.length();
    int counter = 0;
    String decoded = "";
    while (counter < length) {
      char letter = encoded.charAt(counter);
      char character = (char) (letter - shift);
      //int unicode = (int) letter - shift; same case as above [solved]
      //char character = (char) unicode;
      decoded = decoded + character;
      counter++;
    }
    return decoded;
   }
  public static String crackCipher(String encoded, int numberLetters) {
    String[] finalWords = new String[numberLetters];
    for (int i = 0; i < numberLetters; i++) {
     finalWords[i]= caesarDecrypt(encoded, i);
    }
    int[] number = new int[numberLetters];
    //System.out.println(Arrays.toString(finalWords)); //just for me to see if it has been put in an array
     for (int i = 0; i < numberLetters; i++) {
     number[i] = SentenceChecker.countEnglishWords(finalWords[i]);
     }
     //System.out.println(Arrays.toString(number)); //just for me to see if it has been put in an array
     int largest = Integer.MIN_VALUE;
     String largestWord = "";
     //in the case of a tie, this method takes the FIRST String with the greatest number of English words
     //assignment says just to pick one to be the greatest--I pick first.
     //if the LAST String with the greatest number of English words is needed, make it number[i] >= largest
     for (int i = 0; i < number.length; i++) {
        if (number[i] > largest) {   
          largest = number[i];
          largestWord = finalWords[i];
        }
     }
     return largestWord;
  }
  public static void shuffle(char[] array) {
    Random generator = new Random(12345);
    int n = array.length;
    for (int i = 0; i < Math.pow(n,4); i++) {
      int number1 = generator.nextInt(n);
      int number2 = generator.nextInt(n);
      char temp;
      temp = array[number2];
      array[number2] = array[number1];
      array[number1] = temp;
    }
  }
  public static char[] generatePermutation() {
    char[] array = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
    shuffle(array);
    return array;
  }
  public static String permuteEncrypt(String input) { 
    /*had a hard time interpreting this question
    had to ask someone at the CSUS helpdesk what the objective was
    interpreted as take the input String and change every letter to the same place they are in the alphabet 
    but with the permutated array*/
    String encryptedPermutation = "";
    char[] array = generatePermutation();
    System.out.println(Arrays.toString(array)); //for me to see the shuffled array
    for (int i = 0; i < input.length(); i++) {
      input = input.toUpperCase(); //makes all letters lowercase to uppercase
      char inputLetter = input.charAt(i);
      char arrayLetter = array[inputLetter -'A']; //make baseline A 
      inputLetter = arrayLetter;
      encryptedPermutation = encryptedPermutation + inputLetter;
    }
    return encryptedPermutation; //e.g. D is in the 3rd place in alphabet, should return G since G is 3rd in array
  }
}

 
      

    