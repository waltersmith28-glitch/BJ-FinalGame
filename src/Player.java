public class Player {
    Card[] hand;
    int numOfCards = 0;
    boolean bust = false;


    public Player(Card Card1, Card Card2) {
        hand = new Card[2];
        hand[0] = Card1;
        hand[1] = Card2;
        numOfCards=2;
    }


    public void hit(Card newCard) {
        numOfCards = numOfCards + 1;
        Card[] newHand = new Card[numOfCards];
        for(int i = 0; i < numOfCards-1; i++){
            newHand[i] = hand[i];
        }
        newHand[numOfCards-1] = newCard;

        hand = newHand;
    }

    public void stand() {

    }

    public void printInfo() {
        for (int a = 0; a < numOfCards; a++) {
            System.out.println(hand[a].value + " of " + hand[a].suite);
        }
        System.out.println("=======================");
    }

    public int getHandValue() {
        int sum = 0;
        for(int i = 0; i < hand.length; i++){
            sum = sum + hand[i].value;
        }
        return sum;
    }
}
