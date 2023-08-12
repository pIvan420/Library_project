package ru.pivan.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.pivan.models.Person;
import ru.pivan.repositories.PeopleRepository;

import java.util.*;

@Service
@Transactional(readOnly = true)
public class PeopleServices {

    private final PeopleRepository peopleRepository;

    public PeopleServices(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    public List<Person> findAll(){
        return peopleRepository.findAll();
    }

    public Person findOne(int id){
        Person person = peopleRepository.findById(id).orElse(null);
        System.out.println(person.getBooks());
        return person;
    }

    public Person findByFullName(String fullName){
        return peopleRepository.findByFullName(fullName).orElse(null);
    }

    @Transactional
    public void save(Person person){
        peopleRepository.save(person);
    }

    @Transactional
    public void update(int id, Person updatedPerson){
        updatedPerson.setId(id);
        peopleRepository.save(updatedPerson);
    }

    @Transactional
    public void delete(int id){
        peopleRepository.deleteById(id);
    }
}
