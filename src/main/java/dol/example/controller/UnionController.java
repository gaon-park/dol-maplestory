package dol.example.controller;

import dol.example.common.exception.advice.APIException;
import dol.example.common.info.ExceptionInfo;
import dol.example.common.info.WorldInfo;
import dol.example.domain.TUser;
import dol.example.dto.common.UnionDetail;
import dol.example.service.TUnionService;
import dol.example.service.TUserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin
@AllArgsConstructor
@RestController
@RequestMapping("/v1/union")
public class UnionController {

    @Autowired
    TUnionService tUnionService;

    @Autowired
    TUserService tUserService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<List<UnionDetail>> getUnionDetailList(@RequestBody TUser tUser) {
        chkLogicalId(tUser);

        return ResponseEntity.ok(tUnionService.findTUnionByUser(tUser));
    }

    @RequestMapping(value = "/{worldName}", method = RequestMethod.GET)
    public ResponseEntity<UnionDetail> getUnionDetailBySpecificWorldName(@PathVariable String worldName, @RequestBody TUser request) {
        chkLogicalId(request);

        TUser tUser = tUserService.findTUser(Long.valueOf(request.getId()));
        WorldInfo worldInfo = WorldInfo.getWorldInfoByWorldName(worldName);
        if (worldInfo == null) {
            throw new APIException(ExceptionInfo.INVALID_REQUEST_EXCEPTION, "존재하지 않는 월드입니다.");
        }

        return ResponseEntity.ok(tUnionService.findTUnionByUserAndWorldInfoId(tUser, worldInfo.getId()));
    }

    private void chkLogicalId(TUser request) {
        Long id = request.getId();

        if (id == null) {
            throw new APIException(ExceptionInfo.INVALID_REQUEST_EXCEPTION);
        }
    }
}
