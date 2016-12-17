package presentation.view.customerui.customerui;

import java.io.File;
import java.util.Date;
import java.util.Map;

import businesslogic.roomInfobl.RoomInfoCustomerServiceImpl;
import businesslogicservice.roominfoblservice.RoomInfoCustomerService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.ImageHelper;
import vo.HotelVO;
import vo.RoomInfoVO;

public class HotelInfoUIController 
{
		@FXML private TableView list;
		@FXML private Button backButton;
		@FXML private TableColumn typeColumn;
		@FXML private TableColumn priceColumn;
		@FXML private TableColumn buttonColumn;
		
		@FXML private ImageView hotelImageView;
		@FXML private Label addressLabel;
		@FXML private Label introduceLabel;
		@FXML private Label facilityLabel;
		
		private Stage stage;
		private Scene preScene;
		private Scene hotelInfoPageScene;
		
		private ObservableList<RoomInfoVO> roomdata;
		
		private RoomInfoCustomerService service = new RoomInfoCustomerServiceImpl();
		private Map<String, RoomInfoVO> roomlist;
		private HotelVO hotelVO;
		private RoomInfoVO selectedRoom;
		private Date checkinTime;
		
		public void init(Stage stage, Scene preScece, Scene hotelInfoPageScene, HotelVO hotelVO,Date checkinTime)
		{
			this.stage = stage;
			this.preScene = preScece;
			this.hotelInfoPageScene = hotelInfoPageScene;
			this.hotelVO = hotelVO;
			this.checkinTime = checkinTime;
			initLabel();
			initTable();
		}
		
		@FXML 
		private void back()
		{
			stage.setScene(preScene);
		}
		
		private void initLabel()
		{
			Image defaultImage = null;
			for (File hotelImageFile : hotelVO.getEnvironment())
			{
				Image hotelImage = ImageHelper.fileToImage(hotelImageFile);
				if(hotelImage!=null){defaultImage = hotelImage;}
			}
			hotelImageView.setImage(defaultImage);
			addressLabel.setText(hotelVO.getAddress());
			addressLabel.setWrapText(true);
			introduceLabel.setText(hotelVO.getIntroduction());
			introduceLabel.setWrapText(true);
			facilityLabel.setText(hotelVO.getFacility());
			facilityLabel.setWrapText(true);
		}
		private void initTable()
		{
			typeColumn.setCellValueFactory(new PropertyValueFactory<>("roomType"));
			priceColumn.setCellValueFactory(new PropertyValueFactory<>("defaultPrice"));
			buttonColumn.setCellFactory(new Callback<TableColumn<RoomInfoVO, Boolean>, TableCell<RoomInfoVO, Boolean>>()
			{
				@Override
				public TableCell call(TableColumn param)
				{
					return new MakeOrderButtonCell(stage);
				}
			});
			roomdata = FXCollections.observableArrayList();
			if(service.getRoomList(hotelVO.getId(), checkinTime) != null)
			{
				for(RoomInfoVO roomInfoVO:service.getRoomList(hotelVO.getId(), checkinTime).values())
				{
					roomdata.add(roomInfoVO);
				}
			}else
			{
				System.out.println("service.getRoomList(hotelVO.getId(), checkinTime) = null");
			}
			list.setItems(roomdata);
		}
		
		public class MakeOrderButtonCell extends TableCell<RoomInfoVO, Boolean>
		 {
			 private Button makeOrderButton = new Button("预订");
			 
			 public MakeOrderButtonCell(Stage stage)
			 {
				 makeOrderButton.setOnAction((ActionEvent e)->{
					 int selectedIndex = getTableRow().getIndex();
					 selectedRoom = (RoomInfoVO)list.getItems().get(selectedIndex);
					 stage.setScene(new MakeOrderPage(new Group(), stage, hotelInfoPageScene, hotelVO, selectedRoom, checkinTime));
				 });
			 }
			 
			 protected void updateItem(Boolean t, boolean empty) 
			 {
				super.updateItem(t, empty);
				if(empty)
				{
					setGraphic(null);
					setText(null);
				}else
				{
					setGraphic(makeOrderButton);
					setText(null);
				}
			 }
		 }
		
}
