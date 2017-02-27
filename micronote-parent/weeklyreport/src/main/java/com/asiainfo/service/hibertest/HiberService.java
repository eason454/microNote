package com.asiainfo.service.hibertest;

import com.asiainfo.domain.hibtest.Person;
import com.asiainfo.repository.hibertest.PersonRepository;
import com.asiainfo.repository.hibertest.PhoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by eason on 2017/2/10.
 */
@Service
public class HiberService {
    private final List foo;
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private PhoneRepository phoneRepository;

    public HiberService() {
        foo = new ArrayList<>();
        foo.add("foo");
    }

    public  void test(){
        Person person=new Person();
        personRepository.save(person);
        person.getNames().clear();
        person.getNames().add("zhao");
        person.getNames().add("eason");
        Person person1 = personRepository.save(person);
        foo.add("test");
        System.out.println(foo.size());

    }
}
