package models;

import java.lang.reflect.Constructor;

public class Employee{
    private String name;
    private String position;

    public Employee(String name,String position){
        if(name == null || name.isBlank() || position==null || position.isBlank()){
            throw new IllegalArgumentException("'Name' or 'position' cannot be null/blank!");
        }
        this.name = name;
        this.position = position;
    }

    public Employee(Employee source){
        this.name = source.name;
        this.position = source.position;
    }
}