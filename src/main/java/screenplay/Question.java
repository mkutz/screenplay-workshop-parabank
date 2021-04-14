package screenplay;

public interface Question<A> {

  A answerAs(Actor actor);
}
