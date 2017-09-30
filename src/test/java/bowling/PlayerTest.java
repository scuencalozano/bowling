package bowling;

import org.apache.commons.lang3.StringUtils;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class PlayerTest {

    @Test
    public void formatPinfallsTest() {

        Bowling bowling = new Bowling(loadPlayers());
        Player player = bowling.players.get(0);
        String playerResult = player.toString();

        assertTrue(playerResult.contains("Jeff"));
        assertTrue(playerResult.contains("Pinfalls"));
        assertTrue(playerResult.contains("Score"));
        assertTrue(playerResult.contains("X"));
        assertTrue(playerResult.contains("7"));
        assertTrue(playerResult.contains("/"));
        assertFalse(playerResult.contains("F"));
        assertEquals(2, StringUtils.countMatches(playerResult, Bowling.LINE_SEPARATOR));
    }

    private List<String> loadPlayers(){
        String[] players = { "Jeff 10", "Jeff 7", "Jeff 3"};
        return Arrays.asList(players);
    }

}
