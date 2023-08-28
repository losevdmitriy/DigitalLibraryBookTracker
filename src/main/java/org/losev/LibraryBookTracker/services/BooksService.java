package org.losev.LibraryBookTracker.services;

import org.losev.LibraryBookTracker.models.Book;
import org.losev.LibraryBookTracker.repositories.BooksRepository;
import org.losev.LibraryBookTracker.repositories.PeopleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class BooksService {
    private final PeopleRepository peopleRepository;
    private final BooksRepository booksRepository;

    @Autowired
    public BooksService(PeopleRepository peopleRepository, BooksRepository booksRepository) {
        this.peopleRepository = peopleRepository;
        this.booksRepository = booksRepository;
    }

    public List<Book> findAll() {
        return booksRepository.findAll();
    }

    public List<Book> findAll(PageRequest pageRequest) {
        return booksRepository.findAll(pageRequest).getContent();
    }

    public List<Book> findAll(Sort sort) {
        return booksRepository.findAll(sort);
    }

    public Book findOne(int id) {
        return booksRepository.findById(id).orElse(null);
    }

    @Transactional(readOnly = false)
    public void save(Book book) {
        booksRepository.save(book);
    }

    @Transactional(readOnly = false)
    public void update(int id, Book updatedBook) {
        Book bookToBeUpdated = booksRepository.findById(id).get();
        updatedBook.setOwner(bookToBeUpdated.getOwner());
        updatedBook.setId(id);
        booksRepository.save(updatedBook);
    }

    @Transactional(readOnly = false)
    public void delete(int id) {
        booksRepository.deleteById(id);
    }

    public List<Book> findByOwner(int id) {
        List<Book> books = booksRepository.findByOwner(peopleRepository.findById(id).orElse(null));
        if (books != null) {
            for (Book book : books) {
                if ((Math.abs(new Date().getTime() - book.getTakenAt().getTime())) > 864000000) {
                    book.setExpired(true);
                }
            }
        }

        return books;
    }

    @Transactional(readOnly = false)
    public void returnBook(int id) {
        Book book = booksRepository.findById(id).orElse(null);
        if (book != null) {
            book.setOwner(null);
            book.setTakenAt(null);
            booksRepository.save(book);
        }
    }

    @Transactional(readOnly = false)
    public void issueBook(int personId, int bookId) {
        booksRepository.findById(bookId).ifPresent(book -> {
            book.setOwner(peopleRepository.findById(personId).orElse(null));
            book.setTakenAt(new Date());
        });
    }

    public List<Book> search(String query) {
        return booksRepository.searchBooksByNameStartingWith(query);
    }


}
