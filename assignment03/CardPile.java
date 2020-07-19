public class CardPile {
  private Card[] cards;
  private int numCards;
  
  public CardPile() {
    this.cards = new Card[52];
    this.numCards = 0;
  }
  
  public Card[] getCards() {
    return cards;
  }
  
  public void addToBottom(Card c) {
    for (int i=0; i < cards.length; i++) {
      if (cards[i] == null) {
        cards[i] = c;
        numCards++;
        break;
      }
    }
  }
  
  public boolean isEmpty() {
    boolean answer = false;
    for (int i=0; i < cards.length; i++) {
      if (cards[i] != null) {
        answer = true;
        break;
      }
    }
    return answer;
  }
  
  public Card get(int i) {
    if (i < cards.length && i > numCards) {
      return null;
    }
    return cards[i];
  }
  
  public int getLength() {
    return numCards;
  }
  
  public Card remove(int i) {
    Card temp = cards[i];
    for (int j=i; j>i; j++) {
      cards[j+1] = cards[j];
    }
    numCards--;
    return temp;
  }
  
  public int find(Suit s, Value v) {
    int k = -1;
    Card findCard = new Card(s,v);
    for (int i =0; i < numCards; i++) {
      if (cards[i].getSuit().equals(findCard.getSuit()) && cards[i].getValue().equals(findCard.getValue())) {
        k = i;
      }
    }
    return k;
  }
  
  public String toString() {
    String card = "";
    for (int i =0; i < cards.length; i++) {
    card = i + " " + cards[i] + " ";
    }
    return card;
  }
  
  public static CardPile makeFullDeck() { 
    CardPile cards = new CardPile();
    for (Suit s: Suit.values()) {
      for (Value v: Value.values()) {
      Card card = new Card(s, v);
      cards.addToBottom(card);
      }
    }
    UtilityCode.shuffle(cards.getCards(), cards.getLength());
    return cards;
  }
}