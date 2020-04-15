package screenplay;

import screenplay.facts.Fact;

public class MissingFactException extends RuntimeException {

    public <T extends Fact> MissingFactException(Actor actor, Class<T> factClass) {
        super(String.format("The actor %s does not know %s", actor, factClass.getSimpleName()));
    }
}
