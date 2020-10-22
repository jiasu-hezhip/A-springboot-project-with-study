package com.sll.demo.eduservice.service.impl;

import com.sll.demo.eduservice.entity.EduCourse;
import com.sll.demo.eduservice.entity.EduCourseDescription;
import com.sll.demo.eduservice.entity.vo.CourseInfo;
import com.sll.demo.eduservice.mapper.EduCourseMapper;
import com.sll.demo.eduservice.service.EduCourseDescriptionService;
import com.sll.demo.eduservice.service.EduCourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sll.servicebase.exception.GuliException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2020-10-21
 */
@Service
public class EduCourseServiceImpl extends ServiceImpl<EduCourseMapper, EduCourse> implements EduCourseService {

    @Autowired
    private EduCourseDescriptionService eduCourseDescriptionService;

    @Override
    public String saveCourseInfo(CourseInfo courseInfo) {
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfo,eduCourse);
        int row = baseMapper.insert(eduCourse);
        if (row == 0 ){
            throw new GuliException(20001,"添加失败");
        }
        //获取添加之后课程id
        String cid = eduCourse.getId();
        EduCourseDescription eduCourseDescription = new EduCourseDescription();
        eduCourseDescription.setDescription(courseInfo.getDescription());
        eduCourseDescription.setId(cid);
        eduCourseDescriptionService.save(eduCourseDescription);
        return cid;
    }
}
