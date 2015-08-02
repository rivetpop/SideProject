package BlackJack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;
import java.util.Scanner;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import Casino.Card;
import Casino.Player;



public class BJGame {

	/**
	 * Nombre de cartes dans un paquet
	 */
	public static final int CARDS_PER_DECK = 52;
	public static final int CARDS_PER_KIND = CARDS_PER_DECK/4;
	public static final int NAMING_CARD_OFFSET = 2;
	public static final int KIND = 4;
	
	public ArrayList<Card> deck = null;
	public ArrayList<Card> playerHand = null;
	public ArrayList<Card> dealerHand = null;
	
	int currentCard;
	
	private Player player = null;
	private Dealer dealer = null;
	
	private int currentBet;
	
	public BJGame(Player pPlayer, Dealer pDealer){
		
		deck = new ArrayList<Card>(CARDS_PER_DECK);
		playerHand = new ArrayList<Card>(CARDS_PER_DECK);
		dealerHand = new ArrayList<Card>(CARDS_PER_DECK);
		dealer = pDealer;
		player = pPlayer;
		createDeck();
	}
	
	public int getBet(){
		
		return this.currentBet;
	}
	
	public boolean setBet(int pBet){
		
		boolean ok = validateBet(pBet);
		
		if(ok){
			
			this.currentBet = pBet;
		}
		
		return (ok);
	}
	
	public static boolean validateBet(int pBet){
		
		return (pBet >= 10 && pBet <= 200);
	}
	
	public int betInput(){
		
		TextInputDialog bet = new TextInputDialog();
		bet.setTitle("Place your bet!");
		bet.setContentText("Between 10 and 200.");
		
		Optional<String>betEntered = bet.showAndWait();
			
		return Integer.parseInt(betEntered.get());
	}
	
	public int placeBet(){
		
		int bet;
		
		do{
			
			bet = betInput();
			
		}while (!setBet(bet));
			
		
		return bet;
	}
	
	public void drawCard(){
		
		playerHand.add(deck.get(0));
		dealerHand.add(deck.get(1));
		playerHand.add(deck.get(2));
		dealerHand.add(deck.get(3));
	}
	
	public void createDeck(){
		
		Image imgCard = null;
		
		for(int i = 0; i < KIND; i++){
			
			for(int j = NAMING_CARD_OFFSET; j < CARDS_PER_KIND + NAMING_CARD_OFFSET; j++){
				
				String imgName = null;
				
				switch(i){
				
				case 0:
					
					imgName = j + "H.png";
					
				case 1:
					
					imgName = j + "D.png";
				
				case 2:
					
					imgName = j + "S.png";
					
				case 3:
					
					imgName = j + "C.png";
				}
				
				imgCard = new Image(imgName);
				
				deck.add(new Card(j, i, imgCard));
				
			}
		}
		
		
		Collections.shuffle(deck);
	}
	
	
	public void checkWin(){
		
		
	}
	
	public int countHand(ArrayList<Card> pHand){
		
		int handValue = 0;
		
		for(int i = 0; i < pHand.size(); i++){
			
			if(pHand.get(i).getName() < 10){
				
				handValue += pHand.get(i).getName();
			}
			else if(pHand.get(i).getName() > 10){
				
				
			}
		}
		
		return handValue;
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
