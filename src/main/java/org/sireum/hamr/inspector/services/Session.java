package org.sireum.hamr.inspector.services;

import lombok.*;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

/**
 * THIS VALUE MUST BE IMMUTABLE (because it's used as a key in maps).
 *
 * Represents a session (completed or currently running) visible by the Inspector.
 * This value MUST remain immutable because its hashcode is used as a key in certain caches.
 */
@ToString
@EqualsAndHashCode
@AllArgsConstructor(access = AccessLevel.PACKAGE)
public final class Session implements Comparable<Session> {

    @Getter
    @NotNull
    private final String name;

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

}