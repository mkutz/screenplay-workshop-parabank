package screenplay.questions;

import screenplay.Actor;

public interface Question<T> {

    T answeredBy(Actor actor);
}
