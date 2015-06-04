package Casino;

public class Card {
	
	private String name = null;
	private String kind = null;
	
	public Card(String pName, String pKind){
		
		setName(pName);
		setKind(pKind);
		
	}
	
	public String getName(){
		
		return name;
	}
	
	public String getKind(){
		
		return kind;
	}
	
	public void setName(String pName){
		
		if(validateName(pName)){
			
			name = pName;
		}
	}
	
	public void setKind(String pKind){
		
		if(validateKind(pKind)){
			
			kind= pKind;
		}
	}
	
	public static boolean validateName(String pName){
		
		return (pName == "2" || pName == "3" || pName == "4" || pName == "5" || pName == "6" 
				|| pName == "7" || pName == "8" || pName == "9" || pName == "10" 
				|| pName == "Jack" || pName == "Queen" || pName == "King" || pName == "Ace");
	}
	
	public static boolean validateKind(String pKind){
		
		return (pKind == "Heart" || pKind == "Diamond" || pKind == "Spade" || pKind == "Clover");
	}
	
	
	public String toString(){
		
		String text = name + " " + kind + "\n";
		
		return text;
	}
	
}
