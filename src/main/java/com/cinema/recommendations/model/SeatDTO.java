package com.cinema.recommendations.model;

import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SeatDTO {
    @Min(value = 1)
    private int row;

    @Min(value = 1)
    private int seat;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SeatDTO seatDTO = (SeatDTO) o;
        return row == seatDTO.row && seat == seatDTO.seat;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, seat);
    }
}
