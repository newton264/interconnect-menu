package com.ibm.microservices.interconnect17.controller;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.lang.Object;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.boot.json.BasicJsonParser;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

@Controller
public class MenuController {

  @Autowired
  private RestTemplate restTemplate;

  @RequestMapping("/")
  public String getMenuForIndex(Model model){

    String drinksString = getDrinksMenu();

	  BasicJsonParser jsonParser = new BasicJsonParser();
	  Map<String, Object> drinksJsonMap = jsonParser.parseMap(drinksString);

	  List<String> drinksOptions = (List<String>)drinksJsonMap.get("menu");
	  model.addAttribute("drinksOptions", drinksOptions);

    String foodString = getFoodMenu();

	  Map<String, Object> foodJsonMap = jsonParser.parseMap(foodString);

	  List<String> foodOptions = (List<String>)foodJsonMap.get("menu");
	  model.addAttribute("foodOptions", foodOptions);

    return "index";
  }

  private String getDrinksMenu() {
    String drinks = this.restTemplate.getForObject("http://localhost:8081/drinks", String.class);
    System.out.println(drinks);
    return drinks;
  }
  private String getFoodMenu() {
    String food = this.restTemplate.getForObject("http://localhost:8082/food", String.class);
    System.out.println(food);
    return food;
  }

}
