package com.alfian.allecgallery.ui.screens.detail

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assert
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.printToLog
import com.alfian.allecgallery.R
import com.alfian.allecgallery.data.source.local.FakeBouquetDataSource
import com.alfian.allecgallery.domain.model.OrderBouquet
import com.alfian.allecgallery.onNodeWithStringId
import com.alfian.allecgallery.ui.theme.AllecGalleryTheme
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class DetailContentTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    private val fakeOrderBouquet = OrderBouquet(
        bouquet = FakeBouquetDataSource.dummyBouquets[2],
        count = 0
    )

    @Before
    fun setUp(){
        composeTestRule.setContent {
            AllecGalleryTheme {
                DetailContent(
                    fakeOrderBouquet.bouquet.image,
                    fakeOrderBouquet.bouquet.name,
                    fakeOrderBouquet.bouquet.bouquetPrice,
                    fakeOrderBouquet.count,
                    onBackClick = {},
                    onAddToCart = {}
                )
            }
        }
        composeTestRule.onRoot().printToLog("currentLabelExists")
    }

    //Scenario Testing
//    Memastikan data pada halaman detail tampil.
//    Memastikan ketika jumlah produk ditambah, status tombol menjadi aktif.
//    Memastikan ketika tombol + ditekan 3 kali, jumlah produk menjadi 3.
//    Memastikan ketika tombol dikurangi dari 0, jumlah produk tetap 0.

    @Test
    fun detailBouquet_isDisplayed(){
        composeTestRule.onNodeWithText(fakeOrderBouquet.bouquet.name).assertIsDisplayed()
        composeTestRule.onNodeWithText(
            composeTestRule.activity.getString(
                R.string.bouquet_price,
                fakeOrderBouquet.bouquet.bouquetPrice
            )
        ).assertIsDisplayed()
    }

    @Test
    fun increaseBouquet_buttonEnabled(){
        composeTestRule.onNodeWithContentDescription(composeTestRule.activity.getString(R.string.order_button)).assertIsNotEnabled()
        composeTestRule.onNodeWithStringId(R.string.plus_symbol).performClick()
        composeTestRule.onNodeWithContentDescription(composeTestRule.activity.getString(R.string.order_button)).assertIsDisplayed()
    }

    @Test
    fun increaseBouquet_correctCounter(){
        composeTestRule.onNodeWithStringId(R.string.plus_symbol).performClick().performClick()
        composeTestRule.onNodeWithTag("count").assert(hasText("2"))
    }

    @Test
    fun decreaseBouquet_stillZero(){
        composeTestRule.onNodeWithStringId(R.string.minus_symbol).performClick()
        composeTestRule.onNodeWithTag("count").assert(hasText("0"))
    }
}