package com.gof.command.revoke;

/**
 * 命令模式 多重撤销、反撤销实现
 *
 * 在撤销命令的实现上，可以结合备忘录模式做，达到保存对象的目的
 */
public class Test {
    public static void main(String[] args) {
        /*CommandManager commandManager = new CommandManager();

        Receiver ra = new ConcreteReceiverA();
        Receiver rb = new ConcreteReceiverB();

        Command  a = new ConcreteCommandA(ra);
        Command  b = new ConcreteCommandB(rb);

        //执行命令
        commandManager.executeCommand(a);
        commandManager.executeCommand(b);

        //撤销命令
        commandManager.undo();
        commandManager.undo();

        //反撤销命令
        commandManager.redo();
        commandManager.redo();*/

        /***************************************************/


        CommandManager invoker = new CommandManager();

        Workflow workflow = new Workflow(3);
        WorkFlowReceiver wr = new WorkFlowReceiver(workflow);

        Command wc = new WorkflowCommand(wr);

        invoker.executeCommand(wc);

        invoker.undo();

        invoker.redo();

    }
}
