package com.ff14mbrest;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

@RestController
public class ItemsWantedController {

//With this mapping a list of items can be sent to it and a JSON response is given with all recipe ids
//and potential matches are returned
@CrossOrigin(origins = "http://localhost:3000/")
@GetMapping("/itemsWanted")
    public ArrayList item(@RequestParam(value="name", defaultValue = "Iron") String name) throws InterruptedException {
        //splitting incoming items into an array and initalizing variables
        String[] itemsWanted = name.split(",");
        WebClient client = WebClient.create();
        ArrayList items = new ArrayList<Item>();

        //looping through every item submitted to find that items recipe ids
        for(int x=0; x < itemsWanted.length; x++){
            String url = "https://xivapi.com/search?indexes=Recipe&columns=ID,Name&string=" + itemsWanted[x];
            Item item = new Item(itemsWanted[x]);

            //sleeper every 15 items submitted for 20 requests per second api limit
            if((x%15)==0){
                TimeUnit.SECONDS.sleep(1);
            }

            //pinging XIVAPI for the item wanted looking for all potential matches
            // and finding their respective recipe id
            WebClient.ResponseSpec responseSpec = client.get().uri(url).retrieve();
            String responseBody = responseSpec.bodyToMono(String.class).block();
            JSONObject responseJSON = new JSONObject(responseBody);
            JSONArray responseArray = responseJSON.getJSONArray("Results");

            //adding all potential matches to the item (Recipe ID + Item Name)
            for( int y=0; y< responseArray.length(); y++){
                JSONObject jsonItem = responseArray.getJSONObject(y);
                //only adding recipe ID for the first result and setting alts map for others
                if( y == 0){
                    item.setRecID(jsonItem.getInt("ID"));
                }
                else{
                    item.addAltRec(jsonItem.getString("Name"), jsonItem.getInt("ID"));
                }
            }
            //adding complete item to array list
            items.add(item);
        }
        return items;
    }
}
