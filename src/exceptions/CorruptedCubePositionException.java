package exceptions;

public class CorruptedCubePositionException extends IllegalArgumentException{
    public CorruptedCubePositionException(String s) {
        super(s);
    }
}
