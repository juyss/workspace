package com.juyss.pojo;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: File
 * @Desc: 对应数据库的file表
 * @package com.juyss.pojo
 * @project TestDemo
 * @date 2020/8/25 22:09
 */
public class File {

    private Integer id;
    private String name;
    private String path;
    private String pwd;

    public File() {
    }

    public File(Integer id, String name, String path, String pwd) {
        this.id = id;
        this.name = name;
        this.path = path;
        this.pwd = pwd;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    @Override
    public String toString() {
        return "File{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", path='" + path + '\'' +
                ", pwd='" + pwd + '\'' +
                '}';
    }
}
