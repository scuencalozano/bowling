package bowling;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class BowlingTest {

    Bowling bowling;

    @Before
    public void setUp(){
        bowling = new Bowling(getPlayers());
    }

    @Test
    public void shouldBeCreatedBowling() {
        assertEquals(bowling.getPlayers().size(), 2);

        // player 0
        assertEquals(bowling.getPlayers().get(0).getName(), "Jeff");
        assertEquals(bowling.getPlayers().get(0).getFrames().size(), 10);

        // player 1
        assertEquals(bowling.getPlayers().get(1).getName(), "John");
        assertEquals(bowling.getPlayers().get(1).getFrames().size(), 10);
    }

    @Test
    public void shouldCalculateScores() {
        bowling.calculateScore();

        // player 0
        List<Frame> frames = bowling.getPlayers().get(0).getFrames();
        assertEquals(frames.get(0).getScore(), 20);
        assertEquals(frames.get(1).getScore(), 39);
        assertEquals(frames.get(2).getScore(), 48);
        assertEquals(frames.get(3).getScore(), 66);
        assertEquals(frames.get(4).getScore(), 74);
        assertEquals(frames.get(5).getScore(), 84);
        assertEquals(frames.get(6).getScore(), 90);
        assertEquals(frames.get(7).getScore(), 120);
        assertEquals(frames.get(8).getScore(), 148);
        assertEquals(frames.get(9).getScore(), 167);

        // player 1
        List<Frame> frames1 = bowling.getPlayers().get(1).getFrames();
        assertEquals(frames1.get(0).getScore(), 16);
        assertEquals(frames1.get(1).getScore(), 25);
        assertEquals(frames1.get(2).getScore(), 44);
        assertEquals(frames1.get(3).getScore(), 53);
        assertEquals(frames1.get(4).getScore(), 82);
        assertEquals(frames1.get(5).getScore(), 101);
        assertEquals(frames1.get(6).getScore(), 110);
        assertEquals(frames1.get(7).getScore(), 124);
        assertEquals(frames1.get(8).getScore(), 132);
        assertEquals(frames1.get(9).getScore(), 151);
    }

    @Test
    public void shouldValidateInputValues_notCorrectValues() {
        String lineNoWhitespace = "noWhiteSpaces";
        assertFalse(bowling.isValidLine(lineNoWhitespace));

        String lineWhitExtraData = "Jeff 5 F";
        assertFalse(bowling.isValidLine(lineWhitExtraData));

        String lineWhitPinfallNotAccepted = "Jeff 11";
        assertFalse(bowling.isValidLine(lineWhitPinfallNotAccepted));
    }

    @Test
    public void shouldValidateInputValues_correctValues() {
        String lineWithOneSpace = "  name 10";
        assert(bowling.isValidLine(lineWithOneSpace));

        String lineWithAnyWhitepace = " name          10  ";
        assert(bowling.isValidLine(lineWithAnyWhitepace));

        String lineWhitPinfallFail = "      Jeff              F     ";
        assert(bowling.isValidLine(lineWhitPinfallFail));
    }

    @Test
    public void shouldValidateBowlingPlayers_correctInfo() {
        List<String> players = getPlayers();
        Bowling correctInfo = new Bowling(players);
        assert(correctInfo.isValid());
    }

    @Test
    public void shouldValidateBowlingPlayers_notCompleteInfo() {
        Bowling notCompleteInfo = new Bowling(Arrays.asList( new String[]{"Jeff 10", "Jeff 3", "Jeff 7"} ));
        assertFalse(notCompleteInfo.isValid());
   }

    @Test
    public void shouldValidateBowlingPlayers_extraInfo() {
        List<String> players = new ArrayList<String>(getPlayers());
        List<String> extraPlayerInfo = Arrays.asList( new String[]{"Jeff 10", "Jeff 3", "Jeff 7"} );
        players.addAll(extraPlayerInfo); // add extra info
        Bowling extraInfo = new Bowling(players);
        // is valid because the app will ignore any extra info
        assert(extraInfo.isValid());
    }

    private List<String> getPlayers(){
        String[] players = {"Jeff 10", "John 3", "John 7", "Jeff 7",
                "Jeff 3", "John 6", "John 3", "Jeff 9", "Jeff 0", "John 10",
                "Jeff 10", "John 8", "John 1", "Jeff 0", "Jeff 8", "John 10",
                "Jeff 8", "Jeff 2", "John 10", "Jeff F", "Jeff 6", "John 9",
                "John 0", "Jeff 10", "John 7", "John 3", "Jeff 10", "John 4",
                "John 4", "Jeff 10", "Jeff 8", "Jeff 1", "John 10", "John 9",
                "John 0"};
        return Arrays.asList(players);
    }

}
