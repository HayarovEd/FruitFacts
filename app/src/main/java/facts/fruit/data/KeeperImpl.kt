package facts.fruit.data

import android.app.Application
import android.content.Context
import facts.fruit.domain.Keeper
import javax.inject.Inject

class KeeperImpl @Inject constructor(
    application: Application
): Keeper.Keeper {
    private val sharedPref =
        application.getSharedPreferences(SAVED_SETTINGS, Context.MODE_PRIVATE)

    override fun getSharedUrl(): String? = sharedPref.getString(URL_SETTINGS, "")

    override fun setSharedUrl(url:String) {
        sharedPref.edit().putString(URL_SETTINGS, url).apply()
    }

    override fun getSharedTo(): Boolean = sharedPref.getBoolean(TO_SETTINGS, false)

    override fun setSharedTo(to:Boolean) {
        sharedPref.edit().putBoolean(TO_SETTINGS, to).apply()
    }
}