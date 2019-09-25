package util.Generator;

import com.github.javafaker.Faker;
import com.google.inject.internal.cglib.proxy.$Factory;

public class BookGenerator
{
    public BookGenerator(){}

    /**
     * Creates a random book;
     * @return
     */
    public Book generateBook()
    {
        Faker faker = new Faker();
        com.github.javafaker.Book book = faker.book();
        Book temp = Book.builder()
                .id(Faker.instance().idNumber().valid())
                .title(book.author())
                .author(book.author())
                .available(true)
                .build();

        return temp;
    }
    /**
    private Book[] generateBooks(int count)
    {

    }
     **/
}
