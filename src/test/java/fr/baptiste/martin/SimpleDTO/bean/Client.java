package fr.baptiste.martin.SimpleDTO.bean;

import fr.baptiste.martin.SimpleDTO.annotation.DTOTarget;
import fr.baptiste.martin.SimpleDTO.annotation.NoTransform;
import fr.baptiste.martin.SimpleDTO.annotation.TransformWith;
import fr.baptiste.martin.SimpleDTO.impl.DAOBeanImpl;

import java.util.Date;

/**
 * Created by baptiste on 12/03/16.
 */
@DTOTarget(className = "fr.baptiste.martin.SimpleDTO.bean.User")
public class Client  extends DAOBeanImpl {
    private String name;
    private String password;
    private Date birthday;

    public String getName() {
        return name;
    }

    @TransformWith(getter = "getUsername")
    public void setName(String name) {
        this.name = name;
    }

    @NoTransform
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
