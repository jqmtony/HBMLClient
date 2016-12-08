package presentation.view.WebMarketerUI;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Created by LENOVO on 2016/12/1.
 */
public class WebMarketerUIController {


    @FXML private Button exceptionOrderButton;
    @FXML private Button webPromotionButton;
    @FXML private Button regionPromotionButton;
    @FXML private Button memberGradeButton;
    @FXML private Button creditValueButton;
    @FXML private VBox infoVBox;

    private Stage stage;
    private Scene loginScene;
    public void init(Stage stage,Scene loginScene){
        this.stage=stage;
        this.loginScene=loginScene;
        infoVBox.getChildren().add(new ExceptionOrderUI());
    }

    /**
     * 点击注销按钮，返回到登录界面
     */
    @FXML
    private void backToLogin(){
        stage.setScene(loginScene);
    }


    /**
     * 点击异常订单处理按钮,跳转至对应界面
     */
    @FXML
    private void toExceptionOrder(){
        infoVBox.getChildren().remove(0);
        infoVBox.getChildren().add(new ExceptionOrderUI());
    }

    /**
     * 点击网站促销活动指定按钮，跳转至对应界面
     */
    @FXML
    private void toWebPromotion(){
        infoVBox.getChildren().remove(0);
        infoVBox.getChildren().add(new WebPromotionUI(infoVBox));
    }

    /**
     * 点击特定商圈折扣制定按钮，跳转至对应界面
     */
    @FXML
    private void toRegionPromotion(){
        infoVBox.getChildren().remove(0);
        infoVBox.getChildren().add(new RegionPromotionUI());
    }

    /**
     * 点击会员等级制定，跳转至对应界面
     */
    @FXML
    private void toMemberGrade(){

    }

    /**
     * 点击信用值充值界面,跳转至对应界面
     */
    @FXML
    private void toCreditValue(){
        infoVBox.getChildren().remove(0);
        infoVBox.getChildren().add(new SearchCustomerUI(infoVBox));
    }
}