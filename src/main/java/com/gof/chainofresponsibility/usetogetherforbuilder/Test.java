package com.gof.chainofresponsibility.usetogetherforbuilder;

/**
 * 进阶责任链模式 与建造者模式结合使用
 * 实现动态可扩展责任链
 *
 * 实例场景

 * 在公司内部员工请假一般情况是这样的：员工在OA系统中提交一封请假邮件，该邮件会自动转发到你的直接上级领导邮箱里，
 * 如果你的请假的情况特殊的话，该邮件也会转发到你上级的上级的邮箱，根据请假的情况天数多少，系统会自动转发相应的责任人的邮箱。
 * 我们就以这样一种场景为例完成一个责任链模式的代码。为了更清晰的描述这种场景我们规定如下：
 *
 * ① GroupLeader（组长 ）：他能批准的假期为2天，如果请假天数超过2天就将请假邮件自动转发到组长和经理邮箱。
 * ② Manager（经理）：他能批准的假期为4天以内，如果请假天数大于4天将该邮件转发到自动转发到组长、经理和部门领导的邮箱。
 * ③ DepartmentHeader（部门领导）：他能批准的假期为7天以内，如果大于7天就只批准7天。
 *
 * 示例参考链接：http://blog.csdn.net/u012810020/article/details/71194853
 */
public class Test {
    public static void main(String[] args) {
        /*Request request = new Request.Builder().setName("张三").setDays(5)
                .setReason("事假").build();
        ChainOfResponsibilityClient client = new ChainOfResponsibilityClient();
        Result result = client.execute(request);

        System.out.println("结果：" + result.toString());*/

        /*****************************此后，动态扩展一个“责任人” CustomInterceptor *******************************/

        Request request = new Request.Builder().setName("张三").setDays(5)
                .setReason("事假").build();
        ChainOfResponsibilityClient client = new ChainOfResponsibilityClient();
        //添加自定义处理器
        client.addRatifys(new CustomInterceptor());
        Result result = client.execute(request);

        System.out.println("结果：" + result.toString());
    }
}
