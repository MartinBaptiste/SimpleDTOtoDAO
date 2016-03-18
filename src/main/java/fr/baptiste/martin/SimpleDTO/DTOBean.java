package fr.baptiste.martin.SimpleDTO;

import fr.baptiste.martin.SimpleDTO.exception.TransformationException;

/**
 * Super interface DTO bean
 */
public interface DTOBean {
    /**
     * Transform DTO to DAO bean
     * @return DAO bean with DTO
     * @throws TransformationException If transformation error
     */
    public DAOBean transformToDAO() throws TransformationException;
}
