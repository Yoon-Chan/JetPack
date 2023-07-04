package com.example.chap47_bottombardemo

import androidx.compose.ui.graphics.vector.ImageVector

//하단 바에 필요한 BarItem 데이터 클래스 생성
data class BarItem(val title : String, val image : ImageVector, val route : String)
