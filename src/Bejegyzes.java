import java.time.LocalDateTime;

public class Bejegyzes {
    private String szerzo;
    private String tartalom;
    private int likeok;
    private LocalDateTime letrejott;
    private LocalDateTime szerkesztve;

    Bejegyzes(String szerzo, String tartalom) {
        this.szerzo = szerzo;
        this.tartalom = tartalom;
        this.likeok = 0;
        this.letrejott = LocalDateTime.now();
        this.szerkesztve = LocalDateTime.now();
    }
    public String GetSZerzo(){
        return this.szerzo;
    }
    public String GetTartalom(){
        return this.tartalom;
    }
    public void SetTartalom(String tartalom){
        this.tartalom = tartalom;
        this.szerkesztve = LocalDateTime.now();
    }
    public int GetLikok(){
        return this.likeok;
    }
    public LocalDateTime GetLetrejott(){
        return this.letrejott;
    }
    public LocalDateTime GetSzerkesztve(){
        return this.szerkesztve;
    }
    public void Like(){
        this.likeok++;
    }

    //még van itt mit
    @Override
    public String toString() {
        String output = "";
        output += this.szerzo + "-" + this.likeok + "-" + this.letrejott + "\n";
        if (this.szerkesztve != this.letrejott) output += "Szerkesztve: "+ this.szerkesztve + "\n";
        output += "Tartalom: " + this.tartalom + "\n";
        return output;
    }
}
