package pizza.web;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import lombok.extern.slf4j.Slf4j;
import pizza.Ingredient;
import pizza.Ingredient.Type;
import pizza.Order;
import pizza.Pizza;
import pizza.User;
import pizza.data.IngredientRepository;
import pizza.data.PizzaRepository;
import pizza.data.UserRepository;

@Controller
@RequestMapping("/design")
@SessionAttributes("order")
@Slf4j
public class DesignPizzaController {
  
  private final IngredientRepository ingredientRepo;
  
  private PizzaRepository pizzaRepo;

  private UserRepository userRepo;

  @Autowired
  public DesignPizzaController(
        IngredientRepository ingredientRepo, 
        PizzaRepository pizzaRepo,
        UserRepository userRepo) {
    this.ingredientRepo = ingredientRepo;
    this.pizzaRepo = pizzaRepo;
    this.userRepo = userRepo;
  }

  @ModelAttribute(name = "order")
  public Order order() {
    return new Order();
  }
  
  @ModelAttribute(name = "design")
  public Pizza design() {
    return new Pizza();
  }
  
  @GetMapping
  public String showDesignForm(Model model, Principal principal) {
    log.info("   --- Designing pizza");
    List<Ingredient> ingredients = new ArrayList<>();
    ingredientRepo.findAll().forEach(i -> ingredients.add(i));
    
    Type[] types = Ingredient.Type.values();
    for (Type type : types) {
      model.addAttribute(type.toString().toLowerCase(), 
          filterByType(ingredients, type));      
    }

    System.out.println(principal);
    String username = principal.getName();
    User user = userRepo.findByUsername(username);
    model.addAttribute("user", user);

    return "design";
  }

  @PostMapping
  public String processDesign(@Valid Pizza pizza, Errors errors, @ModelAttribute Order order) {

    log.info("   --- Saving pizza");
    if (errors.hasErrors()) {
      return "design";
    }

    Pizza saved = pizzaRepo.save(pizza);
    order.addDesign(saved);

    return "redirect:/orders/current";
  }

  private List<Ingredient> filterByType(
      List<Ingredient> ingredients, Type type) {
    return ingredients
              .stream()
              .filter(x -> x.getType().equals(type))
              .collect(Collectors.toList());
  }
}
