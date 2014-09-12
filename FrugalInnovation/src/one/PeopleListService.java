package one;

import javax.persistence.*;
import java.util.*;

public class PeopleListService {
	 private EntityManager manager;

	 public PeopleListService(EntityManager manager) {
		 this.manager = manager;
	 }

    // method to create a new record
     public PeopleList createPerson(String firstName, String lastName, String comapanyName, String emailAddress) {
            PeopleList person = new PeopleList();
 	    //person.setPeopleID(id);
 	    person.setFirstName(firstName);
 	    person.setLastName(lastName);
 	    person.setCompanyName(comapanyName);
 	    person.setEmailAddress(emailAddress);
 	    manager.persist(person);
 	    return person;
     }

    // method to read a record
     public PeopleList readPeople(int id) {
            PeopleList person = manager.find(PeopleList.class, id);
            return person;
     }

     // method to read all records
     public List<PeopleList> readAll() {
            TypedQuery<PeopleList> query = manager.createQuery("SELECT e FROM PEOPLE e", PeopleList.class);
            List<PeopleList> result =  query.getResultList();
            return result;
     }

    // method to update a record
     public PeopleList updatePerson(int peopleID, String firstName, String lastName, String comapanyName, String emailAddress) {
            PeopleList person = manager.find(PeopleList.class, peopleID);
            if (person != null) {
                           person.setFirstName(firstName);
                       person.setLastName(lastName);
                       person.setCompanyName(comapanyName);
                       person.setEmailAddress(emailAddress);
            }
            return person;
     }

    // method to delete a record
    public void deletePerson(String id) {
            PeopleList person = manager.find(PeopleList.class, id);
            if (person != null) {
                    manager.remove(person);
            }
    }
}
