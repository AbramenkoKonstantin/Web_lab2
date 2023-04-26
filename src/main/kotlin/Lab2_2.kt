import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

data class Person(
    @SerializedName("name") val name: String,
    @SerializedName("age") val age: Int,
    @SerializedName("isStudent") val isStudent: Boolean
)

class JsonConverter {

    private val gson = Gson()

    fun toJson(person: Person): String {
        return gson.toJson(person)
    }

    fun fromJson(json: String): Person {
        return gson.fromJson(json, Person::class.java)
    }
}

class PersonTest {
    private val jConv = JsonConverter()

    @Test
    fun serializePerson() {
        val person = Person("Alice", 25, true)
        val json = jConv.toJson(person)
        assertEquals("""{"name":"Alice","age":25,"isStudent":true}""", json)
        println("Cериализация: \nВходные данные: $person \nВыходные данные: $json")
        println()
    }

    @Test
    fun deserializePerson() {
        val json = """{"name":"Bob","age":30,"isStudent":false}"""
        val person = jConv.fromJson(json)
        assertEquals(Person("Bob", 30, false), person)
        println("Десериализация: \nВходные данные: $json \nВыходные данные: $person")
        println()
    }
}