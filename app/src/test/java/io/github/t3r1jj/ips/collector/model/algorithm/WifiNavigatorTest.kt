package io.github.t3r1jj.ips.collector.model.algorithm

import android.os.Build
import io.github.t3r1jj.ips.collector.BuildConfig
import io.github.t3r1jj.ips.collector.model.data.Fingerprint
import io.github.t3r1jj.ips.collector.model.data.WifiDataset
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.Mockito
import weka.classifiers.lazy.IBk
import java.io.FileInputStream


class WifiNavigatorTest {
    @Test
    fun classify() {
        val classLoader = this.javaClass.classLoader
        val resource = classLoader.getResource("test.model")
        val fis = FileInputStream(resource.path)
        val navigator = WifiNavigator(fis)
        fis.close()
        val result = navigator.classify(WifiDataset("test", listOf()))
        assertEquals("121", result)
    }

    @Test
    fun classifyInMemory() {
        val ibk = IBk()
        ibk.knn = 2
        val navigator = WifiNavigator(ibk, Regex("eduroam"))
        val fingerprint = Fingerprint("bssid", -50, System.currentTimeMillis(), "eduroam")
        val fingerprint2 = Fingerprint("bssid2", -51, System.currentTimeMillis(), "eduroam")
        val result = navigator.classify(
                arrayListOf(WifiDataset("testPlace", (0 until 100).map {
                    if (it % 2 == 0) {
                        fingerprint
                    } else {
                        fingerprint2
                    }
                }.toList()),
                        WifiDataset("testPlace2", (0 until 100).map {
                            fingerprint2
                        }.toList())),
                WifiDataset("?", listOf(Fingerprint("bssid", -50, System.currentTimeMillis(), "eduroam"))))
        assertEquals("testPlace", result)
    }

}