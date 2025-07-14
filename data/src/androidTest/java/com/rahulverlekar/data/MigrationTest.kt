import androidx.room.testing.MigrationTestHelper
import androidx.sqlite.db.framework.FrameworkSQLiteOpenHelperFactory
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.rahulverlekar.data.db.AppDatabase
import com.rahulverlekar.data.db.migrations.MIGRATION_1_2
import com.rahulverlekar.data.entities.GhostSighting
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class MigrationTest {
    private val TEST_DB = "migration-test"

    @get:Rule
    val helper: MigrationTestHelper = MigrationTestHelper(
        InstrumentationRegistry.getInstrumentation(),
        AppDatabase::class.java.canonicalName,
        FrameworkSQLiteOpenHelperFactory()
    )

    @Test
    @Throws(IOException::class)
    fun migrate1To2() {
        val entity: GhostSighting = GhostSighting(1, "casper", 2)
        var db = helper.createDatabase(TEST_DB, 1).apply {
            execSQL(
                sql = "INSERT INTO `GhostSighting` (`id`,`name`,`scariness`) VALUES (nullif(?, 0),?,?)",
                bindArgs = arrayOf(
                    entity.id,
                    entity.name,
                    entity.scariness
                )
            )
            close()
        }
        val migratedDb = helper.runMigrationsAndValidate(TEST_DB, 2, true, MIGRATION_1_2)

        val cursor = migratedDb.query("SELECT id,name FROM `GhostSighting` WHERE id = ?", arrayOf(entity.id))
        Assert.assertTrue(cursor.moveToFirst())
        Assert.assertEquals(entity.name, cursor.getString(cursor.getColumnIndexOrThrow("name")))
        cursor.close()
        migratedDb.close()

        // MigrationTestHelper automatically verifies the schema changes,
        // but you need to validate that the data was migrated properly.
    }
}