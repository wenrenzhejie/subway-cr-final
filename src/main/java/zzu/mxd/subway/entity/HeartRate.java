package zzu.mxd.subway.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModelProperty;


//import com.baomidou.mybatisplus.annotations.Version;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 心率
 * </p>
 *
 * @author mxd
 * @since 2019-04-26
 */
@Data
@Accessors(chain = true)
@TableName("subway_heartRate")
public class HeartRate implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private BigDecimal value;

    private Integer uid;

    private LocalDateTime datetime;

    /**
     * 舒适度值
     */
    @ApiModelProperty(value="舒适度值")
    @TableField("comfortDegree")
    private Integer comfortDegree;

    public HeartRate(BigDecimal value, Integer uid, LocalDateTime datetime, Integer comfortDegree) {
        this.value = value;
        this.uid = uid;
        this.datetime = datetime;
        this.comfortDegree = comfortDegree;
    }

    public HeartRate() {
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

    public Integer getComfortDegree() {
        return comfortDegree;
    }

    public void setComfortDegree(Integer comfortDegree) {
        this.comfortDegree = comfortDegree;
    }
}
