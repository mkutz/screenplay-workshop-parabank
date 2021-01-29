package screenplay;

public class MissingFactException extends RuntimeException {

  public <F extends Fact> MissingFactException(
      Actor actor, Class<F> factClass) {
    super(actor + " can't remember " + factClass);
  }
}
