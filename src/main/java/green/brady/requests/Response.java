package green.brady.requests;

import java.util.function.Consumer;
import java.util.function.Function;

public class Response<T> {

    private final T data;
    private final Throwable error;

    public Response(T data, Throwable error) {
        this.data = data;
        this.error = error;
    }

    public <E extends Exception> T getOrThrow(Function<Throwable, E> handler) throws E {
        if (error != null) {
            throw handler.apply(error);
        }
        return data;
    }

    public T getOrNull() {
        return data;
    }

    public T getOrElse(T defaultValue) {
        return data != null ? data : defaultValue;
    }

    public Throwable getError() {
        return error;
    }

    public boolean hasError() {
        return error != null;
    }

    public boolean hasData() {
        return data != null;
    }

    public void fold(Consumer<T> dataConsumer, Consumer<Throwable> errorConsumer) {
        if (error != null) {
            errorConsumer.accept(error);
        } else {
            dataConsumer.accept(data);
        }
    }

    public <R> Response<R> map(Function<T, R> mapper) {
        if (error != null) {
            return new Response<>(null, error);
        } else {
            return new Response<>(mapper.apply(data), null);
        }
    }
}
