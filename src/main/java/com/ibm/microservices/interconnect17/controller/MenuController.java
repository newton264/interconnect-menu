package com.ibm.microservices.interconnect17.controller;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.lang.Object;
import java.lang.String;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.boot.json.BasicJsonParser;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Value;



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

  private CharSequence cloud_domain="mybluemix.net";

  @Value("${location.drinks}")
  private String drinks_location;

  @Value("${location.food}")
  private String food_location;

  private String getDrinksMenu() {
    String drinks;
    if (drinks_location.contains(cloud_domain))
      drinks = this.restTemplate.getForObject("http://" + drinks_location + "/drinks", String.class);
    else
      drinks = this.restTemplate.getForObject("http://" + drinks_location + ":8081/drinks", String.class);
    System.out.println(drinks);
    return drinks;
  }
  private String getFoodMenu() {
    String food;
    if (food_location.contains(cloud_domain))
      food = this.restTemplate.getForObject("http://" + food_location + "/food", String.class);
    else
      food = this.restTemplate.getForObject("http://" + food_location + ":8082/food", String.class);
    System.out.println(food);
    return food;
  }



}
