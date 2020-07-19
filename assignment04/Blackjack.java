import java.util.ArrayList;
import java.util.Scanner;
public class Blackjack {
  public enum Results {
  DEALER_WINS,
    PLAYER_WINS,
    TIE,
    BLACKJACK,
    INVALID_INPUT //default return for playRound
  }
  public static void main(String[] args) {
    int numChips = Integer.parseInt(args[0]);
    CardPile deck = CardPile.makeFullDeck(4);
    int deckSize = deck.getCards().size();
    while (deckSize > 10) {
      System.out.println("How many chips would you like to bet? Enter a negative number if you would like to stop playing.");
      Scanner reader = new Scanner(System.in);
      int bet = reader.nextInt();
      //initial check for chip balance
      if (numChips <= 0) {
        System.out.println("Insufficient funds!");
        break;
      }
      if (bet < 0) {
        System.out.println("Leaving game now.");
        break;
      }
      if (bet > numChips) {
        System.out.println("Not enough chips!");
      }
      if (bet <= numChips) {
        if (bet == numChips) {
          System.out.println("You are betting all of your chips. GOOD LUCK!");
          System.out.println();
        }
        Results game = playRound(deck);
        System.out.println(game);
        System.out.println();
        if (game == Results.PLAYER_WINS) {
          numChips = numChips + bet;
        } else if (game == Results.DEALER_WINS) {
          numChips = numChips - bet;
        } else if (game == Results.BLACKJACK) {
          numChips = (int) (numChips*1.5);
        } else if (game == Results.INVALID_INPUT) {
          System.out.println("Another round will begin. Please input a valid move this time!");
          System.out.println();
        }
      }
      if (bet < 0 || numChips < 0) {
        break;
      }
      if (numChips <= 0) { //final check of chip balance
        System.out.println("You have no chips left!");
        break;
      } else if (numChips > 0) {
        System.out.println("Number of Chips Left: " + numChips);
      }
    }
    System.out.println("GAME OVER.");
  }
  
  //input Card object and return score in blackjack
  public static int getScore(Card card) {
    int score = 0;
    if (card.getValue() == Value.values()[12]) {
      score = 11;
    }
    for (int i=0; i < 8; i++) {
      if (card.getValue() == Value.values()[i]) {
          score = i+2;
      } 
    }
    for (int i=8; i<12; i++) {
      if (card.getValue() == Value.values()[i]) {
        score = 10;
      }
    }
    return score;
  }
  
  //adds the values of each card together
  public static int totalValue(CardPile deck) {
    int sum = 0;
    ArrayList<Integer> score = new ArrayList<Integer>();
    for(int i=0; i < deck.getCards().size(); i++) {
      score.add(getScore(deck.getCards().get(i)));
    }
    for(int i=0; i < score.size();  i++) {
      sum = sum + score.get(i);
    }
    
    return sum;
  }
  //score of each hand with adjustment of value of ace if necessary
   public static int countValues(CardPile deck) {
     int score = totalValue(deck);
     for (int i=0; i < deck.getCards().size(); i++) {
       if (score > 21 && deck.get(i).getValue() == Value.ACE) {
         score = score-10;
       }
     }
     return score;  
 }
  
  //deals and plays for one round of Blackjack
  public static Results playRound(CardPile deck) {
    Results result = Results.INVALID_INPUT; //default (extra enum that I added) to avoid "missing return statement"
    CardPile[] hands = new CardPile[2]; //only one player vs dealer
    for (int i=0; i < hands.length; i++) {
      hands[i] = new CardPile();
    }
    //deal two cards to player and dealer each 
    for (int i=0; i < 2; i++) { //this loop ensures that each person is dealt two card twice
      for (int j=0; j < 2; j++) { //this loop deals one card to each person
        hands[j].getCards().add(deck.remove(0)); //should always be top of the deck
      }
    }
    //blackjack case, hands[0] = player, hands[1] = dealer
    //at this point each ONLY have two cards
    if (countValues(hands[0]) == 21 && countValues(hands[1]) ==21) {
      System.out.println();
      printsAll(hands);
      System.out.println("Results:");
      System.out.println("Both you and the dealer have BLACKJACK. TIE!");
      return Results.TIE;
    }
    if (countValues(hands[0]) == 21 && countValues(hands[1]) != 21) {
      System.out.println();
      printsAll(hands);
      System.out.println("Results:");
      return Results.BLACKJACK;
    }
    if (countValues(hands[0]) > 21) {
      System.out.println();
      System.out.println("Your hand: " + hands[0].getCards());
      System.out.println("Your score: " + countValues(hands[0]));
      System.out.println("Results:");
      System.out.println("BUSTED!");
      return Results.DEALER_WINS;
    }
    if (countValues(hands[1]) > 21) {
      System.out.println();
      System.out.println("FINAL results:");
      printsAll(hands);
      System.out.println("Dealer's BUSTED!");
      return Results.PLAYER_WINS;
    }
    if (countValues(hands[0]) < 21 && countValues(hands[1]) < 21) {
      System.out.println("Your hand: "+ hands[0].getCards());
      System.out.println("Score: " + countValues(hands[0]));
      System.out.println("Dealer's hand: HIDDEN, " + hands[1].get(1)); //only prints second card of dealer to show player
      Scanner player = new Scanner(System.in);
      System.out.println("HIT or STAY?");
      String playerMove = player.next();
      while (playerMove.equalsIgnoreCase("Hit")) {
        playerMove = hit(hands[0], deck);
        if (playerMove.equalsIgnoreCase("lost")) {
          return Results.DEALER_WINS;
        }
        if (playerMove.equalsIgnoreCase("blackjack")) {
          playerMove = "Stay";
        }
      }
      if (playerMove.equalsIgnoreCase("Stay")) {
        System.out.println("Dealer's turn.");
        while (countValues(hands[1]) < 18) {
         System.out.println("Dealer HITS.");
         hands[1].getCards().add(deck.remove(0));
         System.out.print("Dealer's hand: HIDDEN, ");
         for (int i =1; i < hands[1].getCards().size()-1; i++) {
           System.out.print(hands[1].get(i) + ", ");
         }
         System.out.println(hands[1].get(hands[1].getCards().size()-1));
        }
        if (countValues(hands[1]) > 21) {
         System.out.println();
         System.out.println("FINAL results: ");
         printsAll(hands);
         System.out.println("Dealer's BUSTED!");
         return Results.PLAYER_WINS;
        }
        if (countValues(hands[1]) >= 18 && countValues(hands[1]) <= 21) {
          if (countValues(hands[1]) != 21) {
            System.out.println("Dealer STAYS.");
          }
          System.out.println();
          System.out.println("FINAL results:");
          if (countValues(hands[0]) > countValues(hands[1])) {
            printsAll(hands);
            return Results.PLAYER_WINS;
          } else if (countValues(hands[0]) == countValues(hands[1])) {
            printsAll(hands);
            return Results.TIE;
          } else {
            printsAll(hands);
            return Results.DEALER_WINS;
          }       
        }
      }
    }
    return result;
  }
  
  //when the player wants a hit, new method to make it easier in a loop
  public static String hit(CardPile playerHand, CardPile deck) {
    String playerMove = "";
    playerHand.getCards().add(deck.remove(0)); //from top of the deck
    System.out.println("Your hand: " + playerHand.getCards());
    System.out.println("Your score: " + countValues(playerHand));
    if (countValues(playerHand) > 21) {
      System.out.println();
      System.out.println("FINAL results:");
      System.out.println("BUSTED!");
      playerMove = "lost";
    } else if(countValues(playerHand) == 21) {
      playerMove = "blackjack";
    } else {
      System.out.println("HIT or STAY?");
      Scanner player = new Scanner(System.in);
      playerMove = player.next();
    }
    return playerMove;
  }
  
  //prints hands of dealer and player with score, cleans up code above
  public static void printsAll(CardPile[] hands) {
    System.out.println("Your hand: " + hands[0].getCards());
    System.out.println("Your score: " + countValues(hands[0]));
    System.out.println("Dealer's hand: " + hands[1].getCards());
    System.out.println("Dealer's score: " + countValues(hands[1]));
  }
}
  