package com.inspur.netty.example_07_thrift;

import com.inspur.netty.thrift.DataException;
import com.inspur.netty.thrift.Person;
import com.inspur.netty.thrift.PersonService;
import org.apache.thrift.TException;

/**
 * User: YANG
 * Date: 2019/4/24
 * Time: 13:42
 * Description: No Description
 */
public class PersonServiceImpl implements PersonService.Iface {

    @Override
    public Person getPersonByUsername(String username) throws DataException, TException {
        System.out.println("getPersonByUsername method invoke ----------:" + username);

        Person person = new Person();
        person.setUsername("zhangsan");
        person.setAge(20);
        person.setMarried(false);

        return person;
    }

    @Override
    public void savePerson(Person person) throws DataException, TException {
        System.out.println("savePerson method invoke -----------!");
        System.out.println(person.getUsername());
        System.out.println(person.getAge());
        System.out.println(person.isMarried());
    }
}
