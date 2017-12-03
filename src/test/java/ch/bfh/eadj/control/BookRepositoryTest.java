package ch.bfh.eadj.control;

import ch.bfh.eadj.AbstractTest;
import ch.bfh.eadj.dto.BookInfo;
import ch.bfh.eadj.entity.Book;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertTrue;

public class BookRepositoryTest extends AbstractTest {
    private BookRepository bookRepo;


    @Before
    public void setUpRepo() throws Exception {
        bookRepo = new BookRepository();
        bookRepo.em = em;
    }

    @Test
    public void souldFindBookByIsbn() throws Exception {
        //given
        String isbn = "417998182-3";

        //when
        List<Book> bookByIsbn = bookRepo.findBookByIsbn(isbn);

        //then
        assertThat(bookByIsbn.size(), is(1));
        Book bk = bookByIsbn.get(0);
        assertEquals(isbn, bk.getIsbn());
        assertEquals(bk.getTitle(),"Far Side of the Moon, The (Face cachée de la lune, La)");
        assertEquals(bk.getAuthors(),"Betsey Kindred");
        assertThat(bk.getPrice(),is(new BigDecimal("85.22")));
    }

    @Test
    public void shouldFindBooksByTitleKeywords() throws Exception {

        //given
        List<String> keywords = Arrays.asList("Far Side", "Moon", "the");

        //when
        List<BookInfo> booksByKeywords = bookRepo.findBooksByKeywords(keywords);

        //then
        assertThat(booksByKeywords.size(),is(1));
        BookInfo foundBook = booksByKeywords.get(0);
        assertTrue(foundBook.getTitle().contains(keywords.get(0)));
        assertTrue(foundBook.getTitle().contains(keywords.get(1)));
        assertTrue(foundBook.getTitle().contains(keywords.get(2)));
    }

    @Test
    public void shouldFindBooksByAuthorKeywords() throws Exception {

        //given
        List<String> keywords = Arrays.asList("Hana");

        //when
        List<BookInfo> booksByKeywords = bookRepo.findBooksByKeywords(keywords);

        //then
        assertThat(booksByKeywords.size(),is(2));
        BookInfo foundBook = booksByKeywords.get(0);
        assertTrue(foundBook.getAuthors().contains(keywords.get(0)));
    }

    @Test
    public void shouldFindBooksByPublisherKeywords() throws Exception {

        //given
        List<String> keywords = Arrays.asList("Babblestorm");

        //when
        List<BookInfo> booksByKeywords = bookRepo.findBooksByKeywords(keywords);

        //then
        assertThat(booksByKeywords.size(),is(2));
        BookInfo foundBook = booksByKeywords.get(0);
        //TODO assertion
    }

}