package com.cinema.recommendations.model;

import lombok.Getter;
import lombok.Setter;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class SessionDTO {
    private Long sessionId;

    @NotBlank
    private String filmTitle;

    @NotEmpty
    private List<@NotBlank String> genres;

    @NotBlank
    private String ageRestriction;

    @FutureOrPresent
    private Date startTime;

    @NotBlank
    private String theaterName;

    @NotNull
    @Min(0)
    private Integer availableSeats;

    @NotBlank
    private String language;

    private List<@NotBlank String> subtitles;

    @NotBlank
    private String posterUrl;

    @NotNull
    @Min(1)
    private Integer numberOfRows;

    @NotNull
    @Min(1)
    private Integer seatsPerRow;
}
