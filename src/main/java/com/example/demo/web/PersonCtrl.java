package com.example.demo.web;

import com.example.demo.domain.Person;
import com.example.demo.service.IPersonService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ZhaoYuJie on 2018/7/2.
 */
@RestController
@RequestMapping(value = "/person",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(description = "用户操作接口")
public class PersonCtrl {
    @Autowired
    private IPersonService personService;

    @GetMapping("/query")
    @ApiOperation(value = "获取用户详细信息",notes = "根据用户id查询用户详细信息")
    @ApiResponses({
            @ApiResponse(code = 200,message = "成功，返回结果如下：",response = Person.class)
    })
    public Map<String,Object> query(@ApiParam(value = "用户ID",required = true)@RequestParam int id){
        Map<String,Object> retMap = new HashMap<>();
        Person person = personService.selectByPrimaryKey(id);

        retMap.put("person",person);

        return retMap;
    }

    @PostMapping("/add")
    @ApiOperation(value = "添加用户",notes = "接收Person对象属性，添加用户信息",response = Map.class)
    public Map<String,Object> add( Person person){
        Map<String,Object> retMap = new HashMap<>();

        personService.insertSelective(person);

        retMap.put("personID",person.getId());

        return retMap;
    }


}
