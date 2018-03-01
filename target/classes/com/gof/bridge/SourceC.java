package com.gof.bridge;

/**
 * 桥接模式 来源C
 */
public class SourceC extends Source {

     public SourceC(Arrive qiao){
          super.qiao = qiao;
     }

     @Override
     void fromAreaA() {
          System.out.println("我来自C");
     }

}
