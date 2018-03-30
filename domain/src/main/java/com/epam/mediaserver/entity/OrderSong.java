package com.epam.mediaserver.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "order_song")
public class OrderSong  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinTable(name = "id_order",foreignKey = @ForeignKey(name = "ORDER_SONG_ORDER_ID_FK"))
    private Order order;

    @OneToOne
    @JoinTable(name = "id_song" ,foreignKey = @ForeignKey(name = "ORDER_SONG_SONG_ID_FK"))
    private Song song;
}
