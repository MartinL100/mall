package com.lovo.csc.vo.clientvo;


//用户状态维护 审核完成后返回结果VO
public class PreserveResultVo {
    //维护用户的姓名集合
    private String[] userNameArray;
    //用户账号状态 1、审核通过，3：已冻结
    private String userState;
    //审核意见
    private String auditOpinion;
    //批复时间
    private String auditReplyTime;



    public PreserveResultVo() {
    }




    public String[]  getUserNameArray() {
        return userNameArray;
    }

    public void setUserNameArray(String[] userNameArray) {
        this.userNameArray = userNameArray;
    }

    public String getUserState() {
        return userState;
    }

    public void setUserState(String userState) {
        this.userState = userState;
    }

    public String getAuditOpinion() {
        return auditOpinion;
    }

    public void setAuditOpinion(String auditOpinion) {
        this.auditOpinion = auditOpinion;
    }

    public String getAuditReplyTime() {
        return auditReplyTime;
    }

    public void setAuditReplyTime(String auditReplyTime) {
        this.auditReplyTime = auditReplyTime;
    }



    @Override
    public String toString() {
        return "PreserveResultVo{" +
                "userNameArray=" + userNameArray +
                ", userState='" + userState + '\'' +
                ", auditOpinion='" + auditOpinion + '\'' +
                ", auditReplyTime='" + auditReplyTime + '\'' +
                '}';
    }

//    public static void main(String[] args) throws JsonProcessingException {
//        Set set = new HashSet();
//        set.add(1);
//        set.add(2);
//        PreserveResultVo vo =new PreserveResultVo();
//        vo.setUserName("占山");
//        vo.setUserNameSet(set);
//      String json=  new ObjectMapper().writeValueAsString(vo);
//        System.out.println(json);
//
//    }
}
