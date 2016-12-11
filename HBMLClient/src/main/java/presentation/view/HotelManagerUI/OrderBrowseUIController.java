package presentation.view.HotelManagerUI;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import presentation.view.customerui.customerui.OrderVO;
import vo.HotelVO;

/**
 * Created by LENOVO on 2016/12/1.
 */
public class OrderBrowseUIController {

    /**
     * 未执行订单列表
     */
    @FXML private TableView unexecutedOrderTableView;
    @FXML private TableColumn unexecutedCustomerNameTableColumn;
    @FXML private TableColumn unexecutedOrderIdTableColumn;
    @FXML private TableColumn unexecutedPriceTableColumn;
    @FXML private TableColumn unexecutedOperationTableColumn;

    /**
     * 已执行订单列表
     */
    @FXML private TableView executedOrderTableView;
    @FXML private TableColumn executedCustomerNameTableColumn;
    @FXML private TableColumn executedOrderIdTableColumn;
    @FXML private TableColumn executedPriceTableColumn;
    @FXML private TableColumn executedOperationTableColumn;

    /**
     * 异常订单列表
     */
    @FXML private TableView abnormalOrderTableView;
    @FXML private TableColumn abnormalCustomerNameTableColumn;
    @FXML private TableColumn abnormalOrderIdTableColumn;
    @FXML private TableColumn abnormalPriceTableColumn;
    @FXML private TableColumn abnormalOperationTableColumn;

    /**
     * 已撤销订单
     */
    @FXML private TableView cancelledOrderTableView;
    @FXML private TableColumn cancelledCustomerNameTableColumn;
    @FXML private TableColumn cancelledOrderIdTableColumn;
    @FXML private TableColumn cancelledPriceTableColumn;
    @FXML private TableColumn cancelledOperationTableColumn;


    private ObservableList unexecutedData;
    private ObservableList executedData;
    private ObservableList abnormalData;
    private ObservableList cancelledData;
    private VBox infoVBox;
    private VBox thisVBox;
    public void init(VBox infoVBox,VBox thisVBox){
        this.infoVBox=infoVBox;
        this.thisVBox=thisVBox;
        initTableView();
    }


    /**
     * 点击未执行订单按钮，显示未执行订单列表
     */
    @FXML
    private void toUnexecutedOrder(){
        unexecutedOrderTableView.setVisible(true);
        executedOrderTableView.setVisible(false);
        abnormalOrderTableView.setVisible(false);
        cancelledOrderTableView.setVisible(false);
    }

    /**
     * 点击已执行订单按钮,显示已执行订单列表
     */
    @FXML
    private void toExecutedOrder(){
        unexecutedOrderTableView.setVisible(false);
        executedOrderTableView.setVisible(true);
        abnormalOrderTableView.setVisible(false);
        cancelledOrderTableView.setVisible(false);
    }

    /**
     * 点击异常订单按钮,显示异常订单列表
     */
    @FXML
    private void toAbnormalOrder(){
        unexecutedOrderTableView.setVisible(false);
        executedOrderTableView.setVisible(false);
        abnormalOrderTableView.setVisible(true);
        cancelledOrderTableView.setVisible(false);
    }

    /**
     * 点击已撤销订单按钮,显示已撤销订单列表
     */
    @FXML
    private void toCancelledOrder(){
        unexecutedOrderTableView.setVisible(false);
        executedOrderTableView.setVisible(false);
        abnormalOrderTableView.setVisible(false);
        cancelledOrderTableView.setVisible(true);
    }

    /**
     * 点击浏览订单详情按钮,跳转至对应界面
     */
//    @FXML
//    private void toOrderInfo(){
//        stage.setScene(new HotelOrderInfoUI(new Group(),stage,thisScene,loginScene));
//    }

    /**
     * 初始化hotelOrderTableView
     */
    private void initTableView(){
        unexecutedCustomerNameTableColumn.setCellValueFactory(new PropertyValueFactory<>("userID"));
        unexecutedOrderIdTableColumn.setCellValueFactory(new PropertyValueFactory<>("orderID"));
        unexecutedPriceTableColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        unexecutedOperationTableColumn.setCellFactory(new Callback<TableColumn<OrderVO,Boolean>, TableCell<OrderVO,Boolean>>() {
            @Override
            public TableCell call(TableColumn param) {
                return new HotelOrderOperationButtonCell(infoVBox,thisVBox,0);
            }
        });
        unexecutedData=FXCollections.observableArrayList();

        executedCustomerNameTableColumn.setCellValueFactory(new PropertyValueFactory<>("userID"));
        executedOrderIdTableColumn.setCellValueFactory(new PropertyValueFactory<>("orderID"));
        executedPriceTableColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        executedOperationTableColumn.setCellFactory(new Callback<TableColumn<OrderVO,Boolean>, TableCell<OrderVO,Boolean>>() {
            @Override
            public TableCell call(TableColumn param) {
                return new HotelOrderOperationButtonCell(infoVBox,thisVBox,1);
            }
        });
        executedData=FXCollections.observableArrayList();

        abnormalCustomerNameTableColumn.setCellValueFactory(new PropertyValueFactory<>("userID"));
        abnormalOrderIdTableColumn.setCellValueFactory(new PropertyValueFactory<>("orderID"));
        abnormalPriceTableColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        abnormalOperationTableColumn.setCellFactory(new Callback<TableColumn<OrderVO,Boolean>, TableCell<OrderVO,Boolean>>() {
            @Override
            public TableCell call(TableColumn param) {
                return new HotelOrderOperationButtonCell(infoVBox,thisVBox,2);
            }
        });
        abnormalData=FXCollections.observableArrayList();

        cancelledCustomerNameTableColumn.setCellValueFactory(new PropertyValueFactory<>("userID"));
        cancelledOrderIdTableColumn.setCellValueFactory(new PropertyValueFactory<>("orderID"));
        cancelledPriceTableColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        cancelledOperationTableColumn.setCellFactory(new Callback<TableColumn<OrderVO,Boolean>, TableCell<OrderVO,Boolean>>() {
            @Override
            public TableCell call(TableColumn param) {
                return new HotelOrderOperationButtonCell(infoVBox,thisVBox,2);
            }
        });
        cancelledData=FXCollections.observableArrayList();

    }

    public class HotelOrderOperationButtonCell extends TableCell<OrderVO,Boolean>{
        private HBox operationHBox=new HBox();
        private Button viewButton=new Button();
        private Button executeButton=new Button();
        private Button checkOutButton=new Button();
        private VBox infoVBox;
        private VBox beforeVBox;
        public HotelOrderOperationButtonCell(VBox infoVBox,VBox beforeVBox,int orderType){
            this.infoVBox=infoVBox;
            this.beforeVBox=beforeVBox;
            operationHBox.getChildren().add(viewButton);
            if(orderType==0){
                operationHBox.getChildren().add(executeButton);
            }else if(orderType==1){
                operationHBox.getChildren().add(checkOutButton);
            }

            ButtonEvent();
        }

        private void ButtonEvent(){
            viewButton.setOnAction((ActionEvent e)->{
                infoVBox.getChildren().remove(0);
                infoVBox.getChildren().add(new HotelOrderInfoUI(infoVBox,beforeVBox));
            });
            executeButton.setOnAction((ActionEvent e)->{

            });
            checkOutButton.setOnAction((ActionEvent e)->{

            });
        }

        @Override
        protected  void updateItem(Boolean t,boolean empty){
            super.updateItem(t,empty);
            if(empty){
                setGraphic(null);
                setText(null);
            }else{
                setGraphic(operationHBox);
                setText(null);
            }
        }
    }


}
