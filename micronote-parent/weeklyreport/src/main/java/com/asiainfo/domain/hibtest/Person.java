package com.asiainfo.domain.hibtest;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by eason on 2017/2/10.
 */
@Entity(name = "Person")
public  class Person {

    @Id
    @GeneratedValue
    private Long id;
//    @OneToMany(mappedBy = "person",cascade = CascadeType.ALL,orphanRemoval = true)
//    private List<Phone> phones = new ArrayList<>();
//
//    public List<Phone> getPhones() {
//        return phones;
//    }
    @ElementCollection
    private List<String> names=new ArrayList<>();
    public List<String> getNames(){
        return names;
    }
}
