package com.example.chap48_gesturedemo

import android.graphics.Point
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.rememberTransformableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.gestures.transformable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
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
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
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
    //DragDemo()

    //4. PointerInput의 detectDragGestures을 이용항 드래그 x,y축 움직이기
    //PointerInputDrag()

    //5. scrollable 모디파이어를 이용해보기
    //ScrollableModifier()

    //6. 확대/축소 사용 예제
    //MultiTouchDemo()

    //7. 회전 제스처 감지하기
    //MultiTouchDemo2()

    //8.변환 제스처 감지
}

@Composable
fun MultiTouchDemo(){
    var scale by remember {
        mutableStateOf(1f)
    }

    val state = rememberTransformableState{ scaleChange, offsetChange, rotationChange ->
        scale *= scaleChange
    }

    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()){
        Box(
            Modifier
                .graphicsLayer(
                    scaleX = scale,
                    scaleY = scale
                )
                .transformable(state = state)
                .background(Color.Blue)
                .size(100.dp))
    }
}

@Composable
fun MultiTouchDemo2(){
    var scale by remember {
        mutableStateOf(1f)
    }

    //추가된 부분
    var angle by remember { mutableStateOf(0f ) }

    val state = rememberTransformableState{ scaleChange, offsetChange, rotationChange ->
        scale *= scaleChange

        //추가된 부분
        angle += rotationChange
    }

    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()){
        Box(
            Modifier
                .graphicsLayer(
                    scaleX = scale,
                    scaleY = scale,
                    //추가된 부볂
                    rotationZ = angle
                )
                .transformable(state = state)
                .background(Color.Blue)
                .size(100.dp))
    }
}

@Composable
fun MultiTouchDemo3(){
    var scale by remember {
        mutableStateOf(1f)
    }

    //추가된 부분
    var angle by remember { mutableStateOf(0f ) }

    //변환 제스처 추가
    var offset by remember {
        mutableStateOf(Offset.Zero)
    }

    val state = rememberTransformableState{ scaleChange, offsetChange, rotationChange ->
        scale *= scaleChange

        //추가된 부분
        angle += rotationChange

        //변환 제스처 추가
        offset += offsetChange
    }

    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()){
        Box(
            Modifier
                .graphicsLayer(
                    scaleX = scale,
                    scaleY = scale,
                    //추가된 부분
                    rotationZ = angle,

                    //변환 값 변경 추가
                    rotationX = offset.x,
                    rotationY = offset.y
                )
                .transformable(state = state)
                .background(Color.Blue)
                .size(100.dp))
    }
}


@Composable
fun ScrollableModifier() {
    var offset by remember {
        mutableStateOf(0f)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .scrollable(
                orientation = Orientation.Vertical,
                state = rememberScrollableState { distance ->
                    offset += distance
                    distance
                })
    ) {
        Box(modifier = Modifier
            .size(90.dp)
            .offset { IntOffset(0, offset.roundToInt()) }
            .background(Color.Red))
    }


}

@Composable
fun PointerInputDrag() {
    Box(modifier = Modifier.fillMaxSize()) {
        var xOffset by remember {
            mutableStateOf(0f)
        }

        var yOffset by remember {
            mutableStateOf(0f)
        }

        Box(modifier = Modifier
            .offset { IntOffset(xOffset.roundToInt(), yOffset.roundToInt()) }
            .size(100.dp)
            .background(Color.Blue)
            .pointerInput(Unit) {
                detectDragGestures { _, dragAmount ->
                    xOffset += dragAmount.x
                    yOffset += dragAmount.y
                }
            }
        )
    }

}

@Composable
fun DragDemo() {
    Box(modifier = Modifier.fillMaxSize()) {
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