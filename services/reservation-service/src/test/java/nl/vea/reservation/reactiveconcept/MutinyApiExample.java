package nl.vea.reservation.reactiveconcept;

import io.smallrye.mutiny.Multi;
import org.junit.jupiter.api.Test;

public class MutinyApiExample {

    private void example(){
        Multi.createFrom().items("a", "b", "c", "d")
                .onItem()
                .transform(String::toUpperCase)
                .onItem()
                .invoke(s -> System.out.printf("intermediate: %s\n", s))
                .onItem()
                .transform(s -> s + " item")
                .filter(s -> !s.startsWith("B"))
                .onCompletion()
                .invoke(() -> System.out.println("Stream completed."))
                .subscribe().with(s -> System.out.printf("received: %s\n", s));
    }

    @Test
    public void runExample(){
        example();
    }
}
