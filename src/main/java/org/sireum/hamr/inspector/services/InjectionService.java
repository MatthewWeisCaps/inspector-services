package org.sireum.hamr.inspector.services;

import org.jetbrains.annotations.NotNull;
import org.sireum.hamr.inspector.common.Injection;
import org.springframework.stereotype.Controller;

@Controller
@SuppressWarnings("unused")
public interface InjectionService {

    void inject(@NotNull String session, @NotNull Injection injection);

}









