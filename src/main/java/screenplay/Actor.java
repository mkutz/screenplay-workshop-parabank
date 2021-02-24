package screenplay;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Actor {

  private static final Logger log =
      LoggerFactory.getLogger(Actor.class);
  private final String name;
  private final Map<Class<? extends Ability>, Ability> abilities;
  private final Map<Class<? extends Fact>, Fact> facts;

  public Actor(String name) {
    this.name = name;
    this.abilities = new HashMap<>();
    this.facts = new HashMap<>();
  }

  public Actor can(Ability ability) {
    abilities.put(ability.getClass(), ability);
    log.info(this + " can " + ability);
    return this;
  }

  public <A extends Ability> A uses(Class<A> abilityClass) {
    log.info(this + " uses ability to " + abilityClass);
    return Optional.of(abilities.get(abilityClass))
        .map(abilityClass::cast)
        .orElseThrow(() ->
            new MissingAbilityException(this, abilityClass)
        );
  }

  public Actor does(Task task) {
    task.performedBy(this);
    return this;
  }

  public <A> A checks(Question<A> question) {
    return question.answeredBy(this);
  }

  public Actor learns(Fact fact) {
    this.facts.put(fact.getClass(), fact);
    return this;
  }

  public <F extends Fact> F remembers(Class<F> factClass) {
    return Optional.of(facts.get(factClass))
        .map(factClass::cast)
        .orElseThrow(() ->
            new MissingFactException(this, factClass)
        );
  }

  @Override
  public String toString() {
    return this.name;
  }
}
