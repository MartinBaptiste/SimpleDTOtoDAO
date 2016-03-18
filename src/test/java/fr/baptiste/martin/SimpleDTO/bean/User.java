package fr.baptiste.martin.SimpleDTO.bean;

import fr.baptiste.martin.SimpleDTO.annotation.DAOTarget;
import fr.baptiste.martin.SimpleDTO.annotation.TransformWith;
import fr.baptiste.martin.SimpleDTO.impl.DTOBeanImpl;

import java.util.Date;

/**
 * Created by baptiste on 12/03/16.
 */
@DAOTarget(className = "fr.baptiste.martin.SimpleDTO.bean.Client")
public class User extends DTOBeanImpl {
    private String username;
    private String password;
    private Date birthday;

    public String getUsername() {
        return username;
    }

    @TransformWith(getter = "getName")
    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
}
