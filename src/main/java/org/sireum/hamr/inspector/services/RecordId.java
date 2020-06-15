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

public abstract class RecordId implements Comparable<RecordId> {
    RecordId() { }

    @NotNull
    public static RecordId of(long millisecondsTime, long sequenceNumber) {
        return new ConcreteRecordId(millisecondsTime, sequenceNumber);
    }

    @NotNull
    public static RecordId from(@NotNull Msg msg) {
        return new ReferenceRecordId(msg);
    }

    public abstract long timestamp();

    public abstract long sequence();

    @Override
    public final int hashCode() {
        return super.hashCode();
    }

    @Override
    public final boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public final String toString() {
        return super.toString();
    }

    @Override
    public int compareTo(@NotNull RecordId other) {
        final int timestampCompare = Long.compare(timestamp(), other.timestamp());
        if (timestampCompare != 0) {
            return timestampCompare;
        } else {
            return Long.compare(sequence(), other.sequence());
        }
    }

    private static final class ConcreteRecordId extends RecordId {

        private final long millisecondsTime;
        private final long sequenceNumber;

        ConcreteRecordId(long millisecondsTime, long sequenceNumber) {
            this.millisecondsTime = millisecondsTime;
            this.sequenceNumber = sequenceNumber;
        }

        @Override
        public long timestamp() {
            return millisecondsTime;
        }

        @Override
        public long sequence() {
            return sequenceNumber;
        }
    }

    private static final class ReferenceRecordId extends RecordId {

        private final Msg msg;

        ReferenceRecordId(@NotNull Msg msg) {
            this.msg = msg;
        }

        @Override
        public long timestamp() {
            return msg.timestamp();
        }

        @Override
        public long sequence() {
            return msg.sequence();
        }
    }

}