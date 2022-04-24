package com.icepoint.base.web.sys.entity;


import com.icepoint.base.api.domain.BasicEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

/**
 * 文件定义
 * @author BD
 *
 */
@Entity
@Table(name = "t_common_file_logs")
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
public class File extends BasicEntity<Long> {

    /**
     * 相册照片还是公告对应照片、视频，或者用户头像等。主要指主表对象类型
     * 1-头像 2-老茶商品图片 3- 4-相册
     * 订单
     * 退货单
     */
    private Integer objType;

    /**
     * 主表对象的id，用于主表和文件一对多关系记录
     */
    private Long objId;

    @Length(max = 32, message = "业务码长度不能超过32")
    private String bizCode;// 

    /**
     * 指应用意义上的分类，图片、视频、文档、其他
     */
    private Integer fileClass;

    /**
     * 文件夹节点代码
     */
    private Long folderId;

    @Length(max = 128, message = "文件路径长度不能超过128")
    private String fileKey;

    @NotEmpty(message = "文件名称不能为空")
    @Length(max = 64, message = "文件名称长度不能超过64")
    private String name;

    private Integer duration;

    private Integer fileSize;

    @NotEmpty(message = "文件名不能为空")
    @Length(max = 1024, message = "文件名长度不能超过1024")
    private String fileName;

    @Length(max = 32, message = "文件后缀长度不能超过32")
    private String filePxf;

    /**
     * 文件具体的类型，和文件后缀相关
     */
    private Integer fileType;

    private Integer width;

    private Integer height;

    @Length(max = 2048, message = "缩略文件长度不能超过2048")
    private String thumbFile;

    @Length(max = 256, message = "文件描述长度不能超过256")
    private String fileDesc;

    private Integer status;

    private Long ownerId;

    private Long appId;

    private Long createUser;

    private Long createTime;

    private Long modifyUser;

    private Long modifyTime;

    private Integer deleted;

}