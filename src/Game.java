import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;



public class Game {

	/**
	 * Nombre de cartes dans un paquet
	 */
	public static final int CARDS_PER_DECK = 52;
	
	protected ArrayList<Card> deck = null;
	
	int currentCard = 0;
	
	public Game(){
		
		deck = new ArrayList<Card>(CARDS_PER_DECK);
		createDeck();
	}
	
	public void createDeck(){
		
		Card c1 = new Card("2", 2, "Heart");
		Card c2 = new Card("2", 2, "Diamond");
		Card c3 = new Card("2", 2, "Spade");
		Card c4 = new Card("2", 2, "Clover");
		
		Card c5 = new Card("3", 3, "Heart");
		Card c6 = new Card("3", 3, "Diamond");
		Card c7 = new Card("3", 3, "Spade");
		Card c8 = new Card("3", 3, "Clover");
		
		Card c9 = new Card("4", 4, "Heart");
		Card c10 = new Card("4", 4, "Diamond");
		Card c11 = new Card("4", 4, "Spade");
		Card c12 = new Card("4", 4, "Clover");
		
		Card c13 = new Card("5", 5, "Heart");
		Card c14 = new Card("5", 5, "Diamond");
		Card c15 = new Card("5", 5, "Spade");
		Card c16 = new Card("5", 5, "Clover");
		
		Card c17 = new Card("6", 6, "Heart");
		Card c18 = new Card("6", 6, "Diamond");
		Card c19 = new Card("6", 6, "Spade");
		Card c20 = new Card("6", 6, "Clover");
		
		Card c21 = new Card("7", 7, "Heart");
		Card c22 = new Card("7", 7, "Diamond");
		Card c23 = new Card("7", 7, "Spade");
		Card c24 = new Card("7", 7, "Clover");
		
		Card c25 = new Card("8", 8, "Heart");
		Card c26 = new Card("8", 8, "Diamond");
		Card c27 = new Card("8", 8, "Spade");
		Card c28 = new Card("8", 8, "Clover");
		
		Card c29 = new Card("9", 9, "Heart");
		Card c30 = new Card("9", 9, "Diamond");
		Card c31 = new Card("9", 9, "Spade");
		Card c32 = new Card("9", 9, "Clover");
		
		Card c33 = new Card("10", 10, "Heart");
		Card c34 = new Card("10", 10, "Diamond");
		Card c35 = new Card("10", 10, "Spade");
		Card c36 = new Card("10", 10, "Clover");
		
		Card c37 = new Card("Jack", 10, "Heart");
		Card c38 = new Card("Jack", 10, "Diamond");
		Card c39 = new Card("Jack", 10, "Spade");
		Card c40 = new Card("Jack", 10, "Clover");
		
		Card c41 = new Card("Queen", 10, "Heart");
		Card c42 = new Card("Queen", 10, "Diamond");
		Card c43 = new Card("Queen", 10, "Spade");
		Card c44 = new Card("Queen", 10, "Clover");
		
		Card c45 = new Card("King", 10, "Heart");
		Card c46 = new Card("King", 10, "Diamond");
		Card c47 = new Card("King", 10, "Spade");
		Card c48 = new Card("King", 10, "Clover");
		
		Card c49 = new Card("Ace", 11, "Heart");
		Card c50 = new Card("Ace", 11, "Diamond");
		Card c51 = new Card("Ace", 11, "Spade");
		Card c52 = new Card("Ace", 11, "Clover");
		
		deck.add(c1);
		deck.add(c2);
		deck.add(c3);
		deck.add(c4);
		deck.add(c5);
		deck.add(c6);
		deck.add(c7);
		deck.add(c8);
		deck.add(c9);
		deck.add(c10);
		deck.add(c11);
		deck.add(c12);
		deck.add(c13);
		deck.add(c14);
		deck.add(c15);
		deck.add(c16);
		deck.add(c17);
		deck.add(c18);
		deck.add(c19);
		deck.add(c20);
		deck.add(c21);
		deck.add(c22);
		deck.add(c23);
		deck.add(c24);
		deck.add(c25);
		deck.add(c26);
		deck.add(c27);
		deck.add(c28);
		deck.add(c29);
		deck.add(c30);
		deck.add(c31);
		deck.add(c32);
		deck.add(c33);
		deck.add(c34);
		deck.add(c35);
		deck.add(c36);
		deck.add(c37);
		deck.add(c38);
		deck.add(c39);
		deck.add(c40);
		deck.add(c41);
		deck.add(c42);
		deck.add(c43);
		deck.add(c44);
		deck.add(c45);
		deck.add(c46);
		deck.add(c47);
		deck.add(c48);
		deck.add(c49);
		deck.add(c50);
		deck.add(c51);
		deck.add(c52);
		
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
	
	public static void main(String[] args){
		
		Scanner choice = new Scanner(System.in);
		
		Game g1 = new Game();
		
		Dealer d1 = new Dealer("Jean");
		Player p1 = new Player("Guy");
		
	
		p1.setPlayerHand(g1.deck.get(g1.currentCard));
		g1.currentCard++;
		d1.setDealerHand(g1.deck.get(g1.currentCard));
		g1.currentCard++;
		p1.setPlayerHand(g1.deck.get(g1.currentCard));
		g1.currentCard++;
		d1.setDealerHand(g1.deck.get(g1.currentCard));
		g1.currentCard++;
		
		
	
		System.out.println(d1.getName() + " hand\n" + g1.formatDeck(d1.getDealerHand()));
		//System.out.println(g1.handTotal(d1.getDealerHand()) + "\n");
		System.out.println(p1.getName() + " hand\n" + g1.formatDeck(p1.getPlayerHand()));
		//System.out.println(g1.handTotal(p1.getPlayerHand()));
		
		if(p1.handTotal() == 21){
			
			System.out.println("You Won!");
			System.exit(0);
		}
		
		while(p1.handTotal() < 21){
			
			System.out.println("Another card?");
			String p1Choice = choice.nextLine();
			
			if(p1Choice.equals("y")){
	
				p1.setPlayerHand(g1.deck.get(g1.currentCard));
				System.out.println(g1.deck.get(g1.currentCard).getName() + "\t" + g1.deck.get(g1.currentCard).getKind());
				g1.currentCard++;
			}
			else if(p1Choice.equals("n")){
				
				if(p1.handTotal() > d1.handTotal()){
					
					System.out.println("You Won!");
				}
				
				else if(p1.handTotal() < d1.handTotal()){
					
					System.out.println("You lose!");
				}
				
				break;
			}
		}
		
		if(p1.handTotal() > 21){
			
			System.out.println("You Busted!");
		}
		
		else if((p1.handTotal() == 21) || (p1.handTotal() > d1.handTotal())){
			
			System.out.println("You Won!");
		}

		else if(p1.handTotal() < d1.handTotal()){
			
			System.out.println("You lose!");
		}
	}
}
