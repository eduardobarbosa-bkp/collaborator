package br.com.cds.ebc.collabator.dao;

import br.com.cds.ebc.collaborator.dao.UserDAO;
import br.com.cds.ebc.collaborator.entity.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by Eduardo on 08/01/2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "classpath:/dao-test.xml"})
@Transactional
public class UserDAOTest {

    @Autowired
    private UserDAO userDAO;

    @PersistenceContext
    protected EntityManager em;

    private Integer userId;
    private static final String USER_NAME = "USERNAME";
    private static final String PASSWORD = "PASSWORD";

    @Before
    public void setup(){
        clearData();
        insertData();
    }

    private void clearData(){
        em.createQuery("delete from User").executeUpdate();
    }

    private void insertData(){
        User user = new User();
        user.setUsername(USER_NAME);
        user.setPassword(PASSWORD);
        user.setName(USER_NAME);
        userDAO.save(user);
        this.userId = user.getId();
    }

    @Test
    public void testFindUserByLogin(){
        User user = userDAO.findUserByLogin(USER_NAME);
        Assert.assertNotNull(user);
        Assert.assertEquals(user.getUsername(), USER_NAME);
    }

}
