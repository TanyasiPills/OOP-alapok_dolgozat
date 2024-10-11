import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Bejegyzes first = new Bejegyzes("Pista","Hol volt hol nem volt");
        Bejegyzes second = new Bejegyzes("Pista","Volt egyszer egy pista");

        List<Bejegyzes> list = new ArrayList<Bejegyzes>();
        list.add(first);
        list.add(second);

        Scanner in = new Scanner(System.in);


        boolean need = true;
        int bekezdesek = 0;
        while (need){
            System.out.println("Add meg a felvevendő bejegyzések számát");
            String vmi = in.nextLine();
            try{
                bekezdesek = Integer.parseInt(vmi);
                need = false;
            }
            catch (Exception e){
                System.out.println("Nem megfelelő bemenet");
            }
        }

        need = true;
        for (int i = 0; i < bekezdesek; i++) {
            System.out.println("Add meg a szerző nevét:");
            String szerzo = in.nextLine();
            System.out.println("Add meg a bejegyzés tartalmát:");
            String bejegyzes = in.nextLine();
            list.add(new Bejegyzes(szerzo,bejegyzes));
        }
        try (BufferedReader br = new BufferedReader(new FileReader("bejegyzesek.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(";");
                list.add(new Bejegyzes(values[0],values[1]));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


        Random rnd = new Random();
        int neededLikes = list.size()*20;
        for (int i = 0; i < neededLikes; i++) {
            list.get(rnd.nextInt(list.size())).Like();
        }


        System.out.println("Adj meg egy szöveget:");
        String szoveg = in.nextLine();
        list.get(1).SetTartalom(szoveg);


        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i).toString());
        }

        Bejegyzes best = list.get(0);
        for (int i = 0; i < list.size(); i++) {
            if(list.get(i).GetLikok() > best.GetLikok()) best = list.get(i);
        }
        System.out.println("A leg like-oltabb bejegyzés like-jainak száma: "+best.GetLikok());

        if(list.stream().anyMatch(e -> e.GetLikok() > 35)) System.out.println("Van olyan bejegyzés aminek több mint 35 like-ja van");
        else System.out.println("Nincs olyan bejegyzés aminek több mint 35 like-ja van");


        int count = (int) list.stream().filter(e -> e.GetLikok() < 15).count();
        System.out.println(count+" bejegyzésnek van kevesebb mint 15 like-ja");


        list.sort((s1,s2) -> s1.GetLikok() - s2.GetLikok());


        File myObj = new File("bejegyzesek_rendezet.txt");
        if(myObj.exists()) myObj.delete();
        myObj.createNewFile();
        FileWriter myWriter = new FileWriter("bejegyzesek_rendezet.txt");
        for (int i = 0; i < list.size(); i++) {
            myWriter.write(list.get(i).toString());
        }
        myWriter.close();
    }
}