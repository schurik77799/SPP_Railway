package code.controller.user;

import code.controller.GetAction;
import code.service.GenericService;
import com.opensymphony.xwork2.ActionSupport;
import code.service.UserService;
import code.model.User;

import java.util.List;

/**
 * Created by PC-Alyaksei on 14.03.2016.
 */
public class UserListShowingAction extends GetAction {

    private List<User> users;


    // I don't know why, but when i try to access on url that connected with this action
    // it response i can't find method  view in this class, so it was created!
    @Override
    public String view() {
        users = new GenericService<User, Integer>(User.class).findAll();
        return SUCCESS;
    }


    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

}
