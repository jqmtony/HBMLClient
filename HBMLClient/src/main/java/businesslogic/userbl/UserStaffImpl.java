package businesslogic.userbl;

import businesslogicservice.userblservice.UserStaffService;
import message.ResultMessage;
import vo.UserVO;

/**
 * Created by alex on 12/13/16.
 */
public class UserStaffImpl implements UserStaffService{

    @Override
    public UserVO getUserData(int userID) {
        return null;
    }

    @Override
    public UserVO getUserData(String accountName) {
        return null;
    }

    @Override
    public ResultMessage login(String accountName, String pwd) {
        return null;
    }

    @Override
    public ResultMessage signup(UserVO vo) {
        return null;
    }
}