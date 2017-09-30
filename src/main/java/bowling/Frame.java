package bowling;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;

@Data
public class Frame {

    private static final String STRIKE_VALUE = "X";
    private static final String SPARE_VALUE = "/";
    private static final int VALUE_FAULT = 0;

    private int score;
    private String pinfallA;
    private String pinfallB;
    private String pinfallC; // can be used only in the last frame

    public Frame(String pinfallA){
        this.pinfallA = pinfallA;
    }

    public boolean isStrike() {
        return "10".equals(pinfallA);
    }

    public boolean isSpare() {
        return getSumPinfalls() == 10;
    }

    public int getSumPinfalls() {
        return getValue(pinfallA) + getValue(pinfallB);
    }

    public int getSumPinfallsLastFrame() {
        return getValue(pinfallA) + getValue(pinfallB) + getValue(pinfallC);
    }

    public int getValuePinfallA() {
        return getValue(pinfallA);
    }

    public boolean hasAllPinfalls() {
        return null != pinfallB;
    }

    @Override
    public String toString(){
        if (isStrike() && null == pinfallC) {
            return new StringBuilder().append(StringUtils.repeat(Bowling.SEPARATOR, 2))
                    .append(STRIKE_VALUE).toString();
        }

        if (null != pinfallC) { // player get strike on the last frame
            return new StringBuilder().append(Bowling.SEPARATOR)
                    .append(STRIKE_VALUE)
                    .append(Bowling.SEPARATOR)
                    .append("10".equals(pinfallB) ? STRIKE_VALUE : pinfallB)
                    .append(Bowling.SEPARATOR)
                    .append("10".equals(pinfallC) ? STRIKE_VALUE : pinfallC).toString();
        }

        if (isSpare()) {
            return new StringBuilder().append(Bowling.SEPARATOR)
                    .append(pinfallA)
                    .append(Bowling.SEPARATOR)
                    .append(SPARE_VALUE).toString();
        }

        return new StringBuilder().append(Bowling.SEPARATOR)
                    .append(pinfallA)
                    .append(Bowling.SEPARATOR)
                    .append(pinfallB).toString();
    }

    private int getValue(String pinfall){
        if ("F".equals(pinfall)){
            return VALUE_FAULT;
        }
        if (null == pinfall) {
            return 0;
        }

        try {
            return Integer.parseInt(pinfall);
        }
        catch (NumberFormatException e){
            e.printStackTrace();
        }
        return 0;
    }

}
