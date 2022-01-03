package cc.sukazyo.untitled.telegram.api.extra;

import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class ExtraActionTest {
	
	@ParameterizedTest
	@MethodSource
	public void testPermissionCheck (UserPermissionLevel user, UserPermissionLevel required, boolean should) {
		Assertions.assertEquals(should, user.hasPermission(required));
	} public static Stream<Arguments> testPermissionCheck () {return Stream.of(
			Arguments.of(
					UserPermissionLevel.ADMINISTRATOR,
					UserPermissionLevel.MEMBER,
					true
			),
			Arguments.of(
					UserPermissionLevel.ADMINISTRATOR,
					UserPermissionLevel.ADMINISTRATOR,
					true
			),
			Arguments.of(
					UserPermissionLevel.ADMINISTRATOR,
					UserPermissionLevel.CREATOR,
					false
			),
			Arguments.of(
					UserPermissionLevel.ADMINISTRATOR,
					UserPermissionLevel.MEMBER,
					true
			),
			Arguments.of(
					UserPermissionLevel.MEMBER,
					UserPermissionLevel.MEMBER,
					true
			),
			Arguments.of(
					UserPermissionLevel.MEMBER,
					UserPermissionLevel.RESTRICTED,
					true
			),
			Arguments.of(
					UserPermissionLevel.RESTRICTED,
					UserPermissionLevel.MEMBER,
					false
			),
			Arguments.of(
					UserPermissionLevel.LEFT,
					UserPermissionLevel.LEFT,
					true
			),
			Arguments.of(
					UserPermissionLevel.KICKED,
					UserPermissionLevel.LEFT,
					false
			)
	);}
	
}
