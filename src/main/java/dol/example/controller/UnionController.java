package dol.example.controller;

import dol.example.common.info.WorldInfo;
import dol.example.domain.TUser;
import dol.example.dto.common.UnionDetail;
import dol.example.service.TUnionService;
import dol.example.service.TUserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@CrossOrigin
@AllArgsConstructor
@RestController
@RequestMapping("/v1/union")
public class UnionController {

    @Autowired
    TUnionService tUnionService;

    @Autowired
    TUserService tUserService;

    @RequestMapping(value = "/{worldName}", method = RequestMethod.GET)
    public ResponseEntity<UnionDetail> getUnionInfoOfSpecificWorld(@PathVariable String worldName, @RequestBody TUser request) {
        WorldInfo worldInfo = WorldInfo.getWorldInfoByWorldName(worldName);
        TUser tUser = tUserService.findTUser(Long.valueOf(request.getId()));

        return ResponseEntity.ok(tUnionService.findTUnionByUserAndWorldInfoId(tUser, worldInfo.getId()));
    }
}
