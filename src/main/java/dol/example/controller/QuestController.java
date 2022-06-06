package dol.example.controller;

import dol.example.service.TQuestOfCharacterService;
import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@AllArgsConstructor
@RestController
@RequestMapping("/v1/quest")
public class QuestController {

    @Autowired
    TQuestOfCharacterService tQuestOfCharacterService;

    @RequestMapping(method = RequestMethod.GET)
    public void getQuest(){
        try {
            tQuestOfCharacterService.findQuestOfCharacter(1L);
        } catch (NotFoundException e){
            e.printStackTrace();
        }
    }
}
