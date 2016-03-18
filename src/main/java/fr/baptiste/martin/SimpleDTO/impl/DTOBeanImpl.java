package fr.baptiste.martin.SimpleDTO.impl;

import fr.baptiste.martin.SimpleDTO.DAOBean;
import fr.baptiste.martin.SimpleDTO.DTOBean;
import fr.baptiste.martin.SimpleDTO.abs.BeanAbstract;
import fr.baptiste.martin.SimpleDTO.exception.TransformationException;

/**
 * Super parent DTO bean
 */
public class DTOBeanImpl extends BeanAbstract implements DTOBean {
    /**
     * Default constructor init transformer
     */
    public DTOBeanImpl() {
        init();
    }

    public DAOBean transformToDAO() throws TransformationException {
        Class dao = null;
        String name = null;
        try {
            name = getDAOName();
        } catch (ClassNotFoundException e) {
            throw new TransformationException(e.getMessage(), e);
        }
        try {
            dao = Class.forName(name);
        } catch (ClassNotFoundException e) {
            throw new TransformationException("DAO object " + name + " not found", e);
        }

        DAOBean result = null;
        try {
            result = (DAOBean) dao.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new TransformationException("DAO object instance error", e);
        }

        transformationAbstract(dao.getMethods(), result);

        return result;
    }
}
