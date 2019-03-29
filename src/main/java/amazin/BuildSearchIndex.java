package amazin;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * This class is used to build the Lucene index at application startup. This is
 * needed when the database has data before the web application is started.
 * 
 */
@Component
@Transactional
public class BuildSearchIndex implements ApplicationListener<ApplicationReadyEvent> {

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Create an initial Lucene index for the data already present in the database.
     * This method is called when Spring's startup.
     */
    @Override
    public void onApplicationEvent(final ApplicationReadyEvent event) {
        try {
            FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);
            fullTextEntityManager.createIndexer().startAndWait();
        } catch (InterruptedException e) {
            System.out.println("An error occurred trying to build the serach index: " + e.toString());
        }
        return;
    }

}