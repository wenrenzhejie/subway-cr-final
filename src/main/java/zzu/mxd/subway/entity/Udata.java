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
 * 
 * </p>
 *
 * @author mxd
 * @since 2019-03-14
 */
@Data
@Accessors(chain = true)
@TableName("subway_udata")
public class Udata implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    @ApiModelProperty(value="编号")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 加速度（重力）传感器
     */
    @ApiModelProperty(value="加速度（重力）传感器")
    private String dataAccelerometer;

    /**
     * 方向传感器
     */
    @ApiModelProperty(value="方向传感器")
    private String dataOrientation;

    /**
     * 光线传感器
     */
    @ApiModelProperty(value="光线传感器")
    private String dataLight;

    /**
     * 温度传感器
     */
    @ApiModelProperty(value="温度传感器")
    private String dataTemperature;

    /**
     * 磁场传感器
     */
    @ApiModelProperty(value="磁场传感器")
    private String dataMagnetic;

    /**
     * 距离（临近性）传感器
     */
    @ApiModelProperty(value="距离（临近性）传感器")
    private String dataProximity;

    /**
     * 所属用户ID
     */
    @ApiModelProperty(value="所属用户ID")
    private Long dataUid;

    /**
     * 日期
     */
    @ApiModelProperty(value="日期")
    private LocalDateTime dataDate;


}
