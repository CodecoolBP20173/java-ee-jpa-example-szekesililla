package com.codecool.jpaexample.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;

    @Transient
    private long age;

    @OneToOne
    @JoinColumn(name = "address_id")
    private Address address;

    @ElementCollection
    @CollectionTable(name = "Phone")
    private List<String> phoneNumbers = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "klass_id")
    private Klass klass;

    public Student() {
    }

    public Student(String name, String email, Date dateOfBirth, List<String> phoneNumber) {
        this.name = name;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.age = (Calendar.getInstance().getTimeInMillis() - dateOfBirth.getTime())
                / (60L * 60L * 1000L * 24L * 365L);
        this.phoneNumbers = phoneNumber;
    }

    public Student(String name, String email, Date dateOfBirth, List<String> phoneNumber, Address address) {
        this(name, email, dateOfBirth, phoneNumber);
        this.address = address;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public long getAge() {
        return age;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", address id=" + address.getId() +
                '}';
    }

    public void setKlass(Klass klass) {
        this.klass = klass;
    }
}
