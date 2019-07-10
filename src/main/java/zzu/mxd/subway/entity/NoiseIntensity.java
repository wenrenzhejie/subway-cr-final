package zzu.mxd.subway.entity;

import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.TableField;
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
 * 噪声强度
 * </p>
 *
 * @author mxd
 * @since 2019-04-18
 */
@Data
@Accessors(chain = true)
@TableName("subway_noiseIntensity")
public class NoiseIntensity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 长度4位，小数点2位
     */
    @ApiModelProperty(value="长度4位，小数点2位")
    private BigDecimal value;

    private LocalDateTime datetime;

    private Integer uid;

    /**
     * 舒适度值
     */
    @ApiModelProperty(value="舒适度值")
    @TableField("comfortDegree")
    private Integer comfortDegree;

    public Integer getComfortDegree() {
        return comfortDegree;
    }

    public void setComfortDegree(Integer comfortDegree) {
        this.comfortDegree = comfortDegree;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public LocalDateTime getDatetime() {
        return datetime;
    }

    public void setDatetime(LocalDateTime datetime) {
        this.datetime = datetime;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }
}
