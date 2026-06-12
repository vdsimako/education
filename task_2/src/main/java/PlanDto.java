import java.math.BigDecimal;
import java.util.Objects;

public class PlanDto {
    private String planId;
    private String name;
    private BigDecimal price;

    public PlanDto(String planId, String name, BigDecimal price) {
        this.planId = planId;
        this.name = name;
        this.price = price;
    }

    public String getPlanId() {
        return planId;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof PlanDto planDto)) return false;
        return Objects.equals(planId, planDto.planId) && Objects.equals(name, planDto.name) && Objects.equals(price, planDto.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(planId, name, price);
    }
}
