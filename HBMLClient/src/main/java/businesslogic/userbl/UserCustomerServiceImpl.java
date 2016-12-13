/*package businesslogic.userbl;

import businesslogic.utility.TransferImpl;
import businesslogicservice.userblservice.UserCustomerService;
import dao.UserDao;
import message.ResultMessage;
import po.UserPO;
import rmi.ClientRunner;
import vo.UserVO;

import java.rmi.RemoteException;
import java.util.ArrayList;

*//**
 * Created by alex on 16-11-9.
 *//*
public class UserCustomerServiceImpl implements UserCustomerService{
    private ArrayList<UserPO> customerInfo;

    private UserDao userDao;

    public UserCustomerServiceImpl(){
        userDao= ClientRunner.remoteHelper.getUserDao();
        try{
            customerInfo=userDao.getUserList();
        }catch (RemoteException e){
            e.printStackTrace();
        }

    }


    @Override
    public ResultMessage modifyUserInfo(UserVO vo) {
        userDao=new UserDaoImpl_stub();
        try {
            TransferImpl transfer=new TransferImpl();
            UserPO po=transfer.voToPo(vo);
            userDao.update(po);
        } catch (RemoteException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return ResultMessage.failure;
    }

    @Override
    public UserVO showUserInfo() {
        userDao=new UserDaoImpl_stub();
        try {
            TransferImpl transfer=new TransferImpl();
            UserVO vo=transfer.poToVo(userDao.getUserList().get(0));
            return(vo);
        } catch (RemoteException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ResultMessage login(String id, String pwd) {
        userDao=new UserDaoImpl_stub();
        try {
            userDao.login(id,pwd);
            return ResultMessage.success;
        } catch (RemoteException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return ResultMessage.failure;
    }

    @Override
    public ResultMessage logout() {
        return ResultMessage.success;
    }

    @Override
    public ResultMessage signup(UserVO vo) {
        userDao=new UserDaoImpl_stub();
        try {
            TransferImpl transfer=new TransferImpl();
            userDao.signup(transfer.voToPo(vo));
            return ResultMessage.success;
        } catch (RemoteException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return ResultMessage.failure;
    }
}
*/