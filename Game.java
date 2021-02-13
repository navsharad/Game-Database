/**
 * Game Class
 * CIS 22C
 */

public class Game implements Comparable<Game>{
	private String id; //primary key
	private String title;
	private String year; //secondary key
	private String developer;
	private String price;
	private String rating;
	public boolean test;
	
	/**
	 * Default constructor for Game
	 * Assigns the following default values
	 * id: "ID Unknown"
	 * title: "Title Unknown"
	 * year: "Year Unknown"
	 * developer: "Developer Unknown"
	 * price: "Price Unknown"
	 * rating: "Rating Unknown"
	 */
	public Game() {
		id = "ID Unknown";
		title = "Title Unknown";
		year = "Year Unknown";
		developer = "Developer Unkown";
		price = "Price Unknown";
		rating = "Rating Unknown";
	}
	
	/**
	 * One-argument constructor for Game
	 * @param title the title of the game
	 * This constructor is used for both of the keys in the gameDatabase
	 * Year can only be 4 characters long Game IDs are longer than 4 characters 
	 * so if it is greater it assumes a Game ID has been entered
	 */
	public Game(String s) {
		if(s.length() > 4) {
			this.id = s;
			this.title = "Title Unknown";
			this.year = "Year Unknown";
			this.developer = "Developer Unkown";
			this.price = "Price Unknown";
			this.rating = "Rating Unknown";
			test = true;
		} else {
			this.id = "ID Unknown";
			this.title = "Title Unknown";
			this.year = s;
			this.developer = "Developer Unkown";
			this.price = "Price Unknown";
			this.rating = "Rating Unknown";
			test = false;
		}
			
	}
	
	/**
	 * Two-argument constructor for Game
	 * @param title the title of the game
	 * @param year the release year
	 */
	public Game(String title, String year) {
		this.id = "ID Unknown";
		this.title = title;
		this.year = year;
		this.developer = "Developer Unknown";
		this.price = "Price Unknown";
		this.rating = "Rating Unknown";
	}
	

	
	/**
	 * Multi-argument constructor for Game
	 * @param id the id of the game
	 * @param title the title of the game
	 * @param year the release year
	 * @param developer the developer of the game
	 * @param price the price of the game
	 * @param rating the critical rating out of 100
	 */
	public Game(String id, String title, String year, String developer, String price, String rating) {
		this.id = id;
		this.title = title;
		this.year = year;
		this.developer = developer;
		this.price = price;
		this.rating = rating;
		test = true;
	}
	
	/**
	 * Multi-argument constructor for Game
	 * @param id the id of the game
	 * @param title the title of the game
	 * @param year the release year
	 * @param developer the developer of the game
	 * @param price the price of the game
	 * @param rating the critical rating out of 100
	 * @param dummy variable used for the secondary bst in the database
	 */
	public Game(String id, String title, String year, String developer, String price, String rating, boolean b) {
		this.id = id;
		this.title = title;
		this.year = year;
		this.developer = developer;
		this.price = price;
		this.rating = rating;
		test = false;
	}
	
	/**
	 * Creates a new game that is a copy of g
	 * Except id is incremented by 0
	 * @param g another game
	 */
	public Game(Game g) {
		this.id = "ID Unknown";
		this.title = g.title;
		this.year = g.year;
		this.developer = g.developer;
		this.price = g.price;
		this.rating = g.rating;
	}
	
	/**
	 * Accesses the ID of game
	 * @return the id of the game
	 */
	public String getID() {
		return id;
	}
	
	/**
	 * Accesses the title of the game
	 * @return the title of the game
	 */
	public String getTitle() {
		return title;
	}
	
	/**
	 * Accesses the release year of the game
	 * @return the release year of the game
	 */
	public String getYear() {
		return year;
	}
	
	/**
	 * Accesses the developer of the game
	 * @return the developer of the game
	 */
	public String getDeveloper() { 
		return developer;
	}
	
	/**
	 * Accesses the price of the game
	 * @return the price of the game
	 */
	public String getPrice() {
		return price;
	}
	
	/**
	 * Accesses the critical rating of the game
	 * @return the critical rating of the game
	 */
	public String getRating() {
		return rating;
	}
	
	/**
	 * Assigns a new ID for the game
	 * @param id the id of the game
	 */
	public void setID(String id) {
		this.id = id;
	}
	
	/**
	 * Assigns a new title for the game
	 * @param title title of the game
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	
	/**
	 * Assigns a new release year for the game
	 * @param year release year of the game
	 */
	public void setYear(String year) {
		this.year = year;
	}
	
	/**
	 * Assigns a new developer for the game
	 * @param developer developer for the game
	 */
	public void setDeveloper(String developer) {
		this.developer = developer;
	}
	
	/**
	 * Assigns a new price for the game
	 * @param price price of the game
	 */
	public void setPrice (String price) {
		this.price = price;
	}
	
	/**
	 * Assigns a new critical rating for the game
	 * @param rating critical rating of the game
	 */
	public void setRating(String rating) {
		this.rating = rating;
	}
	
	
	/**
	 * Creates a String of game information
	 * ID: <id>
	 * Title: <title>
	 * Year: <year>
	 * Developer: <developer>
	 * Price: <price>
	 * Rating: <rating>
	 * @return the game information
	 */
	@Override public String toString() {
		String result =  id
				+ "\n" + title
				+ "\n" + year
				+ "\n" + developer
				+ "\n" + price
				+ "\n" + rating + "\n";
		return result;
	}
	
	
    /**
    * Returns whether two Game objects
    * Have the same components (only)
    */
    @Override public boolean equals(Object o) {
        if(o == this) {
        	return true;
        } else if(!(o instanceof Game)) {
        	return false;
        } else {
        	Game Cmp = (Game)o;
        	if(!(Cmp.id == this.id || Cmp.title == this.title || 
        	   Cmp.year == this.year || Cmp.developer == this.developer ||
        	   Cmp.price == this.price || Cmp.rating == this.rating)) {
        		return false;
        }
        	return true;
    
        }
    }
	
    /**
     * Returns a consistent hash code for each
     * Game by summing the unicode values of each
     * character in the key
     * key = id
     * @return the hash code
     */
    @Override public int hashCode() {
    	String key = id;
    	int sum = 0;
    	for (int i = 0; i < key.length(); i++) {
    		sum += (int) key.charAt(i);
    	}
    	return sum;
    }
	
    /**
     * Compares two Game objects
     * Returns 0 if the two Game objects are equal
     * Otherwise, if the two games are different, returns
     * the String compareTo value of this.title compare to g.title
     * Otherwise, returns the compareTovalue of this.id compared to g.id
     */
    @Override public int compareTo(Game g) {
    	if(test == false) {
    		if(year.compareTo(g.year) != 0) {
    			return year.compareTo(g.year);
    	}
    	} else if(test == true) {
    	if(id.compareTo(g.id) != 0) {
    		return id.compareTo(g.id);
    	} 
    	}
    	return 0;
    }
	
	
}