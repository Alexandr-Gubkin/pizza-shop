package pizza;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import pizza.Ingredient.Type;
import pizza.data.IngredientRepository;

@SpringBootApplication
public class PizzaShopApplication {

  public static void main(String[] args) {
    SpringApplication.run(PizzaShopApplication.class, args);
  }

  @Bean
  public CommandLineRunner dataLoader(IngredientRepository repo) {
    return new CommandLineRunner() {
      @Override
      public void run(String... args) throws Exception {
        repo.save(new Ingredient("THDO", "Thin dough", Type.DOUGH));
        repo.save(new Ingredient("PUDO", "Puffy dough", Type.DOUGH));
        repo.save(new Ingredient("DWYE", "Dough without yeast", Type.DOUGH));
        repo.save(new Ingredient("CHIC", "Chicken", Type.MEAT));
        repo.save(new Ingredient("GRBF", "Ground Beef", Type.MEAT));
        repo.save(new Ingredient("PORK", "Pork", Type.MEAT));
        repo.save(new Ingredient("SALM", "Salmon", Type.FISH));
        repo.save(new Ingredient("PIKE", "Pike perch", Type.FISH));
        repo.save(new Ingredient("TUNA", "Tuna", Type.FISH));
        repo.save(new Ingredient("PORK", "Pork", Type.MEAT));
        repo.save(new Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES));
        repo.save(new Ingredient("MUSH", "Mushrooms", Type.VEGGIES));
        repo.save(new Ingredient("ONIO", "Onion", Type.VEGGIES));
        repo.save(new Ingredient("CHED", "Cheddar", Type.CHEESE));
        repo.save(new Ingredient("RUCH", "Russian cheese", Type.CHEESE));
        repo.save(new Ingredient("MOZA", "Mozarella", Type.CHEESE));
        repo.save(new Ingredient("SLSA", "Salsa", Type.SAUCE));
        repo.save(new Ingredient("TOSA", "Tomato sauce", Type.SAUCE));
        repo.save(new Ingredient("SRCR", "Sour Cream", Type.SAUCE));
      }
    };
  }
}
