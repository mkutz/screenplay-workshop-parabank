package screenplay;

import java.util.HashSet;
import java.util.Set;

public class Actor {

  private final String name;
  private final Set<Ability> abilities = new HashSet<>();
  private Set<Fact> facts = new HashSet<>();

  public Actor(String name) {
    this.name = name;
  }

  public Actor can(Ability ability) {
    abilities.add(ability);
    return this;
  }

  public <A extends Ability> A uses(Class<A> abilityClass) {
    return abilities.stream()
        .filter(ability -> abilityClass.equals(ability.getClass()))
        .findAny()
        .map(abilityClass::cast)
        .orElseThrow(() ->
            new MissingAbilityException(this, abilityClass)
        );
  }

  public Actor performs(Task task) {
    task.performedBy(this);
    return this;
  }

  public <A> A answers(Question<A> question) {
    return question.answeredBy(this);
  }

  public Actor learns(Fact fact) {
    this.facts.add(fact);
    return this;
  }

  public <F extends Fact> F remembers(Class<F> factClass) {
    return facts.stream()
        .filter(ability -> factClass.equals(ability.getClass()))
        .findAny()
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
