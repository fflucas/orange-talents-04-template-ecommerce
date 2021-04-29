package br.com.zupacademy.fabio.mercadolivre.shared.validator;

import br.com.zupacademy.fabio.mercadolivre.user.User;
import br.com.zupacademy.fabio.mercadolivre.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Optional;

public class IsUniqueEmailValidator implements ConstraintValidator<IsUniqueEmail, String> {

   private UserRepository userRepository;

   @Autowired
   public IsUniqueEmailValidator(UserRepository userRepository) {
      this.userRepository = userRepository;
   }

   @Override
   public void initialize(IsUniqueEmail constraintAnnotation) {}

   @Override
   public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
      Optional<User> optionalUser = userRepository.findByUsername(value);
      if(optionalUser.isPresent()){
         System.out.println(optionalUser.get().getUsername());
      }
      return optionalUser.isEmpty();
   }
}
