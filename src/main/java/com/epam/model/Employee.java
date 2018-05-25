package com.epam.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "employee")
public class Employee implements Serializable {

    public Employee() {

    }
    private Long empId;
    private String empName;
    private Set<Issue> issues = new HashSet<Issue>();


    @Id
    @Column(name = "empId", nullable = false)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="EMPLOYEE_SEQ")
    @SequenceGenerator(name="EMPLOYEE_SEQ", sequenceName="EMPLOYEE_SEQ", initialValue=4)
    public Long getempId() {
        return empId;
    }

    public void setempId(Long empId) {
        this.empId = empId;
    }

    @Basic
    @Column(name = "empName", nullable = false)
    public String getempName() {
        return empName;
    }

    public void setempName(String empName) {
        this.empName = empName;
    }

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "employee", cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE})
    public Set<Issue> getIssues() {
        return issues;
    }

    public void setIssues(Set<Issue> issues) {
        this.issues = issues;
    }
}