package com.turfsport.gameapp;

import com.turfsport.gameapp.domain.entities.Game;
import com.turfsport.gameapp.domain.entities.SALotto;
import com.turfsport.gameapp.domain.exceptions.GameNotFoundException;
import com.turfsport.gameapp.factory.GameFactory;
import com.turfsport.gameapp.repositories.DivisionRepository;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Unit test for simple GameApplication.
 */
public class SALottoTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void givenAValidGameNameItShouldReturnAGame() {
        Game saLottoGame = GameFactory.getInstance().getGame("SA Lotto", "2018-07-16");

        assertNotNull(saLottoGame);
        assertEquals("SA Lotto", saLottoGame.getName());
    }

    @Test
    public void givenAnInvalidGameNameItShouldThrowException() {

        expectedException.expect(GameNotFoundException.class);
        expectedException.expectMessage(GameNotFoundException.NOT_FOUND);

        GameFactory.getInstance().getGame("US Lotto", "2018-07-16");
    }

    @Test
    public void givenInvalidEntryNumbersItShouldThrowException() {

        Game saLottoGame = GameFactory.getInstance().getGame("SA Lotto", "2018-07-16");
        Integer[] winningDraw = {29, 15, 22, 41, 19, 4, 17};
        saLottoGame.captureDraw(winningDraw);

        Integer[] entry = {1, 3, 53, 21, 0, 31};
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(SALotto.INVALID_NUMBER);
        saLottoGame.getDivision(entry);
    }

    @Test
    public void givenInvalidWinningDrawNumbersItShouldThrowException() {

        Game saLottoGame = GameFactory.getInstance().getGame("SA Lotto", "2018-07-16");

        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(SALotto.INVALID_NUMBER);

        Integer[] winningDraw = {0, 15, 62, 41, 19, 98, 17};
        saLottoGame.captureDraw(winningDraw);
    }

    @Test
    public void givenADrawItShouldStoreItInGameInstance() {
        Game saLottoGame = GameFactory.getInstance().getGame("SA Lotto", "2018-07-16");

        Integer[] winningDraw = {29, 15, 22, 41, 19, 4, 17};
        saLottoGame.captureDraw(winningDraw);

        assertNotNull(saLottoGame);
        assertNotNull(saLottoGame.getWinningDraw());
    }

    @Test
    public void givenASetOfWinningNumbersItShouldReturnADivision() {
        Game saLottoGame = GameFactory.getInstance().getGame("SA Lotto", "2018-07-16");
        Integer[] winningDraw = {29, 15, 22, 41, 19, 4, 17};
        saLottoGame.captureDraw(winningDraw);

        Integer[] entry = {1, 3, 20, 21, 30, 31};

        assertNotNull(saLottoGame);
        assertNotNull(saLottoGame.getWinningDraw());
        assertEquals(DivisionRepository.getInstance().getDivision("Not a winner"), saLottoGame.getDivision(entry));
    }

    @Test
    public void givenWinningNumbersWithBonusItShouldReturnADivision() {
        Game saLottoGame = GameFactory.getInstance().getGame("SA Lotto", "2018-07-16");
        Integer[] winningDraw = {29, 15, 22, 41, 19, 4, 17};
        saLottoGame.captureDraw(winningDraw);

        Integer[] entry = {3, 15, 19, 23, 26, 17};

        assertNotNull(saLottoGame);
        assertNotNull(saLottoGame.getWinningDraw());
        assertEquals(DivisionRepository.getInstance().getDivision("DIVISION8"), saLottoGame.getDivision(entry));
    }

    @Test
    public void givenASetOfLosingNumbersItShouldReturnALoss() {
        Game saLottoGame = GameFactory.getInstance().getGame("SA Lotto", "2018-07-16");
        Integer[] winningDraw = {29, 15, 22, 41, 19, 4, 17};
        saLottoGame.captureDraw(winningDraw);

        Integer[] entry = {3, 15, 19, 22, 29, 41};

        assertNotNull(saLottoGame);
        assertNotNull(saLottoGame.getWinningDraw());
        assertEquals(DivisionRepository.getInstance().getDivision("DIVISION3"), saLottoGame.getDivision(entry));
    }

}
