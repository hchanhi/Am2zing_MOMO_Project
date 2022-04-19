package com.momo.comment;

import java.util.List;

public interface PersonService {
	 Person createPerson(Person person);

     void deletePerson(Long id);

   List<Person> getAllPersons();
}
