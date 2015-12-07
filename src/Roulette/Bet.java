package Roulette;

import java.util.ArrayList;

import javafx.scene.paint.Color;

//Bets objects are made of a bet type String (straight, corner, etc.), 
//a payout ratio, a list of logicalPocket(made of a number and a color),
//and a cash amount
public class Bet 
{
	private String betType;//Type of the bet (straight, corner,etc.)
	private double payoutRatio;//Ratio used to multiply the cash in case of a winning bet. Different for each bet type.
	private ArrayList<LogicalPocket> pocketList;//pocketList used to compare the bet with the winning pocket
	private int cash;//Amount of cash bet by the player
	
	protected Bet(String betType, ArrayList<LogicalPocket> pocketList, int cash)
	{
		this.betType = betType;
		this.payoutRatio = getPayoutRatio(betType);
		this.pocketList = pocketList;
		this.cash = cash;
	}
	
	public String getBetType() {
		return betType;
	}

	public void setBetType(String betType) {
		this.betType = betType;
	}

	public double getPayoutRatio(String betType)
	{	
		double payoutRatio = 0;
		switch (betType)
		{
			case "straight":
					payoutRatio = 35;
					break;
			case "split":
					payoutRatio = 17;
					break;
			case "basket":
					payoutRatio = 11;
					break;
			case "corner":
					payoutRatio = 8;
					break;
			case "topline":
				    payoutRatio = 6;
				    break;
			case "doublestreet":
					payoutRatio = 5;
					break;
			case "columnTop":
			case "columnMid":
			case "columnBottom":
					payoutRatio = 2;
					break;
			case "first12":
			case "second12":
			case "third12":
					payoutRatio = 2;
					break;
			case "odd":
					payoutRatio = 1;
					break;
			case "even":
					payoutRatio = 1;
					break;
			case "red":
					payoutRatio = 1;
					break;
			case "black":
					payoutRatio = 1;
					break;
			case "1to18":
					payoutRatio = 1;
					break;
			case "19to36":
					payoutRatio = 1;
					break;	   
		}
		return payoutRatio;
	}

	public void setPayoutRatio(double payoutRatio) {
		this.payoutRatio = payoutRatio;
	}

	public ArrayList<LogicalPocket> getPocketList() {
		return pocketList;
	}

	public void setPocketList(ArrayList<LogicalPocket> pocketList) {
		this.pocketList = pocketList;
	}

	public int getCash() {
		return cash;
	}

	public void setCash(int cash) {
		this.cash = cash;
	}
	
}
