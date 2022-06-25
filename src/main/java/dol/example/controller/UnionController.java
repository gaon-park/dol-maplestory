package dol.example.controller;

import dol.example.dto.response.UnionResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@AllArgsConstructor
@RestController
@RequestMapping("/v1/union")
public class UnionController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<List<UnionResponse>> getAllUnionInfo() {
        return ResponseEntity.ok(new ArrayList<>());
    }
}
