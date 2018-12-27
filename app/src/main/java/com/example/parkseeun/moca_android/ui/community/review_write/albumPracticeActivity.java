package com.example.parkseeun.moca_android.ui.community.review_write;

import android.Manifest;
import android.content.ClipData;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import com.example.parkseeun.moca_android.R;

import java.util.ArrayList;
import java.util.List;

public class albumPracticeActivity extends AppCompatActivity {
    private ImageView img_addreview_image;
    int PICK_IMAGE_MULTIPLE = 1;
    public static final int PICK_GROUP_FROM_ALBUM = 2;
    String imageEncoded;
    List<String> imagesEncodedList;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_community_writereview);
        img_addreview_image = findViewById(R.id.rv_photo_review);
        img_addreview_image.setOnClickListener(new View.OnClickListener() {

            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                checkVerify(PICK_GROUP_FROM_ALBUM);
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent,"Select Picture"), PICK_IMAGE_MULTIPLE);

//                Intent intent = new Intent(Intent.ACTION_PICK);
//                intent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//                intent.setType("image/*");
//
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//                    checkVerify(PICK_GROUP_FROM_ALBUM);
//                }
//                else {
//                    startActivityForResult(intent, PICK_GROUP_FROM_ALBUM);
//                }
            }
        });
    }

    //권한 체크
    @RequiresApi(api = Build.VERSION_CODES.M)
    public void checkVerify(int requestCode) {
        if (
                checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED ||
                        checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED ||
                        checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                ) {
            // Should we show an explanation?
            if (shouldShowRequestPermissionRationale(Manifest.permission.READ_EXTERNAL_STORAGE)) {
                // ...
            }
            else if(shouldShowRequestPermissionRationale(Manifest.permission.WRITE_EXTERNAL_STORAGE))
            {

            }
            requestPermissions(new String[]{Manifest.permission.CAMERA,
                            Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    1);
        }
        else {
            Intent intent = new Intent(Intent.ACTION_PICK);

            //   intent.setType(MediaStore.Images.Media.CONTENT_TYPE);
            intent.setData(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            intent.setType("image/*");
            startActivityForResult(intent, requestCode);
        }
    }
        @Override
        protected void onActivityResult(int requestCode, int resultCode, Intent data) {
            try {
//                if(requestCode == )
                // When an Image is picked
                if (requestCode == PICK_IMAGE_MULTIPLE && resultCode == RESULT_OK
                        && null != data) {
                    // Get the Image from data

                    String[] filePathColumn = { MediaStore.Images.Media.DATA };
                    imagesEncodedList = new ArrayList<String>();
                    if(data.getData()!=null){

                        Uri mImageUri=data.getData();

                        // Get the cursor
                        Cursor cursor = getContentResolver().query(mImageUri,
                                filePathColumn, null, null, null);
                        // Move to first row
                        cursor.moveToFirst();

                        int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                        imageEncoded  = cursor.getString(columnIndex);
                        cursor.close();

                    } else {
                        if (data.getClipData() != null) {
                            ClipData mClipData = data.getClipData();
                            ArrayList<Uri> mArrayUri = new ArrayList<Uri>();
                            for (int i = 0; i < mClipData.getItemCount(); i++) {
                                ClipData.Item item = mClipData.getItemAt(i);
                                Uri uri = item.getUri();
                                mArrayUri.add(uri);
                                // Get the cursor
                                Cursor cursor = getContentResolver().query(uri, filePathColumn, null, null, null);
                                // Move to first row
                                cursor.moveToFirst();

                                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                                imageEncoded  = cursor.getString(columnIndex);
                                imagesEncodedList.add(imageEncoded);
                                    cursor.close();

                            }
                            Log.v("LOG_TAG", "Selected Images" + mArrayUri.size());
                        }
                    }
                } else {
                    Toast.makeText(this, "You haven't picked Image",
                            Toast.LENGTH_LONG).show();
                }
            } catch (Exception e) {
                Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG)
                        .show();
            }
            super.onActivityResult(requestCode, resultCode, data);
        }
    }



