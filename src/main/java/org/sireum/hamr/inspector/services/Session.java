/*
 * Copyright (c) 2020, Matthew Weis, Kansas State University
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this
 *    list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

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