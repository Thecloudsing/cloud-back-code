package org.example.async;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class GameLogic {
    @Async
    public void aVoid() {

    }

}
