package dol.example.controller;

import dol.example.util.SoapUtil;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@AllArgsConstructor
@RestController
@RequestMapping("/")
public class IndexController {

    @GetMapping
    public ResponseEntity<Object> testGet(){
//        System.out.println("TEST SOAP API");
//        SoapUtil soapUtil = new SoapUtil();
//        soapUtil.getCharacterInfoByAccountID();
        return null;
    }
}
