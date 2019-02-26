package org.my.demo.java8;

import java.util.Objects;

public class Employee {
    private Integer id;
    private String name;
    private Integer age;
    private Double inCome;
    private Status status;


    public Employee() {
    }

    public Employee(Integer id) {
        this.id = id;
    }

    public Employee(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Employee(Integer id, String name, Integer age, Double inCome) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.inCome = inCome;
    }

    public Employee(Integer id, String name, Integer age, Double inCome, Status status) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.inCome = inCome;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Double getInCome() {
        return inCome;
    }

    public void setInCome(Double inCome) {
        this.inCome = inCome;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", inCome=" + inCome +
                ", status=" + status +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(id, employee.id) &&
                Objects.equals(name, employee.name) &&
                Objects.equals(age, employee.age) &&
                Objects.equals(inCome, employee.inCome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, age, inCome);
    }

    public enum Status {
        FREE,
        BUSY,
        VOCATION;
    }
}
