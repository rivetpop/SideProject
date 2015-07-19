package BlackJack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import Casino.Card;
import Casino.Player;



public class BJGame {

	/**
	 * Nombre de cartes dans un paquet
	 */
	public static final int CARDS_PER_DECK = 52;
	
	protected ArrayList<Card> deck = null;
	
	int currentCard = 0;
	
	private Player player = null;
	private Dealer dealer = null;
	
	public BJGame(Player pPlayer, Dealer pDealer){
		
		deck = new ArrayList<Card>(CARDS_PER_DECK);
		dealer = pDealer;
		player = pPlayer;
		//createDeck();
	}
	
	
	//public void affectValue()
	
	public void createDeck(){
		
		for(int i = 2; i < 15; i++){
			
			String imgName = i + "H.png";
			
			Image imgCard = null;
			imgCard = new Image(imgName);
			
			deck.add(new Card("i", "Heart", imgCard));
		}
		
		for(int i = 2; i < 15; i++){
			
			String imgName = i + "D.png";
			
			Image imgCard = null;
			imgCard = new Image(imgName);
			
			deck.add(new Card("i", "Diamond", imgCard));
		}
		
		for(int i = 2; i < 15; i++){
			
			String imgName = i + "S.png";
			
			Image imgCard = null;
			imgCard = new Image(imgName);
			
			deck.add(new Card("i", "Spade", imgCard));
		}
		
		for(int i = 2; i < 15; i++){
			
			String imgName = i + "D.png";
			
			Image imgCard = null;
			imgCard = new Image(imgName);
			
			deck.add(new Card("i", "Clover", imgCard));
		}
		
		Collections.shuffle(deck);
	}
	
	public void checkWin(){
		
		
	}
	
	public String formatDeck(ArrayList<Card> pDeck){
		
		String text = "";
		
		for(int i = 0; i < pDeck.size(); i++){
			
			text += pDeck.get(i).getName() + "\t";
			text += pDeck.get(i).getKind() + "\n";
		}
		
		return text;
	}
	
}
