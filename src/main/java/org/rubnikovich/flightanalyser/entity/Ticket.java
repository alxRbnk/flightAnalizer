package org.rubnikovich.flightanalyser.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Ticket {

    @JsonProperty("origin")
    private String origin;

    @JsonProperty("origin_name")
    private String originName;

    @JsonProperty("destination")
    private String destination;

    @JsonProperty("destination_name")
    private String destinationName;

    @JsonProperty("departure_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yy")
    private LocalDate departureDate;

    @JsonProperty("departure_time")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "H:mm")
    private LocalTime departureTime;

    @JsonProperty("arrival_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yy")
    private LocalDate arrivalDate;

    @JsonProperty("arrival_time")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "H:mm")
    private LocalTime arrivalTime;

    @JsonProperty("carrier")
    private String carrier;

    @JsonProperty("stops")
    private int stops;

    @JsonProperty("price")
    private BigDecimal price;

}

/*
{
        "tickets":[{
        "origin":"VVO",
        "origin_name":"Владивосток",
        "destination":"TLV",
        "destination_name":"Тель-Авив",
        "departure_date":"12.05.18",
        "departure_time":"16:20",
        "arrival_date":"12.05.18",
        "arrival_time":"22:10",
        "carrier":"TK",
        "stops":3,
        "price":12400
        },{
        "origin":"VVO",
        "origin_name":"Владивосток",
        "destination":"TLV",
        "destination_name":"Тель-Авив",
        "departure_date":"12.05.18",
        "departure_time":"17:20",
        "arrival_date":"12.05.18",
        "arrival_time":"23:50",
        "carrier":"S7",
        "stops":1,
        "price":13100
        }]
        }

 */
