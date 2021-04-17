package io.github.lama06.lamamod.events;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public final class Event<T> {
    private final List<T> listeners = new ArrayList<>();
    private final Function<List<T>, T> invoker;

    public Event(Function<List<T>, T> invoker) {
        this.invoker = invoker;
    }

    public void register(T listener) {
        listeners.add(listener);
    }

    public T invoker() {
        return invoker.apply(listeners);
    }
}