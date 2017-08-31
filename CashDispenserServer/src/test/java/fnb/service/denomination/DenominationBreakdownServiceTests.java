package fnb.service.denomination;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class DenominationBreakdownServiceTests {

	private DenominationBreakdownService denominationBreakdownService;
	
	@Before
	public void setUp() {
		denominationBreakdownService = new DenominationBreakdownServiceImpl();
	}
	
	@Test
	public void testDenominationBreakdownWithAmountOfTwentyFive_shouldReturnTheCorrectBreakdown() {

		List<String> breakdowns = denominationBreakdownService.computeBreakdown(new BigDecimal(25));
		
		assertThat(breakdowns.size(), is(equalTo(2)));
		assertThat(breakdowns.get(0), is(equalTo("1 x R20")));
		assertThat(breakdowns.get(1), is(equalTo("1 x R5")));

	}
	
	@Test
	public void testDenominationBreakdownWithAmountOfTwentyFiveRandFiftyCent_shouldReturnTheCorrectBreakdown() {

		List<String> breakdowns = denominationBreakdownService.computeBreakdown(new BigDecimal(25.50d));
		
		assertThat(breakdowns.size(), is(equalTo(3)));
		assertThat(breakdowns.get(0), is(equalTo("1 x R20")));
		assertThat(breakdowns.get(1), is(equalTo("1 x R5")));
		assertThat(breakdowns.get(2), is(equalTo("1 x 50cent")));
	}

	@Test
	public void testDenominationBreakdownWithAmountOfFourHundredRand_shouldReturnTheCorrectBreakdown() {

		List<String> breakdowns = denominationBreakdownService.computeBreakdown(new BigDecimal(400));
		
		assertThat(breakdowns.size(), is(equalTo(1)));
		assertThat(breakdowns.get(0), is(equalTo("4 x R100")));
	}
	
	@Test
	public void testDenominationBreakdownWithAmountOfTwoHundredAndSeventyFiveRand_shouldReturnTheCorrectBreakdown() {

		List<String> breakdowns = denominationBreakdownService.computeBreakdown(new BigDecimal(275));
		
		assertThat(breakdowns.size(), is(equalTo(4)));
		assertThat(breakdowns.get(0), is(equalTo("2 x R100")));
		assertThat(breakdowns.get(1), is(equalTo("1 x R50")));	
		assertThat(breakdowns.get(2), is(equalTo("1 x R20")));
		assertThat(breakdowns.get(3), is(equalTo("1 x R5")));
	}

	@Test
	public void testDenominationBreakdownWithAmountOfSeventyFiveRandTwelveCents_shouldReturnTheCorrectBreakdown() {

		List<String> breakdowns = denominationBreakdownService.computeBreakdown(new BigDecimal(75.12));
		
		assertThat(breakdowns.size(), is(equalTo(5)));
		assertThat(breakdowns.get(0), is(equalTo("1 x R50")));	
		assertThat(breakdowns.get(1), is(equalTo("1 x R20")));
		assertThat(breakdowns.get(2), is(equalTo("1 x R5")));
		assertThat(breakdowns.get(3), is(equalTo("1 x 10cent")));
		assertThat(breakdowns.get(4), is(equalTo("2 x 1cent")));
	}
	
	@Test
	public void testDenominationBreakdownWithAmountOfSeventySixCents_shouldReturnTheCorrectBreakdown() {

		List<String> breakdowns = denominationBreakdownService.computeBreakdown(new BigDecimal(0.76));
		
		assertThat(breakdowns.size(), is(equalTo(3)));
		assertThat(breakdowns.get(0), is(equalTo("1 x 50cent")));
		assertThat(breakdowns.get(1), is(equalTo("1 x 25cent")));
		assertThat(breakdowns.get(2), is(equalTo("1 x 1cent")));
	}
}
