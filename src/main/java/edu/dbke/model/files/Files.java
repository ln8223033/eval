package edu.dbke.model.files;

import org.apache.commons.lang.builder.EqualsBuilder;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * 所有文件的共同属性
 * Created by hp on 2016/12/12.
 */
@Table(name = "T_Files")
public class Files  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;//文件名称
    private Date uploadTime;//文件上传时间
    private String fileSize;//文件大小
    private String contentType;//内容类型
    private String uploadExt;//文件上传时的扩展名
    //后半段的路径，此路径随方法，当前文件数据的变化而变化
    //同时可以根据这段路径来获知此文件所属分类，这在进行权限判断时会乃到
    private String savePath;

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

    public Date getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
    }

    public String getFileSize() {
        return fileSize;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getUploadExt() {
        return uploadExt;
    }

    public void setUploadExt(String uploadExt) {
        this.uploadExt = uploadExt;
    }

    public String getSavePath() {
        return savePath;
    }

    public void setSavePath(String savePath) {
        this.savePath = savePath;
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
