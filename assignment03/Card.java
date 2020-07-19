public class Card {
  private Suit suit;
  private Value value;
  
  public Card (Suit theSuit, Value theValue) {
    this.suit = theSuit;
    this.value = theValue;
  }
  
  public Suit getSuit() {
    return this.suit;
  }
  
  public Value getValue() {
    return this.value;
  }
  
  public String toString() {
    return this.value + " of " + this.suit;
  }
}