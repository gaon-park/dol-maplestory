package dol.example.controller;

import dol.example.common.info.WorldInfo;
import dol.example.dto.common.UnionDetail;
import dol.example.service.TUnionService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@AllArgsConstructor
@RestController
@RequestMapping("/v1/union")
public class UnionController {

    @Autowired
    TUnionService tUnionService;

    @RequestMapping(value = "/{worldName}", method = RequestMethod.GET)
    public ResponseEntity<List<UnionDetail>> getUnionInfoOfSpecificWorld(@PathVariable String worldName) {
        WorldInfo worldInfo = WorldInfo.getWorldInfoByWorldName(worldName);


        return ResponseEntity.ok(new ArrayList<>());
    }
}
