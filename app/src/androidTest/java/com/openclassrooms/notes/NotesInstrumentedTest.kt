package com.openclassrooms.notes

import android.content.Intent
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.openclassrooms.notes.data.model.Note
import com.openclassrooms.notes.data.service.LocalNotesApiService
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.Assert.assertTrue
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import com.openclassrooms.notes.ui.screen.NotesScreen
import com.openclassrooms.notes.ui.viewmodel.NotesViewModel
import org.junit.Rule
import java.lang.Thread.sleep

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class NotesInstrumentedTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private lateinit var localApiService: LocalNotesApiService
    private lateinit var notesViewModel: NotesViewModel

    @Before
    fun setUp() {
        localApiService = LocalNotesApiService()
        notesViewModel = NotesViewModel()
    }

    @Test
    fun testIsNoteTitreDisplayedWhenActivityStarts() {
        // given
        val expected = localApiService.getAllNotes()
        notesViewModel.getNotes()
        // when
        composeTestRule.setContent {
            NotesScreen(modifier = Modifier, notesViewModel = notesViewModel)
        }
        // then
        composeTestRule.onNodeWithText(expected.first().titre)
            .assertIsDisplayed()
    }

    @Test
    fun testIsNoteCorpsDisplayedWhenActivityStarts() {
        // given
        val expected = localApiService.getAllNotes()
        notesViewModel.getNotes()
        // when
        composeTestRule.setContent {
            NotesScreen(modifier = Modifier, notesViewModel = notesViewModel)
        }
        // then
        composeTestRule.onNodeWithText(expected.first().corps)
            .assertIsDisplayed()
    }
}