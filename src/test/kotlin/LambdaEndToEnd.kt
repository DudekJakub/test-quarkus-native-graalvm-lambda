import io.quarkus.amazon.lambda.runtime.AmazonLambdaApi.API_BASE_PATH_TEST
import io.quarkus.test.junit.QuarkusTest
import io.restassured.module.kotlin.extensions.Given
import io.restassured.module.kotlin.extensions.Then
import io.restassured.module.kotlin.extensions.When
import org.junit.jupiter.api.Test

@QuarkusTest
open class LambdaEndToEnd {
    @Test
    fun `Lambda#handleRequest should serialize given value in native mode`() {
        Given {
            body("{}")
        } When {
            post(API_BASE_PATH_TEST)
        } Then {
            statusCode(200)
        }
    }
}
