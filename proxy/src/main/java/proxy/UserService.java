package proxy;

/**
 * author  : wangjia
 * time    : 2016/12/2 17:06
 * contact : wangjia_yql@qq.com
 * desc    :
 */
public class UserService implements IUserService {
    @Override
    public void addUser() {
        System.out.println("add user");
    }
}
