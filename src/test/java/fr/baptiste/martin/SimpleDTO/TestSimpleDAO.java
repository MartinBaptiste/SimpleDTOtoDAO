package fr.baptiste.martin.SimpleDTO;

import fr.baptiste.martin.SimpleDTO.bean.Bean1DAO;
import fr.baptiste.martin.SimpleDTO.bean.Bean1DTO;
import fr.baptiste.martin.SimpleDTO.exception.TransformationException;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestSimpleDAO {
    private static final Logger LOGGER = Logger.getLogger(TestSimpleDAO.class);

    @BeforeClass
    public static void init() {
        BasicConfigurator.configure();
    }

    @Test
    public void testDTOtoDAOSimple() {
        Bean1DTO bean1DTO = new Bean1DTO();
        bean1DTO.setName("test");
        bean1DTO.setValue(55);

        Bean1DAO bean1DAO = null;
        try {
            bean1DAO = (Bean1DAO) bean1DTO.transformToDAO();
        } catch (TransformationException e) {
            LOGGER.error(e.getMessage(), e);
            fail(e.getMessage());
        }

        assertEquals("test", bean1DAO.getName());
        assertEquals(55, bean1DAO.getValue());
    }

    @Test
    public void testDAOtoDTOSimple() {
        Bean1DAO bean1DAO = new Bean1DAO();
        bean1DAO.setName("test");
        bean1DAO.setValue(55);

        Bean1DTO bean1DTO = null;
        try {
            bean1DTO = (Bean1DTO) bean1DAO.transformToDTO();
        } catch (TransformationException e) {
            LOGGER.error(e.getMessage(), e);
            fail(e.getMessage());
        }

        assertEquals("test", bean1DTO.getName());
        assertEquals(55, bean1DTO.getValue());
    }
}