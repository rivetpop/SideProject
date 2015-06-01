

import javafx.application.Application;
import javafx.stage.Stage;

public class Control  extends Application{

	private BlackJack view;
	private Stage stage;
	
	public void start(Stage pStage){
		
		stage = pStage;
		view = new BlackJack();
		stage.setTitle("BlackJack");
		stage.setScene(view.scene);
		stage.show();
	}
	
	public static void main(String[] args) {

		Application.launch(args);
	}

}
