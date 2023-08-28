package org.losev.LibraryBookTracker.utill;

import org.losev.LibraryBookTracker.models.Person;
import org.losev.LibraryBookTracker.services.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class PersonValidator implements Validator {

    private final PeopleService peopleService;

    @Autowired
    public PersonValidator(PeopleService peopleService) {
        this.peopleService = peopleService;
    }


    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Person person = (Person) target;


        if (peopleService.findByName(person.getName()).getName() == null) {
            errors.rejectValue("name", "", "This name is already taken");
        }

    }
}
