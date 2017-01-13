package br.com.cds.ebc.collaborator.dao;

import br.com.cds.ebc.collaborator.entity.User;
import org.springframework.stereotype.Repository;

import javax.persistence.NonUniqueResultException;
import java.util.List;

/**
 * Created by Eduardo on 08/01/2017.
 */
@Repository
public class UserDAO extends GenericDAO<User, Integer> {

    public User findUserByLogin(String username){
        List<User> results = entityManager.createQuery("SELECT u FROM User u where u.username = :username", User.class)
                .setParameter("username", username)
                .getResultList();
        if (results.isEmpty()) return null;
        else if (results.size() == 1) return results.get(0);
        throw new NonUniqueResultException();
    }

}
