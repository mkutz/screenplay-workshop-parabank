package screenplay;

import java.util.HashSet;
import java.util.Set;

public class Actor {

  private final String name;
  private final Set<Ability> abilities = new HashSet<>();

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
        .orElseThrow(() -> new MissingAbilityException(this, abilityClass));
  }

  public Actor performs(Task task) {
    task.performedBy(this);
    return this;
  }

  public <A> A answers(Question<A> question) {
    return question.answeredBy(this);
  }

  @Override
  public String toString() {
    return this.name;
  }
}
