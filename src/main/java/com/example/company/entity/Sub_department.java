package com.example.company.entity;

import com.example.company.enums.SubDepartments;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Sub_department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id_sub_dep;

    @Column
    @Enumerated(EnumType.STRING)
    private SubDepartments name;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="id_dep", referencedColumnName = "id_dep")
    private Department department;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sub_department")
    private List<Position> positions = new ArrayList<>();

    public Sub_department() {
    }

    public Sub_department(SubDepartments name, Department department) {
        this.name = name;
        this.department = department;
    }

    public int getId_sub_dep() {
        return id_sub_dep;
    }

    public void setId_sub_dep(int id_sub_dep) {
        this.id_sub_dep = id_sub_dep;
    }

    public SubDepartments getName() {
        return name;
    }

    public void setName(SubDepartments name) {
        this.name = name;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public List<Position> getPositions() {
        return positions;
    }

    public void setPositions(List<Position> positions) {
        this.positions = positions;
    }

    @Override
    public String toString() {
        return name + ", department - " + department;
    }
}
