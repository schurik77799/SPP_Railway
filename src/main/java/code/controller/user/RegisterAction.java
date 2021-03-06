package code.controller.user;

import code.controller.PostAction;
import code.dao.hibernatedao.GenericHibernateDao;
import code.model.Role;
import code.model.User;
import code.service.GenericService;
import code.service.RoleService;
import code.service.UserService;
import code.infrastructure.ValidationUtils;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by PC-Alyaksei on 18.03.2016.
 */
public class RegisterAction extends PostAction {

    private static final long serialVersionUID = 1L;
    public static final int ADMIN_ROLE_ID = 1;

    private User user;
    private List<String> errorList;


    @Override
    public String create() {
        Role role = RoleService.getUserRole();
        if (getUserFromSession() == null || getUserFromSession().getRole().getId() != ADMIN_ROLE_ID) {
            user.setRole(role);
        }

        if (validate(user)) {
            try {
                user.setLogin(user.getLogin().toLowerCase());
                user.setPassword(user.getPassword().toLowerCase());
                new UserService().persist(user);
            } catch (Exception e) {
                errorList = new ArrayList();
                errorList.add("Inputted data isn't correct!");
                e.printStackTrace();
            }
        }

        return SUCCESS;
    }

    private boolean validate(User user) {
        errorList = ValidationUtils.validate(user);

        if (new UserService().getUserByLogin(user.getLogin()) != null) {
            errorList.add("User with such login is already exists!");
        }

        if (errorList.size() == 0) {
            errorList = null;
            return true;
        }
        else {
            return false;
        }
    }


    private String generateEmptyFieldMessage(String field) {
        return "Field " + field + " shouldn't be empty!";
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    public List<String> getErrorList() {
        return errorList;
    }

    public void setErrorList(List<String> errorList) {
        this.errorList = errorList;
    }
}

