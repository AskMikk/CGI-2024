package com.cinema.recommendations.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class BookingDTO {
    @NotNull
    private Long sessionId;

    @NotNull
    private Long userId;

    @Min(value = 1)
    private int row;

    @NotEmpty
    private List<Integer> seats;
}
