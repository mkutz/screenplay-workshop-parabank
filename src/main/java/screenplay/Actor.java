package screenplay;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static java.lang.String.format;

public class Actor {

  private static final Logger log =
      LoggerFactory.getLogger(Actor.class);

  private final String name;
  private final Map<Class<? extends Ability>, Ability> abilities;

  public Actor(String name) {
    this.name = name;
    this.abilities = new HashMap<>();
  }

  public Actor can(Ability ability) {
    abilities.put(ability.getClass(), ability);
    log.debug(format("%s can %s",
        this, ability));
    return this;
  }

  public <A extends Ability> A uses(Class<A> abilityClass) {
    final var ability = Optional.of(abilities.get(abilityClass))
        .map(abilityClass::cast)
        .orElseThrow(() ->
            new MissingAbilityException(this, abilityClass)
        );
    log.debug(format("%s uses %s",
        this, ability));
    return ability;
  }

  public Actor does(Task task) {
    try {
      task.performAs(this);
    } catch (Exception e) {
      log.error(format("%s does %s ✘ %s",
          this, task, e.getMessage()));
      throw e;
    }
    log.info(format("%s does %s ✔",
        this, task));
    return this;
  }

  public <A> A checks(Question<A> question) {
    try {
      final var answer = question.answerAs(this);
      log.info(format("%s checks %s → %s ✔",
          this, question, answer));
      return answer;
    } catch (Exception e) {
      log.error(format("%s does %s ✘ %s",
          this, question, e.getMessage()));
      throw e;
    }
  }

  @Override
  public String toString() {
    return name;
  }
}
