package com.gof.command.revoke;

/**
 * 工作流的具体行为类
 */
public class WorkFlowReceiver implements Receiver {
    private Workflow workflow;
    private WorkflowCaretaker workflowCaretaker;

    public Workflow getWorkflow() {
        return workflow;
    }

    public void setWorkflow(Workflow workflow) {
        this.workflow = workflow;
    }

    public WorkflowCaretaker getWorkflowCaretaker() {
        return workflowCaretaker;
    }

    public void setWorkflowCaretaker(WorkflowCaretaker workflowCaretaker) {
        this.workflowCaretaker = workflowCaretaker;
    }

    public WorkFlowReceiver(Workflow workflow){
        this.workflow = workflow;
        workflowCaretaker = new WorkflowCaretaker();
    }

    @Override
    public void action() {
        workflowCaretaker.setWorkflow(workflow.createMemento());
        workflow.setState(1);
        System.out.println("工作流状态修改，状态修改为： "+workflow.getState());

    }


}
