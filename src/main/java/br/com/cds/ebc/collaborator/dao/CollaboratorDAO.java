package br.com.cds.ebc.collaborator.dao;

import br.com.cds.ebc.collaborator.entity.Collaborator;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.Collection;

/**
 * Created by Eduardo on 08/01/2017.
 */
@Repository
public class CollaboratorDAO extends GenericDAO<Collaborator, Integer> {

    public Collection<Collaborator> search(String searchTerm) throws Exception{
        FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(this.entityManager);
        fullTextEntityManager.createIndexer().startAndWait();
        QueryBuilder qb = fullTextEntityManager.getSearchFactory().buildQueryBuilder().forEntity(Collaborator.class).get();
        org.apache.lucene.search.Query luceneQuery = qb.bool()
                .must(qb.keyword().onFields("name", "background").matching(searchTerm).createQuery()).createQuery();
        Query fullTextQuery = fullTextEntityManager.createFullTextQuery(luceneQuery, Collaborator.class);
        Collection<Collaborator> result = (Collection<Collaborator>) fullTextQuery.getResultList();
        return result;
    }

    public Collection<Collaborator> find(Integer pageIndex, Integer pageSize) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Collaborator> query = builder.createQuery(Collaborator.class);
        query.from(Collaborator.class);
        return entityManager.createQuery(query)
                .setFirstResult(pageIndex*pageSize)
                .setMaxResults(pageSize)
                .getResultList();
    }

    public Collection<Collaborator> search(String searchTerm, Integer pageIndex, Integer pageSize) throws Exception{
        FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(this.entityManager);
        fullTextEntityManager.createIndexer().startAndWait();
        QueryBuilder qb = fullTextEntityManager.getSearchFactory().buildQueryBuilder().forEntity(Collaborator.class).get();
        org.apache.lucene.search.Query luceneQuery = qb.bool()
                .must(qb.keyword().onFields("name", "background").matching(searchTerm).createQuery()).createQuery();
        Query fullTextQuery = fullTextEntityManager.createFullTextQuery(luceneQuery, Collaborator.class);
        Collection<Collaborator> result = (Collection<Collaborator>)
                fullTextQuery
                        .setFirstResult(pageIndex*pageSize)
                        .setMaxResults(pageSize)
                        .getResultList();
        return result;
    }
}
