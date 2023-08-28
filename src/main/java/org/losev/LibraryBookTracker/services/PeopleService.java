package org.losev.LibraryBookTracker.services;

import org.losev.LibraryBookTracker.models.Person;
import org.losev.LibraryBookTracker.repositories.BooksRepository;
import org.losev.LibraryBookTracker.repositories.PeopleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class PeopleService {


    private final PeopleRepository peopleRepository;
    private final BooksRepository booksRepository;

    @Autowired
    public PeopleService(PeopleRepository peopleRepository, BooksRepository booksRepository) {
        this.peopleRepository = peopleRepository;
        this.booksRepository = booksRepository;
    }

    public List<Person> findAll() {
        return peopleRepository.findAll();
    }

    public Person findOne(int id) {
        return peopleRepository.findById(id).orElse(null);
    }

    @Transactional(readOnly = false)
    public void save(Person person) {
        peopleRepository.save(person);
    }

    @Transactional(readOnly = false)
    public void update(int id, Person updatedPerson) {
        updatedPerson.setId(id);
        peopleRepository.save(updatedPerson);
    }

    @Transactional(readOnly = false)
    public void delete(int id) {
        peopleRepository.deleteById(id);
    }

    public Person findByBooks(int id) {
        return peopleRepository.findByBooks(booksRepository.findById(id).orElse(null));
    }

    public Person findByName(String name) {
        return peopleRepository.findByName(name);
    }


}
