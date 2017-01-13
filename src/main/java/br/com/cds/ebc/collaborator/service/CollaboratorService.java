package br.com.cds.ebc.collaborator.service;

import br.com.cds.ebc.collaborator.dao.CollaboratorDAO;
import br.com.cds.ebc.collaborator.dao.GenericDAO;
import br.com.cds.ebc.collaborator.entity.Collaborator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

/**
 * Created by Eduardo on 07/01/2017.
 */
@Service
public class CollaboratorService extends ServiceBase<Collaborator, Integer> {

    @Autowired
    private CollaboratorDAO dao;

    @Override
    protected GenericDAO<Collaborator, Integer> getDAO() {
        return dao;
    }

    @Override
    public void delete(Collaborator entity) {
        Collaborator collaborator = dao.findById(entity.getId());
        if(collaborator != null){
            collaborator.setSkills(null);
            dao.delete(collaborator);
        }
    }

    @Transactional
    public Collection<Collaborator> search(String searchTerm) throws Exception{
       return dao.search(searchTerm);
    }

    @Transactional
    public Collection<Collaborator> findPagination(Integer pageIndex, Integer pageSize) {
        return dao.find(pageIndex, pageSize);
    }

    public Collection<Collaborator> searchPagination(String searchTerm, Integer pageIndex, Integer pageSize) throws Exception{
        return dao.search(searchTerm, pageIndex, pageSize);
    }
}
