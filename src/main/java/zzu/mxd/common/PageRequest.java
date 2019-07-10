package zzu.mxd.common;

import io.swagger.annotations.ApiModelProperty;

public class PageRequest {
    @ApiModelProperty(value="每页显示条数",example="10") // 为什么其它属性大多用不了
    private int size = 10;

    @ApiModelProperty(value="当前页",example="1")
    private int current = 1;

    public int getSize() {
        return size;
    }
    public void setSize(int size) {
        this.size = size;
    }
    public int getCurrent() {
        return current;
    }
    public void setCurrent(int current) {
        this.current = current;
    }

}
