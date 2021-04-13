import java.io.Serializable;

public class Trama implements Serializable
{
    private int id; 
    private String info;
    //private byte[] bytes;

    public Trama(int i, String inf){
        this.id = i;
        this.info = inf;
    }

    public Trama(){
        this.id = 0;
        this.info = "";
    }

    public int getID(){ return this.id; }
    public String getInfo(){ return this.info; }

    public void setID(int i){ this.id = i; }
    public void setInfo(String inf){ this.info = inf; }

    public int size(){ return this.id; }
}