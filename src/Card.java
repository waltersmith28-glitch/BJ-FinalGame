public class Card {
    String suite;
    String name;
    int value;

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

    public void printInfo(){
        System.out.println(name + " of " + suite);
    }
}











