package com.gof.command.revoke;

/**
 * 典型的抽象命令类代码如下所示
 */
public interface Command {
    void execute();
    void undo();
}
