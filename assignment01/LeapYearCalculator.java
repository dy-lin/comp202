public class LeapYearCalculator {
  public static void main(String[] args) {
    printIsLeapYear(1000); //should print NOT a leap year
    printIsLeapYear(4);// should print IS a leap year
    System.out.println(isLeapYear(1996)); //prints true or false depending on leap year or not
    System.out.println(subsequentLeapYear(2005)); //should print 2008 as next leapYear
    System.out.println(subsequentLeapYear(2000)); //should print 2004 as next Leap Year
    
  }
  public static void printIsLeapYear(int year) {
    if(year%100 != 0 && year%4 == 0) //condition all years except turn of the century divisble by 4 are all leap years
      System.out.println(year + " is a leap year.");
    if(year%100 !=0 && year%4 != 0) //condition all years except turn of the century not divisble by 4 are NOT leap years
      System.out.println(year + " is NOT a leap year.");
    if(year%100 == 0 && year%400 == 0) //condition of turns of century must also be divisble by 400 to be a leap year
      System.out.println(year + " is a leap year.");
    if(year%100 == 0 && year%400 != 0) //condition turns of century are NOT leap years if not divisible by 400
      System.out.println(year + " is NOT a leap year.");
  }
  public static boolean isLeapYear(int year) {
    if((year%100 !=0 && year %4 ==0) || (year%100 == 0 && year%400 == 0)) { //all leap year conditions
      return true;
    } else {
      return false;
    }
  }
  public static int subsequentLeapYear(int year) { //if year = leap year, return next leap year. if year = non-leap year, return next leap year? or return input year?
    boolean leapYear = isLeapYear(year);
    if (leapYear == true) {
      return (year+4);
    } else {
      int nextYear = ((year/4) + 1)*4; //integer division always rounds down, but I want the next leap year not the previous one, so add one
      return nextYear; 
    }
  }
}