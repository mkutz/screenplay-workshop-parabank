package screenplay;

import static java.lang.String.format;

public class MissingAbilityException extends RuntimeException {

  public MissingAbilityException(
      Actor actor, Class<? extends Ability> abilityClass) {
    super(format(actor + "%s misses the ability to %s",
        actor, abilityClass.getSimpleName()));
  }
}
