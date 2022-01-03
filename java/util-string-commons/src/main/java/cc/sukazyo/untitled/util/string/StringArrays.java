package cc.sukazyo.untitled.util.string;

import javax.annotation.Nonnull;
import javax.annotation.Nonnegative;

public class StringArrays {
	
	@Nonnull
	public static String connectStringArray (
			@Nonnull String[] array, @Nonnull String connector, @Nonnegative int startIndex, @Nonnegative int stopIndex
	) {
		final StringBuilder builder = new StringBuilder();
		for (int i = startIndex; i < stopIndex; i++) {
			builder.append(array[i]);
			builder.append(connector);
		}
		builder.append(array[stopIndex]);
		return builder.toString();
	}
	
}
