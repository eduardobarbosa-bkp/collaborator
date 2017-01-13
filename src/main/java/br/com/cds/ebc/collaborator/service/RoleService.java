package br.com.cds.ebc.collaborator.service;

import br.com.cds.ebc.collaborator.dao.GenericDAO;
import br.com.cds.ebc.collaborator.dao.RoleDAO;
import br.com.cds.ebc.collaborator.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by eduardo.costa on 11/01/2017.
 */
@Service
public class RoleService extends ServiceBase<Role, Integer> {

    @Autowired
    private RoleDAO dao;

    @Override
    protected GenericDAO<Role, Integer> getDAO() {
        return dao;
    }
}
