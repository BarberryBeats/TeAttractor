package kg.geektech.teattractor.ui.photo_activity

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.ViewModelProvider
import kg.geektech.teattractor.databinding.ActivityPhotoBinding
import kg.geektech.teattractor.ui.user_activity.UserViewModel

class PhotoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPhotoBinding
    private lateinit var adapter: PhotoAdapter

    private var imageList = arrayListOf<Uri>()

    private val REQUEST_CODE = 707



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPhotoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initAdapter()
        initListener()

    }

    private fun initListener() {
        binding.btnAddPicture.setOnClickListener {
            openGallery()
        }

        binding.btnClear.setOnClickListener{
            adapter.clean()
        }
    }

    private fun initAdapter() {
        adapter = PhotoAdapter()
        binding.rvPictures.adapter = adapter
    }

    private fun openGallery() {

        if (Build.VERSION.SDK_INT < 19) {
            val intent = Intent()
            intent.type = "image/*"
            intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
            intent.action = Intent.ACTION_GET_CONTENT
            startActivityForResult(
                Intent.createChooser(intent, "Choose Pictures"), REQUEST_CODE
            )
        } else {
            val intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
            intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
            intent.addCategory(Intent.CATEGORY_OPENABLE)
            intent.type = "image/*"
            startActivityForResult(intent, REQUEST_CODE);
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_CODE) {

            if (data?.clipData != null) {
                val count = data.clipData?.itemCount

                if (count != null) {
                    for (i in 0 until count) {
                        val imageUri: Uri = data.clipData!!.getItemAt(i).uri
                        imageList.add(imageUri)
                    }
                    Log.d("Ray", "imageList $imageList")
                    adapter.setImageList(imageList)

                }

            } else if (data?.data != null) {

                val imageUri: Uri? = data.data

                imageList.add(imageUri!!)
                adapter.setImageList(imageList)

            }
        }
    }


}