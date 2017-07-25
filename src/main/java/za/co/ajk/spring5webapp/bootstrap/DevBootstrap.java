package za.co.ajk.spring5webapp.bootstrap;

import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import za.co.ajk.spring5webapp.model.Author;
import za.co.ajk.spring5webapp.model.Book;
import za.co.ajk.spring5webapp.repositories.AuthorRepository;
import za.co.ajk.spring5webapp.repositories.BookRepository;

@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent> {
    
    private static final Log log = LogFactory.getLog(DevBootstrap.class);
    
    private AuthorRepository authorRepository;
    
    private BookRepository bookRepository;
    
    /**
     * Default constructor with repository injection
     *
     * @param authorRepository
     * @param bookRepository
     */
    public DevBootstrap(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }
    
    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        initData();
        
        Iterable<Author> authors = authorRepository.findAll();
        for (Author author : authors) {
            log.info("Author is : " + author);
            // System.out.println("  had books : "+author.getBooks());
        }
        
    }
    
    public void initData() {
        
        Author eric = new Author("Eric", "Evans");
        Book ddd = new Book("Domain Driven Design", "1234", "Harper Collins");
        
        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);
        
        authorRepository.save(eric);
        bookRepository.save(ddd);
        
        Author rod = new Author("Rod", "Johnson");
        Book noEJB = new Book("J2EE Development without EJB", "23444", "Worx");
        
        rod.getBooks().add(noEJB);
        noEJB.getAuthors().add(rod);
        
        authorRepository.save(rod);
        bookRepository.save(noEJB);
    }
    
    
}
