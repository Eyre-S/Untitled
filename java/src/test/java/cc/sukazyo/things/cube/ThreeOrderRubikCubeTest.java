package cc.sukazyo.things.cube;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class ThreeOrderRubikCubeTest {
	
	@ParameterizedTest
	@MethodSource
	public void testLawCyclePeriod(String law, int period) {
		Assertions.assertEquals(
				period,
				ThreeOrderRubikCube.calcLawCyclePeriod(law)
		);
	} @SuppressWarnings("unused") public static Stream<Arguments> testLawCyclePeriod () { return Stream.of(
			Arguments.of("R", 4),
			Arguments.of("U'", 4),
			Arguments.of("V'", 4),
			Arguments.of("RU'", 63),
			Arguments.of("RU'RURURU'R'U'RR", 3),
			Arguments.of("RRDDR'U'RDDR'UR'", 3),
			Arguments.of("RU'R'U'", 5),
			Arguments.of("RU'R'", 4),
			Arguments.of("RRDDLLUU", 6),
			Arguments.of("RULD", 315),
			Arguments.of("RU", 105),
			Arguments.of("LD", 105),
			Arguments.of("UF", 105),
			Arguments.of("DB'", 63),
			Arguments.of("FR'", 63),
			Arguments.of("BU", 105),
			Arguments.of("R'D", 63),
			Arguments.of("L'U'", 105),
			Arguments.of("U'B", 63),
			Arguments.of("D'F", 63),
			Arguments.of("F'R'", 105),
			Arguments.of("B'R", 63),
			Arguments.of("V'U'", 8),
			Arguments.of("HV", 12),
			Arguments.of("I'LU'", 72)
	);}
	
}
