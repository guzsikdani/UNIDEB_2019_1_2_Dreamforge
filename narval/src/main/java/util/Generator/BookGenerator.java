package util.Generator;

import com.github.javafaker.Faker;
import com.google.inject.Guice;
import com.google.inject.Injector;
import dao.BookDAO;
import model.Book;
import util.guice.PersistenceModule;


public class BookGenerator
{
    private BookDAO bookDAO;
    public BookGenerator()
    {
        Injector injector = Guice.createInjector(new PersistenceModule("jpa-persistence-unit-1"));
        bookDAO = injector.getInstance(BookDAO.class);
    }

    /**
     * Creates a random book;
     * @return
     */
    public void generateBookTable()
    {
        Faker faker = new Faker();
        com.github.javafaker.Book book = faker.book();
        Book temp = Book.builder()
                .id(Faker.instance().idNumber().valid())
                .title(book.author())
                .author(book.author())
                .available(true)
                .build();

        bookDAO.persist(temp);

    }

    public void generateBooks(int count)
    {
        for(int i=0;i<count;i++)
        {
            generateBookTable();
        }
    }
}
