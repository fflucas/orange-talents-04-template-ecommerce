package br.com.zupacademy.fabio.mercadolivre.product;

import br.com.zupacademy.fabio.mercadolivre.category.Category;
import br.com.zupacademy.fabio.mercadolivre.user.User;
import br.com.zupacademy.fabio.mercadolivre.user.validator.RawPassword;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

class ProductTest {
    @DisplayName("Product can be created if it has at least 3 features")
    @ParameterizedTest
    @MethodSource("test1Generator")
    public void test1(Set<RequestFeature> features){
        Category category = new Category("category");
        User owner = new User("owner@test.com", new RawPassword("123456"));
        new Product("product1", BigDecimal.TEN, 10, features, "description", category, owner);
    }

    static Stream<Arguments> test1Generator(){
        return Stream.of(
                Arguments.of(
                        Set.of(
                                new RequestFeature("key1","value1"),
                                new RequestFeature("key2","value2"),
                                new RequestFeature("key3","value3")
                        )
                ),
                Arguments.of(
                        Set.of(
                                new RequestFeature("key1","value1"),
                                new RequestFeature("key2","value2"),
                                new RequestFeature("key3","value3"),
                                new RequestFeature("key4","value4")
                        )
                )
        );
    }

    @DisplayName("Product can't be created if it does not have at least 3 features")
    @ParameterizedTest
    @MethodSource("test2Generator")
    public void test2(Set<RequestFeature> features){
        Category category = new Category("category");
        User owner = new User("owner@test.com", new RawPassword("123456"));
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Product("product1", BigDecimal.TEN, 10, features, "description", category, owner);
        });
    }

    static Stream<Arguments> test2Generator(){
        return Stream.of(
                Arguments.of(
                        Set.of(
                                new RequestFeature("key1","value1"),
                                new RequestFeature("key2","value2")
                        )
                ),
                Arguments.of(
                        Set.of(
                                new RequestFeature("key1","value1")
                        )
                )
        );
    }

}