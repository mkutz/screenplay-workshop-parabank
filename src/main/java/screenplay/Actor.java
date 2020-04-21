package screenplay;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import screenplay.abilities.Ability;
import screenplay.facts.Fact;
import screenplay.questions.Question;
import screenplay.tasks.Task;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Actor {

    private static final Logger log = LoggerFactory.getLogger(Actor.class);

    private final String name;
    private final Map<Class<? extends Ability>, Ability> abilities = new HashMap<>();
    private final Map<Class<? extends Fact>, Fact> facts = new HashMap<>();

    public Actor(String name) {
        log.info(String.format("Creating new actor named \"%s\"", name));
        this.name = name;
    }

    public Actor can(Ability ability) {
        log.info(String.format("%s can %s", this, ability));
        abilities.put(ability.getClass(), ability);
        return this;
    }

    @SuppressWarnings("unchecked")
    public <T extends Ability> T usesAbility(Class<T> abilityClass) {
        log.info(String.format("%s uses ability to %s", this, abilityClass.getSimpleName()));
        return (T) Optional.ofNullable(abilities.get(abilityClass))
                .orElseThrow(() -> new MissingAbilityException(this, abilityClass));
    }

    public void performs(Task task) {
        log.info(String.format("%s performs %s", this, task));
        task.performAs(this);
    }

    public <T> T seesThat(Question<T> question) {
        log.info(String.format("%s sees that %s", this, question));
        return question.answeredBy(this);
    }

    public Actor knows(Fact fact) {
        facts.put(fact.getClass(), fact);
        log.info(String.format("%s knows %s", this, fact));
        return this;
    }

    @SuppressWarnings("unchecked")
    public <T extends Fact> T remembers(Class<T> factClass) {
        log.info(String.format("%s remembers %s", this, factClass.getSimpleName()));
        return (T) Optional.ofNullable(facts.get(factClass))
                .orElseThrow(() -> new MissingFactException(this, factClass));
    }

    @Override
    public String toString() {
        return name;
    }
}
