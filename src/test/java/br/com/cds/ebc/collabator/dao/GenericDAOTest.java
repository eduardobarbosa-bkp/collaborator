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
import javax.validation.ConstraintViolationException;
import java.util.List;

/**
 * Created by Eduardo on 07/01/2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "classpath:/dao-test.xml"})
@Transactional
public class GenericDAOTest {

    @Autowired
    private UserDAO userDAO;

    private Integer userId;
    private static final String USER_NAME = "USERNAME";
    private static final String PASSWORD = "PASSWORD";

    @PersistenceContext
    protected EntityManager em;

    @Before
    public void setup(){
        clearData();
        insertData();
    }

    private void clearData(){
        em.createQuery("delete from User").executeUpdate();
    }

    private void insertData(){
        User user = getUser(USER_NAME, PASSWORD);
        userDAO.save(user);
        this.userId = user.getId();
    }

    @Test
    public void testInsert(){
        User user = getUser(USER_NAME, PASSWORD);
        userDAO.save(user);
        Assert.assertNotNull(user.getId());
    }

    private User getUser(String username, String password) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setName(username);
        return user;
    }

    @Test
    public void testUpdate(){
        User user = userDAO.findById(this.userId);
        user.setUsername("UPDATE-USERNAME");
        userDAO.update(user);
        user = userDAO.findById(user.getId());
        Assert.assertNotNull(user);
        Assert.assertEquals(user.getUsername(), "UPDATE-USERNAME");
    }

    @Test
    public void testDelete(){
        User user =  userDAO.findById(this.userId);
        userDAO.delete(user);
        user = userDAO.findById(user.getId());
        Assert.assertNull(user);
    }


    @Test
    public void testFindById(){
        User user = userDAO.findById(this.userId);
        Assert.assertNotNull(user);
        Assert.assertEquals(user.getUsername(), USER_NAME);
        Assert.assertEquals(user.getPassword(), PASSWORD);
    }


    @Test
    public void testFindAll(){
        List<User> users = userDAO.findAll();
        Assert.assertFalse(users.isEmpty());
        Assert.assertEquals(users.size(), 1);
    }

}
