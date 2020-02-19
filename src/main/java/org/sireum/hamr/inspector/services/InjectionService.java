package org.sireum.hamr.inspector.services;

import org.jetbrains.annotations.NotNull;
import org.sireum.hamr.inspector.common.Injection;

public interface InjectionService {

    /**
     * Inject an {@link Injection} into the event-stream of a running {@link Session}.
     * This method will have no effect if a stopped or non-existent session is passed.
     *
     * @param session A running {@link Session} whose event stream will be injected into.
     * @param injection A {@link Injection} object to describe how and what should be injected.
     */
    void inject(@NotNull Session session, @NotNull Injection injection);

}









