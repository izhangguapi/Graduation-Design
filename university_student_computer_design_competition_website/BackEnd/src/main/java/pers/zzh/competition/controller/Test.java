package pers.zzh.competition.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.zzh.competition.dao.UsersDao;
import pers.zzh.competition.entities.Users;

import java.util.List;

@RestController
@RequestMapping("/test")
public class Test {

//    @Autowired
//    public Environment environment;
//    @Autowired
//    private MyDataSource myDataSource;
    @Autowired
    private UsersDao userDao;

    @GetMapping
    public List<Users> getById(){
        return null;
    }

}
