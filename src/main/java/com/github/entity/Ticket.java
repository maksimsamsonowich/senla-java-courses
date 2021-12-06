package com.github.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tickets")
@NamedEntityGraph(
        name = "ticket-entity-graph",
        attributeNodes = {
                @NamedAttributeNode("eventHolding"),
                @NamedAttributeNode("owner")
        }
)
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "order_date")
    private Date orderDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id")
    private Event eventHolding;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User owner;


    public String toString() {
        return String.format(
                "Ticket [id=%d, " +
                        "orderDate=%s]",
                id,
                orderDate
        );
    }

}
