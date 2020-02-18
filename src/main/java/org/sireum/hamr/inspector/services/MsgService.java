package org.sireum.hamr.inspector.services;

import org.jetbrains.annotations.NotNull;
import org.sireum.hamr.inspector.common.Msg;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface MsgService {

    @NotNull
    Flux<Msg> replayThenLive(@NotNull String key);

    @NotNull
    Flux<Msg> replaySnapshot(@NotNull String key);

    @NotNull
    Mono<Long> count(@NotNull String key);

}