package screenplay;

public interface Question<A> {

  A answeredBy(Actor actor);
}
