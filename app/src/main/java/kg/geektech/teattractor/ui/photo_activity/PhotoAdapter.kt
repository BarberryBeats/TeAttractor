package kg.geektech.teattractor.ui.photo_activity

import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import kg.geektech.teattractor.databinding.ItemPicturesBinding
import kg.geektech.teattractor.ext.loadImage
import kg.geektech.teattractor.ui.diff_utils.PicturesListDiffCallBack

class PhotoAdapter:
    RecyclerView.Adapter<PhotoAdapter.ViewHolder>() {

    private var imagesList = arrayListOf<Uri>()


    internal fun setImageList(imagesList: ArrayList<Uri>) {
        this.imagesList = imagesList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoAdapter.ViewHolder {
        val binding =
            ItemPicturesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PhotoAdapter.ViewHolder, position: Int) {
        holder.onBind(imagesList[position])

    }

    override fun getItemCount() = imagesList.size
    fun clean() {
        imagesList.clear()
        notifyDataSetChanged()
    }


    inner class ViewHolder(private val binding: ItemPicturesBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(i: Uri) {
            Log.d("Ray", i.toString())
               binding.imgPictures.setImageURI(i)
        }
    }
}