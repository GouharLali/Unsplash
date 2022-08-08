package com.gouhar.unsplash

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.updateLayoutParams
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.fueled.reclaim.AdapterItem
import com.fueled.reclaim.BaseViewHolder

// Displaying a single item but duplicate it for the items I have.

class PhotoAdapterItem(
    private val photo: Photo
) : AdapterItem <PhotoViewHolder>() {
    override val layoutId: Int = R.layout.item_photo      // Specify the layoutId

    override fun onCreateViewHolder(view: View) = PhotoViewHolder(view)

    override fun updateItemViews(viewHolder: PhotoViewHolder) {
        viewHolder.apply{
            postImageView.updateLayoutParams<ConstraintLayout.LayoutParams> {
                dimensionRatio = (photo.width.toFloat() / photo.height).toString()
        }

            Glide.with(postImageView)
                .load(photo.urls.regular) // This would load the image from URL.
                .into(postImageView) // Where it's going.

            titleTextView.text = photo.description ?: "No description"

            locationTextView.text = photo.user.location ?: "Unknown"
            userNameTextView.text = photo.user.username

            Glide.with(profileImageView)
                .load(photo.user.profileImage.medium)
                .transform(CircleCrop())
                .into(profileImageView)
        }
    }
}


class PhotoViewHolder(view: View) : BaseViewHolder(view) {
    val postImageView: ImageView = view.findViewById(R.id.post_image_view)
    val titleTextView: TextView = view.findViewById((R.id.title_text_view))
    val userNameTextView: TextView = view.findViewById(R.id.username_text_view)
    val profileImageView: ImageView = view.findViewById(R.id.profile_image_view)
    val locationTextView : TextView = view.findViewById(R.id.location_text_view)

}
