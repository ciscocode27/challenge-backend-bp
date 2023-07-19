package com.pichincha.prueba.rest.Utils.Classes;

import com.pichincha.prueba.rest.Utils.Classes.Enums.Gender;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class Persona {

    @Column(nullable = false)
    protected String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    protected Gender gender;

    @Column(nullable = false)
    protected int age;

    @Column(nullable = false)
    protected String dni;

    @Column(nullable = false)
    protected String address;

    @Column(nullable = false)
    protected String phone;

    public Persona() {}
    public Persona(String name, Gender gender, int age, String dni, String address, String phone) {
        super();
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.dni = dni;
        this.address = address;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
