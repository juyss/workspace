package com.github.tangyi.user.synchrodata;

import com.github.tangyi.common.core.model.ResponseBean;
import com.github.tangyi.user.synchrodata.service.ClientAuthService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api("同步数据任务")
@RestController
@RequestMapping("/v1/synch")
public class SynchController {

    @Autowired
    private AsyncExecute asyncExecute;
    @Autowired
    private ClientAuthService clientAuthService;
    @Autowired
    private SynchTask synchTask;

    @GetMapping("/get_token")
    public Object getAccessToken() {
        return clientAuthService.getAccessToken();
    }

    @ApiOperation(value = "同步用户信息", notes = "拉取主系统的用户信息")
    @GetMapping("/execute_user_task")
    public ResponseBean excuteTask() {
        AsyncExecute.ExecuteState state = asyncExecute.execute(asyncExecute.createTask("user", () -> synchTask.synchUserData()));
        return new ResponseBean(state);
    }

    @ApiOperation(value = "同步角色信息", notes = "推送角色到主系统")
    @GetMapping("/execute_role_task")
    public ResponseBean excuteTask2() {
        AsyncExecute.ExecuteState state = asyncExecute.execute(asyncExecute.createTask("role", () -> synchTask.synchRoleData()));
        return new ResponseBean(state);
    }

    @ApiOperation(value = "同步部门信息", notes = "从统一认证同步部门数据到子系统")
    @GetMapping("/execute_dept_task")
    public ResponseBean excuteTask3() {
        AsyncExecute.ExecuteState state = asyncExecute.execute(asyncExecute.createTask("dept", () -> synchTask.synchDeptData()));
        return new ResponseBean(state);
    }
    @ApiOperation(value = "查询同步状态", notes = "查询同步任务的状态")
    @GetMapping("/query_task_state")
    public ResponseBean queryState(@RequestParam String taskName){
        AsyncExecute.ExecuteState state = asyncExecute.getExecuteState(asyncExecute.createTask(taskName, null));
        return new ResponseBean(state);
    }
}
