package fr.baptiste.martin.SimpleDTO.bean;

import fr.baptiste.martin.SimpleDTO.impl.DTOBeanImpl;

/**
 * Created by baptiste on 12/03/16.
 */
public class Bean1DTO extends DTOBeanImpl {
    private String name;
    private int value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}