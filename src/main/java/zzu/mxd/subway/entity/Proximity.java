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
 * 距离（临近性）
 * </p>
 *
 * @author mxd
 * @since 2019-04-18
 */
@Data
@Accessors(chain = true)
@TableName("subway_proximity")
public class Proximity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String value;

    private Long uid;

    private LocalDateTime datetime;


}
