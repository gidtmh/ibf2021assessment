package assessment;

public class InputHandler {

    public Integer getportNumber(String commands, String argument, Integer port){
        port = Integer.valueOf(argument.trim());
        return port;
    }
    
}
