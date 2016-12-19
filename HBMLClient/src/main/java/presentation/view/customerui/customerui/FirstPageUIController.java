package presentation.view.customerui.customerui;

import java.awt.event.ActionEvent;
import java.time.LocalDate;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import businesslogic.hotelInfobl.HotelCustomerImpl;
import businesslogic.hotelInfobl.helper.RegionHelper;
import businesslogicservice.hotelinfoblservice.HotelCustomerService;
import businesslogicservice.hotelinfoblservice.HotelRegionHelper;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.DateHelper;
import vo.HotelVO;
import vo.RegionVO;
import vo.UserVO;

public class FirstPageUIController {
	@FXML private TextField searchField;
	@FXML private Button searchButton;
	@FXML private Button firstPageButton;
	@FXML private Button orderButton;
	@FXML private Button commentButton;
	@FXML private Button personInfoButton;
	@FXML private Button loginButton;
	
	@FXML private Button searchTwoButton;
	
	@FXML private ChoiceBox<String> provinceBox;
	@FXML private ChoiceBox<String> cityBox;
	@FXML private ChoiceBox<String> regionBox;
	@FXML private DatePicker checkinTimePicker;
	@FXML private DatePicker checkoutTimePicker;
	@FXML private Button searchByConditionsButton;
	@FXML private CheckBox fiveStarCheckBox;
	@FXML private CheckBox fourStarCheckBox;
	@FXML private CheckBox threeStarCheckBox;
	@FXML private CheckBox twoStarCheckBox;
	@FXML private CheckBox oneStarCheckBox;
	
	private Scene firstPageUI;
	private Stage stage;
	
	private boolean logined;
	private UserVO userVO;
	private String userName;
	private int userID;
	
	private Map<Integer, HotelVO> hotelList = null;
	private HotelRegionHelper helper = null;
	private String provinceName;
	private ObservableList<String> provinceShowList;
	private String cityName;
	private ObservableList<String> cityShowList;
	private String regionName;
	private ObservableList<String> regionNameShowList ;
	private Map<String, Integer> regionNameMap ;
	private int regionID;
	private int star;
	private Date checkinTime;
	private Date checkoutTime;
	private DateHelper dateHelper;
	private ObservableList<String> defaultList;
	
	public void init(Stage stage, Scene firstPageUI, UserVO userVO, boolean logined)
	{
		this.stage = stage;
		this.firstPageUI = firstPageUI;
		helper = new RegionHelper();
		this.userVO = userVO;
		this.logined  = logined;
		initDatePicker();
		initProvinceBox();
	}
	
	@FXML
	private void orderPartAction()
	{
//		System.out.println(userID);
//		stage.setScene(new OrderFirstPageUIFromFirstPage(new Group(),stage,firstPageUI, userID));
		stage.setScene(new OrderFirstPageUIFromFirstPage(new Group(),stage,firstPageUI, 1));
	}
	
	@FXML
	private void commentPartAction()
	{
		stage.setScene(new CommentPageUIFromFirstPage(new Group(),stage,firstPageUI));
	}
	
	@FXML
	private void personalInfoPartAction()
	{
		if(!logined)
		{
			stage.setScene(new NullUserPage(new Group(), stage, firstPageUI));
		}else
		{
			stage.setScene(new PersonalCenterPage(new Group(), stage, firstPageUI, userVO));
		}
	}
	
	
	@FXML 
	private void searchByConditions()
	{
		String hotelName = null;
		if(searchField.getText()!=null&&!searchField.getText().isEmpty())
		{
			hotelName = searchField.getText();
		}
		//获取两个DatePicker里面的时间
		checkinTime = DateHelper.localDateToDate(checkinTimePicker.getValue());
		checkoutTime = DateHelper.localDateToDate(checkoutTimePicker.getValue());
		
		stage.setScene(new HotelListPageUI(new Group(), stage, firstPageUI,userVO, provinceName, cityName, regionID,hotelName, checkinTime, star, logined));
	}
	
	@FXML
	private void fiveStar()
	{
		star = 5;
		fourStarCheckBox.setSelected(false);
		threeStarCheckBox.setSelected(false);
		twoStarCheckBox.setSelected(false);
		oneStarCheckBox.setSelected(false);
	}
	@FXML
	private void fourStar()
	{
		star = 4;
		fiveStarCheckBox.setSelected(false);
		threeStarCheckBox.setSelected(false);
		twoStarCheckBox.setSelected(false);
		oneStarCheckBox.setSelected(false);
	}
	@FXML
	private void threeStar()
	{
		star = 3;
		fiveStarCheckBox.setSelected(false);
		fourStarCheckBox.setSelected(false);
		twoStarCheckBox.setSelected(false);
		oneStarCheckBox.setSelected(false);
	}
	@FXML
	private void twoStar()
	{
		star = 2;
		fiveStarCheckBox.setSelected(false);
		fourStarCheckBox.setSelected(false);
		threeStarCheckBox.setSelected(false);
		oneStarCheckBox.setSelected(false);
	}
	@FXML
	private void oneStar()
	{
		star = 1;
		fiveStarCheckBox.setSelected(false);
		fourStarCheckBox.setSelected(false);
		threeStarCheckBox.setSelected(false);
		twoStarCheckBox.setSelected(false);
	}
	private void initDatePicker()
	{
		checkinTimePicker.setPromptText(LocalDate.now().toString());
		checkoutTimePicker.setPromptText(LocalDate.now().toString());
		checkinTimePicker.setValue(LocalDate.now());
		final Callback<DatePicker, DateCell> dateCellFactory = new Callback<DatePicker, DateCell>() 
		{
			@Override
			public DateCell call(final DatePicker datePicker)
			{
				return new DateCell()
						{
							@Override
							public void updateItem(LocalDate item, boolean empty)
							{
								super.updateItem(item, empty);
								if(item.isBefore(checkinTimePicker.getValue().plusDays(1)))
								{
									setDisable(true);
									 setStyle("-fx-background-color: #ffc0cb;");
								}
							}
						};
			}
		};
		checkoutTimePicker.setDayCellFactory(dateCellFactory);
		checkoutTimePicker.setValue(LocalDate.now().plusDays(1));
	}
	
	private void initProvinceBox()
	{
		List<String> provinceMap = helper.getProvinces();
		provinceShowList = FXCollections.observableArrayList();
		provinceShowList.addAll(provinceMap);
		provinceBox.setItems(provinceShowList);
		defaultList = FXCollections.observableArrayList();
		defaultList.add("");
		
		provinceBox.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				provinceName = provinceShowList.get(newValue.intValue());
				cityBox.setItems(defaultList);
				regionBox.setItems(defaultList);
				initCityBox();
			}
		});
		
		cityBox.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				if (newValue.intValue()>=0) {
					cityName = cityShowList.get(newValue.intValue());
					regionBox.setItems(defaultList);
					initRegionBox();
				}
				
			}
			
		});
		
		regionBox.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				if (newValue.intValue()>=0) {
					regionID = regionNameMap.get(regionNameShowList.get(newValue.intValue()));
				}
			}
			
		});
		
	}
	
	private void initCityBox()
	{
		List<String> cityNameList = helper.getCities(provinceName);
		cityShowList = FXCollections.observableArrayList();
		cityShowList.addAll(cityNameList);
		defaultList = FXCollections.observableArrayList();
		defaultList.add("");
		cityBox.setItems(cityShowList);
	
	}
	
	private void initRegionBox()
	{
		Map<Integer, RegionVO> regionMap = helper.getRegions(cityName);
		regionNameMap = new LinkedHashMap<>();
		
		for (int key : regionMap.keySet()) {
			regionNameMap.put(regionMap.get(key).getRegionName(), key);
		}
	
		regionNameShowList = FXCollections.observableArrayList();
		regionNameShowList.addAll(regionNameMap.keySet());
		regionBox.setItems(regionNameShowList);
		
	
	}
	@FXML 
	private void login()
	{
		if(logined)
		{
			stage.setScene(new WelcomePageUILogoutEdition(new Group(), stage, firstPageUI, this, userVO.getName()));
		}else 
		{
			stage.setScene(new WelcomePageUI(new Group(), stage));
		}
	}
	public boolean getState()
	{
		return logined;
	}
	public void setState(boolean logined, String userName, int userID)
	{
		this.logined = logined;
		this.userName = userName;
		this.userID = userID;
	}
	public void setState(boolean logined)
	{
		this.logined = logined;
	}
}
