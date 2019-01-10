package com.example.parkseeun.moca_android.model

import android.widget.TextView
import de.hdodenhof.circleimageview.CircleImageView

/**
 * Created by choisunpil on 10/01/2019.
 */
data class EvaluationViewItem(
        var baristaImage:CircleImageView,
        var baristaTitle:TextView,
        var baristaName:TextView
)