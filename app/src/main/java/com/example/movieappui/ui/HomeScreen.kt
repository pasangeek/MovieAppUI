package com.example.movieappui.ui


import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.movieappui.model.MovieModel
import com.example.movieappui.model.upcoming
import com.example.movieappui.route.RouteName


@Composable
fun HomeScreen(
    navController: NavHostController,
){
    val scrollState = rememberScrollState()
    Scaffold { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(
                    top = padding.calculateTopPadding() + 24.dp,
                    bottom = padding.calculateBottomPadding() + 24.dp,
                )
        ) {
            Text(
                text = "Welcome back, Pasan !",
                style = MaterialTheme.typography.headlineLarge,
                modifier = Modifier.padding(horizontal = 24.dp)
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Book your Favorite Movie Here!",
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(horizontal = 24.dp)
            )
            Spacer(modifier = Modifier.height(24.dp))
            Banners()
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp)
            ) {
                Text(
                    text = "Category",
                    style = MaterialTheme.typography.headlineSmall,
                )
                TextButton(onClick = { }) {
                    Text(text = "See All")
                }
            }
            Spacer(modifier = Modifier.height(4.dp))
            Categories()
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp)
            ) {
                Text(
                    text = "Now Playing Movie",
                    style = MaterialTheme.typography.headlineSmall,
                )
                TextButton(onClick = { }) {
                    Text(text = "See All")
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            NowPlayingMovie { movie ->
                navController.navigate("${RouteName.Detail}/${movie.id}")
            }
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp)
            ) {
                Text(
                    text = "Upcoming Movie",
                    style = MaterialTheme.typography.headlineSmall,
                )
                TextButton(onClick = { }) {
                    Text(text = "See All")
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            UpcomingMovie()
        }
    }

}
@Preview
@Composable
fun UpcomingMovie()
{
    LazyRow(
        contentPadding = PaddingValues(start = 24.dp)
    ){
        items(count = upcoming.size) { index ->
            Box(modifier = Modifier
                .padding(end = 24.dp)
                .clickable { }) {
                Column(
                    modifier = Modifier.wrapContentHeight(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(id = upcoming[index].assetImage),
                        contentDescription = "Movie Image",
                        contentScale = ContentScale.FillBounds,
                        modifier = Modifier.size(width = 200.dp, height = 260.dp)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = upcoming[index].title,
                        style = MaterialTheme.typography.titleSmall,
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
    }
}

@Composable
fun NowPlayingMovie(
    onMovieClicked: (MovieModel) -> Unit
){}

@Composable
fun Categories()
{}

@Composable
fun Banners()
{}