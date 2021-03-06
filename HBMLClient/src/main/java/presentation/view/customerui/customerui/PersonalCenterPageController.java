package presentation.view.customerui.customerui;



import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import vo.UserVO;

public class PersonalCenterPageController {
	@FXML private Button backButton;
	@FXML private Button vipEnrollButton;
	@FXML private ImageView headImageView;
	@FXML private Label nameLabel;
	@FXML private Label creditValueLabel;
	@FXML private Button myInfoButton;
	@FXML private Button hotelButton;
	@FXML private Button creditButton;
	
	private Stage stage;
	private Scene preScene;
	private Scene presentScene;
	
	
	private UserVO userVO;
	public void init(Stage stage, Scene preScene, Scene presentScene, UserVO userVO)
	{
		this.stage = stage;
		this.preScene = preScene;
		this.presentScene = presentScene;
		this.userVO = userVO;
		nameLabel.setText(userVO.getName());
		if(userVO.getPortrait()!=null)
		{
			Image  headImage=new Image("file:///"+userVO.getPortrait().getAbsolutePath().replace('\\', '/').replaceAll("HBMLClient/HBMLClient", "Final_HBMSServer/HBMSServer"));
			headImageView.setImage(headImage);
		}
	}
	
	@FXML
	private void back()
	{
		stage.setScene(preScene);
	}
	
	@FXML
	private void editInfo()
	{
		stage.setScene(new PersonInfoPage(new Group(), stage, presentScene,userVO));
	}
	
	@FXML
	private void enroll()
	{
		stage.setScene(new EnrollVIPPage(new Group(), stage, presentScene, userVO));
	}
	
	@FXML 
	private void myHotel()
	{
		stage.setScene(new MyHotelListPage(new Group(), stage, presentScene,userVO));
	}
	
	@FXML
	private void myCreditRecord()
	{
		stage.setScene(new CreditRecordPageUI(new Group(), stage, presentScene, userVO));
	}
}
