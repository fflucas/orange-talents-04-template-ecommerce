package br.com.zupacademy.fabio.mercadolivre.shared.validator;

import br.com.zupacademy.fabio.mercadolivre.user.User;
import br.com.zupacademy.fabio.mercadolivre.user.UserRepository;
import br.com.zupacademy.fabio.mercadolivre.user.validator.IsUniqueEmail;
import br.com.zupacademy.fabio.mercadolivre.user.validator.IsUniqueEmailValidator;
import br.com.zupacademy.fabio.mercadolivre.user.validator.RawPassword;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;

import javax.validation.ConstraintValidatorContext;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

class IsUniqueEmailValidatorTest {

    @Mock
    private IsUniqueEmail isUniqueEmail;
    @Mock
    private UserRepository userRepository;
    @Mock
    private ConstraintValidatorContext context;

    private IsUniqueEmailValidator uniqueValidator;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this);
        uniqueValidator = new IsUniqueEmailValidator(userRepository);
        uniqueValidator.initialize(isUniqueEmail);
        ReflectionTestUtils.setField(uniqueValidator, "userRepository", userRepository);
    }

    @Test
    void givenEmail_WhenNotUnique_ThenFalse() {
        final String existingEmail = "teste@teste.com.br";

        User savedUser = new User(existingEmail, new RawPassword("123456"));

        Optional<User> optionalUser = Optional.of(savedUser);
        when(userRepository.findByUsername(anyString())).thenReturn(optionalUser);

        assertFalse(uniqueValidator.isValid(existingEmail, context));
    }

    @Test
    void givenEmail_WhenIsUnique_ThenTrue() {
        final String newEmail = "teste@teste.com.br";

        Optional<User> optionalUser = Optional.empty();
        when(userRepository.findByUsername(anyString())).thenReturn(optionalUser);

        assertTrue(uniqueValidator.isValid(newEmail, context));
    }
}