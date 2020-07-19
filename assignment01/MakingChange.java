public class MakingChange {
  public static void main(String[] args) {
    System.out.println("Please type in run MakingChange and then your value."); //to instruct user what to do when the program is spewing out red text
    int money = Integer.parseInt(args[0]);
    if(money/200 >= 0)
      System.out.println("number of toonies: " + (money/200));
    int loonie = (money%200); //use the remainder to decide which coin is next
    if(loonie/100 >= 0)
      System.out.println("number of loonies: " + (loonie/100));
    int quarter = (loonie%100);
    if(quarter/25 >= 0)
      System.out.println("number of quarters: " + (quarter/25));
    int dime = (quarter%25);
    if(dime/10 >= 0)
      System.out.println("number of dimes: " + (dime/10));
    int nickel = (dime%10);
    if(nickel/5 >= 0)
      System.out.println("number of nickels: " + (nickel/5));
    int penny = (nickel%5); //remainder has to be number of pennies because each penny is 1 cent
    System.out.println("number of pennies: " + penny);
  }
}
