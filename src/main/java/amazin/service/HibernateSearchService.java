package amazin.service;

import amazin.model.Book;
import org.apache.lucene.search.Query;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import java.util.List;

@Service
@Transactional
public class HibernateSearchService {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Book> fuzzySearch(String searchTerm, String[] fieldNames) {

        FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);
        QueryBuilder qb = fullTextEntityManager.getSearchFactory().buildQueryBuilder().forEntity(Book.class).get();
        Query luceneQuery = qb.keyword().fuzzy().withEditDistanceUpTo(2).onFields(fieldNames).matching(searchTerm)
                .createQuery();

        javax.persistence.Query jpaQuery = fullTextEntityManager.createFullTextQuery(luceneQuery, Book.class);

        List<Book> bookList = null;
        try {
            bookList = jpaQuery.getResultList();
        } catch (NoResultException nre) {
            // for now do nothing. Later we will throw an exception and display a message to
            // the user
        }

        return bookList;
    }
}