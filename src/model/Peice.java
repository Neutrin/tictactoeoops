package model;

public class Peice{
    Character marking;

    public Peice(Enum mark) {
        if(mark == EnumMark.MARK_O){
            marking = 'O';
        }else if(mark == EnumMark.MARK_X){
            marking = 'X';
        }else{
            marking = ' ';
        }
    }

    public Character getMarking() {
        return marking;
    }

    public void setMarking(Character marking) {
        this.marking = marking;
    }

    //This function will remove marking from a point
    public void removeMarking() throws Exception {
        if(marking == null){
            throw new Exception("This point is already not marked");
        }
        marking = null;
    }

}