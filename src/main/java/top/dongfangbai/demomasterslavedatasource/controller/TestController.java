package top.dongfangbai.demomasterslavedatasource.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.dongfangbai.demomasterslavedatasource.mapper.master.TestMapper;
import top.dongfangbai.demomasterslavedatasource.mapper.slave.TestSlaveMapper;

@RestController
@RequestMapping(value = "/test")
@Api(tags = "测试模块", description = "测试")
public class TestController {

    @Autowired
    TestMapper testMapper;

    @Autowired
    TestSlaveMapper testSlaveMapper;

    @GetMapping(value = "/indexMaster")
    @ApiOperation("测试index向master库中插入一个用户")
    public void indexMaster(){
        System.out.println("=======" + testMapper.insertUser());
    }

    @GetMapping(value = "/indexSlave")
    @ApiOperation("测试index向slave库中插入一个用户")
    public void indexSlave(){
        System.out.println("=======" + testSlaveMapper.insertSlaveUser());
    }

}
