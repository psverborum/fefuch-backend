package com.verborum.fefuchbackend.Controller;

import com.verborum.fefuchbackend.Entity.Post;
import com.verborum.fefuchbackend.Entity.Thread;
import com.verborum.fefuchbackend.Entity.User;
import com.verborum.fefuchbackend.Repository.PostRepository;
import com.verborum.fefuchbackend.Repository.ThreadRepository;
import com.verborum.fefuchbackend.Repository.UserRepository;
import org.apache.tomcat.util.json.JSONParser;
import org.apache.tomcat.util.json.ParseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.LinkedHashMap;
import java.util.Optional;

@RestController
@RequestMapping(path = "/thread")
public class ThreadController implements BaseController {

    private final UserRepository userRepository;
    private final ThreadRepository threadRepository;

    public ThreadController(UserRepository userRepository, ThreadRepository threadRepository) {
        this.userRepository   = userRepository;
        this.threadRepository = threadRepository;
    }

    @PostMapping(path = "/add")
    public @ResponseBody
    ResponseEntity<Object> addThread(@RequestBody String request) {
        JSONParser jsonParser = new JSONParser(request);

        LinkedHashMap<String, Object> json;
        try {
            json = jsonParser.parseObject();
        } catch (ParseException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        String text = (String) json.get("text");
        Thread thread = new Thread().setText(text);

        String ip = fetchClientIpAddress();
        User user = userRepository.findUserByIp(ip);

        if (user == null) {
            user = new User().setIp(ip);

            thread.setUser(user);

            userRepository.save(user);
        } else {
            thread.setUser(user);
        }

        if (user.isBanned()) {
            return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(null);
        }

        threadRepository.save(thread);

        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

}
