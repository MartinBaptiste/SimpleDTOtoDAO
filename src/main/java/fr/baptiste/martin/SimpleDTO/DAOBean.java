package fr.baptiste.martin.SimpleDTO;

import fr.baptiste.martin.SimpleDTO.exception.TransformationException;

import java.lang.reflect.InvocationTargetException;

/**
 * Super interface DAO bean
 */
public interface DAOBean {
    /**
     * Transform DAO to DTO bean
     * @return DTO bean with DAO
     * @throws TransformationException If transformation error
     */
    public DTOBean transformToDTO() throws TransformationException;
}
