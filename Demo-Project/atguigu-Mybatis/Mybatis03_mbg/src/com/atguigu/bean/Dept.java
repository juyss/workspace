package com.atguigu.bean;

public class Dept {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dept.did
     *
     * @mbggenerated Mon Jun 24 16:25:19 CST 2019
     */
    private Integer did;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dept.dname
     *
     * @mbggenerated Mon Jun 24 16:25:19 CST 2019
     */
    private String dname;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dept.did
     *
     * @return the value of dept.did
     *
     * @mbggenerated Mon Jun 24 16:25:19 CST 2019
     */
    public Integer getDid() {
        return did;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dept.did
     *
     * @param did the value for dept.did
     *
     * @mbggenerated Mon Jun 24 16:25:19 CST 2019
     */
    public void setDid(Integer did) {
        this.did = did;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dept.dname
     *
     * @return the value of dept.dname
     *
     * @mbggenerated Mon Jun 24 16:25:19 CST 2019
     */
    public String getDname() {
        return dname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dept.dname
     *
     * @param dname the value for dept.dname
     *
     * @mbggenerated Mon Jun 24 16:25:19 CST 2019
     */
    public void setDname(String dname) {
        this.dname = dname == null ? null : dname.trim();
    }
}