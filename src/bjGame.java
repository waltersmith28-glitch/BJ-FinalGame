public class bjGame {
    Card[] deck;

    public bjGame() {
        deck = getShuffledDeck();
        for(int i = 0 ; i < deck.length; i++){
            deck[i].printInfo();
        }
    }

    public Card[] getShuffledDeck() {
        String[] suite = new String[]{"Hearts", "clubs", "diamonds", "spades"};
        String[] name = new String[]{"king", "jack", "queen", "ace", "2", "3", "4", "5", "6", "7", "8", "9", "10"};

        Card[] deck = new Card[52];
        int placeInDeck = 0;

        // Sorted cards
        for (int i = 0; i < suite.length; i++) {
            for (int j = 0; j < name.length; j++) {
                deck[placeInDeck] = new Card(suite[i], name[j]);
                placeInDeck++;
            }
        }

        // shuffle
        for (int a = 0; a < deck.length; a++) {
            int randomInt = (int) (Math.random() * deck.length);
            Card temp = deck[a];
            deck[a] = deck[randomInt];
            deck[randomInt] = temp;
        }
        return deck;
    }

}



        // return}



