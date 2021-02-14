package api.body;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class DelayTestsObject {

    @Getter
    private Integer delayRequest;

    @Getter
    private Integer delayExpected;
}
