package org.sireum.hamr.inspector.services;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.Optional;

/**
 * THIS VALUE MUST BE IMMUTABLE (because it's used as a key in maps).
 *
 * Represents a session (completed or currently running) visible by the Inspector.
 * This value MUST remain immutable because its hashcode is used as a key in certain caches.
 */
public final class Session implements Comparable<Session> {

    @NotNull
    private final String name;

    public Session(@NotNull String name) {
        this.name = name;
    }

    @NotNull
    public String getName() {
        return name;
    }

    @Override
    public int compareTo(@NotNull Session o) {
        final var self = parse(this);
        final var other = parse(o);

        if (self.isPresent() && other.isPresent()) {
            return self.get().compareTo(other.get());
        } else if (self.isPresent()) {
            return -1;
        } else if (other.isPresent()) {
            return 1;
        } else {
            return name.compareTo(o.name);
        }
    }

    private static Optional<Long> parse(Session session) {
        try {
            return Optional.of(Long.parseLong(session.name));
        } catch (NumberFormatException unused) {
            return Optional.empty();
        }
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Session session = (Session) o;
        return name.equals(session.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}