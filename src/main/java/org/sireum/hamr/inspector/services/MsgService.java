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
import org.sireum.hamr.inspector.common.Msg;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface MsgService {

    /**
     * Returns a {@link Flux} of {@link Msg}s representing the communication occurring in the given {@link Session}.
     *
     * The returned {@link Flux} will first replay all previous messages, then it will continue to pass along
     * subsequent messages as they occur in the {@link Session}. If no errors occur, the {@link Flux} will eventually
     * conclude with an onComplete signal ({@link reactor.core.publisher.SignalType#ON_COMPLETE}) when the {@link Session}
     * finally completes.
     *
     * Downstream errors are not handled so users may implement their own strategy if desired.
     *
     * @param session the {@link Session} whose {@link Msg}s will be passed by the {@link Flux}
     * @return a {@link Flux} of {@link Msg}s representing communication on the session. First historical, then live.
     */
    @NotNull
    Flux<Msg> replayThenLive(@NotNull Session session);

    /**
     * Returns a {@link Flux} of {@link Msg}s representing the communication occurring in the given {@link Session}.
     *
     * The returned {@link Flux} will replay all messages which existed prior to this method call, then it will complete.
     *
     * Downstream errors are not handled so users may implement their own strategy if desired.
     *
     * @param session the {@link Session} whose {@link Msg}s will be passed by the {@link Flux}
     * @return a {@link Flux} of {@link Msg}s representing all previous communication to have taken place in the session.
     */
    @NotNull
    Flux<Msg> replay(@NotNull Session session);

    /**
     * Returns a {@link Flux} of {@link Msg}s representing the communication occurring in the given {@link Session}.
     *
     * The returned {@link Flux} will pass along messages as they occur in the {@link Session}. No previous messages
     * will be sent. If no errors occur, the {@link Flux} will eventually
     * conclude with an onComplete signal ({@link reactor.core.publisher.SignalType#ON_COMPLETE}) when the {@link Session}
     * finally completes.
     *
     * Downstream errors are not handled so users may implement their own strategy if desired.
     *
     * @param session the {@link Session} whose {@link Msg}s will be passed by the {@link Flux}
     * @return a {@link Flux} of {@link Msg}s representing all previous communication to have taken place in the session.
     */
    @NotNull
    Flux<Msg> live(@NotNull Session session);

    /**
     * Returns a {@link Mono} containing the current number of messages that have occurred in the given {@link Session}.
     *
     * @param session the {@link Session} whose current message count will be returned.
     * @return the number of messages that have occurred thus far on the {@link Session}.
     */
    @NotNull
    Mono<Long> count(@NotNull Session session);

}