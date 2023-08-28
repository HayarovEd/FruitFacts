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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import facts.fruit.R
import facts.fruit.ui.theme.background
import facts.fruit.ui.theme.text
import facts.fruit.ui.theme.titleText


@Composable
fun Content(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel  = hiltViewModel()
) {
    val state = viewModel.state.collectAsState()
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
                id = state.value.fruit.image
            ),
            contentDescription = ""
        )
        Spacer(modifier = modifier.height(8.dp))
        Text(
            modifier = modifier.fillMaxWidth(),
            text = state.value.fruit.content,
            fontSize = 16.sp,
            color = text
        )
        Spacer(modifier = modifier.height(8.dp))
        Button(
            modifier = modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = titleText,
                contentColor = text
            ),
            onClick = {
                viewModel.nextFact()
            }) {
            Text(
                text = stringResource(id = R.string.next_fact),
                fontSize = 16.sp,
                textAlign = TextAlign.Center
            )
        }
    }
}