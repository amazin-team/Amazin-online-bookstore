package amazin.service;

import amazin.model.Book;
import org.apache.lucene.search.Query;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.List;

@Service
public class HibernateSearchService {

    private final EntityManager entityManager;

    @Autowired
    public HibernateSearchService(EntityManager entityManager) {
        super();
        this.entityManager = entityManager;
    }

    @Transactional
    public List<Book> fuzzySearch(String searchTerm, String fieldName) {

        FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);
        QueryBuilder qb = fullTextEntityManager.getSearchFactory().buildQueryBuilder().forEntity(Book.class).get();
        Query luceneQuery = qb.keyword().fuzzy().withEditDistanceUpTo(2).withPrefixLength(0).onFields(fieldName)
                .matching(searchTerm).createQuery();

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

    @Transactional
    public List<Book> keywordSearch(String searchTerm, String fieldName) {

        FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);
        QueryBuilder qb = fullTextEntityManager.getSearchFactory().buildQueryBuilder().forEntity(Book.class).get();
        Query luceneQuery = qb.keyword().onField(fieldName).matching(searchTerm).createQuery();

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

    @Transactional
    public List<Book> wildcardSearch(String searchTerm, String fieldName) {

        FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);
        QueryBuilder qb = fullTextEntityManager.getSearchFactory().buildQueryBuilder().forEntity(Book.class).get();

        StringBuilder cleanedSearchTerm = new StringBuilder(searchTerm);

        // Removs leading and trailing special characters (?, *) to improve search
        // efficiency
        if (cleanedSearchTerm.charAt(0) == '?' || cleanedSearchTerm.charAt(0) == '*')
            cleanedSearchTerm.deleteCharAt(0);
        if (cleanedSearchTerm.charAt(cleanedSearchTerm.length() - 1) == '?'
                || cleanedSearchTerm.charAt(cleanedSearchTerm.length() - 1) == '*')
            cleanedSearchTerm.deleteCharAt(cleanedSearchTerm.length() - 1);

        // Replaces all * with ? because a * represents any character sequence while ?
        // represents a single character in the search function so if the search term
        // happened to have a * we want to replace it with ? to compensate for that one
        // * without affecting the rest string
        for (int i = 0; i < cleanedSearchTerm.length(); i++) {
            if (cleanedSearchTerm.charAt(i) == '*')
                cleanedSearchTerm.setCharAt(i, '?');
        }

        // Adds the * at the end of the string to match any string that starts with the
        // search term provided
        cleanedSearchTerm.append('*');

        Query luceneQuery = qb.keyword().wildcard().onField(fieldName).matching(cleanedSearchTerm.toString())
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