package dc.newgendroid.news

import com.android.bjapplication.util.getAge
import org.junit.Assert
import org.junit.Test

// Unit test for String.getAge extension function
class DateTest {
    private val currentDateTime: Long = 1604322891589 //Mon, 02 Nov 2020 13:18:18 GMT

    @Test
    fun check_Days() {
        val dateInString = "2020-10-23T13:28:15Z"
        val fakeDaysString= "9 days ago"
        Assert.assertEquals(dateInString.getAge(currentDateTime), fakeDaysString)

    }
    @Test
    fun check_Hours() {
        val dateInString = "2020-11-01T13:28:15Z"
        val fakeDaysString= "23 hours ago"
        Assert.assertEquals(dateInString.getAge(currentDateTime), fakeDaysString)

    }
    @Test
    fun check_Minutes() {
        val dateInString = "2020-11-01T20:28:15Z"
        val fakeDaysString= "16 hours ago"
        Assert.assertEquals(dateInString.getAge(currentDateTime), fakeDaysString)

    }
}
