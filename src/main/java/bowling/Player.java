package bowling;

import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Player {

    private String name;
    private List<Frame> frames;

    public Player(String name){
        this.name = name;
        frames = new ArrayList<Frame>();
    }

    public void updateFrames(String pinfall){
        if (frames.isEmpty()) {
            frames.add(new Frame(pinfall));
            return;
        }
        Frame lastFrame = frames.get(frames.size() - 1);
        boolean isTheMaxFrame = frames.size() == Bowling.MAX_FRAMES;

        if ( (lastFrame.isStrike() || lastFrame.hasAllPinfalls()) && !isTheMaxFrame ) {
            frames.add(new Frame(pinfall));
        }
        else if ( isTheMaxFrame && lastFrame.hasAllPinfalls() ) {
            if (null != lastFrame.getPinfallC()){
                System.out.println("Extra pinfall information received, we will ignore it: " + pinfall + ", player: " + name);
                return;
            }
            lastFrame.setPinfallC(pinfall);
        }
        else {
            if (null != lastFrame.getPinfallB()){
                System.out.println("Extra pinfall information received, we will ignore it: " + pinfall + ", player: " + name);
                return;
            }
            lastFrame.setPinfallB(pinfall);
        }
    }

    @Override
    public String toString(){
        StringBuilder pinfalls = new StringBuilder("Pinfalls");
        StringBuilder scores = new StringBuilder("Score");
        for (Frame frame : frames) {
            pinfalls.append(frame);
            scores.append(StringUtils.repeat(Bowling.SEPARATOR, 2))
                  .append(frame.getScore());
        }
        return new StringBuilder().append(name).append(Bowling.LINE_SEPARATOR)
                .append(pinfalls).append(Bowling.LINE_SEPARATOR)
                .append(scores).toString();
    }

}
