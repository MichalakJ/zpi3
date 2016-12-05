package pl.lodz.p.iis.library;

/**
 * @Author Mateusz Wieczorek on 05.12.2016.
 */
public class CheckSumRS {

    private String type;
    private String checkSum;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCheckSum() {
        return checkSum;
    }

    public void setCheckSum(String checkSum) {
        this.checkSum = checkSum;
    }

    @Override
    public String toString() {
        return "CheckSumRS{" +
                "type='" + type + '\'' +
                ", checkSum='" + checkSum + '\'' +
                '}';
    }
}
