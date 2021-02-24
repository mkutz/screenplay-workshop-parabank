package screenplay;

public class MissingAbilityException extends RuntimeException {

  public MissingAbilityException(
      Actor actor, Class<? extends Ability> abilityClass) {
    super(actor + " misses ability to " +
        abilityClass.getSimpleName());
  }
}
