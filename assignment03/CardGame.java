public class CardGame {
  public static void main(String[] args) {
    
    CardPile deck = CardPile.makeFullDeck();
    CardPile[] hands = new CardPile[Integer.parseInt(args[0])];
    
    for (int i=0; i < hands.length; i++) {
      hands[i] = new CardPile();
    }

    for (int i=0; i < deck.getLength(); i++) {
      hands[i%hands.length].addToBottom(deck.get(i));
    }
    
    for (int i=0; i < hands.length; i++) {
      int index = hands[i].find(Suit.SPADES, Value.ACE);
      
      if (index != -1) {
        //make Player 0 = Player 1 because normally counting starts at 1
        System.out.println("Player " + (i+1) + " wins."); 
      }
      
    }
    
  }
}