package com.epam.mediaserver.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "orders")
public class Order  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100)
    private String number;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_user", insertable = false)
    private User user;

    private Double price;

    @CreationTimestamp
    private Timestamp time;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "order_songs", joinColumns = @JoinColumn(name = "id_order"), inverseJoinColumns = @JoinColumn(name = "id_song"))
    private Set<Song> songs = new HashSet<>();


}
