package up.mash.gourmet_mash_up.data.local;

import java.io.File;

public class Stamp {

    private String where;
    private String what;
    private String how;
    private boolean good;
    private File imageFile;


    private static Stamp instance;

    public static Stamp getIntance() {
        if (instance == null) {
            instance = new Stamp();
        }
        return instance;
    }

    private Stamp() {
        this.where = "";
        this.what = "";
        this.how = "";
        this.good = false;
    }

    public void clear() {
        this.where = "";
        this.what = "";
        this.how = "";
        this.good = false;
        this.imageFile.delete();
    }

    public File getImageFile() {
        return imageFile;
    }

    public void setImageFile(File imageFile) {
        this.imageFile = imageFile;
    }

    public String getWhere() {
        return where;
    }

    public void setWhere(String where) {
        this.where = where;
    }

    public String getWhat() {
        return what;
    }

    public void setWhat(String what) {
        this.what = what;
    }

    public String getHow() {
        return how;
    }

    public void setHow(String address) {
        this.how = address;
    }

    public boolean isGood() {
        return good;
    }

    public void setGood(boolean good) {
        this.good = good;
    }
}
