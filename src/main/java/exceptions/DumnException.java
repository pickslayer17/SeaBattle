package exceptions;

/*
This exception occurs when user input is totally incorrect
 */

public class DumnException extends Exception {
    String s;
    public DumnException(String s) {
        this.s = s;
    }

    @Override
    public String toString() {
        return s;
    }
}
