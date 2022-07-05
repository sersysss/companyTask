package com.example.company.entity;

import com.example.company.enums.Departments;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id_dep;

    @Column
    @Enumerated(EnumType.STRING)
    private Departments name;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "department")
    private List<Sub_department> subDepartments = new ArrayList<>();

    public Department(Departments name) {
        this.name = name;
    }

    public Department() {
    }

    public int getId_dep() {
        return id_dep;
    }

    public void setId_dep(int id_dep) {
        this.id_dep = id_dep;
    }

    public Departments getName() {
        return name;
    }

    public void setName(Departments name) {
        this.name = name;
    }

    public List<Sub_department> getSubDepartments() {
        return subDepartments;
    }

    public void setSubDepartments(List<Sub_department> subDepartments) {
        this.subDepartments = subDepartments;
    }

    @Override
    public String toString() {
        return name.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Department)) return false;
        Department that = (Department) o;
        return getId_dep() == that.getId_dep() && getName() == that.getName();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId_dep(), getName());
    }
}
