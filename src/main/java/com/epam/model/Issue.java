package com.epam.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="issue")
public class Issue implements Serializable {

    private Long issueId;
    private String description;
    private Integer urgency;
    private Employee employee;

    public Issue() {

    }

    @Id
    @Column(name = "issueId", nullable = false)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ISSUE_SEQ")
    @SequenceGenerator(name="ISSUE_SEQ", sequenceName="ISSUE_SEQ", initialValue=6)
    public Long getissueId() {
        return issueId;
    }

    public void setissueId(Long issueId) {
        this.issueId = issueId;
    }


    @Basic
    @Column(name = "description", nullable = false)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "urgency", nullable = false)
    public Integer getUrgency() {
        return urgency;
    }

    public void setUrgency(Integer urgency) {
        this.urgency = urgency;
    }

    @JsonIgnore
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "assignedTo", nullable = true)
    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }



}
