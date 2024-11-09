package com.nepenthx.papercut.papercutUI

import android.content.pm.PackageInfo
import android.graphics.drawable.Drawable
import android.util.Log
import android.widget.ImageView
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import com.nepenthx.papercut.packageManager.AppInfo
import com.nepenthx.papercut.packageManager.PaperCutPackageManager
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.viewinterop.AndroidView

@Composable
fun MainUI(appList : List<AppInfo>,modifier: Modifier){
    ShowAppList(appList,modifier)
}


@Composable
fun ShowAppList(appList: List<AppInfo>,modifier: Modifier) {
    LazyColumn(
        modifier,
        contentPadding = PaddingValues(16.dp) // Optional: add padding to the list

    ) {
        items(appList, key = { it.hashCode()}) { data -> // Add key for better performance
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp) // Make each row take full width
            ) {
            AppInfoTag( appInfo = data)
            }
        }
    }
}

@Composable
fun AppInfoTag(appInfo: AppInfo) {
    // 使用 remember 缓存加载的图标，避免每次重组时重新加载
    val appInfo = remember(appInfo) { appInfo }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(8.dp) // 给 Row 加点内边距
    ) {
        // 图标
        Box(
            modifier = Modifier
                .size(20.dp)
                .padding(2.dp)
        ) {
            showDrawable(drawable = appInfo.icon)
        }

        Spacer(modifier = Modifier.width(8.dp)) // 添加间隔
        // 应用名称
        Text(
            text = appInfo.appName,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Composable
fun showDrawable(drawable: Drawable) {
    AndroidView(
        factory = { context ->
            ImageView(context).apply {
                setImageDrawable(drawable)
            }
        },
        modifier = Modifier.fillMaxSize()
    )
}