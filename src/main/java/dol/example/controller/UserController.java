package dol.example.controller;

import dol.example.common.exception.advice.APIException;
import dol.example.common.info.ExceptionInfo;
import dol.example.common.info.WorldInfo;
import dol.example.domain.TCharacter;
import dol.example.domain.TUser;
import dol.example.service.TCharacterService;
import dol.example.service.TUserService;
import dol.example.util.JsoupUtil;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin
@AllArgsConstructor
@RestController
@RequestMapping("/v1/user")
public class UserController {

    @Autowired
    TUserService tUserService;

    @Autowired
    TCharacterService tCharacterService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<TUser> read(@RequestBody Map<String, String> request) {
        String email = request.get("email");

        if (email == null) {
            throw new APIException(ExceptionInfo.INVALID_REQUEST_EXCEPTION);
        }

        return ResponseEntity.ok(tUserService.findTUser(email));
    }

    @RequestMapping(value = "/with-maple", produces = "application/json; charset=utf8", method = RequestMethod.POST)
    public ResponseEntity<TUser> regist(@RequestBody TUser request) {
        chkLogical(request);

        String representativeCharacterName = request.getRepresentativeCharacterName();
        if (representativeCharacterName.isEmpty()) {
            throw new APIException(ExceptionInfo.INVALID_REQUEST_EXCEPTION);
        }

        // 대표 캐릭터 등록을 위한 캐릭터 검색
        JsoupUtil jsoupUtil = new JsoupUtil();
        // 일반 월드 검색
        TCharacter tCharacter = jsoupUtil.getCharacterInfoFromMaplestory(WorldInfo.ALL_0.getName(), representativeCharacterName);
        if (tCharacter == null) {
            // 캐릭터 조회에 실패한 경우, 리부트 월드 검색
            tCharacter = jsoupUtil.getCharacterInfoFromMaplestory(WorldInfo.ALL_1.getName(), representativeCharacterName);
            if (tCharacter == null) {
                throw new APIException(ExceptionInfo.NOT_FOUND_EXCEPTION, "메이플 월드에 존재하지 않는 캐릭터입니다.");
            }
        }

        // tCharacter mapping
        request.addTCharacter(tCharacter);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(tUserService.saveTUser(request));
    }

    @RequestMapping(value = "", produces = "application/json; charset=utf8", method = RequestMethod.POST)
    public ResponseEntity<TUser> onlyDolRegist(@RequestBody TUser request) {
        chkLogical(request);

        return ResponseEntity.ok().body(tUserService.saveTUser(request));
    }

    private void chkLogical(TUser request) {
        String email = request.getEmail();

        if (email.isEmpty()) {
            throw new APIException(ExceptionInfo.INVALID_REQUEST_EXCEPTION);
        }
    }
}
