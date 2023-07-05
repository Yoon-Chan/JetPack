package com.example.chap48_gesturedemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.example.chap48_gesturedemo.ui.theme.Chap48_GestureDemoTheme
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Chap48_GestureDemoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen()
                }
            }
        }
    }
}


@Composable
fun MainScreen() {
    //1. Modifier.clickable 간단 사용 예제
    //ClickDemo()

    //2. pointerInput을 이용한 다양한 탭 기능 구현
    //TapPressDemo()

    //3. Modifier.draggable()을 이용한 드래그 제스처 감지하기
    DragDemo()

}

@Composable
fun DragDemo(){
    Box(modifier = Modifier.fillMaxSize()){
        var xOffset by remember {
            mutableStateOf(0f)
        }

        Box(modifier = Modifier
            .offset { IntOffset(xOffset.roundToInt(), 0) }
            .size(100.dp)
            .background(Color.Blue)
            .draggable(
                orientation = Orientation.Horizontal, state = rememberDraggableState {
                    xOffset += it
                }
            ))

    }
}


@Composable
fun TapPressDemo() {
    var textState by remember {
        mutableStateOf("Waiting ....")
    }

    val tapHandler = { status: String ->
        textState = status
    }

    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxSize()) {
        Box(modifier = Modifier
            .padding(10.dp)
            .background(Color.Blue)
            .size(100.dp)
            .pointerInput(Unit) {
                detectTapGestures(
                    onPress = { tapHandler("onPress Detected") },
                    onDoubleTap = { tapHandler("onDoubleTap Detected") },
                    onLongPress = { tapHandler("onLongPress Detected") },
                    onTap = { tapHandler("onTap Detected") },
                )
            })
        Spacer(modifier = Modifier.height(10.dp))
        Text(textState)
    }
}

@Composable
fun ClickDemo() {
    var colorState by remember {
        mutableStateOf(true)
    }
    var bgColor by remember {
        mutableStateOf(Color.Blue)
    }

    val clickHandler = {
        colorState = !colorState

        bgColor = if (colorState) {
            Color.Blue
        } else {
            Color.DarkGray
        }
    }

    Box(modifier = Modifier
        .clickable { clickHandler() }
        .background(bgColor)
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Chap48_GestureDemoTheme {
        MainScreen()
    }
}