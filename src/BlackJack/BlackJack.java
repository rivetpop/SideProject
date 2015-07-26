package BlackJack;
import Casino.GameInterface;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
		protected Text txtOptions = null;
		
		protected VBox playerZone = null;
		protected HBox playerCard = null;
		
		protected VBox dealerZone = null;
		protected HBox dealerCard = null;
		
		
		
		public Button btnHit;
		public Button btnStand;
		public Button btnSplit;
		public Button btnDouble;
		
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
			
			
			playerCard = new HBox();
			
			txtDealerName = new Text();
			txtDealerName.getStyleClass().add("text");
			dealerCard = new HBox();
			
			txtOptions = new Text();
			txtOptions.getStyleClass().add("text");
			
			dealerZone = new VBox();
			dealerZone.setAlignment(Pos.CENTER);
			dealerZone.getChildren().addAll(txtDealerName, dealerCard);
			
			playerZone = new VBox();
			playerZone.setAlignment(Pos.CENTER);
			playerZone.getChildren().addAll(playerCard, super.playerInfo);
			
			
			centerZone = new BorderPane();
			centerZone.setTop(dealerZone);
			centerZone.setBottom(playerZone);
			centerZone.setCenter(txtOptions);
			
			
			
		}
		
		public void createButtons()
		{
			btnHit = new Button("Hit");
			btnHit.setPrefSize(125, 50);
			btnHit.getStyleClass().add("Button");
			
			btnStand = new Button("Stand");
			btnStand.setPrefSize(125, 50);
			btnStand.getStyleClass().add("Button");
			
			btnSplit = new Button("Split");
			btnSplit.setPrefSize(125, 50);
			btnSplit.getStyleClass().add("Button");
			
			btnDouble = new Button("Double");
			btnDouble.setPrefSize(125, 50);
			btnDouble.getStyleClass().add("Button");
			
			lowerZone = new HBox();
			lowerZone.setPadding(new Insets(0, 40, 0, 40));
			lowerZone.setSpacing(10);
			lowerZone.getChildren().addAll(btnHit, btnStand);		
		}
}

