package hr.fika.teatime

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.Text
import hr.fika.teatime.theme.WearAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            WearApp()
        }
    }
}

@Composable
fun WearApp() {
    val model = TeatimeViewModel()
    WearAppTheme {
        Column(
            modifier = Modifier
                .background(MaterialTheme.colors.background)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(IntrinsicSize.Min)
                    .background(MaterialTheme.colors.background)
                    .padding(horizontal = dimensionResource(id = R.dimen.padding)),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                TeaButton(title = stringResource(id = R.string.roo), duration = 5, model)
                TeaButton(title = stringResource(id = R.string.fruit), duration = 8, model)
                TeaButton(title = stringResource(id = R.string.white), duration = 2, model)
            }
            StatusText(model = model)
        }
    }
}

@Composable
fun TeaButton(title: String, duration: Int, model: TeatimeViewModel) {
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .background(MaterialTheme.colors.background)
            .clickable { model.runAlarm(context = context, duration = duration, title = title) },
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.cup),
            contentDescription = stringResource(id = R.string.cup_of_tea),
            modifier = Modifier
                .width(dimensionResource(id = R.dimen.icon_size))
                .height(dimensionResource(id = R.dimen.icon_size))
        )
        Text(
            textAlign = TextAlign.Center,
            color = MaterialTheme.colors.primary,
            text = title
        )
    }
}

@Composable
fun StatusText(model: TeatimeViewModel) {
    Text(
        textAlign = TextAlign.Center,
        color = MaterialTheme.colors.primary,
        text = model.status,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = dimensionResource(id = R.dimen.top_padding))
    )
}

@Preview(device = Devices.WEAR_OS_SMALL_ROUND, showSystemUi = true)
@Composable
fun DefaultPreview() {
    WearApp()
}