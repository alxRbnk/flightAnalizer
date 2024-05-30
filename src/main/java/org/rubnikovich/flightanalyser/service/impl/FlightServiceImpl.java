package org.rubnikovich.flightanalyser.service.impl;

import org.rubnikovich.flightanalyser.entity.Ticket;
import org.rubnikovich.flightanalyser.service.FlightService;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.*;
import java.util.*;

public class FlightServiceImpl implements FlightService {
    private static final ZoneId ZONE_ID_VVO = ZoneId.of("Asia/Vladivostok");
    private static final ZoneId ZONE_ID_TLV = ZoneId.of("Asia/Jerusalem");
    private static final String ORIGIN = "VVO";
    private static final String DESTINATION = "TLV";

    public Map<String, Duration> getMinFlightTime(List<Ticket> tickets) {
        Map<String, Duration> resultMap = new HashMap<>();
        String carrier;
        Duration duration;
        for (Ticket ticket : tickets) {
            if (!flightFromVVOtoTLV(ticket)) {
                continue;
            }
            carrier = ticket.getCarrier();
            duration = getDuration(ticket);
            if (!resultMap.containsKey(carrier)) {
                resultMap.put(carrier, duration);
            } else if (duration.toSeconds() < resultMap.get(ticket.getCarrier()).toSeconds()) {
                resultMap.put(carrier, getDuration(ticket));
            }
        }
        return resultMap;
    }

    public BigDecimal getDifferenceAverageMedian(List<Ticket> tickets) {
        BigDecimal average = getAveragePrice(tickets);
        BigDecimal median = getMedianPrice(tickets);
        return average.subtract(median);
    }

    private Duration getDuration(Ticket ticket) {
        LocalDate departureDate = ticket.getDepartureDate();
        LocalTime departureTime = ticket.getDepartureTime();
        LocalDate arrivalDate = ticket.getArrivalDate();
        LocalTime arrivalTime = ticket.getArrivalTime();
        LocalDateTime departureDateTime = departureDate.atTime(departureTime);
        LocalDateTime arrivalDateTime = arrivalDate.atTime(arrivalTime);
        ZonedDateTime zonedDateTimeDeparture = ZonedDateTime.of(departureDateTime, ZONE_ID_VVO);
        ZonedDateTime zonedDateTimeArrival = ZonedDateTime.of(arrivalDateTime, ZONE_ID_TLV);
        return Duration.between(zonedDateTimeDeparture, zonedDateTimeArrival);
    }

    private BigDecimal getAveragePrice(List<Ticket> tickets) {
        BigDecimal sum = BigDecimal.ZERO;
        int count = 0;
        for (Ticket ticket : tickets) {
            if (flightFromVVOtoTLV(ticket)) {
                sum = sum.add(ticket.getPrice());
                count++;
            }
        }
        if (count == 0) {
            return BigDecimal.ZERO;
        }
        return sum.divide(new BigDecimal(count), RoundingMode.HALF_UP);
    }

    private BigDecimal getMedianPrice(List<Ticket> tickets) {
        BigDecimal medianPrice;
        List<Ticket> sortedTicked = new ArrayList<>();
        for(Ticket ticket: tickets){
            if(!flightFromVVOtoTLV(ticket)){
                continue;
            }
            sortedTicked.add(ticket);
        }
        Collections.sort(sortedTicked, Comparator.comparing(Ticket::getPrice));
        int size = sortedTicked.size();
        if (size % 2 == 0) {
            int middleIndex = size / 2;
            BigDecimal price1 = sortedTicked.get(middleIndex - 1).getPrice();
            BigDecimal price2 = sortedTicked.get(middleIndex).getPrice();
            medianPrice = price1.add(price2).divide(BigDecimal.valueOf(2));
        } else {
            int middleIndex = size / 2;
            medianPrice = tickets.get(middleIndex).getPrice();
        }
        return medianPrice;
    }

    private boolean flightFromVVOtoTLV(Ticket ticket) {
        return (ticket.getOrigin().equals(ORIGIN) && ticket.getDestination().equals(DESTINATION));
    }
}

//summer UTC+3 - winterUTC+2
//UTC+10