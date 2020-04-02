package com.example.nybooks.presentation.books


import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.nybooks.R
import com.example.nybooks.data.BooksResult
import com.example.nybooks.data.model.Book
import com.example.nybooks.data.repository.BooksRepository
import com.nhaarman.mockitokotlin2.verify
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class BooksViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()


    @Mock
    lateinit var booksLiveDataObserver : Observer<List<Book>>

    @Mock
    lateinit var viewFlipperLiveDataObserver : Observer<Pair<Int, Int?>>

    private lateinit var viewModel:BooksViewModel

//    @Before
//    fun setup(){
//        MockitoAnnotations.initMocks(this)
//    }

    @Test
    fun `hen view model getBooks get success then sets booksLiveData` (){

        //Arrange
        val books = listOf(
            Book("title 1", "author", "description")
        )
        val resultSuccess = MockRepository(BooksResult.Success(books))
        viewModel = BooksViewModel(resultSuccess)
        viewModel.booksLiveData.observeForever(booksLiveDataObserver)
        viewModel.viewFlipperLiveData.observeForever(viewFlipperLiveDataObserver)

        //act
        viewModel.getBooks()

        // Assert
        verify(booksLiveDataObserver).onChanged(books)
        verify(viewFlipperLiveDataObserver).onChanged(Pair(1, null))
    }


    @Test
    fun `hen view model getBooks get Server error then sets booksLiveData` (){

        val resultError = MockRepository(BooksResult.ServerError())
        viewModel = BooksViewModel(resultError)
        viewModel.viewFlipperLiveData.observeForever(viewFlipperLiveDataObserver)

        //act
        viewModel.getBooks()

        // Assert
        verify(viewFlipperLiveDataObserver).onChanged(Pair(2, R.string.error_fatal))


    }




}

class MockRepository(private val result: BooksResult) : BooksRepository{
    override fun getBooks(bookResultCallback: (result: BooksResult) -> Unit) {
        bookResultCallback(result)
    }

}