package businesslogic.userbl;

import businesslogicservice.userblservice.UserTransferService;
import dao.user.UserDao;
import po.UserPO;
import rmi.ClientRunner;

import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Created by alex on 16-11-9.
 */
public class UserTransferImpl implements UserTransferService {
    private ArrayList<Object> pos;

    private UserDao userDao;

    public UserTransferImpl(){
        userDao= ClientRunner.remoteHelper.getUserDao();
        try {
            pos=userDao.getUserList();
        } catch (RemoteException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    @Override
    public CustomerVO findCustomer(String id) {
        userDao=new UserDaoImpl_stub();
        try {
            CustomerVO vo=(CustomerVO) userDao.find(id);
            return vo;
        } catch (RemoteException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public UserPO toCustomerPO(CustomerVO vo) {
        return new UserPO(vo.getId());
    }

    @Override
    public StaffVO findStaff(String id) {
        userDao=new UserDaoImpl_stub();
        try {
            StaffVO vo=(StaffVO) userDao.find(id);
            return vo;
        } catch (RemoteException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public UserPO toStaffPO(StaffVO vo) {
        return new UserPO(vo.getId());
    }

    @Override
    public WebMarketerVO findWebMarketer(String id) {
        userDao=new UserDaoImpl_stub();
        try {
            WebMarketerVO vo=(WebMarketerVO) userDao.find(id);
            return vo;
        } catch (RemoteException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public UserPO toWebMarketerPO(WebMarketerVO vo) {
        return new UserPO(vo.getId());
    }

    @Override
    public WebManagerVO findWebManager(String id) {
        userDao=new UserDaoImpl_stub();
        try {
            WebManagerVO vo=(WebManagerVO) userDao.find(id);
            return vo;
        } catch (RemoteException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public UserPO toWebManagerPO(WebManagerVO vo) {
        return new UserPO(vo.getId());
    }
}
