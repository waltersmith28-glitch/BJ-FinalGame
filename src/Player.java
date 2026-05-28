public class Player {
    private Card[] hand;
    private int numOfCards = 0;
    private boolean bust = false;


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
            System.out.println(hand[a].getValue() + " of " + hand[a].getSuite());
        }
        System.out.println("=======================");
    }

    public int getHandValue() {
        int sum = 0;
        for(int i = 0; i < hand.length; i++){
            sum = sum + hand[i].getValue();
        }
        return sum;
    }

    public Card[] getHand() {
        return hand;
    }

    public boolean getBust() {
        return bust;
    }
    public void setBust(boolean isBust) {
        bust = isBust;
    }

    public int getNumOfCards() {
        return numOfCards;
    }
}
