package com.sll.demo.eduservice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sll.commonutils.R;
import com.sll.demo.eduservice.entity.EduTeacher;
import com.sll.demo.eduservice.entity.vo.TeacherQuery;
import com.sll.demo.eduservice.service.EduTeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2020-10-14
 */
@Api(description = "讲师管理")
@RestController
@RequestMapping("/eduservice/edu-teacher")
@CrossOrigin
public class EduTeacherController {

    @Autowired
    private EduTeacherService eduTeacherService;

    @ApiOperation(value = "所有讲师列表")
    @GetMapping("findAll")
    public R findAllTescher(){
        List<EduTeacher> list = eduTeacherService.list(null);
        return R.ok().data("items",list);
    }

    @ApiOperation(value = "根据ID删除讲师列表")
    @DeleteMapping("{id}")
    public R removeTeacher(
            @ApiParam(name = "id" ,value = "讲师ID" , required = true)
            @PathVariable String id){
        boolean b = eduTeacherService.removeById(id);
        if (b){
            return R.ok();
        }else {
            return R.error();
        }
    }

    @GetMapping("pageTeacher/{current}/{limit}")
    public R pageListTeacher(
            @PathVariable  long current,
            @PathVariable  long limit
    ){
        Page<EduTeacher> page = new Page<>(current,limit);
        eduTeacherService.page(page,null);
        long total = page.getTotal();
        List<EduTeacher> list = page.getRecords();
        return R.ok().data("total",total).data("rows",list);
    }
    //复杂分页查询-get请求
//    @GetMapping("pageTeacherCondiition/{current}/{limit}")
//    private R pageTeacherCondition(
//            @PathVariable long current,
//            @PathVariable long limit,
//            TeacherQuery teacherQuery
//    ){
//        Page<EduTeacher> page = new Page<>(current,limit);
//        QueryWrapper<EduTeacher> wrapper = new QueryWrapper<>();
//        String name = teacherQuery.getName();
//        Integer level = teacherQuery.getLevel();
//        String begin = teacherQuery.getBegin();
//        String end = teacherQuery.getEnd();
//        if (!StringUtils.isEmpty(name)){
//            wrapper.like("name",name);
//        }
//        if (!StringUtils.isEmpty(level)){
//            wrapper.eq("level",level);
//        }
//        if (!StringUtils.isEmpty(begin)){
//            wrapper.ge("gmt_create",begin);
//        }
//        if (!StringUtils.isEmpty(end)){
//            wrapper.le("gmt_create",end);
//        }
//        eduTeacherService.page(page,wrapper);
//        long total = page.getTotal();
//        List<EduTeacher> list = page.getRecords();
//        return R.ok().data("total",total).data("rows",list);
//    }

    //post请求方式
    @PostMapping("pageTeacherCondition/{current}/{limit}")
    private R pageTeacherCondition(
            @PathVariable long current,
            @PathVariable long limit,
            @RequestBody(required = false) TeacherQuery teacherQuery
    ){
        Page<EduTeacher> page = new Page<>(current,limit);
        QueryWrapper<EduTeacher> wrapper = new QueryWrapper<>();
        String name = teacherQuery.getName();
        Integer level = teacherQuery.getLevel();
        String begin = teacherQuery.getBegin();
        String end = teacherQuery.getEnd();
        if (!StringUtils.isEmpty(name)){
            wrapper.like("name",name);
        }
        if (!StringUtils.isEmpty(level)){
            wrapper.eq("level",level);
        }
        if (!StringUtils.isEmpty(begin)){
            wrapper.ge("gmt_create",begin);
        }
        if (!StringUtils.isEmpty(end)){
            wrapper.le("gmt_create",end);
        }
        //排序
        wrapper.orderByDesc("gmt_create");
        eduTeacherService.page(page,wrapper);
        long total = page.getTotal();
        List<EduTeacher> list = page.getRecords();
        return R.ok().data("total",total).data("rows",list);
    }

    //添加讲师
    @PostMapping("addTeacher")
    public R addTeacher(
            @RequestBody EduTeacher eduTeacher
    ){
        boolean save = eduTeacherService.save(eduTeacher);
        if (save){
            return R.ok();
        }else {
            return R.error();
        }
    }

    //根据id查询
    @GetMapping("getTeacher/{id}")
    public R getTeacher(@PathVariable String id){
        EduTeacher eduTeacher = eduTeacherService.getById(id);
        return R.ok().data("teacher",eduTeacher);
    }

    //修改讲师
    @PostMapping("UpdateTeacher")
    public R updateById(@RequestBody EduTeacher eduTeacher){
        boolean b = eduTeacherService.updateById(eduTeacher);
        if (b){
            return R.ok();
        }else {
            return R.error();
        }
    }
}

