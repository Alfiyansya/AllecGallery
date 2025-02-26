package com.alfian.allecgallery

import androidx.activity.ComponentActivity
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollToIndex
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import com.alfian.allecgallery.data.source.local.FakeBouquetDataSource
import com.alfian.allecgallery.ui.navigation.Screen
import com.alfian.allecgallery.ui.theme.AllecGalleryTheme
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test


@HiltAndroidTest
class AllecGalleryAppTest {

    @get:Rule()
    var hiltRule = HiltAndroidRule(this)

    @get:Rule()
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    private lateinit var navController: TestNavHostController

    @Before
    fun setUp() {
        hiltRule.inject()
        composeTestRule.setContent {
            AllecGalleryTheme {
                navController = TestNavHostController(LocalContext.current)
                navController.navigatorProvider.addNavigator(ComposeNavigator())
                AllecGalleryApp(navController = navController)
            }
        }
    }

    @Test
    fun navHost_verifyStartDestination() {
        navController.assertCurrentRouteName(Screen.Home.route)
    }

    @Test
    fun navHost_clickItem_navigatesToDetailWithData() {
        composeTestRule.onNodeWithTag("BouquetList").performScrollToIndex(9)
        composeTestRule.onNodeWithText(FakeBouquetDataSource.dummyBouquets[9].name).performClick()
        navController.assertCurrentRouteName(Screen.DetailBouquet.route)
        composeTestRule.onNodeWithText(FakeBouquetDataSource.dummyBouquets[9].name).assertIsDisplayed()
    }

    @Test
    fun navHost_bottomNavigation_working() {
        composeTestRule.onNodeWithText(composeTestRule.activity.getString(R.string.cart)).performClick()
        navController.assertCurrentRouteName(Screen.Cart.route)
        composeTestRule.onNodeWithText(composeTestRule.activity.getString(R.string.profile)).performClick()
        navController.assertCurrentRouteName(Screen.Profile.route)
        composeTestRule.onNodeWithText(composeTestRule.activity.getString(R.string.home)).performClick()
        navController.assertCurrentRouteName(Screen.Home.route)
    }

    @Test
    fun navHost_clickItem_navigatesBack() {
        composeTestRule.onNodeWithTag("BouquetList").performScrollToIndex(9)
        composeTestRule.onNodeWithText(FakeBouquetDataSource.dummyBouquets[9].name).performClick()
        navController.assertCurrentRouteName(Screen.DetailBouquet.route)
        composeTestRule.onNodeWithContentDescription(composeTestRule.activity.getString(R.string.back)).performClick()
        navController.assertCurrentRouteName(Screen.Home.route)
    }

    @Test
    fun navHost_checkout_rightBackStack() {
        composeTestRule.onNodeWithText(FakeBouquetDataSource.dummyBouquets[5].name).performClick()
        navController.assertCurrentRouteName(Screen.DetailBouquet.route)
        composeTestRule.onNodeWithText(composeTestRule.activity.getString(R.string.plus_symbol)).performClick()
        composeTestRule.onNodeWithContentDescription(composeTestRule.activity.getString(R.string.order_button)).performClick()
        navController.assertCurrentRouteName(Screen.Cart.route)
        composeTestRule.onNodeWithText(composeTestRule.activity.getString(R.string.home)).performClick()
        navController.assertCurrentRouteName(Screen.Home.route)
    }
}
