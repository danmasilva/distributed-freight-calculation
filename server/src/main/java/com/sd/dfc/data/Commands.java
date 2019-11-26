package com.sd.dfc.data;

public enum Commands {

    INSERT("insert", "create", "inserir"),
    READ_ALL("read_all", "readall", "lertodos"),
    UPDATE("update", "change", "alterar"),
    DELETE("delete", "remove", "deletar");

    final String op1;
    final String op2;
    final String op3;

    Commands(String op1, String op2, String op3){
        this.op1 = op1;
        this.op2 = op2;
        this.op3 = op3;
    }

    public String getOp1() {
        return op1;
    }

    public String getOp2() {
        return op2;
    }

    public String getOp3() {
        return op3;
    }

    public String[] getAllOps(){
        return new String[] {this.op1, this.op2, this.op3};
    }
}
