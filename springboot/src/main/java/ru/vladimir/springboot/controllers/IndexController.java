package ru.vladimir.springboot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.vladimir.springboot.entities.Visit;
import ru.vladimir.springboot.repositories.VisitsRepository;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * Создание контроллера и возврат домашней страницы
 */
@Deprecated
@Controller
public class IndexController {
    @Autowired
    VisitsRepository visitsRepository;

    //final VisitsRepository visitsRepository;
    /*public IndexController(VisitsRepository visitsRepository) {
        this.visitsRepository = visitsRepository;
    }*/

    @GetMapping("/")
    public ModelAndView index() {
        Map<String, String> model = new HashMap<>();
        model.put("name", "Vladimir");

        Visit visit = new Visit();
        visit.desription = String.format("Visited at %s", LocalDateTime.now());
        visitsRepository.save(visit);

        return new ModelAndView("index", model);
    }
}
