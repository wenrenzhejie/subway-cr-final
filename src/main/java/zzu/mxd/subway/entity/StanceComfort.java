package zzu.mxd.subway.entity;

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
 * 列车线上每个站点的坐姿舒适度值：5分钟更新一次
 * </p>
 *
 * @author mxd
 * @since 2019-04-22
 */
@Data
@Accessors(chain = true)
@TableName("subway_stanceComfort")
public class StanceComfort implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 舒适度值
     */
    @ApiModelProperty(value="舒适度值")
    private Integer value;

    /**
     * 站点名称
     */
    @ApiModelProperty(value="站点名称")
    private Integer dicId;

    /**
     * 时间
     */
    @ApiModelProperty(value="时间")
    private LocalDateTime datetime;

    public StanceComfort(Integer id, Integer value, Integer dicId, LocalDateTime datetime) {
        this.id = id;
        this.value = value;
        this.dicId = dicId;
        this.datetime = datetime;
    }

    public StanceComfort(Integer value, Integer dicId, LocalDateTime datetime) {
        this.value = value;
        this.dicId = dicId;
        this.datetime = datetime;
    }

    public StanceComfort() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
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
}
