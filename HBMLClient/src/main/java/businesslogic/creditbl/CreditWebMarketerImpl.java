package businesslogic.creditbl;

import businesslogic.creditbl.helper.CreditHelper;
import businesslogicservice.creditblservice.CreditWebMarketerService;
import message.ResultMessage;

/**
 * Created by alex on 12/13/16.
 */
public class CreditWebMarketerImpl implements CreditWebMarketerService{

    CreditHelper creditHelper;

    public CreditWebMarketerImpl() {

        creditHelper=new CreditHelper();

    }


    @Override
    public ResultMessage resumeCreditValue(int userID, long price, int type,int orderID) throws Exception{

        return creditHelper.resumeCreditValue(userID,price,type,orderID);

    }

    @Override
    public ResultMessage addCreditValue(int UserID, long value)throws Exception {

        return creditHelper.addCreditValue(UserID,value);

    }
}
