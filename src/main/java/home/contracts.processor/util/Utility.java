package home.contracts.processor.util;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.time.LocalDate;
import java.time.Period;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.io.FileUtils;

import home.contracts.processor.model.Contract;

public class Utility {

	public static final String ADVANCE_PAYMENT_INDICATOR = "2";

	public static final String COMMA = ",";

	public static final String HYPHEN = "-";

	public static final List<Contract> getContracts(String fileName, String cutOffDate) throws IOException {
		return FileUtils.readLines(new File(fileName), Charset.defaultCharset()).parallelStream()
				.map(e -> Arrays.asList(e.split(COMMA)))
				.map(e -> new Contract(e.get(0), e.get(1), e.get(2), e.get(3), cutOffDate))
				.collect(Collectors.toList());
	}

	public static final int getDayFromDate(String date) {
		return Integer.parseInt(date.substring(date.indexOf(HYPHEN) + 1, date.lastIndexOf(HYPHEN)));
	}

	public static final Period getPeriodBetweenDates(String endDate, String cutOffDate) {
		return Period.between(LocalDate.parse(endDate).plusDays(1), LocalDate.parse(cutOffDate));
	}

	private Utility() {
	}

}
