import java.util.HashMap;
import java.util.Map;

public class VideoTeka implements Kolcsonozheto {
    public static Map<String, Kolcsonozheto> Videos = new HashMap<>();
    public static Map<String, Kolcsonozheto> OktatoVideos = new HashMap<>();

    public VideoTeka() {
    }

    public void hozzaad(Video newVideo) {
        if (newVideo.getClass() == Videos.getClass()) {
            Videos.put(newVideo.getFilmCime(), newVideo);
        }

        if (newVideo.getClass() == OktatoVideos.getClass()) {
            OktatoVideos.put(newVideo.getFilmCime(), newVideo);
        }
    }

    public static Kolcsonozheto keres(String filmcim, boolean oktatoe) throws NotFoundException {
        if (oktatoe && OktatoVideos.containsKey(filmcim)) {
            return OktatoVideos.get(filmcim);
            }
        if (!oktatoe && Videos.containsKey(filmcim)) {
            return Videos.get(filmcim);
        } else {
            try {
                throw new NotFoundException();
            } catch (NotFoundException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public boolean kikolcsonoz(int eletkor, int meddig) {
        return false;
    }

    @Override
    public boolean visszahoz() {
        return false;
    }
}