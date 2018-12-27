package com.example.parkseeun.moca_android.ui.community.review_write

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import com.example.parkseeun.moca_android.R
import kotlinx.android.synthetic.main.activity_community_writereview.*
import org.jetbrains.anko.startActivity
import android.widget.RatingBar
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.example.parkseeun.moca_android.ui.community.review_write.adapter.PhotoAdapter
import com.example.parkseeun.moca_android.ui.community.review_write.data.PhotoData
import com.example.parkseeun.moca_android.ui.community.review_write.data.ReviewImageData
import okhttp3.MediaType
import okhttp3.RequestBody
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileNotFoundException
import java.io.InputStream

class WriteReviewActivity : AppCompatActivity() {
//    var PICK_IMAGE_MULTIPLE = 1
//    var imageEncoded: String? = null
//    var imagesEncodedList = ArrayList<String>()
private val REQ_CODE_SELECT_IMAGE = 100
    lateinit var data : Uri
    var btn_num = 0


    lateinit var photoItems : ArrayList<PhotoData>
    lateinit var totalItems : ArrayList<PhotoData>
    lateinit var PhotoAdapter: PhotoAdapter
    lateinit var requestManager: RequestManager
    lateinit var reviewImageItems: ArrayList<ReviewImageData>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_community_writereview)
        reviewImageItems=ArrayList()
        var flag = 0

        requestManager = Glide.with(this)
        photoItems = ArrayList()

        var displayMetrics = applicationContext.resources.displayMetrics //화면 해상도를 구함
        var width = displayMetrics.widthPixels
        var height = displayMetrics.heightPixels

        img_addreview_image.setOnClickListener {
            //            val intent = Intent()
//            intent.type = "image/*"
//            intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
//            intent.action = Intent.ACTION_GET_CONTENT
//            startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_MULTIPLE)
            img_addreview_image.visibility=View.GONE
                changeImage()

        }


        SetOnClickListener()
        ratingBar()

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQ_CODE_SELECT_IMAGE) {
            if (resultCode == Activity.RESULT_OK) {
                try {
                    //if(ApplicationController.getInstance().is)
                    this.data = data!!.data //그이미지의Uri를 가져옴
                    Log.v("이미지", this.data.toString())

                    val options = BitmapFactory.Options()

                    var input: InputStream? = null // here, you need to get your context.
                    try {
                        input = contentResolver.openInputStream(this.data)
                        Log.v("이미지", this.data.toString())
                    } catch (e: FileNotFoundException) {
                        e.printStackTrace()
                    }

                    val bitmap = BitmapFactory.decodeStream(input, null, options) // InputStream 으로부터 Bitmap 을 만들어 준다.
                    val baos = ByteArrayOutputStream()
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 20, baos)
                    val photoBody = RequestBody.create(MediaType.parse("image/jpg"), baos.toByteArray())
                    val photo = File(this.data.toString()) // 가져온 파일의 이름을 알아내려고 사용합니다

                    ///RequestBody photoBody = RequestBody.create(MediaType.parse("image/jpg"), baos.toByteArray());
                    // MultipartBody.Part 실제 파일의 이름을 보내기 위해 사용!!

                    //이거 서버에 보내줄때 필요 image = MultipartBody.Part.createFormData("photo", photo.name, photoBody) //여기의 photo는 키값의 이름하고 같아야함

                    //body = MultipartBody.Part.createFormData("image", photo.getName(), profile_pic);

                    photoItems.add(PhotoData(data.data))
                    reviewImageItems.add(ReviewImageData(data.data.toString()))

                    PhotoAdapter = PhotoAdapter(photoItems,requestManager)
                    rv_photo_review.layoutManager= LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
                    rv_photo_review.adapter = PhotoAdapter

                } catch (e: Exception) {
                    e.printStackTrace()
                }

            }
        }
    }


fun changeImage(){
    var intent = Intent(Intent.ACTION_PICK)
    intent.type = android.provider.MediaStore.Images.Media.CONTENT_TYPE
    intent.data = android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI
    startActivityForResult(intent,REQ_CODE_SELECT_IMAGE)
}
    fun ratingBar() {
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

    }

 //   override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        try {
//            // When an Image is picked
//            if (requestCode == PICK_IMAGE_MULTIPLE && resultCode == Activity.RESULT_OK && null != data) {
//                // Get the Image from data
//
//                val filePathColumn = arrayOf(MediaStore.Images.Media.DATA)
////                imagesEncodedList = ArrayList<String>()
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
//                }
//                else {
////                    imagesEncodedList = ArrayList<String>()
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
  //  }
}