import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class GameDatabase {
    public static void main(String[] args) throws IOException{
        HashTable<Game> ht = new HashTable<Game>(50); 
        BST<Game> bst = new BST<Game>();
        BST<Game> bst2 = new BST<Game>();
        Scanner input = new Scanner(new File("games.txt"));
        
        
        Game b, b2;
        String title, developer, ID, price, outputFilename, rating, year, userChoice;
        Scanner Userinput = new Scanner(System.in);
        boolean ValidChoice = false;
        
        while(input.hasNextLine())
        {
        	ID = input.nextLine();
        	title = input.nextLine();
        	year = input.nextLine();
        	developer = input.nextLine();
        	price = input.nextLine();
        	rating = input.nextLine();
        	b = new Game(ID, title, year, developer, price, rating);
        	b2 = new Game(ID, title, year, developer, price, rating, true);
        	ht.insert(b);
        	bst.insert(b);
        	bst2.insert(b2);
        }
        
        System.out.println("Welcome to Steam's Video-Game Database!");
        System.out.println();
        while(ValidChoice == false) {
        System.out.println("Please select from one of the following options:");
        System.out.println();        
        System.out.println("A. Add a Game");
        System.out.println("R. Remove a Game");
        System.out.println("S. Search for a Game");
        System.out.println("L. List of Games");
        System.out.println("W. Write to file");
        System.out.println("X. Exit");
        
        System.out.println();        
        System.out.print("Enter your choice: ");
        userChoice = Userinput.next();
        Userinput.nextLine();
    	System.out.println("");
        
        if(userChoice.equalsIgnoreCase("A"))
        {
        	System.out.println("Adding a game!\n");

            System.out.print("Enter the title: ");
            title = Userinput.nextLine();
            
            System.out.print("Enter the ID: ");
            ID = Userinput.nextLine();
            
            System.out.print("Enter the rating: ");
            rating = Userinput.nextLine();
            
            System.out.print("Enter the year released: ");
            year = Userinput.nextLine();
            
            System.out.print("Enter the developer: ");
            developer = Userinput.nextLine();
            
            System.out.print("Enter the price: ");
            price = Userinput.nextLine();
            b = new Game(ID, title, year, developer, price, rating);
            b2 = new Game(ID, title, year, developer, price, rating, true);
            bst.insert(b);
            bst2.insert(b2);
            ht.insert(b);
            System.out.println("");
            System.out.println(title + ": " + ID + " was added!\n");

        }
        
        else if(userChoice.equalsIgnoreCase("L")) //List out games
        {
            System.out.println("U. List unsorted data");
            System.out.println("P. List data by ID (Primary Key)");
            System.out.println("S. List data sorted by year (secondary key)");
            System.out.print("Enter your choice: ");
            userChoice = Userinput.next();
            Userinput.nextLine();
            
            if (userChoice.equalsIgnoreCase("U")) {
            	System.out.println("For all games listed the order is as follows: ");
            	System.out.println("1. ID\n2. Title\n3. Year Released\n4. Developer\n5. Price\n6. Rating\n");
            	ht.printAll();
            } else if (userChoice.equalsIgnoreCase("P")) {
            	System.out.println("For all games listed the order is as follows: ");
            	System.out.println("1. ID\n2. Title\n3. Year Released\n4. Developer\n5. Price\n6. Rating\n");
            	bst.inOrderPrint();
            } else if (userChoice.equalsIgnoreCase("S")) {
            	System.out.println("For all games listed the order is as follows: ");
            	System.out.println("1. ID\n2. Title\n3. Year Released\n4. Developer\n5. Price\n6. Rating\n");
            	bst2.inOrderPrint();
            }
        }
        
        else if(userChoice.equalsIgnoreCase("R")) //remove
        {
        	System.out.print("Enter the ID: ");
        	ID = Userinput.nextLine();
        	b = new Game(ID);
        	System.out.println();        	
        	
        	try {
        	
        	bst.remove(b);
        	bst2.remove(b);
        	System.out.println("Game: " + ID + " has been removed!");
        	}
        	catch(NoSuchElementException e){
            	System.out.println("Game: " + ID + " is not in the database.");
        	}
        	
        }
        
        else if(userChoice.equalsIgnoreCase("S")) // Need to add sub-menu
        {
        	int option = 0;
        	while(option != 1 || option != 2) {
        	System.out.println("Searching for a game!");
        	System.out.println("");
        	System.out.println("Pick an option: ");
        	System.out.println("1. Search by ID ");
        	System.out.println("2. Search by Year ");
        	option = Userinput.nextInt();
        	Userinput.nextLine();
        	
        	if(option == 1) {
        		System.out.print("Enter the ID: ");
        		ID = Userinput.nextLine();
        		b = new Game(ID);
        		if(bst.search(b) == false)
        			System.out.println("Sorry! There are no games with the ID: " + ID + "\n");
        		else {
        			System.out.println("The game with ID: " + ID + " is in the database!\n");
                	System.out.println("For all games listed the order is as follows: ");
                	System.out.println("1. ID\n2. Title\n3. Year Released\n4. Developer\n5. Price\n6. Rating\n");
        			bst.searchPrint(b);
        		}
        		break;
        			
        	}
        	else if(option == 2) {
        		System.out.print("Enter the year: ");
        		year = Userinput.nextLine();
        		b = new Game(year);
        		if(bst2.search(b) == false)
        			System.out.println("\nSorry! There are no games made in the year " + year + "\n");
        		else 
        			System.out.println("\nHere are the games made in " + year + ":\n");
        			bst2.searchPrint(b);
        		break;
        			
        	}
        	else System.out.println("Error: Invalid Option, try again.\n");
        }
        }
        
        else if(userChoice.equalsIgnoreCase("W"))
        {
        	System.out.println("Please enter a name for the output file (include a .txt at the end of the name): ");
        	outputFilename = Userinput.next();
        	bst.inOrderPrintToFile(outputFilename); //BST method to write to file. 
        	System.out.println("The data has been written to the file: " + outputFilename + "\n");
        }
        
        else if(userChoice.equalsIgnoreCase("X")) //exit
        {
        	System.out.println("Goodbye! The updated database has been saved to a file: updatedGames.txt");
        	bst.inOrderPrintToFile("updatedGames.txt");
        	System.exit(1);
        }
        
        else { //invalid choice
        	ValidChoice = false;
        	System.out.println("Invalid Selection!");
        	System.out.println("");
        	
        }
       }
        input.close();
        Userinput.close();
    }
}
