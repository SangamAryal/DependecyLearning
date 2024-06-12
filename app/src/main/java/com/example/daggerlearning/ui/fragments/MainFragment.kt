package com.example.daggerlearning.ui.fragments

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.leanback.app.BrowseSupportFragment
import androidx.leanback.widget.ArrayObjectAdapter
import androidx.leanback.widget.HeaderItem
import androidx.leanback.widget.ListRow
import androidx.leanback.widget.ListRowPresenter
import androidx.lifecycle.ViewModelProvider
import com.example.daggerlearning.R
import com.example.daggerlearning.domain.model.movie.Result
import com.example.daggerlearning.presentation.viewmodel.MovieViewModel
import com.example.daggerlearning.ui.presenter.CardPresenter
import dagger.hilt.android.AndroidEntryPoint
import java.util.Collections
import javax.inject.Inject

@AndroidEntryPoint
class MainFragment : BrowseSupportFragment() {

    private lateinit var movieViewModel: MovieViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupUIElements()
        movieViewModel = ViewModelProvider(this)[MovieViewModel::class.java]
        movieViewModel.fetchMovies()
        movieViewModel.movies.observe(this) { result ->
            if (result != null) {
                loadRows(result.results)
                Log.d(TAG, "${result.results}")
            } else {
                Log.e(TAG, "movie list is empty")
            }
        }


        setupEventListeners()

    }

    private fun setupUIElements() {
        title = getString(R.string.browse_title)
        headersState = HEADERS_ENABLED
        isHeadersTransitionOnBackEnabled = true
        brandColor = ContextCompat.getColor(requireActivity(), R.color.fastlane_background)
        searchAffordanceColor = ContextCompat.getColor(requireActivity(), R.color.search_opaque)
    }

    private fun loadRows(movieList: List<Result>) {
        if (movieList.isEmpty()) {
            Log.e(TAG, "Movie list is empty, no rows to load.")
            return
        }

        val rowsAdapter = ArrayObjectAdapter(ListRowPresenter())
        val cardPresenter = CardPresenter()
        val categories =
            listOf("Upcoming", "Now Playing", "Action", "Comedy", "Drama", "Sci-Fi", "Horror")

        val numCols = movieList.size

        for (i in 0 until NUM_ROWS) {
            try {
                if (i != 0) {
                    Collections.shuffle(movieList)
                }
                val listRowAdapter = ArrayObjectAdapter(cardPresenter)
                for (j in 0 until numCols) {
                    listRowAdapter.add(movieList[j % movieList.size])
                }
                val header = HeaderItem(i.toLong(), categories[i % categories.size])
                rowsAdapter.add(ListRow(header, listRowAdapter))
            } catch (e: Exception) {
                Log.e(TAG, "Error loading row $i: ${e.message}")
            }
        }

        adapter = rowsAdapter
    }

    private fun setupEventListeners() {
        setOnSearchClickedListener {
            Toast.makeText(requireActivity(), "Search is yet to be implemented", Toast.LENGTH_LONG)
                .show()
        }

    }

    companion object {
        private const val TAG = "MainFragment"
        private const val NUM_ROWS = 7
    }


}
