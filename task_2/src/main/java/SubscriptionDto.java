import java.time.LocalDate;
import java.util.Objects;

/**
 * user subscription details
 */
public class SubscriptionDto {
    private String userId;
    private String planId;
    private LocalDate startDate;
    private LocalDate nextBillingDate;

    public SubscriptionDto(String userId,
                           String planId,
                           LocalDate startDate,
                           LocalDate nextBillingDate) {
        this.userId = userId;
        this.planId = planId;
        this.startDate = startDate;
        this.nextBillingDate = nextBillingDate;
    }

    public String getUserId() {
        return userId;
    }

    public String getPlanId() {
        return planId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getNextBillingDate() {
        return nextBillingDate;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof SubscriptionDto that)) return false;
        return Objects.equals(userId, that.userId) && Objects.equals(planId, that.planId) && Objects.equals(startDate, that.startDate) && Objects.equals(nextBillingDate, that.nextBillingDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, planId, startDate, nextBillingDate);
    }
}
