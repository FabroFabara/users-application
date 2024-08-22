package com.evaluation.ec.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "phone")
public class Phone {

    @Id
    @Column(name = "phone_id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "number", nullable = false, length = 20)
    private String number;

    @Column(name = "citycode", nullable = false, length = 10)
    private String citycode;

    @Column(name = "countrycode", nullable = false, length = 10)
    private String countrycode;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}