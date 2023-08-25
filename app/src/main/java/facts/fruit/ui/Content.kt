package facts.fruit.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import facts.fruit.R
import facts.fruit.ui.theme.background
import facts.fruit.ui.theme.text
import facts.fruit.ui.theme.titleText


@Composable
fun Content(
    modifier: Modifier = Modifier
) {
    Column(
        modifier
            .fillMaxSize()
            .background(color = background)
            .padding(8.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            modifier = modifier.fillMaxWidth(),
            text = stringResource(id = R.string.fruit_facts),
            textAlign = TextAlign.Center,
            fontSize = 25.sp,
            color = titleText
        )
        Spacer(modifier = modifier.height(8.dp))
        Image(
            modifier = modifier.fillMaxWidth(),
            painter = painterResource(
                id = R.drawable.fruits
            ),
            contentDescription = ""
        )
        Spacer(modifier = modifier.height(8.dp))
        Text(
            modifier = modifier.fillMaxWidth(),
            text = stringResource(id = R.string.fruit_facts_1_25_content),
            fontSize = 16.sp,
            color = text
        )
    }
}