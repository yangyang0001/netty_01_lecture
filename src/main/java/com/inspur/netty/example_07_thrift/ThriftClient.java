package com.inspur.netty.example_07_thrift;

import com.inspur.netty.thrift.Person;
import com.inspur.netty.thrift.PersonService;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

/**
 * User: YANG
 * Date: 2019/4/24
 * Time: 13:54
 * Description: No Description
 */
public class ThriftClient {

    public static void main(String[] args){

        TTransport transport = new TFramedTransport(new TSocket("localhost", 8899), 600);
        TProtocol protocol = new TCompactProtocol(transport);
        PersonService.Client client = new PersonService.Client(protocol);

        try {
            transport.open();
            Person person = client.getPersonByUsername("zhangsan");
            System.out.println(person.getUsername());
            System.out.println(person.getAge());
            System.out.println(person.isMarried());
            System.out.println("----------------------");

            Person person2 = new Person();
            person2.setUsername("lisi");
            person2.setAge(28);
            person2.setMarried(true);

            client.savePerson(person2);

        } catch (Exception ex){
           throw new RuntimeException(ex.getMessage(), ex);
        } finally {
            transport.close();
        }


    }
}
