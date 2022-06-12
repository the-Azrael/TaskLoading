import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class Main {
    private static final String GAMES_DIR = "D://Java//Games";
    private static final String SAVE_DIR_PATH = GAMES_DIR + "//savegames";
    private static final String ZIP_PATH = SAVE_DIR_PATH + "//saves.zip";
    private static final String SAVE1_PATH = SAVE_DIR_PATH + "//save1.dat";
    private static final String SAVE2_PATH = SAVE_DIR_PATH + "//save2.dat";
    private static final String SAVE3_PATH = SAVE_DIR_PATH + "//save3.dat";

    public static void openZip(String zipPath, String loadPath) {
        File zipFile = new File(zipPath);
        ZipEntry zipEntry;
        String unzipFile;
        try {
            FileInputStream fis = new FileInputStream(zipFile);
            ZipInputStream zis = new ZipInputStream(fis);
            while ((zipEntry = zis.getNextEntry()) != null) {
                unzipFile = zipEntry.getName();
                FileOutputStream fos = new FileOutputStream(loadPath + "//" + unzipFile);
                for (int b = zis.read(); b != -1; b = zis.read()) {
                    fos.write(b);
                }
                fos.flush();
                zis.closeEntry();
                fos.close();
            }
            fis.close();
            zis.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static GameProgress openProgress(String filePath) {
        File file = new File(filePath);
        try {
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);
            GameProgress resultObject = (GameProgress) ois.readObject();
            ois.close();
            fis.close();
            return resultObject;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static void main(String[] args) {
        openZip(ZIP_PATH, SAVE_DIR_PATH);
        GameProgress load1 = openProgress(SAVE1_PATH);
        System.out.println(load1);
        GameProgress load2 = openProgress(SAVE2_PATH);
        System.out.println(load2);
        GameProgress load3 = openProgress(SAVE3_PATH);
        System.out.println(load3);
    }
}
