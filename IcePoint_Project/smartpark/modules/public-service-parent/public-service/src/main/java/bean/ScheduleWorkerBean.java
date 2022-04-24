package bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Objects;

@ApiModel("值班人员bean")
public class ScheduleWorkerBean {

    @ApiModelProperty("值班人员id")
    private Long id;

    @ApiModelProperty("值班人员用户名")
    private String username;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ScheduleWorkerBean that = (ScheduleWorkerBean) o;
        return id.equals(that.id) &&
                username.equals(that.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
