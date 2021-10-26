package com.ff14mbrest;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

@RestController
public class RecipeLookupController {

    @CrossOrigin(origins = "http://localhost:3000/")
    @GetMapping("/recipeWanted")
    public JSONObject recipeWanted(@RequestParam(value="recID", defaultValue = "1") String recID) throws InterruptedException {
        WebClient client = WebClient.create();
        ObjectMapper objectMapper = new ObjectMapper();

        String url = "https://xivapi.com/Recipe/" + recID + "?columns=ItemResult.Name,ItemResult.ID,AmountResult,AmountIngredient0,AmountIngredient1,AmountIngredient2,AmountIngredient3,AmountIngredient4,AmountIngredient5,AmountIngredient6,AmountIngredient7,AmountIngredient8,AmountIngredient9,ItemIngredient0.Name,ItemIngredient0.ID,ItemIngredient1.Name,ItemIngredient1.ID,ItemIngredient2.Name,ItemIngredient2.ID,ItemIngredient3.Name,ItemIngredient3.ID,ItemIngredient4.Name,ItemIngredient4.ID,ItemIngredient5.Name,ItemIngredient5.ID,ItemIngredient6.Name,ItemIngredient6.ID,ItemIngredient7.Name,ItemIngredient7.ID,ItemIngredient8.Name,ItemIngredient8.ID,ItemIngredient9.Name,ItemIngredient9.ID";

        WebClient.ResponseSpec responseSpec = client.get().uri(url).retrieve();
        String responseBody = responseSpec.bodyToMono(String.class).block();
        JSONObject responseJSON = new JSONObject(responseBody);

        //Recipe recipe = new Recipe();
        System.out.println(responseJSON);


        return responseJSON;
    }
}
