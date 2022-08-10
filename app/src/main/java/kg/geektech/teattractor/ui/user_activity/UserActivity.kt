package kg.geektech.teattractor.ui.user_activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import kg.geektech.teattractor.ui.photo_activity.PhotoActivity
import kg.geektech.teattractor.databinding.ActivityMainBinding
import kg.geektech.teattractor.ext.loadImage

class UserActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var viewModel: UserViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViewModel()
        viewModel.jsonToGson()
        observeViewModel()
        clickListener()

    }

    private fun clickListener() {
        binding.btnNextActivity.setOnClickListener {
            val intent = Intent(this, PhotoActivity::class.java)
            startActivity(intent)
        }
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this).get(UserViewModel::class.java)
    }

    private fun observeViewModel() {
        viewModel.getUserInfo().observe(this) {
            binding.tvName.text = it.firstName
            binding.tvLastName.text = it.secondName
            when (it.education) {
                1 -> binding.tvEducation.text = "High School"
                2 -> binding.tvEducation.text = "bachelor"
                3 -> binding.tvEducation.text = "master"
                4 -> binding.tvEducation.text = "doctoral"
            }
            binding.tvPosition.text = it.company[1].position

            binding.imgUserAvatar.loadImage(it.photo)
        }
    }

}