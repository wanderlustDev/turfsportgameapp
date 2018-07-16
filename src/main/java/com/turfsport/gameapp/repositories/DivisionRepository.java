package com.turfsport.gameapp.repositories;

import java.util.HashMap;
import java.util.Map;

public class DivisionRepository {

    private Map<String, String> divisions = new HashMap<>();

    private static DivisionRepository instance;

    public static DivisionRepository getInstance() {

        if (instance == null) {
            return new DivisionRepository();
        }
        return instance;
    }

    private DivisionRepository() {

        createNewDivision("DIVISION8", "Winner - Division 8");
        createNewDivision("DIVISION7", "Winner - Division 7");
        createNewDivision("DIVISION6", "Winner - Division 6");
        createNewDivision("DIVISION5", "Winner - Division 5");
        createNewDivision("DIVISION4", "Winner - Division 4");
        createNewDivision("DIVISION3", "Winner - Division 3");
        createNewDivision("DIVISION2", "Winner - Division 2");
        createNewDivision("DIVISION1", "Winner - Division 1");
        createNewDivision("Not a winner", "Not a winner");


    }

    public void createNewDivision(String value, String description) {
        divisions.put(value, description);
    }

    public String getDivision(String value) {
        return divisions.get(value);
    }

    public void removeDivision(String value) {
        throw new UnsupportedOperationException();
    }

    public Map<String, String> getDivisions() {
        return divisions;
    }
}
