package Casino;

import javafx.scene.image.Image;

public class Card {
	
	private int name;
	private String kind = null;
	private Image cardImage = null;
 
	//Card constructor
	public Card(int pName, int pKind, Image cardImage){
		
		setName(pName);
		setKind(convertKind(pKind));
		setImage(cardImage);
	}
	
	private String convertKind(int pKind){
		
		switch(pKind){
		
		case 0:
			return "Heart";
		
		case 1:
			return "Diamond";
		
		case 2:
			return "Spade";
		
		case 3:
			return "Clover";
		
		default:
			return null;
		
		}
	}
	
	public int getName(){
		
		return name;
	}
	
	public String getKind(){
		
		return kind;
	}
	
	public Image getImage(){
		
		return cardImage;
	}
	
	public void setName(int pName){
		
		if(validateName(pName)){
			
			name = pName;
		}
	}
	
	public void setKind(String pKind){
		
		if(validateKind(pKind)){
			
			kind = pKind;
		}
	}
	
	public void setImage(Image pCardImage){
			
		cardImage = pCardImage;
		
	}
	
	public static boolean validateName(int pName){
		
		return (pName == 2 || pName == 3 || pName == 4 || pName == 5 || pName == 6 
				|| pName == 7 || pName == 8 || pName == 9 || pName == 10 
				|| pName == 11 || pName == 12 || pName == 13 || pName == 14);
	}
	
	public static boolean validateKind(String pKind){
		
		return (pKind == "Heart" || pKind == "Diamond" || pKind == "Spade" || pKind == "Clover");
	}
	
	
	public String toString(){
		
		String text = name + " " + kind + "\n";
		
		return text;
	}
	
}
