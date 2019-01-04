package com.example.parkseeun.moca_android.ui.loginJoin

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.example.parkseeun.moca_android.R
import kotlinx.android.synthetic.main.activity_join.*
import okhttp3.MediaType
import okhttp3.RequestBody
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileNotFoundException
import java.io.InputStream

class JoinActivity : AppCompatActivity() {

    //앨범에서 사진 가져오기
    private val REQ_CODE_SELECT_IMAGE = 100
    lateinit var data: Uri
    lateinit var requestManager: RequestManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_join)

        requestManager = Glide.with(this)

        // 프로필 사진 선택 버튼
        ib_join_selectProfile.setOnClickListener {
            changeImage()
        }

        // 취소 버튼
        btn_join_cancel.setOnClickListener {
            finish()
        }
    }

    // 프로필 사진 선택
    fun changeImage() {
        var intent = Intent(Intent.ACTION_PICK)
        intent.type = android.provider.MediaStore.Images.Media.CONTENT_TYPE
        intent.data = android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        startActivityForResult(intent, REQ_CODE_SELECT_IMAGE)
    }

    //사진 관련해서 return 값 처리
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQ_CODE_SELECT_IMAGE) {
            if (resultCode == Activity.RESULT_OK) {
                try {
                    //if(ApplicationController.getInstance().is)
                    this.data = data!!.data //그이미지의Uri를 가져옴
                    Log.v("이미지1", this.data.toString())

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

                    requestManager
                        .load(data.data)
                        //.centerCrop()
                        .into(civ_join_profile)


                } catch (e: Exception) {
                    e.printStackTrace()
                }

            }
        }
    }
}
