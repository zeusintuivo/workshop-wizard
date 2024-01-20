package backend

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.flywaydb.core.Flyway
import org.postgresql.ds.PGSimpleDataSource

fun runMigration() {
    val dataSource = HikariDataSource(HikariConfig().apply {
        username = "postgres"
        password = "example"
        dataSourceClassName = PGSimpleDataSource::class.qualifiedName
        addDataSourceProperty("databaseName", "workshop")
        addDataSourceProperty("serverName", "localhost")
        addDataSourceProperty("portNumber", "5432")
        maximumPoolSize = 10
        minimumIdle = 1
        idleTimeout = 10001
        connectionTimeout = 1000
        maxLifetime = 30001
    })
    Flyway
        .configure()
        .dataSource(dataSource)
        .load().migrate()
}