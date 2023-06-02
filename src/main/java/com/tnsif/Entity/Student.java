package com.tnsif.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Students",uniqueConstraints =
        {@UniqueConstraint(columnNames = {"s_roll"}),@UniqueConstraint(columnNames = {"s_hallTicketNo"})})
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long s_id;
    @Column(name = "s_name",nullable = false)
    private String s_name;
    @Column(name = "s_roll",nullable = false)
    private long s_roll;
    @Column(name = "s_qualification",nullable = false)
    private String s_qualification;
    @Column(name = "s_course",nullable = false)
    private String course;
    @Column(name = "s_year",nullable = false)
    private int s_year;
    @Column(name = "s_hallTicketNo",nullable = false)
    private long s_hallTicketNo;

}
