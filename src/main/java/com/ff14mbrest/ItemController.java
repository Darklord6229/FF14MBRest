package com.ff14mbrest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class ItemController {
    ArrayList items = new ArrayList<Item>();

    @GetMapping("/itemsWanted")
    public ArrayList item(@RequestParam(value="name", defaultValue = "Iron") String name){

        String[] itemsWanted = name.split(",");
        items.clear();

        for(int x=0; x < itemsWanted.length; x++){
            items.add(new Item(itemsWanted[x]));
        }

        return items;
    }
}
