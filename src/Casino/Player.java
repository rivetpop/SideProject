package Casino;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;


public class Player {

	public static final String DEFAULT_NAME = "default guy";
	
	public static final int NAME_MIN_LENGTH = 2;
	
	public static final int NAME_MAX_LENGTH = 15;
	
	public static final int STARTING_MONEY = 500;
	
	public static final String DEFAULT_IMG_URL = "anonym.jpg";
	
	private String name = "";
	
	private int cash = 0;
	
	private String picture_URL = null;
	
	public Player(String p_name, int p_cash, String p_picture_URL)
	{
		setName(p_name);
		setCash(p_cash);
		setImg(p_picture_URL);
	}
	
	public String getName(){
		
		return name;
	}
	
	public int getCash(){
		
		return cash;
	}
	
	public boolean setName(String pName){
		
		if(validateName(pName)){
			
			name = pName;
		}
		
		return(validateName(pName));
	}
	
	public boolean setCash(int pCash){
		
		if(validateCash(pCash)){
			
			cash = pCash;
		}
		
		return(validateCash(pCash));
	}
	
	public void setImg(String p_picture_URL)
	{
		picture_URL = p_picture_URL;
	}
	
	public static boolean validateName(String pName){
		
		return (pName.length() < NAME_MAX_LENGTH && pName.length() > NAME_MIN_LENGTH);
	}
	
	public boolean validateCash(int pCash){
		
		return (pCash >= 0);
	}
	
	public boolean validatePlayerHand(Card pHand){
		
		return (pHand != null);
	}
}
