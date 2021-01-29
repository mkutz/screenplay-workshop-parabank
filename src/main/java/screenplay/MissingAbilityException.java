package screenplay;

public class MissingAbilityException extends RuntimeException {

  public MissingAbilityException(Actor actor, Class<Ability> abilityClass) {
    super(actor + " misses ability " + abilityClass);
  }
}
