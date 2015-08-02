package BlackJack;
import Casino.GameInterface;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

//BlackJack Class
public class BlackJack extends GameInterface {

	
		private HBox lowerZone = null;
		protected BorderPane centerZone = null;
		//public Text txtPlayerName = null;
		public Text txtDealerName = null;
		public Text txtOptions = null;
		
		protected VBox playerZone = null;
		protected HBox playerCards = null;
		public ImageView playerCard1 = null;
		public ImageView playerCard2 = null;
		public ImageView playerCard3 = null;
		public ImageView playerCard4 = null;
		public ImageView playerCard5 = null;
		public ImageView playerCard6 = null;
		public ImageView playerCard7 = null;
		public ImageView playerCard8 = null;
		public ImageView playerCard9 = null;
		public ImageView playerCard10 = null;
		public ImageView playerCard11 = null;
		
		
		protected VBox dealerZone = null;
		protected HBox dealerCards = null;
		public ImageView dealerCard1 = null;
		public ImageView dealerCard2 = null;
		public ImageView dealerCard3 = null;
		public ImageView dealerCard4 = null;
		public ImageView dealerCard5 = null;
		public ImageView dealerCard6 = null;
		public ImageView dealerCard7 = null;
		public ImageView dealerCard8 = null;
		public ImageView dealerCard9 = null;
		public ImageView dealerCard10 = null;
		
		
		public Button btnHit;
		public Button btnStand;
		public Button btnDraw;
		public Button btnBet;
		
		public Scene scene;
		private BorderPane root;
		
		
		public BlackJack(){
			
			//GameInterface gameInterface = new GameInterface();
			
			createMid();
			createButtons();
			
			root = new BorderPane();
			root.setTop(super.upperZone);
			root.setBottom(lowerZone);
			root.setCenter(centerZone);
			
			scene = new Scene(root, 800, 800);
			root.setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
			scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
		}
		
		
		public void createMid(){
			
			
			playerCards = new HBox();
			playerCards.setAlignment(Pos.CENTER);
			playerCard1 = new ImageView();
			playerCard2 = new ImageView();
			playerCard3 = new ImageView();
			playerCard4 = new ImageView();
			playerCard5 = new ImageView();
			playerCard6 = new ImageView();
			playerCard7 = new ImageView();
			playerCard8 = new ImageView();
			playerCard9 = new ImageView();
			playerCard10 = new ImageView();
			playerCard11 = new ImageView();
			playerCards.getChildren().addAll(playerCard1, playerCard2, playerCard3, 
					 playerCard4, playerCard5, playerCard6,
					 playerCard7, playerCard8, playerCard9,
					 playerCard10);
			
			txtDealerName = new Text();
			txtDealerName.getStyleClass().add("text");
			
			dealerCards = new HBox();
			dealerCards.setAlignment(Pos.CENTER);
			dealerCard1 = new ImageView();
			dealerCard2 = new ImageView();
			dealerCard3 = new ImageView();
			dealerCard4 = new ImageView();
			dealerCard5 = new ImageView();
			dealerCard6 = new ImageView();
			dealerCard7 = new ImageView();
			dealerCard8 = new ImageView();
			dealerCard9 = new ImageView();
			dealerCard10 = new ImageView();
			dealerCards.getChildren().addAll(dealerCard1, dealerCard2, dealerCard3, 
											 dealerCard4, dealerCard5, dealerCard6,
											 dealerCard7, dealerCard8, dealerCard9,
											 dealerCard10);
			
			txtOptions = new Text();
			txtOptions.getStyleClass().add("text");
			
			dealerZone = new VBox();
			dealerZone.setAlignment(Pos.CENTER);
			dealerZone.getChildren().addAll(txtDealerName, dealerCards);
			
			playerZone = new VBox();
			playerZone.setAlignment(Pos.CENTER);
			playerZone.getChildren().addAll(playerCards, super.playerInfo);
			
			
			centerZone = new BorderPane();
			centerZone.setTop(dealerZone);
			centerZone.setBottom(playerZone);
			centerZone.setCenter(txtOptions);
			
		}
		
		public void createButtons(){
			
			btnHit = new Button("Hit");
			btnHit.setPrefSize(125, 50);
			btnHit.getStyleClass().add("Button");
			btnHit.setDisable(true);
			
			btnStand = new Button("Stand");
			btnStand.setPrefSize(125, 50);
			btnStand.getStyleClass().add("Button");
			btnStand.setDisable(true);
			
			btnDraw = new Button("Draw");
			btnDraw.setPrefSize(125, 50);
			btnDraw.getStyleClass().add("Button");
			btnDraw.setDisable(true);
			
			btnBet = new Button("Bet");
			btnBet.setPrefSize(125, 50);
			btnBet.getStyleClass().add("Button");
			btnBet.setDisable(true);
			
			lowerZone = new HBox();
			lowerZone.setPadding(new Insets(10, 60, 10, 60));
			lowerZone.setSpacing(10);
			lowerZone.getChildren().addAll(btnBet, btnDraw, btnHit, btnStand);		
		}
}

