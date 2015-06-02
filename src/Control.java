
import java.util.Optional;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class Control  extends Application{

	private BlackJack view;
	private Game game;
	private Stage stage;
	private Player player = null;
	private Dealer dealer = null;
	
	public void start(Stage pStage){
		
		stage = pStage;
		game = new Game();
		view = new BlackJack();
		addListeners();
		stage.setTitle("BlackJack");
		stage.setScene(view.scene);
		stage.show();
	}
	
	private void addListeners(){
		
		view.menuItemNewGame.setOnAction(new ListenerMenu());
		view.menuItemQuit.setOnAction(new ListenerMenu());
		
		view.btnHit.setOnAction(new ListenerButton());
	}
	
	public class ListenerMenu implements EventHandler<ActionEvent>{
		
		@Override
		public void handle(ActionEvent e){
			
			if(e.getSource() == view.menuItemNewGame){
				
				manageNewGame();
			}
			
			if(e.getSource() == view.menuItemQuit){
				
				manageQuit();
			}
		}
	}
	
	public class ListenerButton implements EventHandler<ActionEvent>{
		
		@Override
		public void handle(ActionEvent e){
			
			if(e.getSource() == view.btnHit){
				
				
			}
			
			if(e.getSource() == view.btnStand){
				
				
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
			
			dealer = new Dealer("Garry");
			view.txtDealerName.setText(dealer.getName());
			view.txtPlayerName.setText(player.getName());
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

	public static void main(String[] args) {

		Application.launch(args);
	}

}
