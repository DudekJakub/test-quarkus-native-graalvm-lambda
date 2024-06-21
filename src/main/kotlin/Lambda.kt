import com.amazonaws.services.lambda.runtime.Context
import com.amazonaws.services.lambda.runtime.RequestHandler
import com.fasterxml.jackson.databind.ObjectMapper
import io.github.oshai.kotlinlogging.KotlinLogging

class Lambda : RequestHandler<Any, String> {
    private val logger = KotlinLogging.logger { }

    override fun handleRequest(event: Any, context: Context): String {
        logger.info { "Triggering native serialization" }
        val serializedValue = ObjectMapper().writeValueAsString(listOf(TestData("Data")))
        logger.info { "Processed with serializedValue=$serializedValue" }
        assert(serializedValue == """[{"data":"Data"}]""")
        return serializedValue
    }
}
