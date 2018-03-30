package com.epam.mediaserver.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(length = 20, nullable = false, unique = true)
    private String login;

    @Column(length = 100,nullable = false)
    private String password;

    @Column(length = 20)
    private String name;

    @Column(length = 20)
    private String surname;

    @Column(length = 20, nullable = false, unique = true)
    private String email;

    @Column(length = 1000)
    private String photo;

    @Column(insertable = false)
    @ColumnDefault(value = "false")
    private Boolean adminRoot = false;
}
