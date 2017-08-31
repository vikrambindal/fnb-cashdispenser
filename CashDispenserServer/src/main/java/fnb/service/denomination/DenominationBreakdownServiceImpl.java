package fnb.service.denomination;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DenominationBreakdownServiceImpl implements DenominationBreakdownService {

    private List<BigDecimal> cannisters = Arrays.asList(BigDecimal.valueOf(100d),
            BigDecimal.valueOf(50d),
            BigDecimal.valueOf(20d),
            BigDecimal.valueOf(10d),
            BigDecimal.valueOf(5d),
            BigDecimal.valueOf(2d),
            BigDecimal.valueOf(1d),
            BigDecimal.valueOf(0.5d),
            BigDecimal.valueOf(0.25d),
            BigDecimal.valueOf(0.10d),
            BigDecimal.valueOf(0.05d),
            BigDecimal.valueOf(0.01d));
    
    public DenominationBreakdownServiceImpl() {
    }

    /**
     * The method computes the breakdown by dividing the denomination for each cannister in CDM
     * by the cash amount to determine the dispensed notes/coins for that cannister in CDM.
     * For every computation, it then decreases the amount processed by the total dispensed
     * notes/coins from the particular cannister. 
     * @param amount	Cash amount for which breakdown must be computed
     * @return			A List containing the breakdown in readable format
     */
    @Override
    public List<String> computeBreakdown(BigDecimal amount) {

    	List<String> denominationBreakdown = new ArrayList<>();
        for (BigDecimal canisterAmount: cannisters) {
            BigDecimal frequency = amount.divide(canisterAmount).setScale(0, RoundingMode.FLOOR);
            amount = amount.subtract(canisterAmount.multiply(frequency));
            if (frequency.intValue() > 0) {
            	denominationBreakdown.add(getBreakdownInReadableFormat(canisterAmount, frequency));
            }
        }
        
        return denominationBreakdown;
    }
    
    private String getBreakdownInReadableFormat(BigDecimal canisterAmount, BigDecimal frequency) {
    	return new StringBuffer()
		.append(frequency)
		.append(" x ")
		.append(canisterAmount.intValue() > 0 ? "R" : canisterAmount.multiply(new BigDecimal(100)).setScale(0, RoundingMode.FLOOR))
		.append(canisterAmount.intValue() > 0 ? canisterAmount.setScale(0, RoundingMode.FLOOR) : "cent")
		.toString();
    }
}
