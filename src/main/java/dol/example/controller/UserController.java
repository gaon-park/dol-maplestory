package dol.example.controller;

import dol.example.common.exception.AlreadyExistException;
import dol.example.common.exception.InvalidValueException;
import dol.example.domain.TUser;
import dol.example.service.TUserService;
import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin
@AllArgsConstructor
@RestController
@RequestMapping("/v1/user")
public class UserController {

    @Autowired
    TUserService tUserService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public Object read(@RequestBody Map<String, String> request){
        Long userId = Long.parseLong(request.get("userId"));
        String email = request.get("email");

        if(userId == null || email == null){
            return HttpStatus.BAD_REQUEST;
        }

        try {
            return tUserService.findTUser(userId);
        } catch (NotFoundException e) {
            return HttpStatus.NOT_FOUND;
        }
    }

    @RequestMapping(value = "", produces = "application/json; charset=utf8", method = RequestMethod.POST)
    public TUser regist(@RequestBody TUser request){
        return tUserService.saveTUser(request);
    }
}
