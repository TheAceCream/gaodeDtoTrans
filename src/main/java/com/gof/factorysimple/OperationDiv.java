package com.gof.factorysimple;

/**
 * 除法运算类
 */
public class OperationDiv extends Operation{
    //好习惯，能公有的类避免在代码中重复实例化
    private static final Exception ex = new Exception("除数不能为0");

    @Override
    public double getResult() throws Exception {
        if(getNumberB()==0){
            throw ex;
        }
        return getNumberA() / getNumberB();
    }
}
