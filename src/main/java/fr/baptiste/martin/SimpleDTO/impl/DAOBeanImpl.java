package fr.baptiste.martin.SimpleDTO.impl;

import fr.baptiste.martin.SimpleDTO.DAOBean;
import fr.baptiste.martin.SimpleDTO.DTOBean;
import fr.baptiste.martin.SimpleDTO.abs.BeanAbstract;
import fr.baptiste.martin.SimpleDTO.exception.TransformationException;

/**
 */
public class DAOBeanImpl extends BeanAbstract implements DAOBean {
    /**
     * Default constructor init transformer
     */
    public DAOBeanImpl() {
        init();
    }

    public DTOBean transformToDTO() throws TransformationException {
        Class dto = null;
        String name = null;
        try {
            name = getDTOName();
            dto = Class.forName(name);
        } catch (ClassNotFoundException e) {
            throw new TransformationException("DTO object " + name + " not found", e);
        }
        DTOBean result = null;
        try {
            result = (DTOBean) dto.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new TransformationException("DTO object instance error", e);
        }

        transformationAbstract(dto.getMethods(), result);

        return result;
    }
}
