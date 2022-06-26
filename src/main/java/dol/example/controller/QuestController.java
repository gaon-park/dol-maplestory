package dol.example.controller;

import dol.example.service.TCharacterQuestService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@AllArgsConstructor
@RestController
@RequestMapping("/v1/quest")
public class QuestController {

    @Autowired
    TCharacterQuestService tQuestOfCharacterService;

//    @RequestMapping(value = "/detail", method = RequestMethod.GET)
//    public ResponseEntity<Object> getQuestDetail(@RequestBody UserCharacterId userCharacterId){
//
//    }

}
