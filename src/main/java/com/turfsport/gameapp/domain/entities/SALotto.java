package com.turfsport.gameapp.domain.entities;

import com.turfsport.gameapp.service.impl.SALottoDivisionService;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SALotto extends Game {

    public static final String INVALID_NUMBER = "Unaccepted numbers for lottery game";

    private static final int UPPERBOUND = 52;

    private static final int LOWERBOUND = 1;

    private Map<Integer, Integer> winningDraw;

    private SALottoDivisionService service = new SALottoDivisionService();

    public SALotto(String name, String date) {
        super(name, date);
    }

    @Override
    public void captureDraw(Integer[] winningDraw) throws IllegalArgumentException {

        for (Integer num : winningDraw) {
            if (num < LOWERBOUND || num > UPPERBOUND) {
                throw new IllegalArgumentException(INVALID_NUMBER);
            }
        }
        List<Integer> numbers = Arrays.asList(winningDraw);
        Map<Integer, Integer> winningNumbersMap = new HashMap<>();
        int i = 0;
        for (Integer number : numbers) {
            winningNumbersMap.put(i, number);
            i++;
        }
        this.winningDraw = winningNumbersMap;
    }

    @Override
    public String getDivision(Integer[] entry) throws IllegalArgumentException {
        return service.getDivision(entry, this.getWinningDraw());
    }

    @Override
    public Map<Integer, Integer> getWinningDraw() {
        return winningDraw;
    }

}
