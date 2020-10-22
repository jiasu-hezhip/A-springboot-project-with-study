package com.sll.demo.eduservice.service;

import com.sll.demo.eduservice.entity.EduCourse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sll.demo.eduservice.entity.vo.CourseInfo;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author testjava
 * @since 2020-10-21
 */
public interface EduCourseService extends IService<EduCourse> {

    String saveCourseInfo(CourseInfo courseInfo);
}
