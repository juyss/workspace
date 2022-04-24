package com.github.tangyi.exam.mapper;

import com.github.tangyi.common.core.model.req.BaseReq;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface TrainCourseMapper {

//    @Select({"<script>SELECT\n" +
//            "\tt1.train_course_id trainCourseId,\n" +
//            "\tif(t1.create_date is null ,'', DATE_FORMAT(t1.create_date,'%Y-%m-%d %H:%i:%S')) createDate,\n" +
//            "\tt1.user_id userId,\n" +
//            "\tt1.is_sign_in isSignIn,\n" +
//            "\tif(t1.sign_in_time is null ,'', DATE_FORMAT(t1.sign_in_time,'%Y-%m-%d %H:%i:%S')) signInTime,\n" +
//            "\tifnull(t2.name,(select identifier from sys_user_auths where user_id=t2.id limit 1)) name,\n" +
//            "\tif(t2.sex=1,'女','男' ) sex \n" +
//            "\t,CASE \n" +
//            "\tWHEN ( SELECT parent_id FROM sys_dept WHERE id = t2.dept_id  ) =- 1 THEN\n" +
//            "\t( SELECT dept_name FROM sys_dept WHERE id = t2.dept_id  ) \n" +
//            "\tWHEN ( SELECT parent_id FROM sys_dept WHERE id = t2.dept_id  ) IS NULL THEN\n" +
//            "\t'' ELSE ( SELECT dept_name FROM sys_dept WHERE id = ( SELECT parent_id FROM sys_dept WHERE id =t2.dept_id ) ) \n" +
//            "END as companyName \n" +
//            ",(select dept_name from sys_dept where id=t2.dept_id) deptName \n"  +
//            "FROM\n" +
//            "\ttrain_user_relation t1\n" +
//            "\tINNER JOIN sys_user t2 ON t1.user_id = t2.id\n" +
//            "\twhere t1.train_course_id = #{id}" +
//            "<if test='dto.kw!=null and dto.kw!=\"\"'>and  LOCATE(#{dto.kw},t2.name) &gt;0</if>" +
//            "<if test='dto.startTime!=null'> and  t1.create_date &gt;=#{dto.startTime}</if>" +
//            "<if test='dto.endTime!=null'> and  t1.create_date &lt;=#{dto.endTime}</if>" +
//            "order by t1.${sort} ${order}</script>"
//    })
    List<Map<String,Object>> getRelationList(@Param("dto") BaseReq baseReq, @Param("id") Long id, @Param("sort") String sort, @Param("order") String order);
}
