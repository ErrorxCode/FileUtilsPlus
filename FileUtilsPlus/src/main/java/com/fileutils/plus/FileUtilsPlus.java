package com.fileutils.plus;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.util.Log;
import android.widget.Toast;
import androidx.core.app.ActivityCompat;
import net.lingala.zip4j.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import org.apache.commons.io.FileUtils;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;


public class FileUtilsPlus {

protected static OneTimeEventInterface EventInterface;
protected static OneTimeDialogInterface DialogInterface;



    // Requesting storage permission

    public static void manageStoragePermission(Activity activity) {
        if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);
        } else {
            Toast.makeText(activity, "Permission Granted", Toast.LENGTH_SHORT).show();
        }
    }


    public static boolean copyFromAssets(Activity activity ,String filename, String path) {
        File dir = new File(path);
        if (!dir.isDirectory()) {
            if (!dir.mkdir()) {
                Toast.makeText(activity, "Path not found", Toast.LENGTH_SHORT).show();
            }
        }
        try {
            InputStream in = activity.getAssets().open(filename);
            File outFile = new File(path, filename);
            OutputStream out = new FileOutputStream(outFile);
            copyFiles(in, out);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }



    private static void copyFiles(InputStream in, OutputStream out ) throws IOException {
        byte[] buffer = new byte[1024];
        int read;
        while ((read = in.read(buffer)) != -1) {
            out.write(buffer, 0, read);
        }
        if (out != null) {
            try {
                out.close();
                in.close();
                out.flush();
            } catch (IOException e) {
               e.printStackTrace();
            }
        }
    }



    // Copy & moving operations

    public static boolean copy(String filepath, String to) {
        if (filepath.endsWith("/")) {
            filepath = filepath.substring(0, filepath.lastIndexOf("/"));
        }
        String filename = filepath.substring(filepath.lastIndexOf("/"));
        if (!to.endsWith("/")) {
            to = to + "/" + filename;
        }
        File fromDir = new File(filepath);
        File toDir = new File(to);
        try {
            InputStream in = new FileInputStream(fromDir);
            OutputStream out = new FileOutputStream(toDir);
            copyFiles(in, out);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }


    public static boolean copyAllAssets(Activity activity ,String path) {
        boolean success = false;
        AssetManager assetManager = activity.getAssets();
        String[] files = null;
        try {
            files = assetManager.list("");
        } catch (IOException e) {
            Log.e("tag", "Failed to get asset file list.", e);
        }
        if (files != null) {
            for (String filename : files) {
                InputStream in = null;
                OutputStream out = null;
                try {
                    in = assetManager.open(filename);
                    File outFile = new File(path, filename);
                    out = new FileOutputStream(outFile);
                    copyFiles(in, out);
                    success = true;
                } catch (IOException e) {
                    Log.e("tag", "Failed to copy asset file: " + filename, e);
                    success = false;
                } finally {
                    if (in != null) {
                        try {
                            in.close();
                        } catch (IOException e) {
                            // NOOP
                        }
                    }
                    if (out != null) {
                        try {
                            out.close();
                        } catch (IOException e) {
                            // NOOP
                        }
                    }
                }
            }
        }
        return success;
    }

    public static boolean moveFile(String filename, String src, String dest) {
        if (!src.endsWith(filename)) {
            src = src + "/" + filename;
        }

        if (!dest.endsWith(filename)) {
            dest = dest + "/" + filename;
        }

        File file = new File(src);
        return file.renameTo(new File(dest));
    }



    public static void rename(String srcPath, String renameTo) {
        if (srcPath.endsWith("/")) {
            srcPath = srcPath.substring(0, srcPath.lastIndexOf("/"));
        }
        String filename = srcPath.substring(srcPath.lastIndexOf("/"));
        File file = new File(srcPath);
        srcPath = srcPath.substring(0, srcPath.lastIndexOf("/"));
        file.renameTo(new File(srcPath + "/" + renameTo));
    }


    public static boolean delete(Activity activity,String filepath) {
        File file = new File(filepath);
        if (file.isDirectory()) {
            try {
                FileUtils.deleteDirectory(file);
                return true;
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        } else if (file.isFile()) {
            return file.delete();
        } else {
            Toast.makeText(activity, "Path is not a file or a directory", Toast.LENGTH_SHORT).show();
            return false;
        }
    }


// UnZip Library

    public static boolean UnZip(String source, String path) {
        ZipFile zipFile = new ZipFile(source);
        try {
            zipFile.extractAll(path);
            return true;
        } catch (ZipException e) {
            e.printStackTrace();
            return false;
        }
    }



    public static boolean writeFile(String filepath, String data,boolean overwrite){
        PrintWriter printWriter;
        try {
            printWriter = new PrintWriter(new FileWriter(new File(filepath),!overwrite));
            printWriter.println(data);
            printWriter.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }


    public static String readFile(String filepath){
        String data = "";
        try {
            data = FileUtils.readFileToString(new File(filepath),"UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    public static void eraseFile(String filepath,String what){
        String data = readFile(filepath);
        data = data.replace(what,"");
        File file = new File(filepath);
        file.delete();
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        writeFile(filepath,data,true);
    }


    public static void oneTimeEvent(Activity activity,OneTimeEventInterface eventInterface){
        EventInterface = eventInterface;
        String randomName = "Pref_" + (System.currentTimeMillis() - 1617057983778L);
        SharedPreferences.Editor editor = activity.getSharedPreferences(randomName, Context.MODE_PRIVATE).edit();
        boolean doAgain = activity.getSharedPreferences(randomName,Context.MODE_PRIVATE).getBoolean("isEventDone",false);
        if (!doAgain){
            boolean bool = eventInterface.OneTimeEvent();
            editor.putBoolean("isEventDone",bool).apply();
        }
    }




    public static void oneTimeDialog(Activity activity ,OneTimeDialogInterface dialogInterface){
        DialogInterface = dialogInterface;
        String randomName = "Pref_" + (System.currentTimeMillis() - 1617037983778L);
        SharedPreferences.Editor editor = activity.getSharedPreferences(randomName, Context.MODE_PRIVATE).edit();
        boolean showAgain = activity.getSharedPreferences(randomName,Context.MODE_PRIVATE).getBoolean("isDialogShown",false);
        if (!showAgain){
            boolean bool = dialogInterface.OneTimeDialog(new AlertDialog.Builder(activity));
            editor.putBoolean("isDialogShown",bool).apply();
        }
    }




    public interface OneTimeEventInterface {
        boolean OneTimeEvent();
    }

    public interface OneTimeDialogInterface {
        boolean OneTimeDialog(AlertDialog.Builder builder);
    }

}


