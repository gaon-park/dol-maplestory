package dol.example.controller;

import dol.example.util.SoapUtil;
import lombok.AllArgsConstructor;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin
@AllArgsConstructor
@RestController
@RequestMapping("/v1/character")
public class CharacterController {

    @RequestMapping(value = "/info/byDolMaplestoryId/{id}", method = RequestMethod.GET)
    public void getCharacterInfoByDolMaplestoryId(){

    }
}
