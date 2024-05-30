package org.rubnikovich.flightanalyser.service;

import org.rubnikovich.flightanalyser.entity.Ticket;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.List;
import java.util.Map;

public interface FlightService {
     Map<String, Duration> getMinFlightTime(List<Ticket> tickets);
    BigDecimal getDifferenceAverageMedian(List<Ticket> tickets);
}
