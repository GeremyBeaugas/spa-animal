package edu.supavenir.spanimals.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.supavenir.spanimals.entites.Animal;
import edu.supavenir.spanimals.entites.Espece;
import edu.supavenir.spanimals.entites.Race;
import edu.supavenir.spanimals.repositories.AnimalRepository;
import edu.supavenir.spanimals.repositories.EspeceRepository;
import edu.supavenir.spanimals.repositories.RaceRepository;
import io.github.jeemv.springboot.vuejs.VueJS;

@Controller
public class TestController {

    @Autowired
    private VueJS vue;

    @ModelAttribute(name = "vue")
    private VueJS getVue() {
	return this.vue;
    }

    @Autowired
    private AnimalRepository aRepo;

    @Autowired
    private EspeceRepository eRepo;

    @Autowired
    private RaceRepository rRepo;

    @GetMapping("add/animal")
    public @ResponseBody String addAnimal() {
	Animal medor = new Animal();
	medor.setNom("Medor");
	medor.setCouleur("Blanc/Marron");
	medor.setDescription("Gentil");
	if (aRepo.save(medor) != null) {
	    return medor + "Ajouté";
	}
	return "Problème d'ajout";
    }

    @GetMapping("/recherche")
    public String indexRecherche(Model model) {
	vue.addDataRaw("dialog", "{visible: true}");
	vue.addData("search");
	List<Espece> especes = eRepo.findAll();
	vue.addData("especes", especes);
	List<Race> races = rRepo.findAll();
	vue.addData("races", races);
	return "recherche";
    }
}