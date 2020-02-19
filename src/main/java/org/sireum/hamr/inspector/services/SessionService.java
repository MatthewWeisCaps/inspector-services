package org.sireum.hamr.inspector.services;

import org.jetbrains.annotations.NotNull;
import reactor.core.publisher.Flux;
import reactor.core.publisher.GroupedFlux;
import reactor.core.publisher.Mono;

public interface SessionService {

    @NotNull
    Flux<Session> sessions();

    @NotNull
    Mono<Long> startTimeOf(@NotNull Session session);

    @NotNull
    Mono<Long> stopTimeOf(@NotNull Session session);

    @NotNull
    Mono<SessionStatus> statusOf(@NotNull Session session);

    @NotNull
    Flux<GroupedFlux<Session, SessionStatus>> liveStatusUpdates();

    @NotNull
    Flux<SessionStatus> liveStatusUpdatesOf(@NotNull Session session);

}









