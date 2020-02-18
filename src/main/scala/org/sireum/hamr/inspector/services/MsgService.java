package org.sireum.hamr.inspector.services;

import org.jetbrains.annotations.NotNull;
import org.sireum.hamr.inspector.common.Msg;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Controller
@SuppressWarnings("unused")
public interface MsgService {

    @NotNull
    Flux<Msg> replayThenLive(@NotNull String key);

    @NotNull
    Flux<Msg> replaySnapshot(@NotNull String key);

    @NotNull
    Mono<Long> count(@NotNull String key);

}