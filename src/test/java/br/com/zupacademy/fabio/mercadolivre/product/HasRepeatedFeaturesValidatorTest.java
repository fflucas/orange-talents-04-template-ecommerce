package br.com.zupacademy.fabio.mercadolivre.product;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.validation.ConstraintValidatorContext;
import java.util.Set;
import java.util.stream.Stream;

class HasRepeatedFeaturesValidatorTest {

    @Mock
    private HasRepeatedFeatures hasRepeatedFeatures;
    @Mock
    private ConstraintValidatorContext context;

    private HasRepeatedFeaturesValidator repeatedFeaturesValidator;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        repeatedFeaturesValidator = new HasRepeatedFeaturesValidator();
        repeatedFeaturesValidator.initialize(hasRepeatedFeatures);
    }

    @ParameterizedTest
    @MethodSource("generator")
    @DisplayName("Expect detect duplicate features name")
    void isValid(boolean expected, Set<RequestFeature> features) {
        Assertions.assertEquals(expected, repeatedFeaturesValidator.isValid(features, context));
    }

    static Stream<Arguments> generator(){
        return Stream.of(
                Arguments.of(true, Set.of()),
                Arguments.of(true, Set.of(new RequestFeature("key1", "value1"))),
                Arguments.of(true, Set.of(
                        new RequestFeature("key1", "value1"),
                        new RequestFeature("key2", "value2")
                        )),
                Arguments.of(false, Set.of(
                        new RequestFeature("key1", "value1"),
                        new RequestFeature("key1", "value2")
                )),
                Arguments.of(false, Set.of(
                        new RequestFeature("key1", "value1"),
                        new RequestFeature("key2", "value2"),
                        new RequestFeature("key1", "value2")
                ))
        );
    }
}