package br.com.cds.ebc.collaborator.service;

import br.com.cds.ebc.collaborator.dao.GenericDAO;
import br.com.cds.ebc.collaborator.dao.SkillDAO;
import br.com.cds.ebc.collaborator.entity.Skill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by eduardo.costa on 11/01/2017.
 */
@Service
public class SkillService extends ServiceBase<Skill, Integer> {

    @Autowired
    private SkillDAO dao;

    @Override
    protected GenericDAO<Skill, Integer> getDAO() {
        return dao;
    }
}
