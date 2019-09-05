
package com.reactlibrary;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Callback;
import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.telecom.Call;
import android.util.Log;
import android.widget.Toast;

import com.facebook.react.bridge.ActivityEventListener;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;


import java.io.File;
import java.util.HashMap;
import java.util.Map;
import android.content.Intent;
import javax.annotation.Nullable;
import android.content.pm.PackageManager;
import android.content.ComponentName;
import android.app.Activity;

import org.json.JSONObject;

public class RNFilePickerModule extends ReactContextBaseJavaModule {

  private final ReactApplicationContext reactContext;

  public RNFilePickerModule(ReactApplicationContext reactContext) {
    super(reactContext);
    this.reactContext = reactContext;
  }

  @Override
  public String getName() {
    return "RNFilePicker";
  }


  private Callback onDone;
  private Callback onCancel;

  @ReactMethod
  public void openFileSelec(final Callback onSuccess, final Callback onCancels) {
    Activity activity = getCurrentActivity();
    if(null != activity) {
      Intent intent =new Intent(Intent.ACTION_GET_CONTENT);
      intent.setType("*/*");//无类型限制
      intent.addCategory(Intent.CATEGORY_OPENABLE);
      activity.startActivityForResult(intent, 1);

      this.onDone = onSuccess;
      this.onCancel = onCancels;
    }
  }

  public String getPath(final Context context, final Uri uri) {


    final boolean isKitKat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;


    // DocumentProvider
    if (isKitKat && DocumentsContract.isDocumentUri(context, uri)) {
      // ExternalStorageProvider
      if (isExternalStorageDocument(uri)) {
        final String docId = DocumentsContract.getDocumentId(uri);
        final String[] split = docId.split(":");
        final String type = split[0];


        if ("primary".equalsIgnoreCase(type)) {
          return Environment.getExternalStorageDirectory() + "/" + split[1];
        }
      }
      // DownloadsProvider
      else if (isDownloadsDocument(uri)) {


        final String id = DocumentsContract.getDocumentId(uri);
        final Uri contentUri = ContentUris.withAppendedId(
                Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));


        return getDataColumn(context, contentUri, null, null);
      }
      // MediaProvider
      else if (isMediaDocument(uri)) {
        final String docId = DocumentsContract.getDocumentId(uri);
        final String[] split = docId.split(":");
        final String type = split[0];


        Uri contentUri = null;
        if ("image".equals(type)) {
          contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        } else if ("video".equals(type)) {
          contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
        } else if ("audio".equals(type)) {
          contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        }


        final String selection = "_id=?";
        final String[] selectionArgs = new String[]{split[1]};


        return getDataColumn(context, contentUri, selection, selectionArgs);
      }
    }
    // MediaStore (and general)
    else if ("content".equalsIgnoreCase(uri.getScheme())) {
      return getDataColumn(context, uri, null, null);
    }
    // File
    else if ("file".equalsIgnoreCase(uri.getScheme())) {
      return uri.getPath();
    }
    return null;
  }

  public String getDataColumn(Context context, Uri uri, String selection,
                              String[] selectionArgs) {


    Cursor cursor = null;
    final String column = "_data";
    final String[] projection = {column};


    try {
      cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs,
              null);
      if (cursor != null && cursor.moveToFirst()) {
        final int column_index = cursor.getColumnIndexOrThrow(column);
        return cursor.getString(column_index);
      }
    } finally {
      if (cursor != null)
        cursor.close();
    }
    return null;
  }


  /**
   * @param uri The Uri to check.
   * @return Whether the Uri authority is ExternalStorageProvider.
   */
  public boolean isExternalStorageDocument(Uri uri) {
    return "com.android.externalstorage.documents".equals(uri.getAuthority());
  }


  /**
   * @param uri The Uri to check.
   * @return Whether the Uri authority is DownloadsProvider.
   */
  public boolean isDownloadsDocument(Uri uri) {
    return "com.android.providers.downloads.documents".equals(uri.getAuthority());
  }


  /**
   * @param uri The Uri to check.
   * @return Whether the Uri authority is MediaProvider.
   */
  public boolean isMediaDocument(Uri uri) {
    return "com.android.providers.media.documents".equals(uri.getAuthority());
  }


  /**
   * 获取文件格式名
   */
  public static String getFormatName(String fileName) {
    //去掉首尾的空格
    fileName = fileName.trim();
    String s[] = fileName.split("\\.");
    if (s.length >= 2) {
      return s[s.length - 1];
    }
    return "";
  }

  private class ActivityEventListener implements com.facebook.react.bridge.ActivityEventListener {

    @Override
    public void onActivityResult(Activity activity, int requestCode, int resultCode, Intent data) {
      if(data == null) {
        return;
      }
      if(requestCode == 1) {
        if (resultCode == Activity.RESULT_OK) {
          Uri uri = data.getData();
          if ("file".equalsIgnoreCase(uri.getScheme())){//使用第三方应用打开
            path = uri.getPath();
            return;
          }
          if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {//4.4以后
            path = getPath(getCurrentActivity(), uri);
            File file = new File(path);
            String name = file.getName();
            String formatStr = getFormatName(name);
            if(onDone != null) {
              onDone.invoke(name,uri.toString(),formatStr);
            }
          }
        }
      }

    }

    @Override
    public void onNewIntent(Intent intent) {

    }
  }
}