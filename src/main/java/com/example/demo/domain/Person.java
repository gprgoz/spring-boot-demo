package com.example.demo.domain;

import com.example.demo.bean.CustomDateTimeSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@ApiModel("用户类")
public class Person {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column person.id
     *
     * @mbggenerated Mon Jul 02 16:20:32 CST 2018
     */
    @ApiModelProperty("用户ID")
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column person.name
     *
     * @mbggenerated Mon Jul 02 16:20:32 CST 2018
     */
    @ApiModelProperty("用户名称")
    private String name;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column person.age
     *
     * @mbggenerated Mon Jul 02 16:20:32 CST 2018
     */
    @ApiModelProperty("用户年龄")
    private Integer age;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column person.createTime
     *
     * @mbggenerated Mon Jul 02 16:20:32 CST 2018
     */
    @ApiModelProperty("创建时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonSerialize(using = CustomDateTimeSerializer.class)
    private Date createTime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column person.id
     *
     * @return the value of person.id
     *
     * @mbggenerated Mon Jul 02 16:20:32 CST 2018
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column person.id
     *
     * @param id the value for person.id
     *
     * @mbggenerated Mon Jul 02 16:20:32 CST 2018
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column person.name
     *
     * @return the value of person.name
     *
     * @mbggenerated Mon Jul 02 16:20:32 CST 2018
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column person.name
     *
     * @param name the value for person.name
     *
     * @mbggenerated Mon Jul 02 16:20:32 CST 2018
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column person.age
     *
     * @return the value of person.age
     *
     * @mbggenerated Mon Jul 02 16:20:32 CST 2018
     */
    public Integer getAge() {
        return age;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column person.age
     *
     * @param age the value for person.age
     *
     * @mbggenerated Mon Jul 02 16:20:32 CST 2018
     */
    public void setAge(Integer age) {
        this.age = age;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column person.createTime
     *
     * @return the value of person.createTime
     *
     * @mbggenerated Mon Jul 02 16:20:32 CST 2018
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column person.createTime
     *
     * @param createTime the value for person.createTime
     *
     * @mbggenerated Mon Jul 02 16:20:32 CST 2018
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}