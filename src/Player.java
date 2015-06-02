import java.util.ArrayList;


public class Player {

	public static final String DEFAULT_NAME = "default guy";
	
	public static final int NAME_MIN_LENGTH = 2;
	
	public static final int NAME_MAX_LENGTH = 15;
	
	public static final int STARTING_MONEY = 500;
	
	private String name = "";
	
	private int cash = 0;
	
	private ArrayList<Card> playerHand;
	
	public Player(String pName, int pStartMoney){
		
		playerHand = new ArrayList<Card>();
		setName(pName);
		setCash(pStartMoney);
		//setPlayerHand(pHand);
	}
	
	public int handTotal(){
		
		int handTotal = 0;
		
		for(int i = 0; i < playerHand.size(); i++){
			
			handTotal += playerHand.get(i).getValue();
		}
		
		return handTotal;
	}
	
	public ArrayList<Card> getPlayerHand(){
		
		return playerHand;
	}
	
	public String getName(){
		
		return name;
	}
	
	public int getCash(){
		
		return cash;
	}
	
	public boolean setName(String pName){
		
		if(validateName(pName)){
			
			name = pName;
		}
		
		return(validateName(pName));
	}
	
	public boolean setCash(int pCash){
		
		if(validateCash(pCash)){
			
			cash = pCash;
		}
		
		return(validateCash(pCash));
	}
	
	public boolean setPlayerHand(Card pHand){
		
		if(validatePlayerHand(pHand)){
			
			playerHand.add(pHand);
		}
		return (validatePlayerHand(pHand));
	}
	
	public static boolean validateName(String pName){
		
		return (pName.length() < NAME_MAX_LENGTH && pName.length() > NAME_MIN_LENGTH);
	}
	
	public boolean validateCash(int pCash){
		
		return (pCash >= 0);
	}
	
	public boolean validatePlayerHand(Card pHand){
		
		return (pHand != null);
	}
}
