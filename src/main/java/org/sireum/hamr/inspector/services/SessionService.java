package org.sireum.hamr.inspector.services;

import javafx.collections.ObservableList;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Controller
@SuppressWarnings("unused")
public interface SessionService {

    @NotNull
    Mono<Long> startTime(@NotNull String key);

    @NotNull
    Mono<Boolean> hasStopped(@NotNull String key);

    @NotNull
    Mono<Long> stopTime(@NotNull String key);

    @NotNull
    Flux<String> querySessions();

    void refreshSessionsList();

    @NotNull
    Mono<Boolean> querySessionRunning(@NotNull String key);

    @NotNull
    ObservableList<Session> sessionsList();

}









