package com.turfsport.gameapp.service.impl;

import com.turfsport.gameapp.repositories.DivisionRepository;
import com.turfsport.gameapp.service.DivisionService;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SALottoDivisionService implements DivisionService {

    public static final String INVALID_NUMBER = "Unaccepted numbers for lottery game";

    public String getDivision(Integer[] entry, Map<Integer, Integer> winningDraw)
            throws IllegalArgumentException {

        String division = "";

        for (Integer num : entry) {
            if (num < 1 || num > 52) {
                throw new IllegalArgumentException(INVALID_NUMBER);
            }
        }

        List<Integer> entryList = Arrays.asList(entry);

        Map<Integer, Integer> originalWinningSet = new HashMap<>(winningDraw);

        winningDraw.values().retainAll(entryList);

        if (winningDraw.size() < 3) {
            division = "Not a winner";
        } else {
            division = getWinningDivision(winningDraw, originalWinningSet);
        }

        return division;
    }

    private String getWinningDivision(Map<Integer, Integer> winningSet, Map<Integer, Integer> originalWinningSet) {

        String division = "Not a winner";

        Integer bonusNumber = originalWinningSet.get(originalWinningSet.size() - 1);

        if (winningSet.size() == 3 && winningSet.containsValue(bonusNumber)) {
            // match 2 + bonus
            division = DivisionRepository.getInstance().getDivision("DIVISION8");
        } else if (winningSet.size() == 3) {
            // match 3
            division = DivisionRepository.getInstance().getDivision("DIVISION7");
        } else if (winningSet.size() == 4 && winningSet.containsValue(bonusNumber)) {
            // match 3 + bonus
            division = DivisionRepository.getInstance().getDivision("DIVISION6");
        } else if (winningSet.size() == 4) {
            // match 4
            division = DivisionRepository.getInstance().getDivision("DIVISION5");
        } else if (winningSet.size() == 5 && winningSet.containsValue(bonusNumber)) {
            // match 4 + bonus
            division = DivisionRepository.getInstance().getDivision("DIVISION4");
        } else if (winningSet.size() == 5) {
            // match 5
            division = DivisionRepository.getInstance().getDivision("DIVISION3");
        } else if (winningSet.size() == 6  && winningSet.containsValue(bonusNumber)) {
            // match 5 + bonus
            division = DivisionRepository.getInstance().getDivision("DIVISION2");
        } else if (winningSet.size() == 6) {
            // match 6
            division = DivisionRepository.getInstance().getDivision("DIVISION1");
        }
        return division;
    }
}
