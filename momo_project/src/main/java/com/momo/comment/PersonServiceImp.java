package com.momo.comment;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonServiceImp implements PersonService{
	
	 	@Autowired // 1
	    private PersonRepository personRepository;

	    @Override
	    public Person createPerson(Person person) {
	        return personRepository.save(person); // 2
	    }

	    @Override
	    public void deletePerson(Long id) {
	        personRepository.deleteById(id); // 3
	    }

	    @Override
	    public List<Person> getAllPersons() {
	        return personRepository.findAll(); // 4
	    }
}
