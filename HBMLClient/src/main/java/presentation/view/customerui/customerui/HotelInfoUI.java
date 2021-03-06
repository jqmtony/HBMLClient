package presentation.view.customerui.customerui;

import java.io.IOException;
import java.util.Date;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import vo.HotelVO;
import vo.UserVO;

public class HotelInfoUI extends Scene
{
	public HotelInfoUI(Parent root, Stage stage, Scene preScene,HotelVO hotelVO,UserVO userVO, Date checkinTime,boolean logined)
	{
		super(root);
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("customeruiFXML/HotelInfo.fxml"));
		try
		{
			this.setRoot(fxmlLoader.load());
		}catch(IOException e)
		{
			e.printStackTrace();
		}
		HotelInfoUIController controller = fxmlLoader.getController();
		controller.init(stage, preScene,this,hotelVO,userVO,checkinTime,logined);
	}
}
