package model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Class representing a book in the library.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Book
{
    /**
     * The Cut-code of the book.
     */
    @Id
    private String id;

    /**
     * The title of the book.
     */
    @Column(nullable = false)
    private String title;

    /**
     * The author of the book.
     */
    @Column(nullable = false)
    private String author;

    /**
     * A marker which is meant to show whenever the book is rented.
     */
    @Column(nullable = false)
    private Boolean available;
}
