package zzu.mxd.subway.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.io.Serializable;
import io.swagger.annotations.ApiModelProperty;


//import com.baomidou.mybatisplus.annotations.Version;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author mxd
 * @since 2019-07-10
 */
@Data
@Accessors(chain = true)
@TableName("subway_siteComfort")
public class SiteComfort implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 唯一标识一个舒适度
     */
    @ApiModelProperty(value="唯一标识一个舒适度")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private int value;

    private Integer dicId;

    private LocalDateTime datetime;

    private String n;

    /**
     * 用户ID
     */
    @ApiModelProperty(value="用户ID")
    private Long uid;

    public SiteComfort(int value, Integer dicId, LocalDateTime datetime, BigDecimal n, Integer uid) {
        this.value = value;
        this.dicId = dicId;
        this.datetime = datetime;
        this.n = String.valueOf(n);
        this.uid = Long.valueOf(uid);
    }

    public SiteComfort() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Integer getDicId() {
        return dicId;
    }

    public void setDicId(Integer dicId) {
        this.dicId = dicId;
    }

    public LocalDateTime getDatetime() {
        return datetime;
    }

    public void setDatetime(LocalDateTime datetime) {
        this.datetime = datetime;
    }

    public String getN() {
        return n;
    }

    public void setN(String n) {
        this.n = n;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }
}
