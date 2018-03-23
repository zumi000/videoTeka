import java.io.Serializable;
public class Video implements Kolcsonozheto, Serializable {
    String filmCime;
    boolean kikolcsonozve;
    String kolcsonzesiIntervallum;  // egynapos, ketnapos, korlatlan
    int korhatar;  // 0, 12, 16, 18

    public boolean isKikolcsonozve() {
        return kikolcsonozve;
    }

    public int getKorhatar() {
        return korhatar;
    }

    public String getFilmCime() {
        return filmCime;
    }

    public void setKikolcsonozve(boolean kikolcsonozve) {
        this.kikolcsonozve = kikolcsonozve;
    }

    public Video(String filmCime, String kolcsonzesiIntervallum, int korhatar) {
        this.filmCime = filmCime;
        if (kolcsonzesiIntervallum.equals("egynapos") || kolcsonzesiIntervallum.equals("ketnapos") || kolcsonzesiIntervallum.equals("korlatlan")) {
            this.kolcsonzesiIntervallum = kolcsonzesiIntervallum;
        } else {
            System.out.println(filmCime + " című film nem hozható létre, nem megfelelő kölcsönzési intervallum lett megadva hozzá");
        }
        this.korhatar = korhatar;
        if (korhatar == 0 || korhatar == 12 || korhatar == 16 || korhatar == 18) {
            this.korhatar = korhatar;
        } else {
            System.out.println(filmCime + " című film nem hozható létre, nem megfelelő korhatár lett megadva hozzá");
        }
    }

    @Override
    public boolean kikolcsonoz(int eletkor, int meddig) throws HibasKolcsonzesException {
        String meddigakarja;
        if (isKikolcsonozve() == false) {
            if (eletkor < korhatar) {
                throw new HibasKolcsonzesException("Korhatár hiba");
            } else {
                switch (meddig) {
                    case 1:
                        meddigakarja = "egynapos";
                        break;
                    case 2:
                        meddigakarja = "ketnapos";
                        break;
                    default:
                        meddigakarja = "korlatlan";
                        break;
                }
                if (kolcsonzesiIntervallum.equals("korlatlan") || (kolcsonzesiIntervallum.equals("ketnapos") && !meddigakarja.equals("korlatlan")) || (kolcsonzesiIntervallum.equals("egynapos") && meddigakarja.equals("egynapos"))) {
                    setKikolcsonozve(true);
                    System.out.println("Sikeres kölcsönzés");
                    return true;
                } else {
                    throw new HibasKolcsonzesException("Kölcsönzési idő hiba");
                }
            }
        } else {
            System.out.println("A filmet nem tudom kiadni, valaki már kikölcsönözte");
            return false;
        }
    }

    @Override
    public boolean visszahoz() {
        if (isKikolcsonozve() == true) {
            setKikolcsonozve(false);
            return true;
        } else {
            return false;
        }
    }
}
