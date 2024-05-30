package org.rubnikovich.flightanalyser.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TicketsWrapper {
    private List<Ticket> tickets;
}
