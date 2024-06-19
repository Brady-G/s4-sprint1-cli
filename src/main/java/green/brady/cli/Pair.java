package green.brady.cli;

import java.util.function.Function;

public record Pair<A, B>(A left, B right) {

    public Pair<A, B> mapLeft(Function<A, A> mapper) {
        return new Pair<>(mapper.apply(left), right);
    }

    public Pair<A, B> mapRight(Function<B, B> mapper) {
        return new Pair<>(left, mapper.apply(right));
    }
}
