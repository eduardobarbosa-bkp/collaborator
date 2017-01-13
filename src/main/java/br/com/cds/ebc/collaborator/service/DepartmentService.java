package br.com.cds.ebc.collaborator.service;

import br.com.cds.ebc.collaborator.dao.DepartmentDAO;
import br.com.cds.ebc.collaborator.dao.GenericDAO;
import br.com.cds.ebc.collaborator.entity.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by eduardo.costa on 11/01/2017.
 */
@Service
public class DepartmentService extends ServiceBase<Department, Integer> {

    @Autowired
    private DepartmentDAO dao;

    @Override
    protected GenericDAO<Department, Integer> getDAO() {
        return dao;
    }
}
