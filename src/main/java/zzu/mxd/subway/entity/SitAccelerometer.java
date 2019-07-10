package zzu.mxd.subway.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import io.swagger.annotations.ApiModelProperty;


//import com.baomidou.mybatisplus.annotations.Version;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 坐姿加速度
 * </p>
 *
 * @author mxd
 * @since 2019-04-18
 */
@Data
@Accessors(chain = true)
@TableName("subway_sitAccelerometer")
public class SitAccelerometer implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 长度5位，小数点3位
     */
    @ApiModelProperty(value="长度5位，小数点3位")
    private BigDecimal x;

    /**
     * 长度5位，小数点3位
     */
    @ApiModelProperty(value="长度5位，小数点3位")
    private BigDecimal y;

    /**
     * 长度5位，小数点3位
     */
    @ApiModelProperty(value="长度5位，小数点3位")
    private BigDecimal z;

    private Integer uid;

    private LocalDateTime datetime;

    public SitAccelerometer(BigDecimal x, BigDecimal y, BigDecimal z, Integer uid) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.uid = uid;
    }

    public SitAccelerometer() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getX() {
        return x;
    }

    public void setX(BigDecimal x) {
        this.x = x;
    }

    public BigDecimal getY() {
        return y;
    }

    public void setY(BigDecimal y) {
        this.y = y;
    }

    public BigDecimal getZ() {
        return z;
    }

    public void setZ(BigDecimal z) {
        this.z = z;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public LocalDateTime getDatetime() {
        return datetime;
    }

    public void setDatetime(LocalDateTime datetime) {
        this.datetime = datetime;
    }
}
