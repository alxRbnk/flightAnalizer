package org.rubnikovich.flightanalyser.main;

import org.rubnikovich.flightanalyser.entity.Ticket;
import org.rubnikovich.flightanalyser.parser.CustomParser;
import org.rubnikovich.flightanalyser.parser.impl.CustomParserImpl;
import org.rubnikovich.flightanalyser.service.FlightService;
import org.rubnikovich.flightanalyser.service.impl.FlightServiceImpl;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.List;
import java.util.Map;

public class Main {
    private static final String PATH = "files/tickets.json";

    public static void main(String[] args) {
        CustomParser customParser = new CustomParserImpl();
        List<Ticket> tickets = customParser.parseFile(PATH);

        FlightService flightService = new FlightServiceImpl();
        Map<String, Duration> flights = flightService.getMinFlightTime(tickets);
        BigDecimal differenceAverageMedian = flightService.getDifferenceAverageMedian(tickets);

        System.out.println("Flight time from Vladivostok to Tel Aviv:");
        for (Map.Entry<String, Duration> entry : flights.entrySet()) {
            String flight = entry.getKey();
            Duration duration = entry.getValue();
            long hours = duration.toHours();
            long minutes = duration.minusHours(hours).toMinutes();
            String strDuration = String.format("%d hours, %d minutes", hours, minutes);
            System.out.println("Flight: " + flight + ", Duration: " + strDuration);
        }
        System.out.println("The difference between the average price and the media price: " + differenceAverageMedian);
    }
}

