//vvvvvvvvvvvvvvvvvvvvvvvvvvvvvv DON'T CHANGE! vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv
// Graphics Libraries
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.sql.SQLOutput;

//^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
public class BasicGameApp implements Runnable, KeyListener {

    //Sets the width and height of the program window
    final int WIDTH = 1000;
    final int HEIGHT = 700;

    //Variable Definition Section
    //You can set their initial values too
    // Like Mario mario = new Mario(); //
    Card[] deck;
    Player user;
    Player dealer;
    int indexInDeck = 0;
    public int currentPlayer = 0;

    // Initialize your variables and construct your program objects here.
    public BasicGameApp() { // BasicGameApp constructor
        setUpGraphics();

        //variable and objects
        //create (construct) the objects needed for the game
        deck = getShuffledDeck();

        user = new Player(deck[indexInDeck],deck[indexInDeck+1]);
        indexInDeck = indexInDeck +2;
        dealer = new Player(deck[indexInDeck],deck[indexInDeck+1]);
        indexInDeck = indexInDeck +2;

        System.out.println("Dealer's cards");
        dealer.printInfo();
        System.out.println("=======================");
        System.out.println("User's cards");
        user.printInfo();
    }
    // end BasicGameApp constructor

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


    public void moveThings() {
        //call the move() code for each object  -

    }

    //Paints things on the screen using bufferStrategy
    private void render() {
        Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();
        g.clearRect(0, 0, WIDTH, HEIGHT);
        g.drawString("Press H to Hit and Press S to Stand",350,350);

        //draw the images
        // Signature: drawImage(Image img, int x, int y, int width, int height, ImageObserver observer)



        // Keep the code below at the end of render()
        g.dispose();
        bufferStrategy.show();
    }














    //XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
//vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv DON'T CHANGE! vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv
    //Declare the variables needed for the graphics
    public JFrame frame;
    public Canvas canvas;
    public JPanel panel;
    public BufferStrategy bufferStrategy;

    // PSVM: This is the code that runs first and automatically
    public static void main(String[] args) {
        BasicGameApp ex = new BasicGameApp();   //creates a new instance of the game
        new Thread(ex).start();                 //creates a threads & starts up the code in the run( ) method
    }

    // main thread
    // this is the code that plays the game after you set things up
    public void run() {
        //for the moment we will loop things forever.
        while (true) {
            moveThings();  //move all the game objects
            render();  // paint the graphics
            pause(10); // sleep for 10 ms
        }
    }
    public void currentPlayerMethod(){
        if(currentPlayer == 0){

        }
    }

    //Pauses or sleeps the computer for the amount specified in milliseconds
    public void pause(int time ) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
        }
    }

    private Image getImage(String filename){
        return Toolkit.getDefaultToolkit().getImage(filename);
    }

    //Graphics setup method
    private void setUpGraphics() {
        frame = new JFrame("Application Template");   //Create the program window or frame.  Names it.

        panel = (JPanel) frame.getContentPane();  //sets up a JPanel which is what goes in the frame
        panel.setPreferredSize(new Dimension(WIDTH, HEIGHT));  //sizes the JPanel
        panel.setLayout(null);   //set the layout

        // creates a canvas which is a blank rectangular area of the screen onto which the application can draw
        // and trap input events (Mouse and Keyboard events)
        canvas = new Canvas();
        canvas.setBounds(0, 0, WIDTH, HEIGHT);
        canvas.setIgnoreRepaint(true);
        canvas.addKeyListener(this);

        panel.add(canvas);  // adds the canvas to the panel.

        // frame operations
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //makes the frame close and exit nicely
        frame.pack();  //adjusts the frame and its contents so the sizes are at their default or larger
        frame.setResizable(false);   //makes it so the frame cannot be resized
        frame.setVisible(true);      //IMPORTANT!!!  if the frame is not set to visible it will not appear on the screen!

        // sets up things so the screen displays images nicely.
        canvas.createBufferStrategy(2);
        bufferStrategy = canvas.getBufferStrategy();
        canvas.requestFocus();
        System.out.println("DONE graphic setup");
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == 72 && currentPlayer == 0 && user.getBust() == false) {
            user.hit(deck[indexInDeck]);
            indexInDeck = indexInDeck + 1;
            user.printInfo();
            //System.out.println("=======================");
            System.out.println(user.getHandValue());
            if (user.getHandValue() > 21) {
                System.out.println("You Bust!");
                user.setBust(true);
                currentPlayer ++;
            }
            else if(user.getHandValue() == 21){
                System.out.println("You got blackjack!");
                currentPlayer++;
            }
        }

        if(e.getKeyCode()==83){
            currentPlayer ++;
        }
        while(dealer.getHandValue() < 17 && currentPlayer == 1){
            System.out.println("Dealer's Cards");
            dealer.hit(deck[indexInDeck]);
            indexInDeck = indexInDeck+1;
            dealer.printInfo();
            //System.out.println("=======================");
            System.out.println(dealer.getHandValue());
        }
        if(dealer.getHandValue() >= 17 && currentPlayer == 1){
            currentPlayer++;
        }
            if(dealer.getHandValue() > 21 && user.getHandValue() <= 21 && currentPlayer == 2){
                System.out.println("Dealer Bust, You Win!");
            }
            else if(user.getBust() == true){
                System.out.println("You lose!");
            }
            else if(user.getHandValue() > dealer.getHandValue() && user.getHandValue() < 21 && currentPlayer == 2){
                System.out.println("You win!");
            }
            else if(dealer.getHandValue() > user.getHandValue() && dealer.getHandValue() <= 21 && currentPlayer == 2){
                System.out.println("You lose!");
            }
            else if(dealer.getHandValue() == user.getHandValue()){
                System.out.println("You push!");
            }

        }



    @Override
    public void keyReleased(KeyEvent e) {

    }
//^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
}

