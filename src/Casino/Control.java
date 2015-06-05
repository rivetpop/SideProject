package Casino;

import java.util.Optional;

import BlackJack.BJGame;
import BlackJack.BlackJack;
import BlackJack.Dealer;
import Roulette.Roulette;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class Control  extends Application{

	private Main_menu viewMainMenu;
	private Welcome viewWelcome;
	private BlackJack viewBlackJack;
	private Roulette viewRoulette;
	
	private BJGame game;
	private Stage stage;
	private Player player = null;
	private Dealer dealer = null;
	
	public void start(Stage pStage){
		
		viewMainMenu = new Main_menu();
		viewWelcome = new Welcome();
		viewBlackJack = new BlackJack();
		viewRoulette = new Roulette();
		
		
		stage = pStage;
		addListeners();
		stage.setTitle("Casino");
		stage.setScene(viewMainMenu.scene);
		stage.show();
	}
	
	private void addListeners(){
		
		//Blackjack
		viewBlackJack.btnHit.setOnAction(new ListenerButton());
		
		//Main_menu<
		viewMainMenu.newGameButton.setOnAction(new ListenerButton());
		viewMainMenu.loadGameButton.setOnAction(new ListenerButton());
		
		//Welcome
		viewWelcome.blackJackButton.setOnAction(new ListenerButton());
		viewWelcome.rouletteButton.setOnAction(new ListenerButton());
	}
	
	public class ListenerMenu implements EventHandler<ActionEvent>{
		
		@Override
		public void handle(ActionEvent e){
			
		
		}
	}
	
	public class ListenerButton implements EventHandler<ActionEvent>{
		
		@Override
		public void handle(ActionEvent e){
			
			if(e.getSource() == viewMainMenu.newGameButton){
				
				manageNewGame();
			}
			
			if(e.getSource() == viewMainMenu.quitButton){
				
				
			}
			
			if(e.getSource() == viewWelcome.blackJackButton){
				
				manageBlackJack();
			}
			
			if(e.getSource() == viewWelcome.rouletteButton){
				
				manageRoulette();
			}
			
			
		}
	}
	
	public void manageQuit(){
		
		if(player == null){
			
			System.exit(0);
		}
		
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("BlackJack");
		alert.setHeaderText("Hey! " + player.getName() + "! You still got " + player.getCash() + "$ to spend");
		alert.setContentText("Do you really want to quit?");
		
		Optional<ButtonType> result = alert.showAndWait();
		
		if(result.get() == ButtonType.OK){
			
			System.exit(0);
		}
	}
	
	public void manageNewGame(){
		
		if(createPlayer()){
			
			viewWelcome.titleWelcome.setText("Welcome " + player.getName() + "!");
			viewWelcome.titleWelcome2.setText("Enjoy your stay and make some money!");
			viewWelcome.playerStats.setText("    Your cash : " + player.getCash() + "$");
			stage.setTitle("Welcome!");
			stage.setScene(viewWelcome.scene);
			stage.show();
		}
	}
	
	public boolean createPlayer(){
		
		boolean ok = false;
		String name = null;
		
		name = enterName("Enter player name:");
		
		if(name != null){
			
			player = new Player(name, Player.STARTING_MONEY);
			ok = true;
		}
		
		return ok;
	}
	
	public static String enterName(String message){
		
		Optional<String>nameEntered = null;
		TextInputDialog dialog = new TextInputDialog();
		dialog.setTitle("Black Jack");
		dialog.setHeaderText("Enter your name");
		dialog.setContentText(message);
		
		nameEntered = dialog.showAndWait();
		
		
		while(!Player.validateName(nameEntered.get())){
			
			dialog.setContentText("Between 2 and 15 characters.\n" + message);
			nameEntered = dialog.showAndWait();
		}
		
		return nameEntered.get();
	}
	
	public void manageBlackJack(){
		
		stage.setTitle("BlackJack");
		stage.setScene(viewBlackJack.scene);
		stage.show();
	}
	
	public void manageRoulette(){
		
		stage.setTitle("Roulette");
		stage.setScene(viewRoulette.scene);
		stage.show();
	}

	public static void main(String[] args) {

		Application.launch(args);
	}

}
