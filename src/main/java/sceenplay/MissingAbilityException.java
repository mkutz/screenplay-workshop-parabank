package sceenplay;

public class MissingAbilityException extends RuntimeException {

  public MissingAbilityException(
      Actor actor, Class<? extends Ability> abilityClass) {
    super(actor + " misses the ability to " +
        abilityClass.getSimpleName());
  }
}
