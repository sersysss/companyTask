package com.example.company.entity;

import com.example.company.enums.Positions;

import javax.persistence.*;
import java.util.*;

@Entity
public class Position {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id_pos;

    @Column
    @Enumerated(EnumType.STRING)
    private Positions name;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="id_sub_dep", referencedColumnName = "id_sub_dep")
    private Sub_department sub_department;

    @ManyToMany(mappedBy = "positions")
    private List<Employee> employees = new ArrayList<>();

    public Position() {}

    public Position(Positions name, Sub_department sub_department) {
        this.name = name;
        this.sub_department = sub_department;
    }

    public int getId_pos() {
        return id_pos;
    }

    public void setId_pos(int id_pos) {
        this.id_pos = id_pos;
    }

    public Positions getName() {
        return name;
    }

    public void setName(Positions name) {
        this.name = name;
    }

    public Sub_department getSub_department() {
        return sub_department;
    }

    public void setSub_department(Sub_department sub_department) {
        this.sub_department = sub_department;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Position)) return false;
        Position position = (Position) o;
        return getId_pos() == position.getId_pos();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId_pos());
    }

    @Override
    public String toString() {
        return "Position - " + name + ", sub_department - " + sub_department;
    }
}
