package com.example.demo.dao;


import com.example.demo.domain.MpUserInfo;

public interface MpUserInfoMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mp_user_info
     *
     * @mbggenerated Sat Jul 14 02:22:30 CST 2018
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mp_user_info
     *
     * @mbggenerated Sat Jul 14 02:22:30 CST 2018
     */
    int insert(MpUserInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mp_user_info
     *
     * @mbggenerated Sat Jul 14 02:22:30 CST 2018
     */
    int insertSelective(MpUserInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mp_user_info
     *
     * @mbggenerated Sat Jul 14 02:22:30 CST 2018
     */
    MpUserInfo selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mp_user_info
     *
     * @mbggenerated Sat Jul 14 02:22:30 CST 2018
     */
    int updateByPrimaryKeySelective(MpUserInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mp_user_info
     *
     * @mbggenerated Sat Jul 14 02:22:30 CST 2018
     */
    int updateByPrimaryKey(MpUserInfo record);

    void deleteByOpenId(String openId);

    MpUserInfo selectByOpenId(String openId);
}