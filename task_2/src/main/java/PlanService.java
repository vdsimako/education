import java.math.BigDecimal;
import java.util.Map;

public class PlanService {
    private static final Map<String, PlanDto> plans = Map.ofEntries(
            Map.entry("1", new PlanDto("1", "BASIC", BigDecimal.valueOf(9.99))),
            Map.entry("2", new PlanDto("2", "PREMIUM", BigDecimal.valueOf(19.99)))
    );

    public PlanService() {
    }

    public PlanDto getPlan(String planId) {
        return plans.get(planId);
    }
}
