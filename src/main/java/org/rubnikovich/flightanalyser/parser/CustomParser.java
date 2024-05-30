package org.rubnikovich.flightanalyser.parser;

import org.rubnikovich.flightanalyser.entity.Ticket;

import java.util.List;

public interface CustomParser {
    List<Ticket> parseFile(String path);
}
