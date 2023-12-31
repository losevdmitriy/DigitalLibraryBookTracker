package org.losev.LibraryBookTracker.repositories;

import org.losev.LibraryBookTracker.models.Book;
import org.losev.LibraryBookTracker.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PeopleRepository extends JpaRepository<Person, Integer> {

    Person findByBooks(Book book);

    Person findByName(String name);
}
