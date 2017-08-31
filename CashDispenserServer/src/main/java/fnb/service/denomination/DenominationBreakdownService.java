package fnb.service.denomination;

import java.math.BigDecimal;
import java.util.List;

public interface DenominationBreakdownService {

	List<String> computeBreakdown(BigDecimal amount);

}
