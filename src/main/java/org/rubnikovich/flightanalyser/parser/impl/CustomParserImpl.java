package org.rubnikovich.flightanalyser.parser.impl;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.rubnikovich.flightanalyser.entity.Ticket;
import org.rubnikovich.flightanalyser.entity.TicketsWrapper;
import org.rubnikovich.flightanalyser.parser.CustomParser;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class CustomParserImpl implements CustomParser {

    public List<Ticket> parseFile(String path) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        TicketsWrapper tickets = null;
        try {
            File jsonFile = new File(path);
            tickets = mapper.readValue(jsonFile, TicketsWrapper.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Collections.unmodifiableList(tickets.getTickets());
    }

}
