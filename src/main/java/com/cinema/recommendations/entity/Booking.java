package com.cinema.recommendations.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "bookings")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Session session;

    @ManyToOne
    @JoinColumn(nullable = false)
    private User user;

    @NotNull
    @Column(nullable = false)
    private int rowNumber;

    @NotNull
    @Column(nullable = false)
    private int seatNumber;

    @NotNull
    @PastOrPresent
    @Temporal(TemporalType.TIMESTAMP)
    private Date bookingTime;
}
