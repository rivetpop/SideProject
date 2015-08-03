//Note: Envisager de générer un ArrayList avec les informations des joueurs provenant du fichier de 
//sauvegarde lors du démarrage du programme,
//ca pourrais simplifier beaucoup certaines methodes.

//Note: Envisager de créer un fichier de sauvegarde par profile de joueur

package Casino;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Optional;

import BlackJack.BJGame;
import BlackJack.BlackJack;
import BlackJack.Dealer;
import Roulette.Roulette;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.*;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class Control  extends Application{

	private Main_menu viewMainMenu;
	private Welcome viewWelcome;
	private BlackJack viewBlackJack;
	private Roulette viewRoulette;
	private LoadPlayerScreen viewLoadPlayer;
	
	private BJGame bjGame;
	private Stage stage;
		
	public static Player currentPlayer = null;
	public static Dealer currentDealer = null;
	
	private File playerInfoFile = new File(".\\src\\Player_info.dat");
	
	public void start(Stage pStage){
		
		viewMainMenu = new Main_menu();
		
		
		stage = pStage;
		addListeners();
		stage.setTitle("Casino");
		stage.setScene(viewMainMenu.scene);
		stage.show();
	}
	
	private void addListeners(){
		
		//Main_menu
		viewMainMenu.newPlayerButton.setOnAction(new ListenerButton());
		viewMainMenu.loadPlayerButton.setOnAction(new ListenerButton());
		viewMainMenu.quitButton.setOnAction(new ListenerButton());
	}
	
	public class ListenerMenu implements EventHandler<ActionEvent>{
		
		@Override
		public void handle(ActionEvent e)
		{
			if (viewBlackJack != null)
			{
				if (e.getSource() == viewBlackJack.menuItemCasinoHall)
				{
					
					manageWelcome();
				}
			
				else if (e.getSource() == viewBlackJack.menuItemQuit)
				{
					
					manageQuit();
				}
			}
			
			if (viewRoulette != null)
			{
				if (e.getSource() == viewRoulette.menuItemCasinoHall)
				{
					manageWelcome();
				}
				
				else if (e.getSource() == viewRoulette.menuItemQuit)
				{
					manageQuit();
				}
			}
		}
	}
	
	public class ListenerButton implements EventHandler<ActionEvent>{
		
		@Override
		public void handle(ActionEvent e){
			
			if(viewMainMenu != null){
				
				if(e.getSource() == viewMainMenu.newPlayerButton){
					
					manageNewPlayer();
				}
				
				else if(e.getSource() == viewMainMenu.loadPlayerButton){
					
					manageLoadProfile();
				}
				
				else if(e.getSource() == viewMainMenu.quitButton){
					
					manageQuit();
				}
			}
			
			if (viewLoadPlayer != null){
				
					if(e.getSource() == viewLoadPlayer.cancelButton){
						
						start(stage);
					}
					
					else if(e.getSource() == viewLoadPlayer.loadPlayerButton){
						
						loadPlayer();
					}
				}
			
			if (viewWelcome != null){
				
				if(e.getSource() == viewWelcome.blackJackButton){
					
					manageBlackJack();
				}
				
				else if(e.getSource() == viewWelcome.rouletteButton){
					
					manageRoulette();
				}
			}
			
			if (viewBlackJack != null){
				
				if(e.getSource() == viewBlackJack.btnHit){
					
				}
				
				else if(e.getSource() == viewBlackJack.btnStand){
					
				}
				
				else if(e.getSource() == viewBlackJack.btnDraw){
					
					//Draw 2 cards for both dealer and player
					bjGame.drawCard();
					viewBlackJack.playerCard1.setImage(bjGame.playerHand.get(0).getImage());
					viewBlackJack.playerCard2.setImage(bjGame.playerHand.get(1).getImage());
					viewBlackJack.dealerCard1.setImage(bjGame.dealerHand.get(0).getImage());
					Image backCard = new Image("b1fv.png");
					viewBlackJack.dealerCard2.setImage(backCard);
					
					//Disable draw button, enable Hit and Stand button
					viewBlackJack.btnDraw.setDisable(true);
					viewBlackJack.btnHit.setDisable(false);
					viewBlackJack.btnStand.setDisable(false);
					
					System.out.println(bjGame.countHand(bjGame.playerHand));
				}
				
				else if(e.getSource() == viewBlackJack.btnBet){
					
					bjGame.placeBet();
					viewBlackJack.btnBet.setDisable(true);
					viewBlackJack.txtOptions.setText("Your current bet : " + bjGame.getBet());
					viewBlackJack.btnDraw.setDisable(false);
				}
			}
			
			if (viewRoulette != null)
			{
				if(e.getSource() == viewRoulette.spinTheWheelButton)
				{
					viewRoulette.spinTheWheel();
				}
				
				else if(e.getSource() == viewRoulette.removeLastBetButton)
				{
					
				}
				
				else if (e.getSource() == viewRoulette.removeAllBetsButton)
				{
					
				}
			}
		}
	}
	
	public void manageQuit(){
		if(currentPlayer == null){
			
			System.exit(0);
		}
		
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Casino");
		alert.setHeaderText("Hey! " + currentPlayer.getName() + "! You still got " + currentPlayer.getCash() + "$ to spend");
		alert.setContentText("Do you really want to quit?");
		
		Optional<ButtonType> result = alert.showAndWait();
		
		if(result.get() == ButtonType.OK){
			
			System.exit(0);
		}
	}
	
	public void manageNewPlayer(){
		
		if(currentPlayer != null  || createPlayer()){
			
			manageWelcome();
		}
		
		else
		{
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Player creation error");
			alert.setContentText("The player could not be created");
			alert.showAndWait();
		}
	}
	
	public void manageWelcome(){
		
		viewWelcome = new Welcome();
		
		//Welcome screen listeners
		viewWelcome.blackJackButton.setOnAction(new ListenerButton());
		viewWelcome.rouletteButton.setOnAction(new ListenerButton());
		
		viewWelcome.playerInfo.setAlignment(Pos.CENTER);
		viewWelcome.titleWelcome.setText("Welcome " + currentPlayer.getName() + "!");
		viewWelcome.titleWelcome2.setText("Enjoy your stay and make some money!");
		viewWelcome.playerStats.setText("    " + currentPlayer.getName() + "\n    Your cash : " + currentPlayer.getCash() + "$");
		System.out.println(currentPlayer.getImg());
		
		Image pImg = null;
		if (currentPlayer.getImg().equals(Player.DEFAULT_IMG_URL)){
			pImg = new Image(Player.DEFAULT_IMG_URL);
		}
		
		else{
			pImg = new Image("file:" + currentPlayer.getImg());
		}
		
		viewWelcome.playerImg.setImage(pImg);
		
		stage.setTitle("Welcome!");
		stage.setScene(viewWelcome.scene);
		stage.show();
	}
	
	public boolean createPlayer(){
		
		boolean ok = false;
		
		//Ask to choose a name
			String name = enterName("Enter player name:");
			
			//while the file contains at least 1 line and the name is already used
			while(!emptyFileCheck() && nameExists(name))
			{
				name = enterName("This name is already used. Please choose another name.");
			}
			
			int cash = Player.STARTING_MONEY;
		
		//Ask to choose an image
			Alert imgAlert = new Alert(AlertType.CONFIRMATION);
			imgAlert.setTitle("Image selection");
			imgAlert.setContentText("Do you want to set an image for your player profile");
			
			ButtonType yesButton = new ButtonType("Yes");
			ButtonType noButton = new ButtonType("No");
			
			imgAlert.getButtonTypes().setAll(yesButton, noButton);
			
			Optional<ButtonType> result = imgAlert.showAndWait();
			
			String img = null;
			if(result.get() == yesButton)
			{
				img = choosePicture();
			}
			
			if(result.get() == noButton)
			{
				img = Player.DEFAULT_IMG_URL;
			}
		
		//Create the new player
			Player player = new Player(name, cash, img);
			currentPlayer = player;
			
			if (currentPlayer != null)
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
			
			bufferFile = new BufferedReader(new FileReader(playerInfoFile));

			String readTest = bufferFile.readLine(); 
			
			if (readTest == null)
			{
				emptyFileCheck = true;
			}	
		}
		catch (IOException e)
		{
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("IO Error");
			alert.setContentText("File error: Cannot read " + playerInfoFile);
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
				alert.setContentText("File error: " + playerInfoFile + " stream cannot be closed)");
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
			bufferRead = new BufferedReader(new FileReader(playerInfoFile));
			
			while((line = bufferRead.readLine())!= null)
			{	
				String[] vector = line.split(";");
				if (vector[0].equals(p_name))
				{	
					nameExists = true;
				}
			}
		}
		catch (IOException e)
		{
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("IO Error");
			alert.setContentText("File error: Cannot read " + playerInfoFile);
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
				alert.setContentText("File error: " + playerInfoFile + " stream cannot be closed)");
				alert.showAndWait();
			}
		}
		
		return nameExists;
	}
	
	public String choosePicture()
	{
		String picture_path = null;
		//URL picture_URL = null;
		
		FileChooser pictureChoice = new FileChooser();
		
		//Allow only image extension files to be chosen
		pictureChoice.getExtensionFilters().add(new ExtensionFilter("JPG", "*.jpeg"));
		pictureChoice.getExtensionFilters().add(new ExtensionFilter("PNG", "*.png"));
		pictureChoice.getExtensionFilters().add(new ExtensionFilter("GIF", "*.gif"));
		pictureChoice.getExtensionFilters().add(new ExtensionFilter("BMP", "*.bmp"));
		
		pictureChoice.setInitialDirectory(new File(System.getProperty("user.dir")));
		File fichier = pictureChoice.showOpenDialog(stage);
		
		
		if (fichier == null)
		{
				picture_path = Player.DEFAULT_IMG_URL;	
		}
		
		else
		{
			picture_path = fichier.getPath();
		}
		
		return picture_path;
	}
	
	public void manageSaveProfile()
	{
		BufferedWriter bufferWrite = null;
		BufferedReader bufferRead = null;
		
		//If a player profile is created or loaded AND if that player profile has been saved at least once before.
				/*In this case, we want to overwrite the player data in the file, 
				otherwise if we just save everything it will create a player profile repetition, and the
				program won't work as expected if we ever try to load that profile again */
				
				if (currentPlayer != null && nameExists(currentPlayer.getName()))
				{			
					try
					{	
						String line = "";
								
						bufferRead = new BufferedReader(new FileReader(playerInfoFile));
						
						//Get all the text contained in the file (line by line) and put it in a String variable (oldText)
						String oldText = "";
						while((line = bufferRead.readLine())!= null)
						{
							oldText += line+"\n";
						}
						
						//Create a new String variable containing the oldText, but replace the data for the line concerning the current player
						// A regular expression is used in the method replaceAll to find the data we want to replace
						String newText = oldText.replaceAll(currentPlayer.getName()+";\\d+;[^\\\\]+", 
															currentPlayer.getName() + ";" + currentPlayer.getCash() +";" +currentPlayer.getImg());
						
						bufferWrite= new BufferedWriter(new FileWriter(playerInfoFile));
						bufferWrite.write(newText);
					}
					catch (IOException e)
					{
						Alert alert = new Alert(AlertType.ERROR);
						alert.setTitle("IO Error");
						alert.setContentText("File error: Cannot write in " + playerInfoFile);
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
							alert.setContentText("File error: " + playerInfoFile + " stream cannot be closed)");
							alert.showAndWait();
						}
					}
				}
				
				else
				{
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Saving");
					alert.setContentText("You must start or load a player profile before saving!");
				}
			
		
		//If a player profile is created or loaded AND if that player profile has never been saved.
		//In this case, we save the name, cash and picture of the player profile on a new line
		if (currentPlayer != null && !nameExists(currentPlayer.getName()))
		{
			
			try
			{
				bufferWrite= new BufferedWriter(new FileWriter(playerInfoFile, true));
				
				String player_save = null;
				
				player_save = currentPlayer.getName() + ";" + currentPlayer.getCash() + ";" + currentPlayer.getImg();
				
				bufferWrite.write(player_save);
				bufferWrite.newLine();
				
				
			}
			catch (IOException e)
			{
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("IO Error");
				alert.setContentText("File error: Cannot write in " + playerInfoFile);
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
					alert.setContentText("File error: " + playerInfoFile + " stream cannot be closed)");
					alert.showAndWait();
				}
			}
		}
		
		else
		{
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Saving");
			alert.setContentText("You must create or load a player profile before saving!");
		}
	}	
		
	
	public void manageLoadProfile()
	{
		viewLoadPlayer = new LoadPlayerScreen();
		
			//Load Player Screen Listeners
			viewLoadPlayer.loadPlayerButton.setOnAction(new ListenerButton());
			viewLoadPlayer.cancelButton.setOnAction(new ListenerButton());
						
		stage.setTitle("Load Player Profile");
		stage.setScene(viewLoadPlayer.scene);
				
		stage.show();
	}
	
	public void loadPlayer()
	{
		/*Get the line number corresponding to the selected profile in the list view. 
		 * For a profile, this line number from the listView is the same as the line number 
		 * of the profile in the save file.
		 */
			int lineNumber = viewLoadPlayer.profileTableView.getSelectionModel().getSelectedIndex();
			
		//Read the file line by line until the one corresponding to the player profile is reached	
			String name = "";
			int cash = 0;
			String img = "";
			
			BufferedReader bufferRead = null;
			
			try
			{
				bufferRead = new BufferedReader(new FileReader(playerInfoFile));
						 
					String line = "";
					int fileLineCount = 0;
					while(fileLineCount <= lineNumber)//Read until desired profile's line is reached
					{
						line = bufferRead.readLine();
						String[] temp_vector = line.split(";");
						name = temp_vector[0];
						cash = Integer.parseInt(temp_vector[1]);
						img = temp_vector[2];
						fileLineCount++;
					}				
			}		
		
			catch (IOException e)
			{
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("IO Error");
				alert.setContentText("File error: Cannot read " + playerInfoFile);
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
					alert.setContentText("File error: " + playerInfoFile + " stream cannot be closed)");
					alert.showAndWait();
				}
			}
			
		//Create the new player using the infos in the file. Make it the currentPlayer.
			if (name!="" && cash!=0 && img!="")//Prevent the creation of an empty player if not profile is loaded (if the user didn't click on a profile before to load)
			{
				Player player = new Player(name, cash, img);
				currentPlayer = player;
			}
			
		//If the user chose a profile by clicking, load the Welcome Screen
			if (lineNumber != -1) // If the user didn't click on a profile, the default lineNumber value is -1
			{
				manageWelcome();
			}
			
			else
			{
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Error");
				alert.setHeaderText("Warning");
				alert.setContentText("You must select the player profile you want to load.");
				alert.showAndWait();
			}
	}
	
	public void manageBlackJack()
	{	
		viewBlackJack = new BlackJack();
		currentDealer = new Dealer("Garry");
		bjGame = new BJGame(currentPlayer, currentDealer);
		
		
		//BlackJack Listeners
		viewBlackJack.menuItemCasinoHall.setOnAction(new ListenerMenu());
		viewBlackJack.menuItemQuit.setOnAction(new ListenerMenu());
		viewBlackJack.btnHit.setOnAction(new ListenerButton());
		viewBlackJack.btnStand.setOnAction(new ListenerButton());
		viewBlackJack.btnBet.setOnAction(new ListenerButton());
		viewBlackJack.btnDraw.setOnAction(new ListenerButton());
		
		viewBlackJack.playerInfo.setAlignment(Pos.CENTER);
		
		Image pImg = null;
	
		if (currentPlayer.getImg().equals(Player.DEFAULT_IMG_URL)){
			
			pImg = new Image(Player.DEFAULT_IMG_URL);
		}
		
		else{
			
			pImg = new Image("file:" + currentPlayer.getImg());
		}
		viewBlackJack.playerImg.setImage(pImg);
		viewBlackJack.playerStats.setText("    " + currentPlayer.getName() + "\n    Your cash : " + currentPlayer.getCash() + "$");
		viewBlackJack.txtDealerName.setText(currentDealer.getName());
		viewBlackJack.txtOptions.setText("Welcome to BlackJack! Minimum bet is 10 and maximum bet is 200.");
		viewBlackJack.btnBet.setDisable(false);
		
		stage.setTitle("BlackJack");
		stage.setScene(viewBlackJack.scene);
		
		stage.show();
	}
	
	public void manageRoulette()
	{
		viewRoulette = new Roulette();
		
		//Menu Listeners	
			viewRoulette.menuItemCasinoHall.setOnAction(new ListenerMenu());
			viewRoulette.menuItemQuit.setOnAction(new ListenerMenu());
		//Button Listeners
			viewRoulette.spinTheWheelButton.setOnAction(new ListenerButton());
			viewRoulette.removeLastBetButton.setOnAction(new ListenerButton());
			viewRoulette.removeAllBetsButton.setOnAction(new ListenerButton());
		
		//Set playerInfos
			Image pImg = null;
			
			if (currentPlayer.getImg().equals(Player.DEFAULT_IMG_URL)){
				
				pImg = new Image(Player.DEFAULT_IMG_URL);
			}
			
			else{
				
				pImg = new Image("file:" + currentPlayer.getImg());
			}
			
			viewRoulette.playerImg.setImage(pImg);
			viewRoulette.playerStats.setText("    " + currentPlayer.getName() + "\n    Your cash : " + currentPlayer.getCash() + "$");
		
		stage.setTitle("Roulette");
		stage.setScene(viewRoulette.scene);
		
		stage.show();
	}

	public static void main(String[] args) {

		Application.launch(args);
	}
}
