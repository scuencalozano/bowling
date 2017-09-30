package bowling;

import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;

@Getter
public class Bowling implements Game {

    public static final int MAX_FRAMES = 10;
    public static final String[] PINFALL_ACCEPTED_VALUES = {"F", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};

    List<Player> players;

    public Bowling(List<String> fileLines){
        HashMap<String, Player> hMap = new HashMap<String, Player>();
        for(String line : fileLines){
            if ( !isValidLine(line) ) {
                continue;
            }
            String[] playerInfo = line.trim().split(REGEX_WHITESPACES);
            String playerName = playerInfo[0];
            String pinfall = playerInfo[1];
            Player player = hMap.get(playerName);
            if (null == player){
                player = new Player(playerName);
                hMap.put(playerName, player);
            }
            player.updateFrames(pinfall);
        }
        players = new ArrayList<Player>(hMap.values());
    }

    @Override
    public void calculateScore() {
        for (Player player : players) {
            int score = 0;
            for (int i = 0; i < player.getFrames().size(); i++) {
                Frame frame = player.getFrames().get(i);
                Frame nextFrame = (i + 1 < player.getFrames().size()) ? player.getFrames().get(i + 1) : null;
                if (frame.isStrike()) {
                    score += getValueStrike(player.getFrames(), i);
                } else if (frame.isSpare()) {
                    score += 10 + nextFrame.getValuePinfallA();
                } else {
                    score += frame.getSumPinfalls();
                }
                frame.setScore(score);
            }
        }
    }

    @Override
    public void showResults() {
        System.out.println(getHeader());
        for ( Player player : players ){
            System.out.println(player);
        }
    }

    @Override
    public boolean isValid(){
        if (players.size() == 0) {
            return false;
        }
        for (Player player : players) {
            if (player.getFrames().size() != MAX_FRAMES){
                System.out.println("We need more information to get score from player " + player.getName());
                return false;
            }
            Frame last = player.getFrames().get(MAX_FRAMES - 1);
            boolean validLastFrame = last.isStrike() ? null != last.getPinfallC() : null != last.getPinfallB();
            if (!validLastFrame){
                System.out.println("We need more information to fill the last frame, player: " + player.getName());
                return false;
            }
        }
        return true;
    }

    /*
     * The accepted pattern: "name<space>score" including "<whitespace>name<whitespace>score<whitespace>"
     * the score value must be contains on PINFALL_ACCEPTED_VALUES array, to be valid
     */
    public boolean isValidLine(String line){
        Pattern pattern = Pattern.compile(REGEX_WHITESPACES);
        line = line.trim();

        boolean isWhitespace = pattern.matcher(line).find();
        if ( !isWhitespace ) {
            System.out.println("This line does not contain any whitespace: " + line );
            return false;
        }
        String[] playerInfo = line.split(REGEX_WHITESPACES);
        if ( playerInfo.length != 2 ) {
            System.out.println("This line does not contain the correct num of params: " + line );
            return false;
        }
        String pinfall = playerInfo[1];
        if ( !Arrays.asList(PINFALL_ACCEPTED_VALUES).contains(pinfall) ) {
            System.out.println("The pinfall value is not accepted: " + pinfall  + " line: " + line);
            return false;
        }

        return true;
    }

    private int getValueStrike(List<Frame> frames, int index){
        if (index == frames.size() - 1) { // is the last frame
            return frames.get(index).getSumPinfallsLastFrame();
        }
        Frame nextFrame = frames.get(index + 1);
        int valueStrike = nextFrame.getSumPinfalls();
        if (nextFrame.isStrike()) {
            valueStrike += (index + 2 < frames.size()) ? frames.get(index + 2).getValuePinfallA() : 0;
        }
        return 10 + valueStrike;
    }

    private String getHeader() {
        StringBuilder header = new StringBuilder("Frame");
        for (int i = 1; i <= Bowling.MAX_FRAMES; i++) {
            header.append(StringUtils.repeat(Bowling.SEPARATOR, 2)).append(i);
        }
        return header.toString();
    }

}
