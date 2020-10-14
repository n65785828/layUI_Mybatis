package com.mzx.springbootweb.mapper;

import com.mzx.springbootweb.entity.Overtime;
import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;

@Mapper
public interface OverTimeMapper {

    @Select("<script>select * from overtime where 1=1 " +
            "<if test='accountId!=null'> and accountId = #{accountId} </if> " +
            "<if test='content!=null'> and content like '%${content}%' </if>"+
            "<if test='overTimeStartDate!=null'> and overTimeStartDate &gt;= #{overTimeStartDate} </if>"+
            "<if test='overTimeEndDate!=null'> and overTimeEndDate &lt;= #{overTimeEndDate} </if>"+
            "order by fillinDate desc limit #{startLine},#{num} </script>")
    List<Overtime> getOverTimePages(@Param("accountId")String accountId,@Param("content")String content,@Param("overTimeStartDate")String overTimeStartDate,@Param("overTimeEndDate")String overTimeEndDate,@Param("startLine") int paramInt1, @Param("num") int paramInt2);


    @Select("<script>select count(*) from overtime where 1=1 " +
            "<if test='accountId!=null'> and accountId = #{accountId} </if> " +
            "<if test='content!=null'> and content like '%${content}%' </if>"+
            "<if test='overTimeStartDate!=null'> and overTimeStartDate &gt;= #{overTimeStartDate} </if>"+
            "<if test='overTimeEndDate!=null'> and overTimeEndDate &lt;= #{overTimeEndDate} </if>"+
            "</script>")
    long overTimeSearchCount(@Param("accountId")String accountId,@Param("content")String content,@Param("overTimeStartDate")String overTimeStartDate,@Param("overTimeEndDate")String overTimeEndDate);

    @Insert({"insert into overtime values(#{id},#{accountId},#{context},#{fillInDate},#{hours},#{overTimeEndDate},#{overTimeStartDate},#{productId},#{projectID},#{type})"})
    int addOverTime(@Param("id")String id, @Param("accountId")String accountId,@Param("context") String context,
                    @Param("fillInDate")Date fillInDate,@Param("hours")Double hours,@Param("overTimeEndDate")Date overTimeEndDate,
                    @Param("overTimeStartDate")Date overTimeStartDate ,@Param("productId")String productId,
                    @Param("projectID")String projectID,@Param("type")Integer type);

    @Delete({"delete from overtime where id = #{id}"})
    int deleteById(@Param("id")String id);

    @Delete("<script>delete from overtime where id in  "+
            "<foreach collection='userIdList' item='id' index='index' open='(' separator=',' close=')'>"+
            "#{id}"+
            "</foreach>"+
            "</script>")
    int deleteByIds(@Param("userIdList")List<String> userIdList);

    @Update({"update overtime set accountId=#{accountId} ,content=#{context}," +
            "hours=#{hours},overTimeEndDate=#{overTimeEndDate}," +
            "overTimeStartDate=#{overTimeStartDate},productId=#{productId}," +
            "projectId=#{projectID},type=#{type} where id=#{id}"})
    int updateOverTime(@Param("id")String id, @Param("accountId")String accountId,@Param("context") String context,
                       @Param("hours")Double hours,@Param("overTimeEndDate")Date overTimeEndDate,
                       @Param("overTimeStartDate")Date overTimeStartDate ,@Param("productId")String productId,
                       @Param("projectID")String projectID,@Param("type")Integer type);
}
