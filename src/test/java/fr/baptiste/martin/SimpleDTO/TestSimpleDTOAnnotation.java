package fr.baptiste.martin.SimpleDTO;

import fr.baptiste.martin.SimpleDTO.bean.Client;
import fr.baptiste.martin.SimpleDTO.bean.User;
import fr.baptiste.martin.SimpleDTO.exception.TransformationException;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.junit.BeforeClass;
import org.junit.Test;

import java.text.SimpleDateFormat;

import static org.junit.Assert.*;

public class TestSimpleDTOAnnotation {
    private static final Logger LOGGER = Logger.getLogger(TestSimpleDTOAnnotation.class);
    private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("dd/MM/yyy");

    @BeforeClass
    public static void init() {
        BasicConfigurator.configure();
    }

    @Test
    public void testDTOtoDAO() throws Exception {
        User user = new User();
        user.setUsername("toto");
        user.setPassword("pass");
        user.setBirthday(SIMPLE_DATE_FORMAT.parse("30/08/1990"));


        Client client = null;
        try {
            client = (Client) user.transformToDAO();
        } catch (TransformationException e) {
            LOGGER.error(e.getMessage(), e);
            fail(e.getMessage());
        }

        assertEquals("toto", client.getName());
        assertEquals("pass", client.getPassword());
        assertEquals(SIMPLE_DATE_FORMAT.parse("30/08/1990"), client.getBirthday());
    }

    @Test
    public void testDAOtoDTO() throws Exception {
        Client client = new Client();
        client.setName("toto");
        client.setPassword("pass");
        client.setBirthday(SIMPLE_DATE_FORMAT.parse("30/08/1990"));


        User user = null;
        try {
            user = (User) client.transformToDTO();
        } catch (TransformationException e) {
            LOGGER.error(e.getMessage(), e);
            fail(e.getMessage());
        }

        assertEquals("toto", user.getUsername());
        assertNull(user.getPassword());
        assertEquals(SIMPLE_DATE_FORMAT.parse("30/08/1990"), user.getBirthday());
    }
}
