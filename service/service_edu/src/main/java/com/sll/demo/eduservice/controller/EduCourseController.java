package com.sll.demo.eduservice.controller;


import com.sll.commonutils.R;
import com.sll.demo.eduservice.entity.vo.CourseInfo;
import com.sll.demo.eduservice.service.EduChapterService;
import com.sll.demo.eduservice.service.EduCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2020-10-21
 */
@RestController
@RequestMapping("/eduservice/course")
@CrossOrigin
public class EduCourseController {
    @Autowired
    private EduCourseService courseService;

    @PostMapping("addCourdeInfo")
    public R addCourdeInfo(@RequestBody CourseInfo courseInfo){
        String id = courseService.saveCourseInfo(courseInfo);
        return R.ok().data("courseId",id);
    }

}

