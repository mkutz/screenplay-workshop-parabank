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

  public Ability uses(Class<Ability> abilityClass) {
    return abilities.stream()
        .filter(ability -> abilityClass.equals(ability.getClass()))
        .findAny()
        .orElseThrow(() -> new MissingAbilityException(this, abilityClass));
  }

  @Override
  public String toString() {
    return this.name;
  }
}
