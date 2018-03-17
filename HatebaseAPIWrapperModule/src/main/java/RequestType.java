/**
 * Created by ionut.tirlea on 01/12/2017.
 */
public enum RequestType {
    RECIPIENT,
    OVERHEARD,
    USED,
    TWITTER;

    public String getType() {
        switch(this)
        {
            case RECIPIENT:
                return "r";
            case OVERHEARD:
                return "o";
            case USED:
                return "u";
            case TWITTER:
                return "t";
            default:
                return null;
        }
    }
}
