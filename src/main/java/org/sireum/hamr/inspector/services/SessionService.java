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
import reactor.core.publisher.Flux;
import reactor.core.publisher.GroupedFlux;
import reactor.core.publisher.Mono;

public interface SessionService {

    /**
     * Queries a cold {@link Flux} that returns all sessions (currently in existence) and then completes.
     * @return a {@link Flux} of all sessions in existence. Will not await any spawning sessions.
     */
    @NotNull
    Flux<Session> sessions();

    /**
     * Queries a cold {@link Mono} which will return one of the following:
     *  (1) If session is not recognised {@link SessionService} implementation, this method will return a
     *      {@link Mono} that completes without emitting any onNext value.
     *  (2) If session is recognised by the {@link SessionService} implementation, a {@link Mono} emitting one
     *      Long will be returned immediately.
     *
     * @param session whose startTime should be returned. All naturally occurring {@link Session}s have a start time.
     * @return A {@link Mono} of long start time (in millis) if session is recognized. Otherwise an empty onComplete.
     */
    @NotNull
    Mono<Long> startTimeOf(@NotNull Session session);

    /**
     * Queries a cold {@link Mono} which will return one of the following:
     *  (1) If session is not recognised {@link SessionService} implementation, this method will return a
     *      {@link Mono} that completes without emitting any onNext value.
     *  (2) If the session is recognised, but has not yet completed, this method will return as if it was case (1).
     *  (3) If session is recognised as finished by the {@link SessionService} implementation, a {@link Mono} emitting
     *      one Long will be returned immediately.
     *
     * @param session whose startTime will be returned. Unrecognized or running sessions onComplete immediately.
     * @return {@link Mono} of long stop time (in millis) if session is recognized. Otherwise just onComplete.
     */
    @NotNull
    Mono<Long> stopTimeOf(@NotNull Session session);

    /**
     * Queries a cold {@link Mono} which will return one of the following:
     *  (1) If session is not recognised {@link SessionService} implementation, this method will return a
     *      {@link Mono} that completes without emitting any onNext value.
     *  (2) If the session is recognized, the status of the session will be returned immediately.
     *
     * @param session whose {@link SessionStatus} will be returned.
     * @return {@link Mono} containing a valid session's {@link SessionStatus}. Otherwise just onComplete.
     */
    @NotNull
    Mono<SessionStatus> statusOf(@NotNull Session session);

    /**
     * Returns a HOT {@link Flux} which NEVER replays and emits exactly two values per key.
     *  First, a newly registered {@link Session} key, with the value {@link SessionStatus#RUNNING}.
     *  Second, a (previously discovered) {@link Session} key mapped to the value {@link SessionStatus#COMPLETED}.
     *
     * @return a real-time {@link Flux} of {@link GroupedFlux} where {@link Session}s emit {@link SessionStatus#RUNNING}
     *  upon discovery followed by {@link SessionStatus#COMPLETED} upon completion. No replay or happens-after listening
     *  guarantees are made. Some implementations may poll this stream while others may use pub/sub for pseudo-real-time
     *  guarantees.
     */
    @NotNull
    Flux<GroupedFlux<Session, SessionStatus>> liveStatusUpdates();

}









