package Casino;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class Control  extends Application{

	private Main_menu viewMainMenu;
	private Welcome viewWelcome;
	private BlackJack viewBlackJack;
	private Roulette viewRoulette;
	
	private BJGame game;
	protected Stage stage;
	private Player player = null;
	private Dealer dealer = null;
	
	private File playerInfo = new File("Player_info.dat");
	
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
		viewMainMenu.newPlayerButton.setOnAction(new ListenerButton());
		viewMainMenu.loadPlayerButton.setOnAction(new ListenerButton());
		
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
			
			if(e.getSource() == viewMainMenu.newPlayerButton){
				
				manageNewPlayer();
			}
			
			if(e.getSource() == viewMainMenu.loadPlayerButton){
				
				manageLoadPlayer();
			}
			
			if(e.getSource() == viewMainMenu.quitButton){
				
				manageQuit();
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
		alert.setTitle("Casino");
		alert.setHeaderText("Hey! " + player.getName() + "! You still got " + player.getCash() + "$ to spend");
		alert.setContentText("Do you really want to quit?");
		
		Optional<ButtonType> result = alert.showAndWait();
		
		if(result.get() == ButtonType.OK){
			
			System.exit(0);
		}
	}
	
	public void manageNewPlayer(){
		
		if(createPlayer()){
			
			viewWelcome.titleWelcome.setText("Welcome " + player.getName() + "!");
			viewWelcome.titleWelcome2.setText("Enjoy your stay and make some money!");
			viewWelcome.playerStats.setText("    Your cash : " + player.getCash() + "$");
			stage.setTitle("Welcome!");
			stage.setScene(viewWelcome.scene);
			stage.show();
		}
		
		else
		{
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Player creation error");
			alert.setContentText("The player could not be created");
			alert.showAndWait();
		}
	}
	
	public boolean createPlayer(){
		
		boolean ok = false;
		
		String name = enterName("Enter player name:");
		
		//while the file contains at least 1 line and the name is already used
		while(!emptyFileCheck() && nameExists(name))
		{
			name = enterName("This name is already used. Please enter another name.");
		}
		
		int cash = Player.STARTING_MONEY;
		
		 String img = choosePicture();
		
		player = new Player(name, cash, img);
		
		if (player != null)
		{
			ok = true;
		}
		
		return ok;
	}
	
	public static String enterName(String message){
		
		Optional<String>nameEntered = null;
		TextInputDialog dialog = new TextInputDialog();
		dialog.setTitle("Casino");
		dialog.setHeaderText("Enter your name");
		dialog.setContentText(message);
		
		nameEntered = dialog.showAndWait();
		
		
		while(!Player.validateName(nameEntered.get())){
			
			dialog.setContentText("Between 2 and 15 characters.\n" + message);
			nameEntered = dialog.showAndWait();
		}
		
		return nameEntered.get();
	}
	
	public boolean emptyFileCheck()
	{
		boolean emptyFileCheck = false;
		BufferedReader bufferFile = null;
		
		try
		{
			System.out.println(playerInfo);
			bufferFile = new BufferedReader(new FileReader(playerInfo));
			
			String readTest = bufferFile.readLine(); //////////////////Erreur potentielle: si le fichier est vide, cette ligne revoit-elle une erreur de IO? Ça ne devrait pas...
			
			if (readTest == null)
			{
				emptyFileCheck = true;
			}	
		}
		catch (IOException e)
		{
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("IO Error");
			alert.setContentText("File error: Cannot read " + playerInfo);
			alert.showAndWait();
		}	
		
		finally
		{
			try
			{
				bufferFile.close();
			}
			catch(IOException e)
			{
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("IO Error");
				alert.setContentText("File error: " + playerInfo + " stream cannot be closed)");
				alert.showAndWait();
			}
		}
		
		return emptyFileCheck;
	}
	
	public boolean nameExists(String p_name)
	{
		boolean nameExists = false;
		String line = "";
		BufferedReader bufferRead = null;
		
		try
		{
			bufferRead = new BufferedReader(new FileReader(playerInfo));
			
			while((line = bufferRead.readLine())!= null)
			{
				String[] vector = line.split(";");
				if (vector[0] == p_name)
				{
					nameExists = true;
				}
			}
		}
		catch (IOException e)
		{
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("IO Error");
			alert.setContentText("File error: Cannot read " + playerInfo);
			alert.showAndWait();
		}
		
		finally
		{
			try
			{
				bufferRead.close();
			}
			catch(IOException e)
			{
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("IO Error");
				alert.setContentText("File error: " + playerInfo + " stream cannot be closed)");
				alert.showAndWait();
			}
		}
		
		return nameExists;
	}
	
	public String choosePicture()
	{
		String picture_URL = null;
		
		FileChooser pictureChoice = new FileChooser();
		
		//Allow only image extension files to be chosen
		pictureChoice.getExtensionFilters().add(new ExtensionFilter("JPG", "*.jpeg"));
		pictureChoice.getExtensionFilters().add(new ExtensionFilter("PNG", "*.png"));
		pictureChoice.getExtensionFilters().add(new ExtensionFilter("GIF", "*.gif"));
		pictureChoice.getExtensionFilters().add(new ExtensionFilter("PNG", "*.bmp"));
		
		pictureChoice.setInitialDirectory(new File(System.getProperty("user.dir")));
		File fichier = pictureChoice.showOpenDialog(stage);
		
		
		if (fichier == null)
		{
			picture_URL = Player.DEFAULT_IMG_URL;
		}
		
		else
		{
			picture_URL = fichier.getPath();
		}
		
		return picture_URL;
	}
	
	public void manageSavePlayer()
	{
		
		BufferedWriter bufferWrite = null;
		BufferedReader bufferRead = null;
		
		//If a player profile is created or loaded AND if that player profile has never been saved
		//In this case, we save the name, cash and picture of the player profile
		if (player != null && !nameExists(player.getName()))
		{
			try
			{
				bufferWrite= new BufferedWriter(new FileWriter(playerInfo, true));
				String player_save = player.getName() + ";" + player.getCash() + ";" + player.getImg();
				bufferWrite.write(player_save);
				bufferWrite.newLine();
			}
			catch (IOException e)
			{
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("IO Error");
				alert.setContentText("File error: Cannot write in " + playerInfo);
				alert.showAndWait();
			}
			
			finally
			{
				try
				{
					bufferWrite.close();
				}
				catch(IOException e)
				{
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("IO Error");
					alert.setContentText("File error: " + playerInfo + " stream cannot be closed)");
					alert.showAndWait();
				}
			}
		}
		
		else
		{
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Sauvegarde");
			alert.setContentText("You must create or load a player profile before saving!");
		}
		
		//If a player profile is created or loaded AND if that player profile has been saved at least once before.
		/*In this case, we want to overwrite the player data in the file, 
		otherwise if we just save everything we're gonna create a player profile repetition, and the
		program will crash if we ever try to load that profile again */
		
		if (player != null && nameExists(player.getName()))
		{			
			try
			{	
				String line = "";
						
				bufferRead = new BufferedReader(new FileReader(playerInfo));
				
				//Get all the text contained in the file (line by line) and put it in a String variable (oldText)
				String oldText = "";
				while((line = bufferRead.readLine())!= null)
				{
					oldText += line+"\n";
				}
				
				//Create a new String variable containing the oldText, but replace the data for the line concerning the current player
				// A regular expression is used in the method replaceAll to find the data we want to replace
				String newText = oldText.replaceAll(player.getName()+";\\d+;[^\\\\]+", 
													player.getName() + ";" + player.getCash() +";" +player.getImg());
				
				bufferWrite= new BufferedWriter(new FileWriter(playerInfo));
				bufferWrite.write(newText);
			}
			catch (IOException e)
			{
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("IO Error");
				alert.setContentText("File error: Cannot write in " + playerInfo);
				alert.showAndWait();
			}
			
			finally
			{
				try
				{
					bufferWrite.close();
				}
				catch(IOException e)
				{
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("IO Error");
					alert.setContentText("File error: " + playerInfo + " stream cannot be closed)");
					alert.showAndWait();
				}
			}
		}
		
		else
		{
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Sauvegarde");
			alert.setContentText("You must start or load a player profile before saving!");
		}
	}
	
	public void manageLoadPlayer()
	{
		
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
