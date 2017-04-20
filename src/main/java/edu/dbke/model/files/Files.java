package edu.dbke.model.files;

import org.apache.commons.lang.builder.EqualsBuilder;

import javax.persistence.*;
import java.util.Date;

/**
 * 所有文件的共同属性
 * Created by hp on 2016/12/12.
 */
@Table(name = "t_files")
public class Files {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 文件名称
     */
    private String name;

    /**
     * 文件上传时间
     */
    @Column(name = "uploadTime")
    private Date uploadtime;

    /**
     * 文件大小
     */
    @Column(name = "fileSize")
    private String filesize;

    /**
     * 内容类型
     */
    @Column(name = "contentType")
    private String contenttype;

    /**
     * 文件上传时的扩展名
     */
    @Column(name = "uploadExt")
    private String uploadext;

    /**
     * 保存路径
     */
    @Column(name = "savePath")
    private String savepath;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取文件名称
     *
     * @return name - 文件名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置文件名称
     *
     * @param name 文件名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取文件上传时间
     *
     * @return uploadTime - 文件上传时间
     */
    public Date getUploadtime() {
        return uploadtime;
    }

    /**
     * 设置文件上传时间
     *
     * @param uploadtime 文件上传时间
     */
    public void setUploadtime(Date uploadtime) {
        this.uploadtime = uploadtime;
    }

    /**
     * 获取文件大小
     *
     * @return fileSize - 文件大小
     */
    public String getFilesize() {
        return filesize;
    }

    /**
     * 设置文件大小
     *
     * @param filesize 文件大小
     */
    public void setFilesize(String filesize) {
        this.filesize = filesize;
    }

    /**
     * 获取内容类型
     *
     * @return contentType - 内容类型
     */
    public String getContenttype() {
        return contenttype;
    }

    /**
     * 设置内容类型
     *
     * @param contenttype 内容类型
     */
    public void setContenttype(String contenttype) {
        this.contenttype = contenttype;
    }

    /**
     * 获取文件上传时的扩展名
     *
     * @return uploadExt - 文件上传时的扩展名
     */
    public String getUploadext() {
        return uploadext;
    }

    /**
     * 设置文件上传时的扩展名
     *
     * @param uploadext 文件上传时的扩展名
     */
    public void setUploadext(String uploadext) {
        this.uploadext = uploadext;
    }

    /**
     * 获取保存路径
     *
     * @return savePath - 保存路径
     */
    public String getSavepath() {
        return savepath;
    }

    /**
     * 设置保存路径
     *
     * @param savepath 保存路径
     */
    public void setSavepath(String savepath) {
        this.savepath = savepath;
    }

    @Override
    public boolean equals(Object other) {
        if ((this == other))
            return true;
        if ((other == null))
            return false;
        if (!(other instanceof Files))
            return false;
        Files castOther = (Files) other;

        return new EqualsBuilder().append(id, castOther.getId()).append(this.name, castOther.getName()).isEquals();
    }
}
