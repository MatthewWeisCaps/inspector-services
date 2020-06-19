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
import org.springframework.data.domain.Range;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface MsgService {

    /**
     * Returns a {@link Mono} containing the current number of messages that have occurred in the given {@link Session}.
     *
     * @param session the {@link Session} whose current message count will be returned.
     * @return the number of messages that have occurred thus far on the {@link Session}.
     */
    @NotNull
    Mono<Long> count(@NotNull Session session);

    /**
     * Returns a {@link Flux} of {@link Msg}s representing the communication occurring in the given {@link Session}.
     *
     * The left-bound of the {@link Range} must be either a preexisting value or one unspecified via
     * {@link Range#leftUnbounded(Range.Bound)}, {@link Range#unbounded()}. In this context, unbounded just means
     * starting from the very first {@link Msg}.
     *
     * The right-bound of the {@link Range} must be greater than the left-bound and be either an existing value or
     * unspecified via {@link Range#rightUnbounded(Range.Bound)}, {@link Range#unbounded()} in which case the stream
     * will continue live until stopped by an operator or the art program terminates.
     *
     * Downstream errors are not handled so users may implement their own strategy as desired.
     *
     * @param session the {@link Session} whose {@link Msg}s will be passed by the {@link Flux}
     * @return a {@link Flux} of {@link Msg}s representing all previous communication to have taken place in the session.
     */
    @NotNull
    Flux<Msg> live(@NotNull Session session, @NotNull Range<RecordId> range);

    /**
     * Returns a {@link Flux} of {@link Msg}s representing the communication occurring in the given {@link Session}.
     *
     * The left-bound of the {@link Range} must be either a preexisting value or one unspecified via
     * {@link Range#leftUnbounded(Range.Bound)}, {@link Range#unbounded()}. In this context, unbounded just means
     * starting from the very first {@link Msg}.
     *
     * The right-bound of the {@link Range} must be greater than the left-bound and be either an existing value or
     * unspecified via {@link Range#rightUnbounded(Range.Bound)}, {@link Range#unbounded()} in which case it will be
     * the latest value at the time of the call.
     *
     * Downstream errors are not handled so users may implement their own strategy as desired.
     *
     * @param session the {@link Session} whose {@link Msg}s will be passed by the {@link Flux}
     * @return a {@link Flux} of {@link Msg}s representing all previous communication to have taken place in the session.
     */
    @NotNull
    Flux<Msg> replay(@NotNull Session session, @NotNull Range<RecordId> range);

    /**
     * Returns a {@link Flux} of {@link Msg}s representing the communication occurring in the given {@link Session}
     * in reverse order. Note that the range should not be logically reversed, but oriented normally.
     *
     * The left-bound of the {@link Range} must be either a preexisting value or one unspecified via
     * {@link Range#leftUnbounded(Range.Bound)}, {@link Range#unbounded()}. In this context, unbounded just means
     * starting from the very first {@link Msg}.
     *
     * The right-bound of the {@link Range} must be greater than the left-bound and be either an existing value or
     * unspecified via {@link Range#rightUnbounded(Range.Bound)}, {@link Range#unbounded()} in which case it will
     * be the lastest value at the time of the call.
     *
     * Downstream errors are not handled so users may implement their own strategy as desired.
     *
     * @param session the {@link Session} whose {@link Msg}s will be passed by the {@link Flux}
     * @return a {@link Flux} of {@link Msg}s representing all previous communication to have taken place in the session.
     */
    @NotNull
    Flux<Msg> replayReverse(@NotNull Session session, @NotNull Range<RecordId> range);

}