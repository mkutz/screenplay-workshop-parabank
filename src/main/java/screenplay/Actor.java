package screenplay;

import screenplay.abilities.Ability;
import screenplay.facts.Fact;
import screenplay.questions.Question;
import screenplay.tasks.Task;

import java.util.HashSet;
import java.util.Set;

public class Actor {

    private final String name;
    private final Set<Ability> abilities = new HashSet<>();
    private final Set<Fact> facts = new HashSet<>();

    public Actor(String name) {
        this.name = name;
    }

    public Actor can(Ability ability) {
        abilities.add(ability);
        return this;
    }

    @SuppressWarnings("unchecked")
    public <T extends Ability> T getAbility(Class<T> abilityClass) {
        return (T) abilities.stream()
                .filter(ability -> (ability.getClass().equals(abilityClass)))
                .findAny()
                .orElseThrow(() -> new MissingAbilityException(this, abilityClass));
    }

    public void perform(Task task) {
        task.performAs(this);
    }

    public <T> T seesThat(Question<T> question) {
        return question.answeredBy(this);
    }

    public Actor knows(Fact fact) {
        facts.add(fact);
        return this;
    }

    @SuppressWarnings("unchecked")
    public <T extends Fact> T getFact(Class<T> factClass) {
        return (T) facts.stream()
                .filter(fact -> (fact.getClass().equals(factClass)))
                .findAny()
                .orElseThrow(() -> new MissingFactException(this, factClass));
    }

    @Override
    public String toString() {
        return name;
    }
}
