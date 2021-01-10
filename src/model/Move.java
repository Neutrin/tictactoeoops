package model;

public class Move {
    int rowNo;
    int colNo;
    EnumMark mark;

    public Move(int rowNo, int colNo, EnumMark mark) {
        this.rowNo = rowNo;
        this.colNo = colNo;
        this.mark = mark;
    }

    public int getRowNo() {
        return rowNo;
    }

    public void setRowNo(int rowNo) {
        this.rowNo = rowNo;
    }

    public int getColNo() {
        return colNo;
    }

    public void setColNo(int colNo) {
        this.colNo = colNo;
    }

    public EnumMark getMark() {
        return mark;
    }

    public void setMark(EnumMark mark) {
        this.mark = mark;
    }

    public boolean isDiagnal(){
        return (rowNo == colNo);
    }

    public boolean isAntiDiagnal(int size){
        if((rowNo + colNo) == (size+1)){
            return true;
        }
        return false;
    }
}