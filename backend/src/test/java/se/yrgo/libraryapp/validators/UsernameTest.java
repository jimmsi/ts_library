package se.yrgo.libraryapp.validators;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.*;

public class UsernameTest {

    @ParameterizedTest
    @EmptySource
    @ValueSource(strings = {"a", "ab", "abc"})
    void rejectsUsernameUnderFourChars(String username) {
        boolean result = Username.validate(username);
        assertThat(result).isFalse();
    }

    @ParameterizedTest
    @ValueSource(strings = {"     ", "ååäöå"})
    void rejectsUsernameWithInvalidChars(String username) {
        boolean result = Username.validate(username);
        assertThat(result).isFalse();
    }

    @ParameterizedTest
    @ValueSource(strings = {"-_12David", ".@Jim"})
    void acceptsUsernameWithMixedValidChars (String username) {
        boolean result = Username.validate(username);
        assertThat(result).isTrue();
    }

    @ParameterizedTest
    @ValueSource(strings = {"John", "tr.@"})
    void acceptsUsernameExactlyFourCharsLong (String username) {
        boolean result = Username.validate(username);
        assertThat(result).isTrue();
    }

}
