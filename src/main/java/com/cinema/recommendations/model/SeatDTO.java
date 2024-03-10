package com.cinema.recommendations.model;

import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SeatDTO {
    @Min(value = 1)
    private int row;

    @Min(value = 1)
    private int seat;
}
