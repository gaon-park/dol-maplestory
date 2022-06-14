package dol.example.controller;

import dol.example.domain.TCharacter;
import dol.example.service.TCharacterService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@AllArgsConstructor
@RestController
@RequestMapping("/v1/character")
public class CharacterController {

    @Autowired
    TCharacterService tCharacterService;

    @RequestMapping(value = "/info/byDolMaplestoryId/{id}", method = RequestMethod.GET)
    public void getCharacterInfoByDolMaplestoryId(){

    }

    @RequestMapping(value = "/list", produces = "application/json; charset=utf8", method = RequestMethod.POST)
    public void postCharacterInfo(@RequestBody List<TCharacter> request) {
        System.out.println(request);
        tCharacterService.saveTCharacterList(request);
    }
}
