public final class OktatoVideo extends Video {
    public OktatoVideo(String filmCime, String kolcsonzesiIntervallum) {
        super(filmCime, kolcsonzesiIntervallum, 0);
    }



    @Override
    public boolean kikolcsonoz(int eletkor, int meddig) throws HibasKolcsonzesException {
        String meddigakarja;
        if (isKikolcsonozve() == false) {
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
        } else {
            System.out.println("A filmet nem tudom kiadni, valaki már kikölcsönözte");
            return false;
        }
    }
}
