public class Card {
    private String suite;
    private String name;
    private int value;

    public Card (String suiteInput, String nameInput){
        suite = suiteInput;
        name = nameInput;
        if(name == "jack") {
            value = 10;
        }
        else if(name == "king") {
            value = 10;
        }
        else if(name == "queen") {
            value = 10;
        }
        else if(name == "ace") {
            value = 1;
        }
        else{
            value = Integer.parseInt(name);
        }
    }

    public int getValue(){
        return value;
    }

    public String getSuite() {
        return suite;
    }

    public void printInfo(){
        System.out.println(name + " of " + suite);
    }
}











