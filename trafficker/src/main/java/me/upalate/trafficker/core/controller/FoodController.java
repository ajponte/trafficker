package me.upalate.trafficker.core.controller;

import me.upalate.trafficker.core.dto.food.Food;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class FoodController {
   final AtomicLong counter = new AtomicLong();
   @RequestMapping
   public Food food(@RequestParam(value="name") String name) {
      return new Food(counter.getAndIncrement(),
                      name);

   }
}
