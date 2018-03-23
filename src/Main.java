import java.io.*;

public class Main {

    public static void main(String[] args) {
        VideoTeka kolcsonzo = new VideoTeka();
        BufferedReader reader = null;
        StringBuilder result = new StringBuilder();
        int tetelek;
        try {
            reader = new BufferedReader(new FileReader(new java.io.File("./input.txt")));
            String line = null;
            String list = null;
            line = reader.readLine();
            tetelek = Integer.parseInt(line);
            for (int i = 0; i < tetelek; i++) {
                while ((list = reader.readLine()) != null) {
                    String[] filmek = list.split("\\W");
                    if (filmek.length < 3) {
                        VideoTeka.OktatoVideos.put(filmek[0], new OktatoVideo(filmek[0], filmek[1]));
                    }
                    if (filmek.length == 3) {
                        VideoTeka.Videos.put(filmek[0], new Video(filmek[0], filmek[1], Integer.parseInt(filmek[2])));
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } // end of reading
        System.out.println(VideoTeka.OktatoVideos.size());
        System.out.println(VideoTeka.Videos.size());
        try {
            (VideoTeka.keres("Szikla", false)).kikolcsonoz(20, 1);
        } catch (NotFoundException e) {
            e.printStackTrace();
        } catch (HibasKolcsonzesException e) {
            e.printStackTrace();
        }

        try {
            FileOutputStream fileOut =
                    new FileOutputStream("./videoTeka.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(VideoTeka.OktatoVideos);
            out.writeObject(VideoTeka.Videos);
            out.close();
            fileOut.close();
            System.out.printf("Serialized data is saved in videoTeka.ser");
        } catch (IOException i) {
            i.printStackTrace();
        }

    }
}

