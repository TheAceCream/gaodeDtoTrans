package com.gof.command.revoke;

/**
 * 工作流具体命令类
 */
public class WorkflowCommand implements Command{

    private WorkFlowReceiver receiver; //维持一个对请求接收者对象的引用

    public WorkflowCommand(WorkFlowReceiver receiver){
        this.receiver = receiver;
    }

    @Override
    public void execute() {
        receiver.action();
    }

    @Override
    public void undo() {
        Workflow workflow = receiver.getWorkflowCaretaker().getWorkflow();
        System.out.println("撤销workflow状态更改操作，当前工作流状态为： "+workflow.getState());
    }
}
