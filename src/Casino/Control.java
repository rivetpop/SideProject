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
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.MenuItem;
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
	private Stage stage;
	public static Player current_player = null;
	private Dealer dealer = null;
	
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
		public void handle(ActionEvent e){
			
			if (e.getSource() == viewRoulette.menuItemSave)
			{
				manageSavePlayer();
			}
			
			if (e.getSource() == viewRoulette.menuItemLoadPlayer)
			{
				manageLoadPlayer();
			}
		}
	}
	
	public class ListenerButton implements EventHandler<ActionEvent>{
		
		@Override
		public void handle(ActionEvent e){
			
			if(e.getSource() == viewMainMenu.newPlayerButton){
				
				manageNewPlayer();
			}
			
			else if(e.getSource() == viewMainMenu.loadPlayerButton){
				
				manageLoadPlayer();
			}
			
			else if(e.getSource() == viewMainMenu.quitButton){
				
				manageQuit();
			}
			
			else if(e.getSource() == viewWelcome.blackJackButton){
				
				manageBlackJack();
			}
			
			else if(e.getSource() == viewWelcome.rouletteButton){
				
				manageRoulette();
			}		
		}
	}
	
	public void manageQuit(){
		if(current_player == null){
			
			System.exit(0);
		}
		
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Casino");
		alert.setHeaderText("Hey! " + current_player.getName() + "! You still got " + current_player.getCash() + "$ to spend");
		alert.setContentText("Do you really want to quit?");
		
		Optional<ButtonType> result = alert.showAndWait();
		
		if(result.get() == ButtonType.OK){
			
			System.exit(0);
		}
	}
	
	public void manageNewPlayer(){
		
		if(current_player != null || createPlayer()){
			
			viewWelcome = new Welcome();
			
			//Welcome screen listeners
			viewWelcome.blackJackButton.setOnAction(new ListenerButton());
			viewWelcome.rouletteButton.setOnAction(new ListenerButton());
			
			viewWelcome.titleWelcome.setText("Welcome " + current_player.getName() + "!");
			viewWelcome.titleWelcome2.setText("Enjoy your stay and make some money!");
			viewWelcome.playerStats.setText("    Your cash : " + current_player.getCash() + "$");
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
		
		//Ask to choose a name
			String name = enterName("Enter player name:");
			
			//while the file contains at least 1 line and the name is already used
			while(!emptyFileCheck() && nameExists(name))
			{
				name = enterName("This name is already used. Please choose another name.");
			}
			
			int cash = Player.STARTING_MONEY;
		
		//Ask to choose an image
			Alert img_alert = new Alert(AlertType.CONFIRMATION);
			img_alert.setTitle("Image selection");
			img_alert.setContentText("Do you want to set an image for your player profile");
			
			ButtonType yes_button = new ButtonType("Yes");
			ButtonType no_button = new ButtonType("No");
			
			img_alert.getButtonTypes().setAll(yes_button, no_button);
			
			Optional<ButtonType> result = img_alert.showAndWait();
			
			String img = null;
			if(result.get() == yes_button)
			{
				img = choosePicture();
			}
			
			if(result.get() == no_button)
			{
				img = Player.DEFAULT_IMG_URL;
			}
		
		//Create the new player
			Player player = new Player(name, cash, img);
			current_player = player;
			
			if (current_player != null)
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
	
	public void manageSavePlayer()
	{
		BufferedWriter bufferWrite = null;
		BufferedReader bufferRead = null;
		
		//If a player profile is created or loaded AND if that player profile has been saved at least once before.
				/*In this case, we want to overwrite the player data in the file, 
				otherwise if we just save everything it will create a player profile repetition, and the
				program won't work as expected if we ever try to load that profile again */
				
				if (current_player != null && nameExists(current_player.getName()))
				{			
					try
					{	
						System.out.println("test");
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
						String newText = oldText.replaceAll(current_player.getName()+";\\d+;[^\\\\]+", 
															current_player.getName() + ";" + current_player.getCash() +";" +current_player.getImg());
						
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
		if (current_player != null && !nameExists(current_player.getName()))
		{
			
			try
			{
				bufferWrite= new BufferedWriter(new FileWriter(playerInfoFile, true));
				
				String player_save = null;
				
				player_save = current_player.getName() + ";" + current_player.getCash() + ";" + current_player.getImg();
				
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
		
	
	public void manageLoadPlayer()
	{
		//If the current player is not null, ask the player to save the 
		//current player profile before to load another one.
		if (current_player != null)
		{
			Alert save_alert = new Alert(AlertType.CONFIRMATION);
			save_alert.setTitle("Save");
			save_alert.setContentText("Do you want to save before to load a new player profile?");
			
			ButtonType yes_button = new ButtonType("Yes");
			ButtonType no_button = new ButtonType("No");
			ButtonType cancel_button = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
			
			save_alert.getButtonTypes().setAll(yes_button, no_button, cancel_button);
			
			Optional<ButtonType> result = save_alert.showAndWait();
			
			if(result.get() == yes_button)
			{
				manageSavePlayer();
			}
		}
		
		String msg = "Enter the name of the player profile you want to load";
		boolean name_found = false;
		
		while (!name_found)
		{
			String name = enterName(msg);	
			String line = "";
			BufferedReader bufferRead = null;
			
			//Read the save file (Player_info.dat) to find the player profile infos (name, cash and picture)
			try
			{
				bufferRead = new BufferedReader(new FileReader(playerInfoFile));
				
				//Vector to get the player profile infos (name, cash and picture), if found
				String[] vector = null;
				
					//Look for the name in the save file
					while((line = bufferRead.readLine())!= null)
					{	
						vector = line.split(";");
						if (vector[0].equals(name))
						{	
							//If the player profile is found, create a new current player
							// using its infos and stop the "while" research loop
							Player player = new Player(vector[0], Integer.parseInt(vector[1]), vector[2]);
							current_player = player;
							
							name_found = true;
							
							break;
						}
					}
					
				//If no match is found in the save file, ask the user for another name
				//or to cancel the load
				if (!name_found)
				{
					msg = "This name cannot be found, please enter another name";
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
		}
		
		manageNewPlayer();
	}
	
	public void manageBlackJack()
	{	
		viewBlackJack = new BlackJack();
		
		
		//BlackJack Listeners
			viewBlackJack.btnHit.setOnAction(new ListenerButton());
		
		stage.setTitle("BlackJack");
		stage.setScene(viewBlackJack.scene);
		stage.show();
	}
	
	public void manageRoulette()
	{
		viewRoulette = new Roulette();
		
		//Menu Listeners	
			viewRoulette.menuItemSave.setOnAction(new ListenerMenu());
			viewRoulette.menuItemLoadPlayer.setOnAction(new ListenerMenu());
		
		stage.setTitle("Roulette");
		stage.setScene(viewRoulette.scene);
		
		stage.show();
	}

	public static void main(String[] args) {

		Application.launch(args);
	}
}
