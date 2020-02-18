package org.sireum.hamr.inspector.services;

import javafx.beans.value.ObservableLongValue;
import javafx.beans.value.ObservableObjectValue;
import org.jetbrains.annotations.NotNull;
import org.sireum.hamr.inspector.common.Msg;
import org.sireum.hamr.inspector.common.Rule;
import org.springframework.stereotype.Controller;
import reactor.util.function.Tuple2;

import java.util.List;

@Controller
@SuppressWarnings("unused")
public interface RuleProcessorService {

    @NotNull
    ObservableObjectValue<RuleStatus> getRuleStatusObservable(@NotNull Tuple2<Session, Rule> sessionRule);

    /**
     *
     * @param sessionRule the session and rule which should be applied to find the stop time
     * @return An {@link ObservableLongValue} initially containing null and then updated to match the time
     */
    @NotNull
    ObservableLongValue getRuleStopTimeObservable(@NotNull Tuple2<Session, Rule> sessionRule);

    @NotNull
    ObservableObjectValue<List<Msg>> getRuleLastMsgObservable(@NotNull Tuple2<Session, Rule> sessionRule);

    @NotNull
    ObservableObjectValue<Throwable> getErrorCause(@NotNull Tuple2<Session, Rule> sessionRule);

}
