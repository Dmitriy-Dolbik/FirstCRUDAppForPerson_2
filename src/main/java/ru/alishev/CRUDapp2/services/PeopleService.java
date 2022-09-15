package ru.alishev.CRUDapp2.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.alishev.CRUDapp2.models.Person;
import ru.alishev.CRUDapp2.repositories.PeopleRepository;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly=true)
public class PeopleService {

    private final PeopleRepository peopleRepository;
    @Autowired
    public PeopleService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }
    public List<Person> findAll(){
        return peopleRepository.findAll();
    }
    public Person findOne(int id){
        Optional<Person> foundPerson = peopleRepository.findById(id);
        return foundPerson.orElse(null);
    }
    @Transactional
    public void save(Person person){
        person.setCreatedAt(new Date());
        //person.setCreatedAt(Calendar.getInstance());
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
    public void test(){
        System.out.println("Testing here with debug. Inside Hibernate transaction");
    }
}
