package com.example.company.entity;


import javax.persistence.*;
import java.util.*;

@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id_emp;

    @Column
    private String name;

    @Column
    private String surname;

    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinTable(name = "emp_pos",
            joinColumns = @JoinColumn(name = "id_emp"),
            inverseJoinColumns = @JoinColumn(name = "id_pos")
    )
    private List<Position> positions = new ArrayList<>();

    public Employee() {
    }

    public Employee(String name, String surname, Position ... positions) {
        this.name = name;
        this.surname = surname;
        for (int i = 0; i < positions.length; i++) {
            this.addPosition(positions[i]);
        }
    }
    public void addPosition(Position position) {
        positions.add(position);
        position.getEmployees().add(this);
    }
    public void removePosition(Position position) {
        positions.remove(position);
        position.getEmployees().remove(this);
    }

    public int getId_emp() {
        return id_emp;
    }

    public void setId_emp(int id_emp) {
        this.id_emp = id_emp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public List<Position> getPositions() {
        return positions;
    }

    public void setPositions(List<Position> positions) {
        this.positions = positions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee)) return false;
        Employee employee = (Employee) o;
        return getId_emp() == employee.getId_emp();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId_emp());
    }

}
