package pers.zzh.competition.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UsersController {

//    @RequestMapping("/login")
//    public Result login(@RequestBody User user) {
//        Result result = new Result();
//        User one = userService.getOne(
//                new QueryWrapper<User>()
//                        .eq("name", user.getName())
//                        .eq("password", user.getPassword()));
//        if(one!=null){
//            return result.ok(null,"登录成功");
//        }else{
//            return result.fail(null,"用户名或密码错误");
//        }
//    }

}
