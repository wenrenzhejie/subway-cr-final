package zzu.mxd.sys.entity;

import com.baomidou.mybatisplus.annotation.TableName;
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
 * @since 2019-04-22
 */
@Data
@Accessors(chain = true)
@TableName("sys_dictionary")
public class Dictionary implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String value;

    private LocalDateTime datetime;


}
