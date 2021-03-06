package presentation.view.customerui.customerui;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class OrderFirstPageUIFromFirstPageController {
	@FXML private Button backButton;
	@FXML private Button secondOrderButton;
	@FXML private Button thirdOrderButton;
	@FXML private Button forthOrderButton;
	@FXML private Button fifthOrderButton;
	
	@FXML private VBox contentBox;

	
	private Stage stage;
	private Scene preScene;
	private int userID;
	private Scene orderFirstPageScene;
	
	public void init(Stage stage, Scene preScene,Scene orderFirstPageScene,int userID)
	{
		this.stage = stage;
		this.preScene = preScene;
		this.orderFirstPageScene = orderFirstPageScene;
		this.userID = userID;
		contentBox.getChildren().add(new UnexecutedOrderPageUI(stage, userID));
	}
	
	@FXML
	private void back()
	{
		stage.setScene(preScene);
	}

	@FXML
	private void secondButtonAction()//未完成订单
	{
		contentBox.getChildren().remove(0);
		contentBox.getChildren().add(new UnexecutedOrderPageUI(stage,userID));
	}
	

	@FXML
	private void thirdButtonAction()//已执行订单
	{
		contentBox.getChildren().remove(0);
		contentBox.getChildren().add(new ExecutedOrderPageUI(stage,userID));
	}
	

	@FXML
	private void forthButtonAction()//已撤销订单
	{
		contentBox.getChildren().remove(0);
		contentBox.getChildren().add(new CancelledOrderPageUI(stage,userID));
	}
	
	@FXML
	private void fifthBuutonAction()//异常订单
	{
		contentBox.getChildren().remove(0);
		contentBox.getChildren().add(new AbnormalOrderPageUI(stage,orderFirstPageScene,userID));
	}
	
	
}
