package lab.orion.authentication.exception;

public class AuthenticationException extends Exception {
    private int status = 403;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
