package com.example.sample.utils;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;

import com.example.sample.R;
import com.example.sample.loading.RotateLoading;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;


public class UiUtils {
    private static int BLOCKS = 128;

    public static Uri getImageUri(Context mContext, Bitmap mBitmap) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        mBitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(mContext.getContentResolver(), mBitmap, "Title", null);
        return Uri.parse(path);
    }

    public static String getFileExtension(String filePath) {
        if (TextUtils.isEmpty(filePath)) {
            return "";
        }
        String[] fileArray = filePath.split(".");
//        String extension = fileArray[(fileArray.length - 1)];
        String ext = "";
        int pos = filePath.lastIndexOf('.');
        if (pos != -1)
            ext = filePath.substring(filePath.lastIndexOf('.') + 1,
                    filePath.length());
        else return "*/*";
        return ext;
    }

    public static void showToast(Context context, String input) {
        Toast.makeText(context, input, Toast.LENGTH_SHORT).show();
    }

//    public static void showSnackBar(View view, String message, int length) {
//        Snackbar snackbar = Snackbar.make(view, message, length);
//        View v = snackbar.getView();
//        TextView textView = (TextView) v.findViewById(android.support.design.R.id.snackbar_text);
//        textView.setTextColor(Color.WHITE);
//        textView.setMaxLines(4);
//        snackbar.show();
//    }
//
//    public static void showSnackBarWithAction(View view, String message, int length, String action, View.OnClickListener actionClickListener) {
//        Snackbar snackbar = Snackbar.make(view, message, length);
//        View v = snackbar.getView();
//        TextView textView = (TextView) v.findViewById(android.support.design.R.id.snackbar_text);
//        textView.setTextColor(Color.WHITE);
//        textView.setMaxLines(4);
//        snackbar.setAction(action, actionClickListener);
//        snackbar.show();
//    }
//
//    public static void showSnackBarWithAction(View view, int messageResId, int length, int actionResId, View.OnClickListener actionClickListener) {
//        Snackbar snackbar = Snackbar.make(view, messageResId, length);
//        View v = snackbar.getView();
//        TextView textView = (TextView) v.findViewById(android.support.design.R.id.snackbar_text);
//        textView.setTextColor(Color.WHITE);
//        textView.setMaxLines(4);
//        snackbar.setAction(actionResId, actionClickListener);
//        snackbar.show();
//    }
//
//    public static void showSnackBar(View view, int message, int length) {
//        Snackbar snackbar = Snackbar.make(view, message, length);
//        View v = snackbar.getView();
//        TextView textView = (TextView) v.findViewById(android.support.design.R.id.snackbar_text);
//        textView.setTextColor(Color.WHITE);
//        textView.setMaxLines(4);
//        snackbar.show();
//    }
//
//    public static void showToast(AppCompatActivity mActivity, String message) {
//        Toast.makeText(mActivity, message, Toast.LENGTH_SHORT).show();
//    }
//
//    public static AlertDialog createSyncErrorDialog(Context context, String title, String message, DialogInterface.OnClickListener onClickListener) {
//        return createAlertDialog(context, title, message, "Dismiss", onClickListener);
//    }
//
//    public static AlertDialog createRemoveItemDialog(Context context, String title, String message, String btnText, DialogInterface.OnClickListener onClickListener) {
//        return createAlertDialog(context, title, message, btnText, onClickListener);
//    }

    public static AlertDialog createAlertDialog(Context context, String title, String message, String buttonText, DialogInterface.OnClickListener onClickListener) {
        AlertDialog alertDialog = new AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(message)
                .setCancelable(true)
                .setPositiveButton(buttonText, onClickListener)
                .create();
        if (TextUtils.isEmpty(title)) {
            alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        }
        return alertDialog;
    }


    public static AlertDialog showLoadingAlertDialog(Context context) {
      try {
          AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
          View dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_loading, null);
          dialogBuilder.setView(dialogView);

          RotateLoading rotateLoading = dialogView.findViewById(R.id.rotateView);
          rotateLoading.start();

          AlertDialog alertDialog = dialogBuilder.create();
          alertDialog.setCancelable(false);

          alertDialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
          alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
          return alertDialog;
      }catch (Exception e){

      }
        return null;
    }


    /*Email valid*/
    public static boolean isEmailValid(String email) {
        String expression = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static String encodeimage(Bitmap bitmap) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
            byte[] b = baos.toByteArray();
            String encodedImage = Base64.encodeToString(b, Base64.DEFAULT);
            return "data:image/gif;base64," + encodedImage.replace("\n", "");
        } catch (Exception e) {
            return "";
        }
    }


    public static byte[] decryptByPrivateKey(byte[] data, String key)
            throws Exception {

        byte[] keyBytes = decryptBASE64(key);

        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");

        Key privateKey = keyFactory.generatePrivate(pkcs8KeySpec);

        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);

        return cipher.doFinal(data);
    }

    public static byte[] decryptBASE64(String key) throws Exception {
        return Base64.decode(key, Base64.DEFAULT);
    }


    public static String RSAEncrypt(final String plain) throws NoSuchAlgorithmException, NoSuchPaddingException,
            InvalidKeyException, IllegalBlockSizeException, BadPaddingException {


        String strPublicString = "";

        try {

            KeyFactory keyFac = KeyFactory.getInstance("RSA");
            KeySpec keySpecNew = new X509EncodedKeySpec(Base64.decode(strPublicString.trim().getBytes(), Base64.DEFAULT));
            Key key = keyFac.generatePublic(keySpecNew);


            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] encryptedBytes = cipher.doFinal(plain.getBytes());
            final String encryptedText = new String(Base64.encode(encryptedBytes, Base64.NO_WRAP), "UTF-8");

            return encryptedText;

        } catch (InvalidKeySpecException | UnsupportedEncodingException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static byte[] encryptAES(String seed, String cleartext)
            throws Exception {
        SecretKeySpec skeySpec = new SecretKeySpec(seed.getBytes("UTF-8"),"AES");
//        SecretKeySpec skeySpec = new SecretKeySpec(rawKey, "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
        return cipher.doFinal(cleartext.getBytes("UTF8"));
    }

    public static byte[] decryptAES(String key, byte[] data) throws Exception {
        SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"),"AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, skeySpec);
        return cipher.doFinal(data);
    }

    private static byte[] getRawKey(byte[] seed) throws Exception {
        KeyGenerator kgen = KeyGenerator.getInstance("AES");
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
        sr.setSeed(seed);
        kgen.init(BLOCKS, sr); // 192 and 256 bits may not be available
        SecretKey skey = kgen.generateKey();
        byte[] raw = skey.getEncoded();
        return raw;
    }

    private static byte[] pad(byte[] seed) {
        byte[] nseed = new byte[BLOCKS / 8];
        for (int i = 0; i < BLOCKS / 8; i++)
            nseed[i] = 0;
        for (int i = 0; i < seed.length; i++)
            nseed[i] = seed[i];

        return nseed;
    }

//    public static String decrypt(String strToDecrypt)
//    {
//
//        try
//        {
//            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding");
//            final SecretKeySpec secretKey = new SecretKeySpec(AppConstants.AESEncryptionKey.getBytes("UTF8"), "AES");
//            cipher.init(Cipher.DECRYPT_MODE, secretKey,new IvParameterSpec(new byte[16])); //new IvParameterSpec(new byte[16])
//            byte base64Data[] = Base64.encode(strToDecrypt.getBytes(), Base64.DEFAULT);
//            @SuppressWarnings("unused")
//            String s = base64Data.toString();
//            byte decBytes[] = cipher.doFinal(base64Data);
//            String decStr = new String(decBytes);
//            return decStr;
//        }
//        catch (Exception e)
//        {
//            e.printStackTrace();
//        }
//        return null;
//    }




}
