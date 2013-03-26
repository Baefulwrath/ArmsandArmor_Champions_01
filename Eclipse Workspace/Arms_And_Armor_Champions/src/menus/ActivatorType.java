package menus;

public enum ActivatorType {
    BUTTON, INPUT, TEXT;
    
    @Override
    public String toString() {
        String s = super.toString();
        return s;
    }
    
    public static ActivatorType parseType(String s){
        ActivatorType AT = BUTTON;
        for(int i = 0; i < ActivatorType.values().length; i++){
            if(ActivatorType.values()[i].toString().equals(s)){
                AT = ActivatorType.values()[i];
                break;
            }
        }
        return AT;
    }
}
