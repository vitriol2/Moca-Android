package com.example.parkseeun.moca_android.ui.community

import android.app.Activity
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.example.parkseeun.moca_android.R
import kotlinx.android.synthetic.main.activity_community_writereview.*
import org.jetbrains.anko.startActivity
import android.widget.RatingBar
import android.widget.TextView
import android.content.Intent
import android.widget.Toast
import android.content.ClipData
import android.net.Uri
import android.provider.MediaStore
import android.util.Log


class WriteReviewActivity : AppCompatActivity() {
    var PICK_IMAGE_MULTIPLE = 1
    var imageEncoded: String? = null
    var imagesEncodedList: List<String>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_community_writereview)
        SetOnClickListener()
        ratingBar()
    }

    fun ratingBar(){
        val ratingBarCustom = findViewById<View>(R.id.ratingBarCustom) as RatingBar
        ratingBarCustom.onRatingBarChangeListener = object : RatingBar.OnRatingBarChangeListener {
            var ratingBarCustomInfo = findViewById<View>(R.id.ratingBarCustomInfo) as TextView
            override fun onRatingChanged(ratingBar: RatingBar, ratingValue: Float, fromUser: Boolean) {
                val currentProgress = ratingBarCustom.progress
                ratingBarCustomInfo.text = "Current start number is $ratingValue, Progress number is $currentProgress"
            }
        }

    }

    fun SetOnClickListener() {
        txt_addreview_cafeaddress.setOnClickListener {
            startActivity<ReviewSearchLocationActivity>()
        }
        img_addreview_image.setOnClickListener {
//            val intent = Intent()
//            intent.type = "image/*"
//            intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
//            intent.action = Intent.ACTION_GET_CONTENT
//            startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_MULTIPLE)
        }
    }
//
//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        try {
//            // When an Image is picked
//            if (requestCode == PICK_IMAGE_MULTIPLE && resultCode == Activity.RESULT_OK
//                && null != data
//            ) {
//                // Get the Image from data
//
//                val filePathColumn = arrayOf(MediaStore.Images.Media.DATA)
//                imagesEncodedList = ArrayList()
//                if (data.data != null) {
//
//                    val mImageUri = data.data
//
//                    // Get the cursor
//                    val cursor = contentResolver.query(
//                        mImageUri!!,
//                        filePathColumn, null, null, null
//                    )
//                    // Move to first row
//                    cursor!!.moveToFirst()
//
//                    val columnIndex = cursor.getColumnIndex(filePathColumn[0])
//                    imageEncoded = cursor.getString(columnIndex)
//                    cursor.close()
//
//                } else {
//                    if (data.clipData != null) {
//                        val mClipData = data.clipData
//                        val mArrayUri = ArrayList<Uri>()
//                        for (i in 0 until mClipData!!.itemCount) {
//
//                            val item = mClipData.getItemAt(i)
//                            val uri = item.uri
//                            mArrayUri.add(uri)
//                            // Get the cursor
//                            val cursor = contentResolver.query(uri, filePathColumn, null, null, null)
//                            // Move to first row
//                            cursor!!.moveToFirst()
//
//                            val columnIndex = cursor.getColumnIndex(filePathColumn[0])
//                            imageEncoded = cursor.getString(columnIndex)
//                            imagesEncodedList.add(imageEncoded!!)
//                            cursor.close()
//
//                        }
//                        Log.v("LOG_TAG", "Selected Images" + mArrayUri.size)
//                    }
//                }
//            } else {
//                Toast.makeText(
//                    this, "You haven't picked Image",
//                    Toast.LENGTH_LONG
//                ).show()
//            }
//        } catch (e: Exception) {
//            Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG)
//                .show()
//        }
//
//        super.onActivityResult(requestCode, resultCode, data)
//    }
}