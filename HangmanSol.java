import java.util.Scanner;

public class HangmanSol {
    public static String[] words = {"ant", "baboon", "badger", "bat", "bear","beaver", "camel", "cat", "clam", "cobra", "cougar", "coyote", "crow", "deer", "dog", "donkey", "duck", "eagle", "ferret", "fox", "frog", "goat", "goose", "hawk", "lion", "lizard", "llama", "mole", "monkey", "moose", "mouse", "mule", "newt", "otter", "owl", "panda", "parrot", "pigeon", "python", "rabbit", "ram", "rat", "raven","rhino", "salmon", "seal", "shark", "sheep", "skunk", "sloth", "snake", "spider", "stork", "swan", "tiger", "toad", "trout", "turkey","turtle", "weasel", "whale", "wolf", "wombat", "zebra"};
    public static String[] gallows = {
        "+---+\n" +
        "|   |\n" +
        "    |\n" +
        "    |\n" +   //if the user didn't miss any guesses.
        "    |\n" +
        "    |\n" +
        "=========\n",
    
        "+---+\n" +
        "|   |\n" +
        "O   |\n" +   //if the user missed one guess.
        "    |\n" +
        "    |\n" +
        "    |\n" +
        "=========\n",
    
        "+---+\n" +
        "|   |\n" +
        "O   |\n" +    //if the user missed two guesses.
        "|   |\n" +
        "    |\n" +
        "    |\n" +
        "=========\n",
    
        " +---+\n" +
        " |   |\n" +
        " O   |\n" +   //if the user missed three guesses.
        "/|   |\n" +
        "     |\n" +
        "     |\n" +
        " =========\n",
    
        " +---+\n" +
        " |   |\n" +
        " O   |\n" +
        "/|\\  |\n"+   //if the user missed four guesses.
        "     |\n" +
        "     |\n" +
        " =========\n",
    
        " +---+\n" +
        " |   |\n" +
        " O   |\n" +
        "/|\\  |\n" +  //if the user missed five guesses.
        "/    |\n" +
        "     |\n" +
        " =========\n",
    
        " +---+\n" +
        " |   |\n" +
        " O   |\n" +
        "/|\\  |\n" +   //if the user missed six guesses.
        "/ \\  |\n" +
        "     |\n" +
        " =========\n"};
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String randWord = "sheep";
        
        int misses = 0;
        String misssedGuesses = "";
        int hits = 0;
        System.out.println("\nLets play Hangman !!\n");
        char[] placeHolder = new char[randWord.length()];
        for(int i=0;i<placeHolder.length;i++){
            placeHolder[i] = '_';
        }
        char c;
        while(misses <= 6){
            System.out.println(gallows[misses]);
            if(misses == 6){
                System.out.println();
                System.out.println("RIP!");
                System.out.println("\nThe word was: '"+randWord+"'\n");
                System.exit(0);
            }
            System.out.print("Word:\t");
            for(int i=0;i<placeHolder.length;i++){
                System.out.print(placeHolder[i]+" ");
            }
            System.out.print("\n\n");
            if(hits == randWord.length()){
                System.out.println("GREAT WORK !!\n");
                System.exit(0);
            }
            System.out.println("Misses:\t"+misssedGuesses+"\n");
            System.out.print("Guess:\t");
            c = scan.next().charAt(0);
            System.out.println("--------------------------------");
            int i = checkGuess(randWord,placeHolder,c);
            if(i != -1){
                updatePlaceHolder(placeHolder, i, randWord);
                hits++;
            }else{
                misses++;
                misssedGuesses += c;
            }
        }
    }
    public static String randomWord() {
        int n = words.length;
        return words[(int)(Math.random()*n)];
    }
    public static int checkGuess(String word,char[] placeHolder,char c) {
        for(int i=0;i<word.length();i++){
            if(word.charAt(i)==c && placeHolder[i]=='_'){
                return i;
            }
        }

        return -1;
    }

    public static void updatePlaceHolder(char[] placeHolder,int i,String randWord) {
        placeHolder[i] = randWord.charAt(i);
    }
}
