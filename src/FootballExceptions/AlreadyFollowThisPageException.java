package FootballExceptions;

public class AlreadyFollowThisPageException extends  Exception {
    public AlreadyFollowThisPageException() {
    }

    public AlreadyFollowThisPageException(String message) {
        super(message);
    }
}
