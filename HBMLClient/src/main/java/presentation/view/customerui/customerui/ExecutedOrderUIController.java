package presentation.view.customerui.customerui;

import java.util.Map;

import businesslogicservice.orderblservice.OrderBLService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import message.OrderStateMessage;
import presentation.view.customerui.customerui.UnexecutedOrderPageController.CheckOrderButtonCell;
import vo.OrderVO;

public class ExecutedOrderUIController {
	@FXML private TableView list;
	
	@FXML private TableColumn idColumn;
	@FXML private TableColumn hotelNameColumn;
	@FXML private TableColumn generateTimeColumn;
	@FXML private TableColumn	 executeTimeColumn;
	@FXML private TableColumn creditColumn;
	@FXML private TableColumn checkOrderButtonColumn;
	
	@FXML private ObservableList<OrderVO> executedOrderData;
	
	private Map<Integer, OrderVO> orderList;
	private OrderBLService service;
	
	public void init()
	{
		initTable();
	}
	
	private void initTable()
	{
		idColumn.setCellValueFactory(new PropertyValueFactory<>("orderID"));
		hotelNameColumn.setCellValueFactory(new PropertyValueFactory<>("hotelName"));
		generateTimeColumn.setCellValueFactory(new PropertyValueFactory<>("generateTime"));
		executeTimeColumn.setCellValueFactory(new PropertyValueFactory<>("cancelTime"));
		creditColumn.setCellValueFactory(new PropertyValueFactory<>("executeDDL"));
		checkOrderButtonColumn.setCellFactory(new Callback<TableColumn<OrderVO, Boolean>, TableCell<OrderVO, Boolean>>() 
		{
			@Override
			public TableCell call(TableColumn param)
			{
				return new CheckOrderButtonCell();
			}
		});
		
		executedOrderData = FXCollections.observableArrayList();
		
//		orderList = service.getExecutedOrderList(idColumn.get, userType);
//		for (OrderVO orderVO : orderList.values())
//		{
//			executedOrderData.add(orderVO);
//		}
		executedOrderData.add(new OrderVO(1111111, 00, 1111, "LVZJ", 000, OrderStateMessage.Abnormal, "20161808", null, null, null, null, 0, 0, 200));
		list.setItems(executedOrderData);
	}
	
	public class CheckOrderButtonCell extends TableCell<OrderVO, Boolean>
	{
		private Button checkOrderButton = new Button("查看");
		
		protected void updateItem(Boolean t, boolean empty)
		{
			super.updateItem(t, empty);
			if(empty)
			{
				setGraphic(null);
				setText(null);
			}else
			{
				setGraphic(checkOrderButton);
				setText(null);
			}
		}
	}
}