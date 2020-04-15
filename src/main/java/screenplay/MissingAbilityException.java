package screenplay;

import screenplay.abilities.Ability;

public class MissingAbilityException extends RuntimeException {

    public MissingAbilityException(Actor actor, Class<? extends Ability> abilityClass) {
        super(String.format("The actor %s is not able to %s", actor, abilityClass.getSimpleName()));
    }
}
