package com.turfsport.gameapp.factory;

import com.turfsport.gameapp.domain.entities.Game;
import com.turfsport.gameapp.domain.entities.SALotto;
import com.turfsport.gameapp.domain.exceptions.GameNotFoundException;

public class GameFactory {

    private static GameFactory instance;

    private GameFactory() {

    }

    public static GameFactory getInstance() {

        if(instance == null) {
            return new GameFactory();
        }

        return instance;
    }

    public Game getGame(String name, String drawDate) throws GameNotFoundException{
        Game game = null;
        if (name == null) {
            throw new IllegalArgumentException();
        }
        if (name.equalsIgnoreCase("SA Lotto")) {
            game = new SALotto(name, drawDate);
        }
        if (game == null)
            throw new GameNotFoundException(GameNotFoundException.NOT_FOUND);

        return game;
    }

}
