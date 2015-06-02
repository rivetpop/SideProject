import java.util.ArrayList;


public class Dealer {
	
	private String name = "Garry";
	
	ArrayList<Card> dealerHand;
	
	public Dealer(String pName){
		
		dealerHand = new ArrayList<Card>();
		name = pName;
	}
	
	public int handTotal(){
		
		int handTotal = 0;
		
		for(int i = 0; i < dealerHand.size(); i++){
			
			handTotal += dealerHand.get(i).getValue();
		}
		
		return handTotal;
	}
	
	public String getName(){
		
		return name;
	}
	
	public ArrayList<Card> getDealerHand(){
			
		return dealerHand;
	}
	
	public boolean setName(String pName){
		
		if(validateName(pName)){
			
			name = pName;
		}
		
		return (validateName(pName));
	}
	
	public boolean setDealerHand(Card pHand){
		
		if(validateDealerHand(pHand)){
			
			dealerHand.add(pHand);
		}
		
		return (validateDealerHand(pHand));
	}
	
	public boolean validateName(String pName){
		
		return (pName != null);
	}
	
	public boolean validateDealerHand(Card pHand){
		
		return (pHand != null);
	}
	
	public String toString(){
		
		return this.getName();
	}
}
